import java.io.ByteArrayOutputStream

plugins {
    id("java-library")
    id("maven-publish")
    id("com.gradleup.shadow") version "9.4.0"
}

subprojects {
    plugins.apply("java-library")
    plugins.apply("maven-publish")
    plugins.apply("com.gradleup.shadow")

    group = "${project.property("group")}"
    version = "${project.property("version")}.${commitsSinceLastTag()}"

    repositories {
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(25))
        }
        withSourcesJar()
    }

    tasks {
        withType<JavaCompile> {
            options.encoding = "UTF-8"
            options.release = 25
            options.compilerArgs.add("-Xlint:none")
        }
        jar {
            archiveClassifier.set("noshade")
        }
        shadowJar {
            archiveClassifier.set("")
            archiveFileName.set("${project.property("artifactName")}-${project.version}.jar")
        }
        build {
            dependsOn(shadowJar)
        }
    }

    publishing {
        repositories {
            if (project.hasProperty("mavenUsername") && project.hasProperty("mavenPassword")) {
                maven {
                    credentials {
                        username = "${project.property("mavenUsername")}"
                        password = "${project.property("mavenPassword")}"
                    }
                    url = uri("https://repo.codemc.io/repository/maven-releases/")
                }
            }
        }
        publications {
            create<MavenPublication>("maven") {
                groupId = "${project.group}"
                artifactId = project.name
                version = "${project.version}"
                from(components["java"])
            }
        }
    }
}

fun commitsSinceLastTag(): String {
    val describe = providers.exec {
        commandLine("git", "describe", "--tags")
        isIgnoreExitValue = true
    }
    if (describe.result.get().exitValue != 0) {
        val revList = providers.exec {
            commandLine("git", "rev-list", "--count", "HEAD")
            isIgnoreExitValue = true
        }
        if (revList.result.get().exitValue != 0) {
            return "0"
        }
        return revList.standardOutput.asText.get().trim().ifEmpty { "0" }
    }
    val tagDescription = describe.standardOutput.asText.get()
    if (tagDescription.indexOf('-') < 0) {
        return "0"
    }
    return tagDescription.split('-')[1]
}

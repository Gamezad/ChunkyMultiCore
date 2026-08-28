pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
        maven("https://maven.minecraftforge.net")
        maven("https://maven.architectury.dev/")
        maven("https://repo.codemc.io/repository/relativitymc/")
    }
}

plugins {
    // Automatically provision the Java toolchain (JDK 25) when the JDK running
    // Gradle is older, e.g. local setups or CI runners without a JDK 25 image.
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "chunky"

sequenceOf(
    "nbt",
    "common",
    "paper",
    "folia",
    "bukkit",
    "fabric",
    "forge",
    "neoforge",
    "sponge"
).forEach {
    include("${rootProject.name}-$it")
    project(":${rootProject.name}-$it").projectDir = file(it)
}

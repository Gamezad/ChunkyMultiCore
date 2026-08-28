# Chunky

![chunkylogo](https://user-images.githubusercontent.com/17698576/171119146-122497e4-7527-438b-8f8e-c1c04c2841b9.jpg)

Minecraft chunk pre-generator, supporting a wide variety of server implementations including Spigot, Paper, Fabric, Forge, NeoForge, and Sponge.

Pre-generate chunks, quickly, efficiently, and safely!

## Getting Started

- [Installing](https://github.com/pop4959/Chunky/wiki/Installing)
- [Pre-generating Chunks](https://github.com/pop4959/Chunky/wiki/Pregeneration)
- [Command Reference](https://github.com/pop4959/Chunky/wiki/Commands)

## Performance / Multi-core Tuning

Generation tasks run asynchronously and keep a configurable number of chunk
requests in flight at the same time, so the server's chunk worker threads can
generate chunks on all available CPU cores in parallel.

- `max-working-count` — maximum number of chunks each task works on
  concurrently. Set it in `config.yml` (Bukkit/Folia), `config.json`
  (Fabric/Forge/NeoForge) or `main.conf` (Sponge). `0` means "auto"
  (4 x CPU cores, minimum 50). A good starting point is `4 x cores` of your
  machine. It can also be overridden with the JVM flag
  `-Dchunky.maxWorkingCount=<n>`, which takes priority over the config.
- On Paper/Folia, also raise `chunk-system.worker-threads` (default auto =
  roughly half of your cores) in `config/paper-global.yml` to e.g. your full
  core count or higher — that is the server-side pool that actually generates
  the chunks, so increasing both values gives the maximum speed.
- After changing the value, run `/chunky reload` (or restart the server) so
  newly started tasks pick it up.

## Support

For questions about Chunky, first try the [Wiki](https://github.com/pop4959/Chunky/wiki) to see if your question is already answered there.

If you can't find what you're looking for, visit us in the #chunky channel on our [Discord server](https://discord.gg/ZwVJukcNQG).

## Special Thanks

[![yklogo](https://www.yourkit.com/images/yklogo.png)](https://www.yourkit.com/)

YourKit supports open source projects with innovative and intelligent tools for monitoring and profiling Java and .NET applications. YourKit is the creator of [YourKit Java Profiler](https://www.yourkit.com/java/profiler/), [YourKit .NET Profiler](https://www.yourkit.com/.net/profiler/),
and [YourKit YouMonitor](https://www.yourkit.com/youmonitor/). We thank them for granting us open source project licenses so that we can continue to deliver the best performance possible.

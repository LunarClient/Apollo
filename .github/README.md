![](https://i.imgur.com/VdgyD5m.png)
# Apollo
[![Discord](https://img.shields.io/discord/1080556677004271666?logo=discord&label=discord)](https://lunarclient.dev/discord)

Apollo is a powerful tool that allows developers to create custom integrations with Lunar Client.

**Resources**
- [Documentation & Wiki](https://lunarclient.dev/apollo/introduction)
- [Downloads](https://lunarclient.dev/apollo/downloads)
- [Maven Repository](https://lunarclient.dev/maven-repository)

## Integration

### API Integration
Use the **Apollo API** directly by depending on it from our [Maven repository](https://lunarclient.dev/maven-repository).  
This is the most straightforward way to create plugins and modules that communicate with Lunar Client.

See the [Waypoint module integration guide](https://lunarclient.dev/apollo/developers/modules/waypoint#integration) for a complete example.

### Lightweight Integration
Our **Lightweight integration** allows you to use Apollo features **without running the Apollo plugin**.  
This is useful for developers who want Apollo functionality but prefer a more minimal approach.

There are two supported methods:
- [Lightweight JSON](https://lunarclient.dev/apollo/developers/lightweight/json/getting-started)
- [Lightweight Protobuf](https://lunarclient.dev/apollo/developers/lightweight/protobuf/getting-started)

Both approaches achieve the same goal, but with different trade-offs in terms of **complexity, flexibility, and performance**.  

Read the [Lightweight introduction](https://lunarclient.dev/apollo/developers/lightweight/introduction) documentation to get started.

## Examples

Apollo includes example plugins to help you get started quickly.  
These are the **same projects used for the code examples in the official documentation** on [lunarclient.dev](https://lunarclient.dev/apollo/introduction/).

Each module page on the documentation site shows how to use **API**, **Lightweight JSON**, and **Lightweight Protobuf** integrations, with code pulled directly from these projects.

### Bukkit
- API example → `example/bukkit/api/build/libs`
- Lightweight JSON example → `example/bukkit/json/build/libs`
- Lightweight Protobuf example → `example/bukkit/protos/build/libs`

### Minestom
- API example server → `example/minestom/api/build/libs`

## Building

Apollo uses [Gradle](https://gradle.org/) to handle dependencies and compile the project.

**Prerequisites**

- Java 8 JDK
- Build Tools for Spigot 1.8.8

**Compiling**

Running the following will compile the Apollo source for Java 8.

```shell
./gradlew build
```

The compiled jars will be available in build/libs for each platform:

- Bukkit: `platform/bukkit/plugin/build/libs`
- Folia: `platform/folia/build/libs`
- Minestom: `platform/minestom/build/libs`
- BungeeCord: `platform/bungee/build/libs`
- Velocity: `platform/velocity/build/libs`

## Contributing

The Apollo project is split into several modules.

- **API** - The publicly available interface for developers wishing to create custom integrations with Lunar Client.
- **Common** - The abstraction used by platform modules to reduce duplicate code and implement the protocol for Lunar Client.
- **Bukkit, Folia, Minestom, BungeeCord, Velocity** - Are modules that implement the common module for each respective platform.

Contributions can be made to Apollo by creating a pull request for improvements or fixes. For new feature ideas please consider making a 
suggestion by creating an [issue](https://github.com/LunarClient/Apollo/issues) or joining our [discord](https://lunarclient.dev/discord).

## License

Apollo is licensed under the [MIT license](https://github.com/LunarClient/Apollo/blob/master/license.txt).

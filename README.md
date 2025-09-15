# Apollo
[![Discord](https://img.shields.io/discord/1080556677004271666?logo=discord&label=discord)](https://lunarclient.dev/discord)

Apollo is a powerful tool that allows developers to create custom integrations with Lunar Client.

You can find the latest downloads, wiki, and more at [lunarclient.dev](https://lunarclient.dev/apollo/introduction).

Creating custom integrations for Apollo requires adding the Apollo API as a dependency from our [maven repository](https://lunarclient.dev/maven-repository) 
to your project.

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
The output jars can be found in the `build/libs` directory relative to each platform.

- Bukkit: `bukkit/plugin/build/libs`
- Folia: `folia/build/libs`
- Minestom: `minestom/build/libs`
- BungeeCord: `bungee/build/libs`
- Velocity: `velocity/build/libs`

## Examples

An example plugin can be compiled to provide a variety of commands that allow you to try different features Apollo provides.
When compiled the output jar can be found in the directory `bukkit/example/build/libs`.

## Contributing

The Apollo project is split into several modules.

- **API** - The publicly available interface for developers wishing to create custom integrations with Lunar Client.
- **Common** - The abstraction used by platform modules to reduce duplicate code and implement the protocol for Lunar Client.
- **Bukkit, Folia, Minestom, BungeeCord, Velocity** - Are modules that implement the common module for each respective platform.

Contributions can be made to Apollo by creating a pull request for improvements or fixes. For new feature ideas please consider making a 
suggestion by creating an [issue](https://github.com/LunarClient/Apollo/issues) or joining our [discord](https://lunarclient.dev/discord).

## License

Apollo is licensed under the [MIT license](https://github.com/LunarClient/Apollo/blob/master/license.txt).

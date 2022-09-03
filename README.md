# Apollo

**Apollo** is an API for interacting with Lunar Client players from a server or proxy.

* Do not shade the API unless you provide docs


## Architecture

`api` contains classes available on all platforms. It defines the protocol between Lunar Client and servers / proxies.

There are modules for different implementations of the API that are also plugins for their platform.
These modules contain source sets for NMS and Mixin code.

## Building

Run Spigot BuildTools to get a copy of the latest Minecraft Spigot in your local maven installation.
(This may take a while.)

```shell
mkdir workdir && cd workdir

curl -o BuildTools.jar https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar
export MAVEN_OPTS="-Xmx2G" java -Xmx2G -jar BuildTools.jar --compile craftbukkit --rev 1.19
export MAVEN_OPTS="-Xmx2G" java -Xmx2G -jar BuildTools.jar --compile craftbukkit --rev 1.18.2
```

### Bukkit

The Bukkit implementation is split into an Ignite mod and a Bukkit plugin.

```shell
# Build jar of the Bukkit plugin
./gradlew :apollo-bukkit:shadowJar

# Build jar of the 1.1X Ignite mod
./gradlew :apollo-bukkit:jar_v1_18
./gradlew :apollo-bukkit:jar_v1_19

# Jars are stored in here
ls bukkit/build/
# ignite mods:
jar -tf bukkit/build/libs/apollo-bukkit-v1_18.jar
# bukkit plugin:
jar -tf bukkit/build/libs/apollo-bukkit-all.jar
```

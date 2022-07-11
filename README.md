# Apollo

**Apollo** is an API for interacting with Lunar Client players from a server or proxy.

## Building

Run Spigot BuildTools to get a copy of the latest Minecraft Spigot in your local maven installation.
(This will take a while.)

```
mkdir workdir && cd workdir

curl -o BuildTools.jar https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar
export MAVEN_OPTS="-Xmx2G" java -Xmx2G -jar BuildTools.jar --compile craftbukkit --rev 1.19
export MAVEN_OPTS="-Xmx2G" java -Xmx2G -jar BuildTools.jar --compile craftbukkit --rev 1.18.2
```

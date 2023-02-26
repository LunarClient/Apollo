# Apollo

**Apollo** is an API for interacting with Lunar Client players from a server or proxy.

* Do not shade the API unless you provide docs


## Architecture

`api` contains classes available on all platforms. It defines the protocol between Lunar Client and servers / proxies.

There are modules for different implementations of the API that are also plugins for their platform.
These modules may contain subprojects for NMS and Mixin code as well.

### Bukkit

The Bukkit implementation is split into an Ignite mod and a Bukkit plugin.

```shell
# Build jar of the Bukkit plugin
./gradlew :apollo-bukkit:apollo-bukkit-common:build

# Build jar of the 1.1X Ignite mod
./gradlew :apollo-bukkit:apollo-bukkit-v1_18:build
./gradlew :apollo-bukkit:apollo-bukkit-v1_19:build

# ignite mods:
jar -tf bukkit/v1_18/build/libs/apollo-bukkit-v1_18.jar
# bukkit plugin:
jar -tf bukkit/common/build/libs/apollo-bukkit-common.jar
```

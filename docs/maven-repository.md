# Maven Repository

## Overview
We offer a public Maven repository for Lunar Client artifacts, hosted at `https://repo.lunarclient.dev`. Please note the TLD, `.dev`, which is used for all of our developer-facing documentation.

## Adding the Repository

Maven:
```Maven POM
<repositories>
    <repository>
        <id>lunarclient</id>
        <url>https://repo.lunarclient.dev</url>
    </repository>
</repositories>
```

Gradle:
```Gradle
repositories {
    maven {
        name = "lunarclient"
        url = "https://repo.lunarclient.dev"
    }
}
```

## Adding Apollo as a dependency

Maven:
```Maven POM
<dependencies>
    <dependency>
        <groupId>com.lunarclient</groupId>
        <artifactId>apollo-api</artifactId>
        <version>1.0.0</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

Gradle:
```Gradle
dependencies {
    api 'com.lunarclient:apollo-api:1.0.0'
}
```

## Other artifacts

### Apollo dependencies
Artifacts required to build Apollo.

* `com.lunarclient:apollo-protos` (source available on Buf Schema Registry, at https://buf.build/lunarclient/apollo)
* `com.lunarclient:apollo-common` (source available in main Apollo repo, at https://github.com/LunarClient/Apollo)

### Legacy API
API / sample implementations for our *legacy* Lunar Client API. These are only available for backwards compatibility; all new development should use Apollo, our next-gen API platform.

* `com.lunarclient:BukkitImpl` (source available at https://github.com/LunarClient/BukkitImpl)
* `com.lunarclient:bukkitapi` (source available at https://github.com/LunarClient/BukkitAPI)
* `com.lunarclient:bukkitapi-nethandler` (source available at https://github.com/LunarClient/BukkitAPI-NetHandler)

### Lunar Client forks
Forks of various open source libraries we use and publish.

* `org.cadixdev:mercury` (source available at https://github.com/LunarClient/Mercury)
* `org.cadixdev:mercurymixin` (source available at https://github.com/LunarClient/MercuryMixin)
* `org.spongepowered:mixin` (source available at https://github.com/LunarClient/Mixin)

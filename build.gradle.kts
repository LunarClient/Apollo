buildscript {
    repositories {
        maven("https://repo.papermc.io/repository/maven-public/")
    }
    dependencies {
        classpath("com.moonsworth:toolchain")
    }
}

plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2" apply false
    id("io.papermc.paperweight.userdev") version "1.5.1" apply false
}

group = "com.moonsworth"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
}

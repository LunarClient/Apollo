buildscript {
    dependencies {
        classpath("com.moonsworth:toolchain")
    }
}

plugins {
    id("fr.il_totore.manadrop") version "0.4.2" apply false
    id("com.github.johnrengelman.shadow") version "7.1.2" apply false
}

group = "com.moonsworth"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
}

plugins {
    id("apollo.base-conventions")
    id("io.papermc.paperweight.userdev")
}

repositories {
    maven("https://repo.lunarclient.dev")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }

    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks {
    build {
        dependsOn(reobfJar)
    }

    reobfJar {
        remapperArgs.add("--mixin")
    }
}

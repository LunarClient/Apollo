plugins {
    id("apollo.paper-conventions")
    id("apollo.platform-conventions")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }

    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(":apollo-api"))
    implementation(project(":apollo-common"))
}

specialGradle {
    minecraftVersion.set("1.8.8")
    specialSourceVersion.set("1.10.0")
}

tasks {
    build {
        dependsOn(productionMappedJar)
    }
}

plugins {
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
    compileOnly(libs.bukkit.api)
    compileOnly(libs.bukkit)

    implementation(project(":apollo-api"))
    implementation(project(":apollo-common"))
}

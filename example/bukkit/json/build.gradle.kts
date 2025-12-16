plugins {
    id("apollo.base-conventions")
    id("apollo.shadow-conventions")
}

java {
    javaTarget(21)
}

dependencies {
    api(libs.bundles.adventure) {
        exclude("org.checkerframework")
        exclude("net.kyori", "adventure-api")
        exclude("net.kyori", "adventure-bom")
    }

    compileOnly(libs.bukkit.api)
    compileOnly(libs.folia)
    implementation(project(":example:bukkit:apollo-example-bukkit-common"))
}

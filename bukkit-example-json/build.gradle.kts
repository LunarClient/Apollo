plugins {
    id("apollo.base-conventions")
    id("apollo.shadow-conventions")
}

dependencies {
    api(libs.bundles.adventure) {
        exclude("org.checkerframework")
        exclude("net.kyori", "adventure-api")
        exclude("net.kyori", "adventure-bom")
    }

    compileOnly(libs.bukkit)
    implementation(project(":apollo-bukkit-example-common"))
}

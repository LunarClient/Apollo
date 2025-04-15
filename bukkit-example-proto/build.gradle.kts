plugins {
    id("apollo.base-conventions")
    id("apollo.shadow-conventions")
}

java {
    javaTarget(21)
}

dependencies {
    api(libs.protobuf)

    api(libs.bundles.adventure) {
        exclude("org.checkerframework")
        exclude("net.kyori", "adventure-api")
        exclude("net.kyori", "adventure-bom")
    }

    compileOnly(libs.bukkit)
    compileOnly(libs.folia)
    implementation(project(":apollo-bukkit-example-common"))
}

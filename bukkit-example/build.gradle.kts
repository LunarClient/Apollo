plugins {
    id("apollo.base-conventions")
    id("apollo.shadow-conventions")
}

dependencies {
    compileOnly(libs.bukkit.api)

    // Used for Proto Implementation
    api(libs.protobuf)

    // Used for Proto & Json Implementation
    api(libs.bundles.adventure) {
        exclude("org.checkerframework")
        exclude("net.kyori", "adventure-api")
        exclude("net.kyori", "adventure-bom")
    }

    // Used for API Implementation
    compileOnly(project(":extra:apollo-extra-adventure4"))
    compileOnly(project(path = ":apollo-api", configuration = "bukkit"))
}

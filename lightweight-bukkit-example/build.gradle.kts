plugins {
    id("apollo.base-conventions")
}

dependencies {
    compileOnly(libs.bukkit.api)

    api(libs.protobuf)
    api(libs.protobuf.java.util)

    api(libs.bundles.adventure) {
        exclude("org.checkerframework")
        exclude("net.kyori", "adventure-api")
        exclude("net.kyori", "adventure-bom")
        exclude("com.google.code.gson", "gson")
    }
}

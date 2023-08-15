plugins {
    id("apollo.publish-conventions")
}

dependencies {
    api(libs.bundles.adventure) {
        exclude("org.checkerframework")
        exclude("net.kyori", "adventure-api")
        exclude("net.kyori", "adventure-bom")
        exclude("com.google.code.gson", "gson")
    }
}


publishJar()

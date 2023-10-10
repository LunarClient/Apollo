plugins {
    id("com.github.johnrengelman.shadow")
    id("apollo.publish-conventions")
}

setupAdventureProject()

dependencies {
    api(libs.bundles.adventure) {
        exclude("org.checkerframework")
        exclude("net.kyori", "adventure-api")
        exclude("net.kyori", "adventure-bom")
        exclude("com.google.code.gson", "gson")
    }
}

publishJar()

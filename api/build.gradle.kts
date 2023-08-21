plugins {
    id("apollo.shadow-conventions")
    id("apollo.publish-conventions")
}

createPlatformSources("bukkit", "bungee", "velocity")

dependencies {
    api(libs.geantyref)

    "commonsCompileOnly"(libs.jetbrains.annotations)
    "commonsCompileOnly"(libs.lombok)

    "bukkitCompileOnly"(libs.bukkit.api)
    "bukkitCompileOnly"(libs.bukkit)

    "bungeeCompileOnly"(libs.bungee)

    "velocityCompileOnly"(libs.velocity)
}

publishShadowJar()

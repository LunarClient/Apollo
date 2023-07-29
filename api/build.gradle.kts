plugins {
    id("apollo.publish-conventions")
}

createPlatformSources("bukkit", "bungee", "velocity")

dependencies {
    api(libs.geantyref)

    "bukkitCompileOnly"(libs.bukkit.api)
    "bukkitCompileOnly"(libs.bukkit)

    "bungeeCompileOnly"(libs.bungee)

    "velocityCompileOnly"(libs.velocity)
}

publishJar()

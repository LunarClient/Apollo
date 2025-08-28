plugins {
    id("apollo.shadow-conventions")
    id("apollo.publish-conventions")
}

setupPlatforms()

setupPlatformDependency("bukkit", "bukkitJar")
setupPlatformDependency("bungee", "bungeeJar")
setupPlatformDependency("velocity", "velocityJar", 17)
setupPlatformDependency("folia", "foliaJar", 21)

val main by sourceSets

dependencies {
    api(project(":extra:apollo-extra-adventure4"))

    "shade"(libs.geantyref)

    "bukkit"(main.output)
    "bukkit"(libs.bukkit.api)
    "bukkit"(libs.bukkit)

    "bungee"(main.output)
    "bungee"(libs.bungee)

    "velocity"(main.output)
    "velocity"(libs.velocity)

    "folia"(main.output)
    "folia"(libs.folia)
}

publishShadowJar()

plugins {
    id("apollo.shadow-conventions")
}

dependencies {
    compileOnly(libs.bukkit.api)

    api(project(":apollo-lightweight-wrapper"))
}

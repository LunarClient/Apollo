plugins {
    id("apollo.shadow-conventions")
}

dependencies {
    compileOnly(libs.bukkit.api)
    compileOnly(libs.bukkit)

    api(projects.apolloApi)
    api(projects.apolloCommon)
}

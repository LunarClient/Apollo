plugins {
    id("apollo.base-conventions")
}

dependencies {
    compileOnly(libs.bukkit.api)

    compileOnlyApi(projects.extra.apolloExtraAdventure4)
    compileOnlyApi(projects.apolloApi) {
        targetConfiguration = "bukkit"
    }
}

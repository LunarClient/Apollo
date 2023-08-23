plugins {
    id("apollo.shadow-conventions")
}

dependencies {
    compileOnly(libs.bukkit.api)
    compileOnly(libs.bukkit)

    compileOnlyApi(projects.extra.apolloExtraAdventure4)

    api(projects.apolloApi) {
        targetConfiguration = "shadow"
    }

    api(projects.apolloCommon)
}

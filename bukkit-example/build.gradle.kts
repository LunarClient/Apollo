plugins {
    id("apollo.shadow-conventions")
}

dependencies {
    compileOnly(libs.bukkit.api)

    compileOnlyApi(projects.apolloApi) {
        targetConfiguration = "shadow"
    }
}

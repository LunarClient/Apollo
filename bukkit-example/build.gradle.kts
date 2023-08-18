plugins {
    id("apollo.base-conventions")
}

dependencies {
    compileOnly(libs.bukkit.api)

    compileOnlyApi(projects.apolloApi) {
        targetConfiguration = "shadow"
    }
}

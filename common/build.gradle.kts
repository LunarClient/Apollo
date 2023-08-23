plugins {
    id("apollo.publish-conventions")
}

dependencies {
    compileOnlyApi(projects.extra.apolloExtraAdventure4)

    api(projects.apolloApi) {
        targetConfiguration = "shadow"
    }

    api(libs.protobuf)
    api(libs.configurate.core)
    api(libs.configurate.yaml)
}

publishJar()

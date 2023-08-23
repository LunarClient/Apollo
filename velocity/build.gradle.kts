plugins {
    id("apollo.shadow-conventions")
}

dependencies {
    compileOnly(libs.velocity)

    compileOnlyApi(projects.extra.apolloExtraAdventure4)

    api(projects.apolloApi) {
        targetConfiguration = "shadow"
    }

    api(projects.apolloCommon)

    annotationProcessor(libs.velocity)
}

plugins {
    id("apollo.shadow-conventions")
}

dependencies {
    api(projects.apolloApi)
    api(projects.apolloCommon)

    compileOnly(libs.velocity)
    annotationProcessor(libs.velocity)
}

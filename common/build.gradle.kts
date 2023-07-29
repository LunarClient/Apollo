plugins {
    id("apollo.publish-conventions")
}

dependencies {
    api(projects.apolloApi)
    api(libs.protobuf)
    api(libs.configurate.core)
    api(libs.configurate.yaml)
}

publishJar()

plugins {
    id("apollo.publish-conventions")
}

dependencies {
    api(project(":apollo-api"))
    api("com.lunarclient:apollo-protos:1.0-SNAPSHOT")
    api(libs.configurate.core)
    api(libs.configurate.yaml)
}

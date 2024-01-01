plugins {
    id("apollo.shadow-conventions")
    id("apollo.publish-conventions")
}

dependencies {
    compileOnly(project(":extra:apollo-extra-adventure4"))

    api(project(path = ":apollo-api", configuration = "shadow"))

    api(libs.protobuf)
    api(libs.gson)
    api(libs.configurate.core)
    api(libs.configurate.yaml)
}

publishShadowJar()

plugins {
    id("apollo.shadow-conventions")
    id("apollo.publish-conventions")
}

dependencies {
    api(libs.protobuf)
    api(libs.protobuf.java.util)
}

publishShadowJar()

plugins {
    id("apollo.shadow-conventions")
    id("apollo.publish-conventions")
}

java {
    javaTarget(21)
}

dependencies {
    compileOnly(libs.minestom)
    compileOnly(libs.protobuf)

    api(project(path = ":apollo-api", configuration = "shadow"))
    api(project(path = ":apollo-common", configuration = "shadow"))

    api(project(":extra:apollo-extra-adventure4"))

    annotationProcessor(libs.minestom)
}

publishShadowJar()

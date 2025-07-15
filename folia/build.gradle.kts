plugins {
    id("apollo.shadow-conventions")
}

java {
    javaTarget(21)
}

dependencies {
    compileOnly(libs.folia)
    compileOnly(libs.protobuf)

    api(project(path = ":apollo-api", configuration = "shadow"))
    api(project(path = ":apollo-common", configuration = "shadow"))

    api(project(":extra:apollo-extra-adventure4"))

    annotationProcessor(libs.folia)
}

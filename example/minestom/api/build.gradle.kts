plugins {
    id("apollo.base-conventions")
    id("apollo.shadow-conventions")
}

java {
    javaTarget(21)
}

dependencies {
    compileOnly(project(path = ":apollo-api", configuration = "minestom"))
    implementation(project(path = ":apollo-minestom", configuration = "shadow"))

    implementation(libs.minestom)
}

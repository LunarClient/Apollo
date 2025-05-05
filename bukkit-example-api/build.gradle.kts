plugins {
    id("apollo.base-conventions")
    id("apollo.shadow-conventions")
}

java {
    javaTarget(21)
}

dependencies {
    compileOnly(project(":extra:apollo-extra-adventure4"))
    compileOnly(project(path = ":apollo-api", configuration = "bukkit"))
    compileOnly(project(path = ":apollo-common", configuration = "shadow"))

    compileOnly(libs.folia)
    implementation(project(":apollo-bukkit-example-common"))
}

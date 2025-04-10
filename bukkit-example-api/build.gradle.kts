plugins {
    id("apollo.base-conventions")
    id("apollo.shadow-conventions")
}

dependencies {
    compileOnly(project(":extra:apollo-extra-adventure4"))
    compileOnly(project(path = ":apollo-api", configuration = "bukkit"))
    compileOnly(project(path = ":apollo-common", configuration = "shadow"))

    implementation(project(":apollo-bukkit-example-common"))
}

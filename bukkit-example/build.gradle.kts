plugins {
    id("apollo.base-conventions")
}

dependencies {
    compileOnly(libs.bukkit)

    compileOnly(project(":extra:apollo-extra-adventure4"))
    compileOnly(project(path = ":apollo-api", configuration = "bukkit"))
}

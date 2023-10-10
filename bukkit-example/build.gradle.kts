plugins {
    id("apollo.base-conventions")
}

dependencies {
    compileOnly(libs.bukkit.api)

    compileOnly(project(":extra:apollo-extra-adventure4"))
    compileOnly(project(path = ":apollo-api", configuration = "bukkit"))
}

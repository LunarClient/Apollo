plugins {
    id("apollo.shadow-conventions")
}

setupDynamicLoader()

setupDynamicDependency("adventure4", "shadowJarAdventure4", "adventure/4/", "libs")

dependencies {
    compileOnly(libs.bukkit)

    api(project(path = ":apollo-api", configuration = "shadow"))
    api(project(":apollo-common"))

    "loaderCompileOnly"(libs.bukkit.api)
    "loaderImplementation"(project(":extra:apollo-extra-loader"))

    "adventure4"(project(":extra:apollo-extra-adventure4"))
}

plugins {
    id("apollo.shadow-conventions")
}

setupDynamicLoader()

setupDynamicDependency("baseAdventure4", "baseJarAdventure4", "adventure/4/", "libs", "adventure4", "dev")
setupDynamicDependency("adventure4", "shadowJarAdventure4", "adventure/4/", "dependencies", "adventure4", "all")

dependencies {
    compileOnly(libs.bukkit)

    api(project(path = ":apollo-api", configuration = "shadow"))
    api(project(path = ":apollo-common", configuration = "shadow"))

    "loaderCompileOnly"(libs.bukkit)
    "loaderImplementation"(project(":extra:apollo-extra-loader"))

    "baseAdventure4"(project(path = ":extra:apollo-extra-adventure4", configuration = "base"))
    "adventure4"(project(path = ":extra:apollo-extra-adventure4", configuration = "dependency"))
}

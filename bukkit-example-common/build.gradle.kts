plugins {
    id("apollo.base-conventions")
    id("apollo.shadow-conventions")
}

dependencies {
    compileOnly(libs.bukkit)
    compileOnly(libs.bukkit.api)
}

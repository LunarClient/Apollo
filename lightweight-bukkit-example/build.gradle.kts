plugins {
    id("apollo.base-conventions")
}

dependencies {
    compileOnly(libs.bukkit.api)

    api(libs.protobuf)
    api(libs.protobuf.java.util)

    "baseAdventure4"(libs.adventure.platform.bukkit)
}

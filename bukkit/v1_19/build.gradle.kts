plugins {
    id("apollo.paper-conventions")
}

dependencies {
    implementation(project(":apollo-api"))
    implementation(project(":apollo-common"))

    compileOnly(libs.ignite)
    compileOnly(libs.mixin)

    paperweight.paperDevBundle("1.19.4-R0.1-SNAPSHOT")
}

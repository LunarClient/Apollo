plugins {
    id("apollo.paper-conventions")
    id("apollo.platform-conventions")
}

dependencies {
    implementation(project(":apollo-api"))
    implementation(project(":apollo-common"))

    paperweight.paperDevBundle("1.19.4-R0.1-SNAPSHOT")
}

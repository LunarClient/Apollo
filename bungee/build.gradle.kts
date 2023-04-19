plugins {
    id("apollo.platform-conventions")
}

dependencies {
    implementation(project(":apollo-common"))

    compileOnly("net.md-5:bungeecord-api:1.19-R0.1-SNAPSHOT")
}

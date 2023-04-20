plugins {
    id("apollo.base-conventions")
    id("com.github.johnrengelman.shadow")
}

tasks {
    shadowJar {
        relocate("com.google.protobuf", "com.lunarclient.apollo.libs.protobuf")
    }
}

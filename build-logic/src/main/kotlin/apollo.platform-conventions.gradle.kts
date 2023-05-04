import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("apollo.base-conventions")
    id("com.github.johnrengelman.shadow")
}

tasks {
    val shadowJar = named<ShadowJar>("shadowJar") {
        relocate("com.google.protobuf", "com.lunarclient.apollo.libs.protobuf")
    }

    build { dependsOn(shadowJar) }
}

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("apollo.base-conventions")
    id("com.github.johnrengelman.shadow")
}

tasks {
    jar {
        archiveFileName.set("${project.name}-${project.version}-dev.jar")
    }

    val shadowJar = named<ShadowJar>("shadowJar") {
        archiveFileName.set("${project.name}-${project.version}.jar")

        relocate("com.google.protobuf", "com.lunarclient.apollo.libs.protobuf")
        relocate("org.spongepowered.configurate", "com.lunarclient.apollo.libs.configurate")
    }

    build { dependsOn(shadowJar) }
}

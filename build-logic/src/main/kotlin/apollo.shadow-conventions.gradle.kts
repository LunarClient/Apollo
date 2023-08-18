import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("apollo.base-conventions")
    id("com.github.johnrengelman.shadow")
}

tasks {
    jar {
        archiveClassifier.set("dev")
//        archiveFileName.set("${project.name}-${project.version}-dev.jar")
    }

    val shadowJar = named<ShadowJar>("shadowJar") {
        archiveClassifier.set("")
        configureRelocations()
//        archiveFileName.set("${project.name}-${project.version}.jar")
    }

    assemble {
        dependsOn(shadowJar)
    }
}

fun ShadowJar.configureRelocations() {
    relocate("com.google.protobuf", "com.lunarclient.apollo.libs.protobuf")
    relocate("org.spongepowered.configurate", "com.lunarclient.apollo.libs.configurate")
}

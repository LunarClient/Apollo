import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("apollo.base-conventions")
    id("com.github.johnrengelman.shadow")
}

tasks {
    jar {
        archiveClassifier.set("dev")
    }

    val shadowJar = named<ShadowJar>("shadowJar") {
        archiveClassifier.set("")
        configureRelocations()
        configureExclusions()
    }

    assemble {
        dependsOn(shadowJar)
    }
}

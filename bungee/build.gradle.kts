import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("apollo.platform-conventions")
}

dependencies {
    implementation(project(":apollo-api"))
    implementation(project(":apollo-common"))

    compileOnly(libs.bungee)
}

tasks.named<ShadowJar>("shadowJar") {
    archiveFileName.set("${project.name}-${project.version}.jar")
}

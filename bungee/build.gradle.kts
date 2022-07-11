import fr.il_totore.manadrop.MinecraftDependencyHelper.*
import fr.il_totore.manadrop.MinecraftRepositoryHelper.*

plugins {
    id("java")
    id("fr.il_totore.manadrop")
}

group = "com.moonsworth"

repositories {
    mavenCentral()

    spigotSnapshot()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/central")
}

val lombokVersion: String by project

dependencies {
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    implementation(project(":apollo-api"))
    compileOnly(bungeecordApi("1.19-R0.1"))
}

bungee {
    desc {
        named("Apollo-Bungee") //Default: the project name
        authors("Moonsworth")
        main("com.moonsworth.apollo.impl.bungee.ApolloBungeePlatform")
    }
}

tasks.processResources.get().finalizedBy(tasks.named("bungeePlugin"))

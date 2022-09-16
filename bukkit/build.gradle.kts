import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import fr.il_totore.manadrop.MinecraftDependencyHelper.*
import fr.il_totore.manadrop.MinecraftRepositoryHelper.*
import fr.il_totore.manadrop.spigot.task.BuildTools
import com.moonsworth.apollo.toolchain.MinecraftVersion

plugins {
    id("java")
    id("fr.il_totore.manadrop")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

group = "com.moonsworth"
val minecraftVersion = "1.19"

repositories {
    mavenCentral()
    mavenLocal() // required for craftbukkit

    spigotSnapshot()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/central")
    maven("https://repo.spongepowered.org/maven/")
}
spigot {
    desc {
        named("Apollo-Bukkit")
        authors("Moonsworth")
        main("com.moonsworth.apollo.impl.bukkit.ApolloBukkitPlatform")
        command {
            named("setkb")
            permission("command.setkb")
        }
        command {
            named("vignette")
            permission("command.vignette")
        }
        apiVersion("1.18")
    }
}

// run build tools to generate 1.19 nms
tasks.named("buildTools", BuildTools::class.java) {
    versions(minecraftVersion)
    workDir = file("${gradle.gradleUserHomeDir}/apollo/workdir/")
    workDir.mkdirs()
}

val craftbukkitSourceSets = MinecraftVersion.values().map { mc ->
    // create a craftbukkit source set that depends on main
    val versionedSourceSet = sourceSets.create("craftbukkit_${mc.name}") {
        val main = sourceSets.main.get()
        this.compileClasspath += main.output
        this.compileClasspath += main.compileClasspath
        val sourceSet = this
        val cb = compileOnlyConfigurationName
        dependencies {
            // Add this if you want the mixins to be shaded in
            // add(main.runtimeClasspathConfigurationName, sourceSet.output)
            add(cb, "org.bukkit:craftbukkit:${mc.exactVersion}-R0.1-SNAPSHOT")
            add(cb, "org.spongepowered:mixin:0.8.5")
        }
    }

    // :apollo-bukkit:jar_v1_X builds a jar containing Mixins for that version
    // + the main sourceset and the api
    tasks.create("jar_${mc.name}", ShadowJar::class) {
        from(versionedSourceSet.output)
        configurations = listOf(project.configurations.runtimeClasspath.get())
        archiveClassifier.set(mc.name)
    }

}

val lombokVersion: String by project

dependencies {
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    implementation(project(":apollo-api"))
    compileOnly(spigotApi(minecraftVersion))
    compileOnly("space.vectrix.ignite:ignite-api:0.8.0")
}

tasks.processResources.get().finalizedBy(tasks.named("spigotPlugin"))

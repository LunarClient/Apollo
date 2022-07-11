import fr.il_totore.manadrop.MinecraftDependencyHelper.*
import fr.il_totore.manadrop.MinecraftRepositoryHelper.*
import fr.il_totore.manadrop.spigot.task.BuildTools
import com.moonsworth.apollo.toolchain.MinecraftVersion

plugins {
    id("java")
    id("fr.il_totore.manadrop")
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
    sourceSets.create("craftbukkit_${mc.name}") {
        val main = sourceSets.main.get()
        this.compileClasspath += main.output
        val sourceSet = this
        val cb = compileOnlyConfigurationName
        dependencies {
            add(main.runtimeClasspathConfigurationName, sourceSet.output)
            add(cb, "org.bukkit:craftbukkit:${mc.exactVersion}-R0.1-SNAPSHOT")
            add(cb, "org.spongepowered:mixin:0.8.5")
        }
    }
}

val lombokVersion: String by project

dependencies {
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    implementation(project(":apollo-api"))
    compileOnly(spigotApi(minecraftVersion))
}

tasks.processResources.get().finalizedBy(tasks.named("spigotPlugin"))

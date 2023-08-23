plugins {
    id("apollo.shadow-conventions")
    id("apollo.publish-conventions")
}

val bukkitConfig = configurations.register("bukkit")
val bungeeConfig = configurations.register("bungee")
val velocityConfig = configurations.register("velocity")

val main by sourceSets

val bukkitSource by sourceSets.register("bukkit") {
    compileClasspath += main.compileClasspath
    compileClasspath += main.runtimeClasspath

    configurations.named(compileOnlyConfigurationName) {
        extendsFrom(bukkitConfig.get())
    }
}

val bungeeSource by sourceSets.register("bungee") {
    compileClasspath += main.compileClasspath
    compileClasspath += main.runtimeClasspath

    configurations.named(compileOnlyConfigurationName) {
        extendsFrom(bungeeConfig.get())
    }
}

val velocitySource by sourceSets.register("velocity") {
    compileClasspath += main.compileClasspath
    compileClasspath += main.runtimeClasspath

    configurations.named(compileOnlyConfigurationName) {
        extendsFrom(velocityConfig.get())
    }
}

dependencies {
    api(projects.extra.apolloExtraAdventure4)
    api(libs.geantyref)

    val bukkit = bukkitSource.name
    bukkit(main.output)
    bukkit(libs.bukkit.api)
    bukkit(libs.bukkit)

    val bungee = bungeeSource.name
    bungee(main.output)
    bungee(libs.bungee)

    val velocity = velocitySource.name
    velocity(main.output)
    velocity(libs.velocity)
}

tasks {
    val bukkitJar by registering(Jar::class) {
        archiveClassifier.set("bukkit")
        from(bukkitSource.output)
    }

    val bungeeJar by registering(Jar::class) {
        archiveClassifier.set("bungee")
        from(bungeeSource.output)
    }

    val velocityJar by registering(Jar::class) {
        archiveClassifier.set("velocity")
        from(velocitySource.output)
    }

    shadowJar {
        configurations = listOf()

        from(jar)
        from(bukkitJar)
        from(bungeeJar)
        from(velocityJar)
    }

    javadoc {
        source(sourceSets.map { it.allJava })
        classpath += sourceSets.map { it.compileClasspath }.reduce { first, second -> first + second }
    }

    assemble {
        dependsOn(shadowJar)
    }
}

publishShadowJar()

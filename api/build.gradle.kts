plugins {
    id("apollo.shadow-conventions")
    id("apollo.publish-conventions")
}

val bukkitConfig = configurations.register("bukkit")
val bungeeConfig = configurations.register("bungee")
val velocityConfig = configurations.register("velocity")

val main by sourceSets

val bukkitSource by sourceSets.register("bukkit") {
    project.dependencies.add(this.implementationConfigurationName, main.output)

    compileClasspath += main.compileClasspath
    compileClasspath += main.runtimeClasspath

    configurations.named(implementationConfigurationName) {
        extendsFrom(bukkitConfig.get())
    }
}

val bungeeSource by sourceSets.register("bungee") {
    project.dependencies.add(this.implementationConfigurationName, main.output)

    compileClasspath += main.compileClasspath
    compileClasspath += main.runtimeClasspath

    configurations.named(implementationConfigurationName) {
        extendsFrom(bungeeConfig.get())
    }
}

val velocitySource by sourceSets.register("velocity") {
    project.dependencies.add(this.implementationConfigurationName, main.output)

    compileClasspath += main.compileClasspath
    compileClasspath += main.runtimeClasspath

    configurations.named(implementationConfigurationName) {
        extendsFrom(velocityConfig.get())
    }
}

dependencies {
    api(project(":extra:apollo-extra-adventure4"))

    api(libs.geantyref)

    val bukkit = bukkitConfig.name
    bukkit(main.output)
    bukkit(libs.bukkit.api)
    bukkit(libs.bukkit)

    val bungee = bungeeConfig.name
    bungee(main.output)
    bungee(libs.bungee)

    val velocity = velocityConfig.name
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
}

artifacts {
    add("bukkit", tasks["bukkitJar"])
    add("bungee", tasks["bungeeJar"])
    add("velocity", tasks["velocityJar"])
}

publishShadowJar()

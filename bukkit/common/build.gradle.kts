plugins {
    `java-library`
    id("fr.il_totore.manadrop")
    id("com.github.johnrengelman.shadow")
    id("io.papermc.paperweight.userdev")
}

dependencies {
    compileOnlyApi(project(":apollo-api"))

    paperweight.paperDevBundle("1.18.2-R0.1-SNAPSHOT")
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

tasks {
    processResources {
        finalizedBy(named("spigotPlugin"))
    }

    build {
        dependsOn(reobfJar)
    }

    reobfJar {
        remapperArgs.add("--mixin")

        outputJar.set(layout.buildDirectory.file("libs/${project.name}.jar"))
    }
}

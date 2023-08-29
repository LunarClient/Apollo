plugins {
    id("apollo.shadow-conventions")
}

val adventure4Config = configurations.register("adventure4")

dependencies {
    compileOnly(libs.bungee)

    val adventure4 = adventure4Config.name
    adventure4(projects.extra.apolloExtraAdventure4)

    api(projects.apolloApi) {
        targetConfiguration = "shadow"
    }

    api(projects.apolloCommon)
}

tasks {
    shadowJar {
        from(sourceSets.main.get().output)

        into("adventure/4/") {
            from(adventure4Config.get().resolve().map { zipTree(it) })
        }
    }
}

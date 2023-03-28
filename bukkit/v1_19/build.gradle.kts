plugins {
    `java-library`
    id("com.github.johnrengelman.shadow")
    id("io.papermc.paperweight.userdev")
}

dependencies {
    implementation(project(":apollo-api"))
    implementation(project(":apollo-common"))

    compileOnly("space.vectrix.ignite:ignite-api:0.8.1")
    compileOnly("org.spongepowered:mixin:0.8.5")

    paperweight.paperDevBundle("1.19.3-R0.1-SNAPSHOT")
}

tasks {
    build {
        dependsOn(reobfJar)
    }

    reobfJar {
        remapperArgs.add("--mixin")

        outputJar.set(layout.buildDirectory.file("libs/${project.name}.jar"))
    }

    shadowJar {
        relocate("com.google.protobuf", "com.moonsworth.apollo.libs.protobuf")
    }
}

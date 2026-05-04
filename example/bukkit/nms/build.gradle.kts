plugins {
    id("apollo.base-conventions")
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.21"
}

java {
    javaTarget(25)
}

paperweight.reobfArtifactConfiguration =
    io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION

tasks.named("reobfJar") {
    enabled = false
}

tasks.compileJava {
    options.compilerArgs.addAll(listOf("--release", "21"))
}

configurations.named("compileClasspath") {
    attributes {
        attribute(TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, 25)
    }
}

configurations.named("runtimeClasspath") {
    attributes {
        attribute(TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, 25)
    }
}

listOf("apiElements", "runtimeElements").forEach { name ->
    configurations.named(name) {
        attributes {
            attribute(TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, 21)
        }
    }
}

dependencies {
    paperweight.paperDevBundle("26.1.1.build.+")
}

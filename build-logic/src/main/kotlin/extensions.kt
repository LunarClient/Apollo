import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.javadoc.Javadoc
import org.gradle.jvm.tasks.Jar
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.*
import org.gradle.jvm.toolchain.JavaToolchainService

fun JavaPluginExtension.javaTarget(version: Int) {
    toolchain.languageVersion.set(JavaLanguageVersion.of(version))
}

fun ShadowJar.configureRelocations() {
    relocate("com.google.protobuf", "com.lunarclient.apollo.libs.protobuf")
    relocate("com.google.gson", "com.lunarclient.apollo.libs.gson")
    relocate("org.spongepowered.configurate", "com.lunarclient.apollo.libs.configurate")
}

fun ShadowJar.configureExclusions() {
    exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")
}

fun Project.setupPlatforms() {
    extensions.configure<JavaPluginExtension> {
        val shade = configurations.register("shade")

        configurations.named("api") {
            extendsFrom(shade.get())
        }

        val jar by tasks.named("jar")

        val shadowJar by tasks.named("shadowJar", ShadowJar::class) {
            configurations = listOf(shade.get())

            from(jar)
        }

        tasks.named("javadoc", Javadoc::class) {
            source(sourceSets.map { it.allJava })
            classpath += sourceSets.map { it.compileClasspath }.reduce { first, second -> first + second }
        }
    }
}

fun Project.setupPlatformDependency(configurationName: String,
                                    jarTaskName: String,
                                    javaVersion: Int? = 8) {
    extensions.configure<JavaPluginExtension> {
        val configuration = configurations.register(configurationName)

        val main by sourceSets

        val source by sourceSets.register(configurationName) {
            project.dependencies.add(this.implementationConfigurationName, main.output)

            compileClasspath += main.compileClasspath
            compileClasspath += main.runtimeClasspath

            configurations.named(this.implementationConfigurationName) {
                extendsFrom(configuration.get())
            }
        }

        if (javaVersion != null) {
            val javaToolchains = project.extensions.getByType(JavaToolchainService::class.java)
            tasks.named<JavaCompile>(source.compileJavaTaskName) {
                javaCompiler.set(javaToolchains.compilerFor {
                    languageVersion.set(JavaLanguageVersion.of(javaVersion))
                })
                sourceCompatibility = javaVersion.toString()
                targetCompatibility = javaVersion.toString()
            }
        }

        val jarTask by tasks.register(jarTaskName, Jar::class) {
            archiveClassifier.set(configurationName)
            from(source.output)
        }

        tasks.named("shadowJar", ShadowJar::class) {
            from(jarTask)
        }

        artifacts {
            add(configurationName, jarTask)
        }
    }
}

fun Project.setupAdventureProject() {
    extensions.configure<JavaPluginExtension> {
        val base by configurations.register("base")
        val dependency by configurations.register("dependency")

        val main by sourceSets

        val jarTask by tasks.register("baseJar", Jar::class) {
            archiveClassifier.set("base")
            from(main.output)
        }

        val shadowTask by tasks.register("dependencyJar", ShadowJar::class) {
            archiveClassifier.set("all")
            configurations = listOf(project.configurations["runtimeClasspath"])
        }

        artifacts {
            add("base", jarTask)
            add("dependency", shadowTask)
        }
    }
}

fun Project.setupDynamicLoader() {
    extensions.configure<JavaPluginExtension> {
        val loaderCompileOnlyConfig = configurations.register("loaderCompileOnly")
        val loaderImplementationConfig = configurations.register("loaderImplementation")

        val main by sourceSets

        val loaderSource by sourceSets.register("platform-loader") {
            configurations.named(this.compileOnlyConfigurationName) {
                extendsFrom(loaderCompileOnlyConfig.get())
            }

            configurations.named(this.implementationConfigurationName) {
                extendsFrom(loaderImplementationConfig.get())
            }
        }

        configurations.named("compileOnly") {
            extendsFrom(loaderCompileOnlyConfig.get())
            extendsFrom(loaderImplementationConfig.get())
        }

        val shadowJar by tasks.named("shadowJar", ShadowJar::class) {
            archiveClassifier.set("all")
        }

        val shadowJarLoader by tasks.creating(ShadowJar::class) {
            archiveClassifier.set("")
            configurations = listOf(loaderImplementationConfig.get())

            dependsOn(shadowJar)

            from(loaderSource.output)

            into("platform/") {
                from(shadowJar.outputs)
                rename { "libs.jarinjar" }
            }

            configureExclusions()
        }

        tasks.named("assemble") {
            dependsOn(shadowJarLoader)
        }
    }
}

fun Project.setupDynamicDependency(configurationName: String, shadowTaskName: String, jarPath: String, jarName: String,
                                   name: String = configurationName, classifier: String = "all") {
    extensions.configure<JavaPluginExtension> {
        val configuration = configurations.findByName(configurationName) ?: configurations.register(configurationName).get()

        configurations.named("compileOnly") {
            extendsFrom(configuration)
        }

        val shadowTask by tasks.register(shadowTaskName, ShadowJar::class) {
            archiveClassifier.set("${name}-${classifier}")
            configurations = listOf(configuration)

            configureExclusions()
        }

        tasks.named("shadowJarLoader", ShadowJar::class) {
            dependsOn(shadowTask)

            into(jarPath) {
                from(shadowTask.outputs)
                rename { "${jarName}.jarinjar" }
            }
        }
    }
}

fun Project.publishJar() {
    configurePublication {
        from(components["java"])
    }
}

fun Project.publishShadowJar() {
    configurePublication {
        artifact(tasks["jar"])
        artifact(tasks["shadowJar"])
        artifact(tasks["sourcesJar"])
    }
}

private fun Project.configurePublication(configurer: MavenPublication.() -> Unit) {
    extensions.configure<PublishingExtension> {
        publications.named<MavenPublication>("mavenJava") {
            apply(configurer)
        }
    }
}

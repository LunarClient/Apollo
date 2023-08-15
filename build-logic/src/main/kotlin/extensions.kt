import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.javadoc.Javadoc
import org.gradle.jvm.tasks.Jar
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.*

fun JavaPluginExtension.javaTarget(version: Int) {
    toolchain.languageVersion.set(JavaLanguageVersion.of(version))
}

fun Project.createApiSources(vararg names: String) {
    extensions.configure<JavaPluginExtension> {
        val commonsCompileOnly by configurations.register("commonsCompileOnly")

        val main by sourceSets
        names.forEach {
            sourceSets.register(it) {
                java.setSrcDirs(setOf("src/$it/java"))

                project.dependencies.add(this.implementationConfigurationName, main.output)

                configurations.named(compileOnlyConfigurationName) {
                    extendsFrom(commonsCompileOnly)
                }
            }.get()
        }

        sourceSets.configureEach {
            val sourceSet = this
            val sourceName = sourceSet.name
            if (sourceName.equals("main")) return@configureEach

            tasks.register("${sourceName}Jar", Jar::class.java) {
                archiveClassifier.set(sourceName)
                from(sourceSet.output)
            }
        }

        tasks.named<ShadowJar>("shadowJar") {
            sourceSets.forEach {
                val sourceName = it.name
                val sourceJarName = if (sourceName.equals("main")) "sourcesJar" else "${sourceName}Jar"

                val sourceJar by tasks.named<Jar>(sourceJarName)
                from(sourceJar)
            }
        }

        tasks.named<Javadoc>("javadoc") {
            source(sourceSets.map { it.allJava })
            classpath += sourceSets.map { it.compileClasspath }.reduce { first, second -> first + second }
        }

//        tasks.named<ShadowJar>("shadowJar") {
//            platformSources.forEach {
//                from(it.output)
//            }
//        }
//
//        tasks.named<Jar>("sourcesJar") {
//            platformSources.forEach {
//                from(it.allSource)
//            }
//        }
//
//        tasks.named<Javadoc>("javadoc") {
//            source(platformSources.map { it.allJava })
//            classpath += platformSources.map { it.compileClasspath }.reduce { first, second -> first + second }
//        }
    }
}

fun Project.publishJar() {
    configurePublication {
        from(components["java"])
    }
}

fun Project.publishShadowJar() {
    configurePublication {
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

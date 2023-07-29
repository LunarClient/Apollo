import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.javadoc.Javadoc
import org.gradle.jvm.tasks.Jar
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.named

fun JavaPluginExtension.javaTarget(version: Int) {
    toolchain.languageVersion.set(JavaLanguageVersion.of(version))
}

fun Project.createPlatformSources(vararg names: String) {
    extensions.configure<JavaPluginExtension> {
        val mainSource by sourceSets.named("main")
        val platformSources = mutableListOf(mainSource)
        platformSources.addAll(names.map {
            sourceSets.register(it) {
                java.setSrcDirs(setOf("src/$it/java"))
                compileClasspath += mainSource.compileClasspath
                runtimeClasspath += mainSource.runtimeClasspath
                dependencies.add(implementationConfigurationName, objects.fileCollection().from(mainSource.output.classesDirs))
            }.get()
        })

        tasks.named<Jar>("jar") {
            platformSources.forEach {
                from(it.output)
            }
        }

        tasks.named<Jar>("sourcesJar") {
            platformSources.forEach {
                from(it.allSource)
            }
        }

        tasks.named<Javadoc>("javadoc") {
            source(platformSources.map { it.allJava })
            classpath += platformSources.map { it.compileClasspath }.reduce { first, second -> first + second }
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

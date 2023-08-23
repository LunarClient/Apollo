import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.*

fun JavaPluginExtension.javaTarget(version: Int) {
    toolchain.languageVersion.set(JavaLanguageVersion.of(version))
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

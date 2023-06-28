plugins {
    id("apollo.base-conventions")
    id("maven-publish")
    id("com.google.cloud.artifactregistry.gradle-plugin")
}

// Expose version catalog
val libs = extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)

java {
    withSourcesJar()
    withJavadocJar()
}

tasks {
    withType<Javadoc> {
        val minimalOptions: MinimalJavadocOptions = options
        if (minimalOptions is StandardJavadocDocletOptions) {
            val options: StandardJavadocDocletOptions = minimalOptions
            options.addBooleanOption("Xdoclint:-missing", true)
            options.addBooleanOption("html5", true)
        }

        exclude("lunarclient/**")
    }
}

publishing {
    repositories {
        maven {
            url = uri("artifactregistry://us-maven.pkg.dev/moonsworth-299m4oir/maven-public")
        }
    }

    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
        }
    }
}

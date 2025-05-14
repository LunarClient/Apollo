plugins {
    id("apollo.base-conventions")
    id("maven-publish")
    id("com.google.cloud.artifactregistry.gradle-plugin")
}

// Expose version catalog
val libs = extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)

java {
    withJavadocJar()
}

publishing {
    repositories.maven {
        url = uri("artifactregistry://us-maven.pkg.dev/mw-lunarclient-maven-repo/public")
    }

    publications.create<MavenPublication>("mavenJava") {
        groupId = rootProject.group as String
        version = rootProject.version as String
        artifactId = project.name
    }
}

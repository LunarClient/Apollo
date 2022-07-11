plugins {
    id("java")
    id("java-gradle-plugin")
    `kotlin-dsl`
}

group = "com.moonsworth"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(gradleApi())
}

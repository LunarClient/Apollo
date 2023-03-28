plugins {
    id("java")
}

group = "com.moonsworth"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}
val lombokVersion: String by project

dependencies {
    implementation(project(":apollo-common"))

    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    val velocity = "com.velocitypowered:velocity-api:3.0.1"
    compileOnly(velocity)
    annotationProcessor(velocity)
}

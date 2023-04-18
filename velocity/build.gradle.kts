plugins {
    id("apollo.platform-conventions")
}

dependencies {
    implementation(project(":apollo-common"))

    val velocity = "com.velocitypowered:velocity-api:3.0.1"
    compileOnly(velocity)
    annotationProcessor(velocity)
}

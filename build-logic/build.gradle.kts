plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.artifactregistry)
    implementation(libs.idea)
    implementation(libs.spotless)
    implementation(libs.shadow)
    implementation(libs.asm)
}

dependencies {
    compileOnly(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

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
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
    target {
        compilations.configureEach {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
}

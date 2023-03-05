plugins {
    `java-library`
    id("idea")
    id("maven-publish")
}

group = "com.moonsworth"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

repositories {
    mavenCentral()
}

publishing {
    repositories {
        maven {
            url = uri("s3://" + System.getenv("REPO_BUCKET"))
            credentials(AwsCredentials::class) {
                accessKey = System.getenv("REPO_ACCESS_KEY")
                secretKey = System.getenv("REPO_SECRET_KEY")
            }
        }
    }
}

dependencies {
    compileOnly("org.jetbrains:annotations:24.0.1")
}

publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
        }
    }
}

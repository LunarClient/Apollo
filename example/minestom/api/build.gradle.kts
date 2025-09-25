plugins {
    id("apollo.base-conventions")
    id("apollo.shadow-conventions")
}

java {
    javaTarget(21)
}

dependencies {
    compileOnly(project(path = ":apollo-api", configuration = "minestom"))
    implementation(project(path = ":platform:apollo-minestom", configuration = "shadow"))

    implementation(libs.minestom)
}

tasks {
    jar {
        manifest {
            attributes["Main-Class"] = "com.lunarclient.apollo.example.ApolloMinestomExample"
        }
    }
}

plugins {
    id("apollo.publish-conventions")
    alias(libs.plugins.protobuf)
}

val protocVersion: String = libs.versions.protobuf.get()

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${protocVersion}"
    }

    generateProtoTasks {
        all().configureEach {
            // The generateProto task does not seem to properly clean its previously generated outputs.
            // See https://github.com/google/protobuf-gradle-plugin/issues/332
            // See https://github.com/google/protobuf-gradle-plugin/issues/331
            this.doFirst {
                delete(this.outputs)
            }
        }
    }
}

dependencies {
    api(project(":apollo-api"))

    api(libs.protobuf)
    api(libs.configurate.core)
    api(libs.configurate.hocon)
}

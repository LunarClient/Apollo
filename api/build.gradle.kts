plugins {
    id("java")
    `java-library`
}

// TODO: enforce this works with Java 8
//java {
//    toolchain {
//        languageVersion.set(JavaLanguageVersion.of(8))
//    }
//}

repositories {
    mavenCentral()
}

val lombokVersion: String by project

dependencies {
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    // these dependencies match vanilla 1.19
    compileOnlyApi("io.netty:netty-transport-native-unix-common:4.1.77.Final")
    compileOnlyApi("io.netty:netty-transport-classes-epoll:4.1.77.Final")
    compileOnlyApi("io.netty:netty-transport:4.1.77.Final")
    compileOnlyApi("io.netty:netty-common:4.1.77.Final")
    compileOnlyApi("io.netty:netty-resolver:4.1.77.Final")
    compileOnlyApi("io.netty:netty-codec:4.1.77.Final")
    compileOnlyApi("io.netty:netty-handler:4.1.77.Final")
    compileOnlyApi("io.netty:netty-buffer:4.1.77.Final")
    compileOnlyApi("io.netty:netty-transport-native-epoll:4.1.77.Final")
    compileOnlyApi("com.google.guava:failureaccess:1.0.1")
    compileOnlyApi("com.google.guava:guava:31.0.1-jre")
    compileOnlyApi("org.apache.commons:commons-lang3:3.12.0")
    compileOnlyApi("commons-io:commons-io:2.11.0")
    compileOnlyApi("commons-codec:commons-codec:1.15")
    compileOnlyApi("com.google.code.gson:gson:2.8.9")
    compileOnlyApi("org.apache.logging.log4j:log4j-api:2.17.1")
}

import com.diffplug.gradle.spotless.FormatExtension
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.util.regex.Pattern
import java.util.stream.Collectors

plugins {
    `java-library`
    id("com.diffplug.spotless")
}

// Expose version catalog
val libs = extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)

group = rootProject.group
version = rootProject.version

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }

    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    compileOnly(libs.jetbrains.annotations)

    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}

spotless {
    fun FormatExtension.applyCommon() {
        trimTrailingWhitespace()
        endWithNewline()
        indentWithSpaces(4)
        targetExclude("build/generated/source/proto/**")
    }

    fun formatLicense(): String {
        val splitPattern = Pattern.compile("\r?\n")
        val lineSeparator = System.lineSeparator()
        val headerPrefix = "/*$lineSeparator"
        val linePrefix = " * "
        val headerSuffix = "$lineSeparator */"

        val headerText = String(Files.readAllBytes(rootProject.file("license_header.txt").toPath()), StandardCharsets.UTF_8)

        return splitPattern.splitAsStream(headerText)
            .map {
                StringBuilder(it.length + 4)
                    .append(linePrefix)
                    .append(it)
                    .toString()
            }
            .collect(Collectors.joining(
                lineSeparator,
                headerPrefix,
                headerSuffix
            ))
    }

    java {
        licenseHeader(formatLicense())
        importOrderFile(rootProject.file(".spotless/lunar.importorder"))
        applyCommon()
    }
}

tasks {
    // For some reason tests are not working for paperweight.
    // Luckily we don't need tests right now anyway.
    test { onlyIf { project.hasProperty("test") } }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.compilerArgs.addAll(listOf(
            "-parameters",
            "-Xlint:all"
        ))
    }
}

pluginManagement {
    includeBuild("build-logic")
    repositories {
        maven(url = "https://repo.stellardrift.ca/repository/internal/") {
            name = "stellardriftReleases"
            mavenContent { releasesOnly() }
        }
        maven(url = "https://repo.stellardrift.ca/repository/snapshots/") {
            name = "stellardriftSnapshots"
            mavenContent { snapshotsOnly() }
        }
        gradlePluginPortal()
        maven("https://repo.jpenilla.xyz/snapshots")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://us-maven.pkg.dev/mw-lunarclient-maven-repo/public")
        mavenCentral()
        mavenLocal()
    }
}

rootProject.name = "apollo-parent"

val mcVersion = startParameter.projectProperties["mc"]
val loadAllVersions = mcVersion == null

listOfNotNull(
    "extra:loader",
    "extra:adventure4",
    "api",
    "common",
    "bukkit",
    "example:bukkit:common",
    "example:bukkit:api",
    "example:bukkit:json",
    "example:bukkit:proto",
    "example:minestom:api",
    if (loadAllVersions) "bungee" else null,
    if (loadAllVersions) "velocity" else null,
    if (loadAllVersions) "folia" else null,
    if (loadAllVersions) "minestom" else null
).forEach {
    include(it)
    findProject(":$it")?.name = "apollo-${it.replace(':', '-')}"
}

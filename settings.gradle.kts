rootProject.name = "apollo-parent"

val mcVersion = startParameter.projectProperties["mc"]
val loadAllVersions = mcVersion == null

val modules = listOfNotNull(
    "api",
    "common",
//    "bukkit",
//    "bukkit:common",
//    if (loadAllVersions || mcVersion == "v1_18") "bukkit:v1_18" else null,
//    if (loadAllVersions || mcVersion == "v1_19") "bukkit:v1_19" else null,
//    if (loadAllVersions) "bungee" else null,
//    if (loadAllVersions) "velocity" else null
)

includeBuild("toolchain")
include(modules)

modules.forEach { findProject(":$it")?.name = "apollo-${it.replace(':', '-')}" }

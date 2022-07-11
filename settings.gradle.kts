rootProject.name = "apollo"
includeBuild("toolchain")
include("api")
include("bukkit")
include("bungee")
include("velocity")

findProject(":api")?.name = "apollo-api"
findProject(":bukkit")?.name = "apollo-bukkit"
findProject(":bungee")?.name = "apollo-bungee"
findProject(":velocity")?.name = "apollo-velocity"

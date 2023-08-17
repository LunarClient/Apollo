plugins {
    eclipse
    id("org.jetbrains.gradle.plugin.idea-ext")
}

eclipse {
    val shadowJar = tasks.getByPath(":apollo-api:shadowJar")
    synchronizationTasks(shadowJar)
}

idea {
    if(project != null) {
        (project as ExtensionAware).extensions["settings"].run {
            (this as ExtensionAware).extensions.getByType(org.jetbrains.gradle.ext.TaskTriggersConfig::class).run {
                afterSync(":apollo-api:shadowJar")
            }
        }
    }
}

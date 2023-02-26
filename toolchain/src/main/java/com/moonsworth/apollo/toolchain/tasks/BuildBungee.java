package com.moonsworth.apollo.toolchain.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import fr.il_totore.manadrop.bungeecord.BungeeExtension;

/**
 * Replaces manadrop "BuildBungeecord" task.
 */
public class BuildBungee extends DefaultTask {
    @TaskAction
    public void run() {
        BungeeExtension plugin = (BungeeExtension) getProject().getExtensions().getByName("bungee");
        plugin.getDescription().ifPresent(desc -> desc.run(this));
    }
}

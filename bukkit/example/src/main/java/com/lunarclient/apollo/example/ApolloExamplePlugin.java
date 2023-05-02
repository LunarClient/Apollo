package com.lunarclient.apollo.example;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class ApolloExamplePlugin extends JavaPlugin {

    @Getter
    private static ApolloExamplePlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        // Register all modules & listeners
    }

    @Override
    public void onDisable() {

    }
}

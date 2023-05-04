package com.lunarclient.apollo.example;

import com.lunarclient.apollo.example.commands.BeamCommands;
import com.lunarclient.apollo.example.commands.BorderCommands;
import com.lunarclient.apollo.example.modules.BeamExample;
import com.lunarclient.apollo.example.modules.BorderExample;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class ApolloExamplePlugin extends JavaPlugin {

    @Getter
    private static ApolloExamplePlugin plugin;

    private BeamExample beamExample;
    private BorderExample borderExample;

    @Override
    public void onEnable() {
        plugin = this;

        this.registerModuleExamples();
        this.registerCommands();
    }

    @Override
    public void onDisable() {

    }

    private void registerModuleExamples() {
        this.beamExample = new BeamExample();
        this.borderExample = new BorderExample();
    }

    private void registerCommands() {
        this.getCommand("beam").setExecutor(new BeamCommands());
        this.getCommand("border").setExecutor(new BorderCommands());
    }
}

package com.lunarclient.apollo.example;

import com.lunarclient.apollo.example.commands.BeamCommand;
import com.lunarclient.apollo.example.commands.BorderCommand;
import com.lunarclient.apollo.example.commands.ColoredFireCommand;
import com.lunarclient.apollo.example.commands.CooldownCommand;
import com.lunarclient.apollo.example.modules.BeamExample;
import com.lunarclient.apollo.example.modules.BorderExample;
import com.lunarclient.apollo.example.modules.ColoredFireExample;
import com.lunarclient.apollo.example.modules.CooldownExample;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class ApolloExamplePlugin extends JavaPlugin {

    @Getter
    private static ApolloExamplePlugin plugin;

    private BeamExample beamExample;
    private BorderExample borderExample;
    private ColoredFireExample coloredFireExample;
    private CooldownExample cooldownExample;

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
        this.coloredFireExample = new ColoredFireExample();
        this.cooldownExample = new CooldownExample();
    }

    private void registerCommands() {
        this.getCommand("beam").setExecutor(new BeamCommand());
        this.getCommand("border").setExecutor(new BorderCommand());
        this.getCommand("coloredfire").setExecutor(new ColoredFireCommand());
        this.getCommand("cooldown").setExecutor(new CooldownCommand());
    }
}

package com.lunarclient.apollo.example;

import com.lunarclient.apollo.example.commands.*;
import com.lunarclient.apollo.example.modules.*;
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
    private HologramExample hologramExample;
    private LimbExample limbExample;
    private MiscExample miscExample;
    private ModSettingsExample modSettingsExample;
    private NametagExample nametagExample;
    private NotificationExample notificationExample;
    private StaffModExample staffModExample;
    private StopwatchExample stopwatchExample;
    private TeamExample teamExample;
    private TitleExample titleExample;
    private TntCountdownExample tntCountdownExample;
    private TransferExample transferExample;
    private WaypointExample waypointExample;

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
        this.hologramExample = new HologramExample();
        this.limbExample = new LimbExample();
        this.miscExample = new MiscExample();
        this.modSettingsExample = new ModSettingsExample();
        this.nametagExample = new NametagExample();
        this.notificationExample = new NotificationExample();
        this.staffModExample = new StaffModExample();
        this.stopwatchExample = new StopwatchExample();
        this.teamExample = new TeamExample();
        this.titleExample = new TitleExample();
        this.tntCountdownExample = new TntCountdownExample();
        this.transferExample = new TransferExample();
        this.waypointExample = new WaypointExample();
    }

    private void registerCommands() {
        this.getCommand("beam").setExecutor(new BeamCommand());
        this.getCommand("border").setExecutor(new BorderCommand());
        this.getCommand("coloredfire").setExecutor(new ColoredFireCommand());
        this.getCommand("cooldown").setExecutor(new CooldownCommand());
        this.getCommand("hologram").setExecutor(new HologramCommand());
        this.getCommand("limb").setExecutor(new LimbCommand());
        this.getCommand("misc").setExecutor(new MiscCommand());
        this.getCommand("modsettings").setExecutor(new ModSettingsCommand());
        this.getCommand("nametag").setExecutor(new NametagCommand());
        this.getCommand("notification").setExecutor(new NotificationCommand());
        this.getCommand("tntcountdown").setExecutor(new TntCountdownCommand());
        this.getCommand("transfer").setExecutor(new TransferCommand());
    }
}

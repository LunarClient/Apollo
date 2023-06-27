/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2023 Moonsworth
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
    private EntityExample entityExample;
    private HologramExample hologramExample;
    private LimbExample limbExample;
    private NametagExample nametagExample;
    private NotificationExample notificationExample;
    private ServerRuleExample serverRuleExample;
    private StaffModExample staffModExample;
    private StopwatchExample stopwatchExample;
    private TeamExample teamExample;
    private TitleExample titleExample;
    private TntCountdownExample tntCountdownExample;
    private TransferExample transferExample;
    private VignetteExample vignetteExample;
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
        this.entityExample = new EntityExample();
        this.hologramExample = new HologramExample();
        this.limbExample = new LimbExample();
        this.nametagExample = new NametagExample();
        this.notificationExample = new NotificationExample();
        this.serverRuleExample = new ServerRuleExample();
        this.staffModExample = new StaffModExample();
        this.stopwatchExample = new StopwatchExample();
        this.teamExample = new TeamExample();
        this.titleExample = new TitleExample();
        this.tntCountdownExample = new TntCountdownExample();
        this.transferExample = new TransferExample();
        this.vignetteExample = new VignetteExample();
        this.waypointExample = new WaypointExample();
    }

    private void registerCommands() {
        this.getCommand("beam").setExecutor(new BeamCommand());
        this.getCommand("border").setExecutor(new BorderCommand());
        this.getCommand("coloredfire").setExecutor(new ColoredFireCommand());
        this.getCommand("cooldown").setExecutor(new CooldownCommand());
        this.getCommand("entity").setExecutor(new EntityCommand());
        this.getCommand("hologram").setExecutor(new HologramCommand());
        this.getCommand("limb").setExecutor(new LimbCommand());
        this.getCommand("nametag").setExecutor(new NametagCommand());
        this.getCommand("notification").setExecutor(new NotificationCommand());
        this.getCommand("serverrule").setExecutor(new ServerRuleCommand());
        this.getCommand("staffmod").setExecutor(new StaffModCommand());
        this.getCommand("stopwatch").setExecutor(new StopwatchCommand());
        this.getCommand("team").setExecutor(new TeamCommand());
        this.getCommand("title").setExecutor(new TitleCommand());
        this.getCommand("tntcountdown").setExecutor(new TntCountdownCommand());
        this.getCommand("transfer").setExecutor(new TransferCommand());
        this.getCommand("vignette").setExecutor(new VignetteCommand());
        this.getCommand("waypoint").setExecutor(new WaypointCommand());
    }
}

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

import com.lunarclient.apollo.example.commands.BeamCommand;
import com.lunarclient.apollo.example.commands.BorderCommand;
import com.lunarclient.apollo.example.commands.ChatCommand;
import com.lunarclient.apollo.example.commands.ColoredFireCommand;
import com.lunarclient.apollo.example.commands.CombatCommand;
import com.lunarclient.apollo.example.commands.CooldownCommand;
import com.lunarclient.apollo.example.commands.EntityCommand;
import com.lunarclient.apollo.example.commands.GlowCommand;
import com.lunarclient.apollo.example.commands.HologramCommand;
import com.lunarclient.apollo.example.commands.LimbCommand;
import com.lunarclient.apollo.example.commands.ModSettingsCommand;
import com.lunarclient.apollo.example.commands.NametagCommand;
import com.lunarclient.apollo.example.commands.NotificationCommand;
import com.lunarclient.apollo.example.commands.ServerRuleCommand;
import com.lunarclient.apollo.example.commands.StaffModCommand;
import com.lunarclient.apollo.example.commands.StopwatchCommand;
import com.lunarclient.apollo.example.commands.TeamCommand;
import com.lunarclient.apollo.example.commands.TitleCommand;
import com.lunarclient.apollo.example.commands.TntCountdownCommand;
import com.lunarclient.apollo.example.commands.TransferCommand;
import com.lunarclient.apollo.example.commands.VignetteCommand;
import com.lunarclient.apollo.example.commands.WaypointCommand;
import com.lunarclient.apollo.example.modules.BeamExample;
import com.lunarclient.apollo.example.modules.BorderExample;
import com.lunarclient.apollo.example.modules.ChatExample;
import com.lunarclient.apollo.example.modules.ColoredFireExample;
import com.lunarclient.apollo.example.modules.CombatExample;
import com.lunarclient.apollo.example.modules.CooldownExample;
import com.lunarclient.apollo.example.modules.EntityExample;
import com.lunarclient.apollo.example.modules.GlowExample;
import com.lunarclient.apollo.example.modules.HologramExample;
import com.lunarclient.apollo.example.modules.LimbExample;
import com.lunarclient.apollo.example.modules.ModSettingsExample;
import com.lunarclient.apollo.example.modules.NametagExample;
import com.lunarclient.apollo.example.modules.NotificationExample;
import com.lunarclient.apollo.example.modules.ServerRuleExample;
import com.lunarclient.apollo.example.modules.StaffModExample;
import com.lunarclient.apollo.example.modules.StopwatchExample;
import com.lunarclient.apollo.example.modules.TeamExample;
import com.lunarclient.apollo.example.modules.TitleExample;
import com.lunarclient.apollo.example.modules.TntCountdownExample;
import com.lunarclient.apollo.example.modules.TransferExample;
import com.lunarclient.apollo.example.modules.VignetteExample;
import com.lunarclient.apollo.example.modules.WaypointExample;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class ApolloExamplePlugin extends JavaPlugin {

    @Getter
    private static ApolloExamplePlugin plugin;

    private BeamExample beamExample;
    private BorderExample borderExample;
    private ChatExample chatExample;
    private ColoredFireExample coloredFireExample;
    private CombatExample combatExample;
    private CooldownExample cooldownExample;
    private EntityExample entityExample;
    private GlowExample glowExample;
    private HologramExample hologramExample;
    private LimbExample limbExample;
    private ModSettingsExample modSettingsExample;
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
        this.chatExample = new ChatExample();
        this.coloredFireExample = new ColoredFireExample();
        this.combatExample = new CombatExample();
        this.cooldownExample = new CooldownExample();
        this.entityExample = new EntityExample();
        this.glowExample = new GlowExample();
        this.hologramExample = new HologramExample();
        this.limbExample = new LimbExample();
        this.modSettingsExample = new ModSettingsExample();
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
        this.getCommand("chat").setExecutor(new ChatCommand());
        this.getCommand("coloredfire").setExecutor(new ColoredFireCommand());
        this.getCommand("combat").setExecutor(new CombatCommand());
        this.getCommand("cooldown").setExecutor(new CooldownCommand());
        this.getCommand("entity").setExecutor(new EntityCommand());
        this.getCommand("glow").setExecutor(new GlowCommand());
        this.getCommand("hologram").setExecutor(new HologramCommand());
        this.getCommand("limb").setExecutor(new LimbCommand());
        this.getCommand("modsettings").setExecutor(new ModSettingsCommand());
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

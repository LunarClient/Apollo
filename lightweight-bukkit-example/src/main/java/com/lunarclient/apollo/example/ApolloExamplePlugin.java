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

import com.lunarclient.apollo.example.commands.SwitchCommand;
import com.lunarclient.apollo.example.commands.feature.BeamCommand;
import com.lunarclient.apollo.example.commands.feature.BorderCommand;
import com.lunarclient.apollo.example.commands.feature.ChatCommand;
import com.lunarclient.apollo.example.commands.feature.ColoredFireCommand;
import com.lunarclient.apollo.example.commands.feature.CombatCommand;
import com.lunarclient.apollo.example.commands.feature.CooldownCommand;
import com.lunarclient.apollo.example.commands.feature.EntityCommand;
import com.lunarclient.apollo.example.commands.feature.GlowCommand;
import com.lunarclient.apollo.example.commands.feature.HologramCommand;
import com.lunarclient.apollo.example.commands.feature.LimbCommand;
import com.lunarclient.apollo.example.commands.feature.ModSettingsCommand;
import com.lunarclient.apollo.example.commands.feature.NametagCommand;
import com.lunarclient.apollo.example.commands.feature.NickHiderCommand;
import com.lunarclient.apollo.example.commands.feature.NotificationCommand;
import com.lunarclient.apollo.example.commands.feature.RichPresenceCommand;
import com.lunarclient.apollo.example.commands.feature.ServerRuleCommand;
import com.lunarclient.apollo.example.commands.feature.StaffModCommand;
import com.lunarclient.apollo.example.commands.feature.StopwatchCommand;
import com.lunarclient.apollo.example.commands.feature.TeamCommand;
import com.lunarclient.apollo.example.commands.feature.TebexCommand;
import com.lunarclient.apollo.example.commands.feature.TitleCommand;
import com.lunarclient.apollo.example.commands.feature.TntCountdownCommand;
import com.lunarclient.apollo.example.commands.feature.TransferCommand;
import com.lunarclient.apollo.example.commands.feature.VignetteCommand;
import com.lunarclient.apollo.example.commands.feature.WaypointCommand;
import com.lunarclient.apollo.example.listeners.json.ApolloPlayerJsonListener;
import com.lunarclient.apollo.example.listeners.proto.ApolloPlayerProtoListener;
import com.lunarclient.apollo.example.modules.ApolloExampleType;
import com.lunarclient.apollo.example.modules.impl.BeamExample;
import com.lunarclient.apollo.example.modules.impl.BorderExample;
import com.lunarclient.apollo.example.modules.impl.ChatExample;
import com.lunarclient.apollo.example.modules.impl.ColoredFireExample;
import com.lunarclient.apollo.example.modules.impl.CombatExample;
import com.lunarclient.apollo.example.modules.impl.CooldownExample;
import com.lunarclient.apollo.example.modules.impl.EntityExample;
import com.lunarclient.apollo.example.modules.impl.GlowExample;
import com.lunarclient.apollo.example.modules.impl.HologramExample;
import com.lunarclient.apollo.example.modules.impl.LimbExample;
import com.lunarclient.apollo.example.modules.impl.ModSettingsExample;
import com.lunarclient.apollo.example.modules.impl.NametagExample;
import com.lunarclient.apollo.example.modules.impl.NickHiderExample;
import com.lunarclient.apollo.example.modules.impl.NotificationExample;
import com.lunarclient.apollo.example.modules.impl.RichPresenceExample;
import com.lunarclient.apollo.example.modules.impl.ServerRuleExample;
import com.lunarclient.apollo.example.modules.impl.StaffModExample;
import com.lunarclient.apollo.example.modules.impl.StopwatchExample;
import com.lunarclient.apollo.example.modules.impl.TeamExample;
import com.lunarclient.apollo.example.modules.impl.TebexExample;
import com.lunarclient.apollo.example.modules.impl.TitleExample;
import com.lunarclient.apollo.example.modules.impl.TntCountdownExample;
import com.lunarclient.apollo.example.modules.impl.TransferExample;
import com.lunarclient.apollo.example.modules.impl.VignetteExample;
import com.lunarclient.apollo.example.modules.impl.WaypointExample;
import com.lunarclient.apollo.example.modules.impl.json.BeamJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.BorderJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.ChatJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.ColoredFireJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.CombatJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.CooldownJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.EntityJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.GlowJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.HologramJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.LimbJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.ModSettingsJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.NametagJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.NickHiderJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.NotificationJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.RichPresenceJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.ServerRuleJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.StaffModJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.StopwatchJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.TeamJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.TebexJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.TitleJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.TntCountdownJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.TransferJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.VignetteJsonExample;
import com.lunarclient.apollo.example.modules.impl.json.WaypointJsonExample;
import com.lunarclient.apollo.example.modules.impl.proto.BeamProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.BorderProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.ChatProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.ColoredFireProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.CombatProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.CooldownProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.EntityProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.GlowProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.HologramProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.LimbProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.ModSettingsProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.NametagProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.NickHiderProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.NotificationProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.RichPresenceProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.ServerRuleProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.StaffModProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.StopwatchProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.TeamProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.TebexProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.TitleProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.TntCountdownProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.TransferProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.VignetteProtoExample;
import com.lunarclient.apollo.example.modules.impl.proto.WaypointProtoExample;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class ApolloExamplePlugin extends JavaPlugin {

    // TODO:
    // Available module options

    @Getter
    private static ApolloExamplePlugin plugin;

    public static ApolloExampleType TYPE;

    private ApolloPlayerProtoListener playerProtoListener;
    private ApolloPlayerJsonListener playerJsonListener;

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
    private NickHiderExample nickHiderExample;
    private NotificationExample notificationExample;
    private RichPresenceExample richPresenceExample;
    private ServerRuleExample serverRuleExample;
    private StaffModExample staffModExample;
    private StopwatchExample stopwatchExample;
    private TeamExample teamExample;
    private TebexExample tebexExample;
    private TitleExample titleExample;
    private TntCountdownExample tntCountdownExample;
    private TransferExample transferExample;
    private VignetteExample vignetteExample;
    private WaypointExample waypointExample;

    @Override
    public void onEnable() {
        plugin = this;

        this.changeImplementationType(ApolloExampleType.JSON);
        this.registerCommands();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        this.getCommand("switch").setExecutor(new SwitchCommand());

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
        this.getCommand("nickhider").setExecutor(new NickHiderCommand());
        this.getCommand("notification").setExecutor(new NotificationCommand());
        this.getCommand("richpresence").setExecutor(new RichPresenceCommand());
        this.getCommand("serverrule").setExecutor(new ServerRuleCommand());
        this.getCommand("staffmod").setExecutor(new StaffModCommand());
        this.getCommand("stopwatch").setExecutor(new StopwatchCommand());
        this.getCommand("team").setExecutor(new TeamCommand());
        this.getCommand("tebex").setExecutor(new TebexCommand());
        this.getCommand("title").setExecutor(new TitleCommand());
        this.getCommand("tntcountdown").setExecutor(new TntCountdownCommand());
        this.getCommand("transfer").setExecutor(new TransferCommand());
        this.getCommand("vignette").setExecutor(new VignetteCommand());
        this.getCommand("waypoint").setExecutor(new WaypointCommand());
    }

    public void changeImplementationType(ApolloExampleType type) {
        TYPE = type;

        if (this.playerProtoListener != null) {
            this.playerProtoListener.disable();
            this.playerProtoListener = null;
        }

        if (this.playerJsonListener != null) {
            this.playerJsonListener.disable();
            this.playerJsonListener = null;
        }

        this.registerModuleExamples();
        this.registerListeners();
    }

    private void registerModuleExamples() {
        switch (TYPE) {
            case API: {
                break;
            }

            case JSON: {
                this.beamExample = new BeamJsonExample();
                this.borderExample = new BorderJsonExample();
                this.chatExample = new ChatJsonExample();
                this.coloredFireExample = new ColoredFireJsonExample();
                this.combatExample = new CombatJsonExample();
                this.cooldownExample = new CooldownJsonExample();
                this.entityExample = new EntityJsonExample();
                this.glowExample = new GlowJsonExample();
                this.hologramExample = new HologramJsonExample();
                this.limbExample = new LimbJsonExample();
                this.modSettingsExample = new ModSettingsJsonExample();
                this.nametagExample = new NametagJsonExample();
                this.nickHiderExample = new NickHiderJsonExample();
                this.notificationExample = new NotificationJsonExample();
                this.richPresenceExample = new RichPresenceJsonExample();
                this.serverRuleExample = new ServerRuleJsonExample();
                this.staffModExample = new StaffModJsonExample();
                this.stopwatchExample = new StopwatchJsonExample();
                this.teamExample = new TeamJsonExample();
                this.tebexExample = new TebexJsonExample();
                this.titleExample = new TitleJsonExample();
                this.tntCountdownExample = new TntCountdownJsonExample();
                this.transferExample = new TransferJsonExample();
                this.vignetteExample = new VignetteJsonExample();
                this.waypointExample = new WaypointJsonExample();
                break;
            }

            case PROTO: {
                this.beamExample = new BeamProtoExample();
                this.borderExample = new BorderProtoExample();
                this.chatExample = new ChatProtoExample();
                this.coloredFireExample = new ColoredFireProtoExample();
                this.combatExample = new CombatProtoExample();
                this.cooldownExample = new CooldownProtoExample();
                this.entityExample = new EntityProtoExample();
                this.glowExample = new GlowProtoExample();
                this.hologramExample = new HologramProtoExample();
                this.limbExample = new LimbProtoExample();
                this.modSettingsExample = new ModSettingsProtoExample();
                this.nametagExample = new NametagProtoExample();
                this.nickHiderExample = new NickHiderProtoExample();
                this.notificationExample = new NotificationProtoExample();
                this.richPresenceExample = new RichPresenceProtoExample();
                this.serverRuleExample = new ServerRuleProtoExample();
                this.staffModExample = new StaffModProtoExample();
                this.stopwatchExample = new StopwatchProtoExample();
                this.teamExample = new TeamProtoExample();
                this.tebexExample = new TebexProtoExample();
                this.titleExample = new TitleProtoExample();
                this.tntCountdownExample = new TntCountdownProtoExample();
                this.transferExample = new TransferProtoExample();
                this.vignetteExample = new VignetteProtoExample();
                this.waypointExample = new WaypointProtoExample();
                break;
            }
        }
    }

    private void registerListeners() {
        switch (TYPE) {
            case API: {
                break;
            }

            case JSON: {
                this.playerJsonListener = new ApolloPlayerJsonListener(this);
                break;
            }

            case PROTO: {
                this.playerProtoListener = new ApolloPlayerProtoListener(this);
                break;
            }
        }
    }

}

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

import com.lunarclient.apollo.example.api.examples.BeamApiExample;
import com.lunarclient.apollo.example.api.examples.BorderApiExample;
import com.lunarclient.apollo.example.api.examples.ChatApiExample;
import com.lunarclient.apollo.example.api.examples.ColoredFireApiExample;
import com.lunarclient.apollo.example.api.examples.CombatApiExample;
import com.lunarclient.apollo.example.api.examples.CooldownApiExample;
import com.lunarclient.apollo.example.api.examples.EntityApiExample;
import com.lunarclient.apollo.example.api.examples.GlowApiExample;
import com.lunarclient.apollo.example.api.examples.HologramApiExample;
import com.lunarclient.apollo.example.api.examples.LimbApiExample;
import com.lunarclient.apollo.example.api.examples.ModSettingsApiExample;
import com.lunarclient.apollo.example.api.examples.NametagApiExample;
import com.lunarclient.apollo.example.api.examples.NickHiderApiExample;
import com.lunarclient.apollo.example.api.examples.NotificationApiExample;
import com.lunarclient.apollo.example.api.examples.RichPresenceApiExample;
import com.lunarclient.apollo.example.api.examples.ServerRuleApiExample;
import com.lunarclient.apollo.example.api.examples.StaffModApiExample;
import com.lunarclient.apollo.example.api.examples.StopwatchApiExample;
import com.lunarclient.apollo.example.api.examples.TeamApiExample;
import com.lunarclient.apollo.example.api.examples.TebexApiExample;
import com.lunarclient.apollo.example.api.examples.TitleApiExample;
import com.lunarclient.apollo.example.api.examples.TntCountdownApiExample;
import com.lunarclient.apollo.example.api.examples.TransferApiExample;
import com.lunarclient.apollo.example.api.examples.VignetteApiExample;
import com.lunarclient.apollo.example.api.examples.WaypointApiExample;
import com.lunarclient.apollo.example.api.listeners.ApolloPlayerApiListener;
import com.lunarclient.apollo.example.common.commands.SwitchCommand;
import com.lunarclient.apollo.example.common.commands.debug.ApolloDebugCommand;
import com.lunarclient.apollo.example.common.commands.module.BeamCommand;
import com.lunarclient.apollo.example.common.commands.module.BorderCommand;
import com.lunarclient.apollo.example.common.commands.module.ChatCommand;
import com.lunarclient.apollo.example.common.commands.module.ColoredFireCommand;
import com.lunarclient.apollo.example.common.commands.module.CombatCommand;
import com.lunarclient.apollo.example.common.commands.module.CooldownCommand;
import com.lunarclient.apollo.example.common.commands.module.EntityCommand;
import com.lunarclient.apollo.example.common.commands.module.GlintCommand;
import com.lunarclient.apollo.example.common.commands.module.GlowCommand;
import com.lunarclient.apollo.example.common.commands.module.HologramCommand;
import com.lunarclient.apollo.example.common.commands.module.InventoryCommand;
import com.lunarclient.apollo.example.common.commands.module.LimbCommand;
import com.lunarclient.apollo.example.common.commands.module.ModSettingsCommand;
import com.lunarclient.apollo.example.common.commands.module.NametagCommand;
import com.lunarclient.apollo.example.common.commands.module.NickHiderCommand;
import com.lunarclient.apollo.example.common.commands.module.NotificationCommand;
import com.lunarclient.apollo.example.common.commands.module.RichPresenceCommand;
import com.lunarclient.apollo.example.common.commands.module.SaturationCommand;
import com.lunarclient.apollo.example.common.commands.module.ServerRuleCommand;
import com.lunarclient.apollo.example.common.commands.module.StaffModCommand;
import com.lunarclient.apollo.example.common.commands.module.StopwatchCommand;
import com.lunarclient.apollo.example.common.commands.module.TeamCommand;
import com.lunarclient.apollo.example.common.commands.module.TebexCommand;
import com.lunarclient.apollo.example.common.commands.module.TitleCommand;
import com.lunarclient.apollo.example.common.commands.module.TntCountdownCommand;
import com.lunarclient.apollo.example.common.commands.module.TransferCommand;
import com.lunarclient.apollo.example.common.commands.module.VignetteCommand;
import com.lunarclient.apollo.example.common.commands.module.WaypointCommand;
import com.lunarclient.apollo.example.common.modules.ApolloExampleType;
import com.lunarclient.apollo.example.common.modules.impl.BeamExample;
import com.lunarclient.apollo.example.common.modules.impl.BorderExample;
import com.lunarclient.apollo.example.common.modules.impl.ChatExample;
import com.lunarclient.apollo.example.common.modules.impl.ColoredFireExample;
import com.lunarclient.apollo.example.common.modules.impl.CombatExample;
import com.lunarclient.apollo.example.common.modules.impl.CooldownExample;
import com.lunarclient.apollo.example.common.modules.impl.EntityExample;
import com.lunarclient.apollo.example.common.modules.impl.GlintExample;
import com.lunarclient.apollo.example.common.modules.impl.GlowExample;
import com.lunarclient.apollo.example.common.modules.impl.HologramExample;
import com.lunarclient.apollo.example.common.modules.impl.InventoryExample;
import com.lunarclient.apollo.example.common.modules.impl.LimbExample;
import com.lunarclient.apollo.example.common.modules.impl.ModSettingsExample;
import com.lunarclient.apollo.example.common.modules.impl.NametagExample;
import com.lunarclient.apollo.example.common.modules.impl.NickHiderExample;
import com.lunarclient.apollo.example.common.modules.impl.NotificationExample;
import com.lunarclient.apollo.example.common.modules.impl.RichPresenceExample;
import com.lunarclient.apollo.example.common.modules.impl.SaturationExample;
import com.lunarclient.apollo.example.common.modules.impl.ServerRuleExample;
import com.lunarclient.apollo.example.common.modules.impl.StaffModExample;
import com.lunarclient.apollo.example.common.modules.impl.StopwatchExample;
import com.lunarclient.apollo.example.common.modules.impl.TeamExample;
import com.lunarclient.apollo.example.common.modules.impl.TebexExample;
import com.lunarclient.apollo.example.common.modules.impl.TitleExample;
import com.lunarclient.apollo.example.common.modules.impl.TntCountdownExample;
import com.lunarclient.apollo.example.common.modules.impl.TransferExample;
import com.lunarclient.apollo.example.common.modules.impl.VignetteExample;
import com.lunarclient.apollo.example.common.modules.impl.WaypointExample;
import com.lunarclient.apollo.example.debug.SpamPacketDebug;
import com.lunarclient.apollo.example.json.examples.BeamJsonExample;
import com.lunarclient.apollo.example.json.examples.BorderJsonExample;
import com.lunarclient.apollo.example.json.examples.ChatJsonExample;
import com.lunarclient.apollo.example.json.examples.ColoredFireJsonExample;
import com.lunarclient.apollo.example.json.examples.CombatJsonExample;
import com.lunarclient.apollo.example.json.examples.CooldownJsonExample;
import com.lunarclient.apollo.example.json.examples.EntityJsonExample;
import com.lunarclient.apollo.example.json.examples.GlowJsonExample;
import com.lunarclient.apollo.example.json.examples.HologramJsonExample;
import com.lunarclient.apollo.example.json.examples.LimbJsonExample;
import com.lunarclient.apollo.example.json.examples.ModSettingsJsonExample;
import com.lunarclient.apollo.example.json.examples.NametagJsonExample;
import com.lunarclient.apollo.example.json.examples.NickHiderJsonExample;
import com.lunarclient.apollo.example.json.examples.NotificationJsonExample;
import com.lunarclient.apollo.example.json.examples.RichPresenceJsonExample;
import com.lunarclient.apollo.example.json.examples.ServerRuleJsonExample;
import com.lunarclient.apollo.example.json.examples.StaffModJsonExample;
import com.lunarclient.apollo.example.json.examples.StopwatchJsonExample;
import com.lunarclient.apollo.example.json.examples.TeamJsonExample;
import com.lunarclient.apollo.example.json.examples.TebexJsonExample;
import com.lunarclient.apollo.example.json.examples.TitleJsonExample;
import com.lunarclient.apollo.example.json.examples.TntCountdownJsonExample;
import com.lunarclient.apollo.example.json.examples.TransferJsonExample;
import com.lunarclient.apollo.example.json.examples.VignetteJsonExample;
import com.lunarclient.apollo.example.json.examples.WaypointJsonExample;
import com.lunarclient.apollo.example.json.listeners.ApolloPlayerJsonListener;
import com.lunarclient.apollo.example.proto.examples.BeamProtoExample;
import com.lunarclient.apollo.example.proto.examples.BorderProtoExample;
import com.lunarclient.apollo.example.proto.examples.ChatProtoExample;
import com.lunarclient.apollo.example.proto.examples.ColoredFireProtoExample;
import com.lunarclient.apollo.example.proto.examples.CombatProtoExample;
import com.lunarclient.apollo.example.proto.examples.CooldownProtoExample;
import com.lunarclient.apollo.example.proto.examples.EntityProtoExample;
import com.lunarclient.apollo.example.proto.examples.GlowProtoExample;
import com.lunarclient.apollo.example.proto.examples.HologramProtoExample;
import com.lunarclient.apollo.example.proto.examples.LimbProtoExample;
import com.lunarclient.apollo.example.proto.examples.ModSettingsProtoExample;
import com.lunarclient.apollo.example.proto.examples.NametagProtoExample;
import com.lunarclient.apollo.example.proto.examples.NickHiderProtoExample;
import com.lunarclient.apollo.example.proto.examples.NotificationProtoExample;
import com.lunarclient.apollo.example.proto.examples.RichPresenceProtoExample;
import com.lunarclient.apollo.example.proto.examples.ServerRuleProtoExample;
import com.lunarclient.apollo.example.proto.examples.StaffModProtoExample;
import com.lunarclient.apollo.example.proto.examples.StopwatchProtoExample;
import com.lunarclient.apollo.example.proto.examples.TeamProtoExample;
import com.lunarclient.apollo.example.proto.examples.TebexProtoExample;
import com.lunarclient.apollo.example.proto.examples.TitleProtoExample;
import com.lunarclient.apollo.example.proto.examples.TntCountdownProtoExample;
import com.lunarclient.apollo.example.proto.examples.TransferProtoExample;
import com.lunarclient.apollo.example.proto.examples.VignetteProtoExample;
import com.lunarclient.apollo.example.proto.examples.WaypointProtoExample;
import com.lunarclient.apollo.example.proto.listeners.ApolloPlayerProtoListener;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class ApolloExamplePlugin extends JavaPlugin {

    @Getter
    private static ApolloExamplePlugin plugin;

    public static ApolloExampleType TYPE;

    private ApolloPlayerApiListener playerApiListener;
    private ApolloPlayerProtoListener playerProtoListener;
    private ApolloPlayerJsonListener playerJsonListener;

    private BeamExample beamExample;
    private BorderExample borderExample;
    private ChatExample chatExample;
    private ColoredFireExample coloredFireExample;
    private CombatExample combatExample;
    private CooldownExample cooldownExample;
    private EntityExample entityExample;
    private GlintExample glintExample;
    private GlowExample glowExample;
    private HologramExample hologramExample;
    private InventoryExample inventoryExample;
    private LimbExample limbExample;
    private ModSettingsExample modSettingsExample;
    private NametagExample nametagExample;
    private NickHiderExample nickHiderExample;
    private NotificationExample notificationExample;
    private RichPresenceExample richPresenceExample;
    private SaturationExample saturationExample;
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

    private SpamPacketDebug spamPacketDebug;

    @Override
    public void onEnable() {
        plugin = this;

        this.changeImplementationType(ApolloExampleType.API);
        this.registerCommands();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        this.getCommand("apollodebug").setExecutor(new ApolloDebugCommand());
        this.getCommand("switch").setExecutor(new SwitchCommand());

        this.getCommand("beam").setExecutor(new BeamCommand());
        this.getCommand("border").setExecutor(new BorderCommand());
        this.getCommand("chat").setExecutor(new ChatCommand());
        this.getCommand("coloredfire").setExecutor(new ColoredFireCommand());
        this.getCommand("combat").setExecutor(new CombatCommand());
        this.getCommand("cooldown").setExecutor(new CooldownCommand());
        this.getCommand("entity").setExecutor(new EntityCommand());
        this.getCommand("glint").setExecutor(new GlintCommand());
        this.getCommand("glow").setExecutor(new GlowCommand());
        this.getCommand("hologram").setExecutor(new HologramCommand());
        this.getCommand("inventory").setExecutor(new InventoryCommand());
        this.getCommand("limb").setExecutor(new LimbCommand());
        this.getCommand("modsettings").setExecutor(new ModSettingsCommand());
        this.getCommand("nametag").setExecutor(new NametagCommand());
        this.getCommand("nickhider").setExecutor(new NickHiderCommand());
        this.getCommand("notification").setExecutor(new NotificationCommand());
        this.getCommand("richpresence").setExecutor(new RichPresenceCommand());
        this.getCommand("saturation").setExecutor(new SaturationCommand());
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

        if (this.playerApiListener != null) {
            this.playerApiListener.disable();
            this.playerApiListener = null;
        }

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
        this.spamPacketDebug = new SpamPacketDebug();

        this.glintExample = new GlintExample();
        this.inventoryExample = new InventoryExample();
        this.saturationExample = new SaturationExample();

        switch (TYPE) {
            case API: {
                this.beamExample = new BeamApiExample();
                this.borderExample = new BorderApiExample();
                this.chatExample = new ChatApiExample();
                this.coloredFireExample = new ColoredFireApiExample();
                this.combatExample = new CombatApiExample();
                this.cooldownExample = new CooldownApiExample();
                this.entityExample = new EntityApiExample();
                this.glowExample = new GlowApiExample();
                this.hologramExample = new HologramApiExample();
                this.limbExample = new LimbApiExample();
                this.modSettingsExample = new ModSettingsApiExample();
                this.nametagExample = new NametagApiExample();
                this.nickHiderExample = new NickHiderApiExample();
                this.notificationExample = new NotificationApiExample();
                this.richPresenceExample = new RichPresenceApiExample();
                this.serverRuleExample = new ServerRuleApiExample();
                this.staffModExample = new StaffModApiExample();
                this.stopwatchExample = new StopwatchApiExample();
                this.teamExample = new TeamApiExample();
                this.tebexExample = new TebexApiExample();
                this.titleExample = new TitleApiExample();
                this.tntCountdownExample = new TntCountdownApiExample();
                this.transferExample = new TransferApiExample();
                this.vignetteExample = new VignetteApiExample();
                this.waypointExample = new WaypointApiExample();
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
                this.playerApiListener = new ApolloPlayerApiListener(this);
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

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

import com.lunarclient.apollo.example.listeners.ApolloPlayerListener;
import com.lunarclient.apollo.example.modules.json.BeamJsonExample;
import com.lunarclient.apollo.example.modules.json.BorderJsonExample;
import com.lunarclient.apollo.example.modules.json.ChatJsonExample;
import com.lunarclient.apollo.example.modules.json.ColoredFireJsonExample;
import com.lunarclient.apollo.example.modules.json.CombatJsonExample;
import com.lunarclient.apollo.example.modules.json.CooldownJsonExample;
import com.lunarclient.apollo.example.modules.json.EntityJsonExample;
import com.lunarclient.apollo.example.modules.json.GlowJsonExample;
import com.lunarclient.apollo.example.modules.json.HologramJsonExample;
import com.lunarclient.apollo.example.modules.json.LimbJsonExample;
import com.lunarclient.apollo.example.modules.json.ModSettingsJsonExample;
import com.lunarclient.apollo.example.modules.json.NickHiderJsonExample;
import com.lunarclient.apollo.example.modules.json.NotificationJsonExample;
import com.lunarclient.apollo.example.modules.json.RichPresenceJsonExample;
import com.lunarclient.apollo.example.modules.json.ServerRuleJsonExample;
import com.lunarclient.apollo.example.modules.json.StaffModJsonExample;
import com.lunarclient.apollo.example.modules.json.StopwatchJsonExample;
import com.lunarclient.apollo.example.modules.json.TeamJsonExample;
import com.lunarclient.apollo.example.modules.json.TitleJsonExample;
import com.lunarclient.apollo.example.modules.json.TntCountdownJsonExample;
import com.lunarclient.apollo.example.modules.json.TransferJsonExample;
import com.lunarclient.apollo.example.modules.json.VignetteJsonExample;
import com.lunarclient.apollo.example.modules.json.WaypointJsonExample;
import com.lunarclient.apollo.example.modules.proto.BeamProtoExample;
import com.lunarclient.apollo.example.modules.proto.BorderProtoExample;
import com.lunarclient.apollo.example.modules.proto.ChatProtoExample;
import com.lunarclient.apollo.example.modules.proto.ColoredFireProtoExample;
import com.lunarclient.apollo.example.modules.proto.CombatProtoExample;
import com.lunarclient.apollo.example.modules.proto.CooldownProtoExample;
import com.lunarclient.apollo.example.modules.proto.EntityProtoExample;
import com.lunarclient.apollo.example.modules.proto.GlowProtoExample;
import com.lunarclient.apollo.example.modules.proto.HologramProtoExample;
import com.lunarclient.apollo.example.modules.proto.LimbProtoExample;
import com.lunarclient.apollo.example.modules.proto.ModSettingsProtoExample;
import com.lunarclient.apollo.example.modules.proto.NickHiderProtoExample;
import com.lunarclient.apollo.example.modules.proto.NotificationProtoExample;
import com.lunarclient.apollo.example.modules.proto.RichPresenceProtoExample;
import com.lunarclient.apollo.example.modules.proto.ServerRuleProtoExample;
import com.lunarclient.apollo.example.modules.proto.StaffModProtoExample;
import com.lunarclient.apollo.example.modules.proto.StopwatchProtoExample;
import com.lunarclient.apollo.example.modules.proto.TeamProtoExample;
import com.lunarclient.apollo.example.modules.proto.TitleProtoExample;
import com.lunarclient.apollo.example.modules.proto.TntCountdownProtoExample;
import com.lunarclient.apollo.example.modules.proto.TransferProtoExample;
import com.lunarclient.apollo.example.modules.proto.VignetteProtoExample;
import com.lunarclient.apollo.example.modules.proto.WaypointProtoExample;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class ApolloExamplePlugin extends JavaPlugin {

    // TODO:
    // snake / camel
    // Finish utils & merge utils
    // Packet enrichment

    @Getter
    private static ApolloExamplePlugin plugin;

    private BeamJsonExample beamJsonExample;
    private BorderJsonExample borderJsonExample;
    private ChatJsonExample chatJsonExample;
    private ColoredFireJsonExample coloredFireJsonExample;
    private CombatJsonExample combatJsonExample;
    private CooldownJsonExample cooldownJsonExample;
    private EntityJsonExample entityJsonExample;
    private GlowJsonExample glowJsonExample;
    private HologramJsonExample hologramJsonExample;
    private LimbJsonExample limbJsonExample;
    private ModSettingsJsonExample modSettingsJsonExample;
    private NickHiderJsonExample nickHiderJsonExample;
    private NotificationJsonExample notificationJsonExample;
    private RichPresenceJsonExample richPresenceJsonExample;
    private ServerRuleJsonExample serverRuleJsonExample;
    private StaffModJsonExample staffModJsonExample;
    private StopwatchJsonExample stopwatchJsonExample;
    private TeamJsonExample teamJsonExample;
    private TitleJsonExample titleJsonExample;
    private TntCountdownJsonExample tntCountdownJsonExample;
    private TransferJsonExample transferJsonExample;
    private VignetteJsonExample vignetteJsonExample;
    private WaypointJsonExample waypointJsonExample;

    private BeamProtoExample beamProtoExample;
    private BorderProtoExample borderProtoExample;
    private ChatProtoExample chatProtoExample;
    private ColoredFireProtoExample coloredFireProtoExample;
    private CombatProtoExample combatProtoExample;
    private CooldownProtoExample cooldownProtoExample;
    private EntityProtoExample entityProtoExample;
    private GlowProtoExample glowProtoExample;
    private HologramProtoExample hologramProtoExample;
    private LimbProtoExample limbProtoExample;
    private ModSettingsProtoExample modSettingsProtoExample;
    private NickHiderProtoExample nickHiderProtoExample;
    private NotificationProtoExample notificationProtoExample;
    private RichPresenceProtoExample richPresenceProtoExample;
    private ServerRuleProtoExample serverRuleProtoExample;
    private StaffModProtoExample staffModProtoExample;
    private StopwatchProtoExample stopwatchProtoExample;
    private TeamProtoExample teamProtoExample;
    private TitleProtoExample titleProtoExample;
    private TntCountdownProtoExample tntCountdownProtoExample;
    private TransferProtoExample transferProtoExample;
    private VignetteProtoExample vignetteProtoExample;
    private WaypointProtoExample waypointProtoExample;

    @Override
    public void onEnable() {
        plugin = this;

        this.registerModuleExamples();
        this.registerCommands();
        this.registerListeners();
    }

    @Override
    public void onDisable() {

    }

    private void registerModuleExamples() {
        this.beamJsonExample = new BeamJsonExample();
        this.borderJsonExample = new BorderJsonExample();
        this.chatJsonExample = new ChatJsonExample();
        this.coloredFireJsonExample = new ColoredFireJsonExample();
        this.combatJsonExample = new CombatJsonExample();
        this.cooldownJsonExample = new CooldownJsonExample();
        this.entityJsonExample = new EntityJsonExample();
        this.glowJsonExample = new GlowJsonExample();
        this.hologramJsonExample = new HologramJsonExample();
        this.limbJsonExample = new LimbJsonExample();
        this.modSettingsJsonExample = new ModSettingsJsonExample();
        this.nickHiderJsonExample = new NickHiderJsonExample();
        this.notificationJsonExample = new NotificationJsonExample();
        this.richPresenceJsonExample = new RichPresenceJsonExample();
        this.serverRuleJsonExample = new ServerRuleJsonExample();
        this.staffModJsonExample = new StaffModJsonExample();
        this.stopwatchJsonExample = new StopwatchJsonExample();
        this.teamJsonExample = new TeamJsonExample();
        this.titleJsonExample = new TitleJsonExample();
        this.tntCountdownJsonExample = new TntCountdownJsonExample();
        this.transferJsonExample = new TransferJsonExample();
        this.vignetteJsonExample = new VignetteJsonExample();
        this.waypointJsonExample = new WaypointJsonExample();

        this.beamProtoExample = new BeamProtoExample();
        this.borderProtoExample = new BorderProtoExample();
        this.chatProtoExample = new ChatProtoExample();
        this.coloredFireProtoExample = new ColoredFireProtoExample();
        this.combatProtoExample = new CombatProtoExample();
        this.cooldownProtoExample = new CooldownProtoExample();
        this.entityProtoExample = new EntityProtoExample();
        this.glowProtoExample = new GlowProtoExample();
        this.hologramProtoExample = new HologramProtoExample();
        this.limbProtoExample = new LimbProtoExample();
        this.modSettingsProtoExample = new ModSettingsProtoExample();
        this.nickHiderProtoExample = new NickHiderProtoExample();
        this.notificationProtoExample = new NotificationProtoExample();
        this.richPresenceProtoExample = new RichPresenceProtoExample();
        this.serverRuleProtoExample = new ServerRuleProtoExample();
        this.staffModProtoExample = new StaffModProtoExample();
        this.stopwatchProtoExample = new StopwatchProtoExample();
        this.teamProtoExample = new TeamProtoExample();
        this.titleProtoExample = new TitleProtoExample();
        this.tntCountdownProtoExample = new TntCountdownProtoExample();
        this.transferProtoExample = new TransferProtoExample();
        this.vignetteProtoExample = new VignetteProtoExample();
        this.waypointProtoExample = new WaypointProtoExample();
    }

    private void registerCommands() {

    }

    private void registerListeners() {
        new ApolloPlayerListener(this);
    }

}

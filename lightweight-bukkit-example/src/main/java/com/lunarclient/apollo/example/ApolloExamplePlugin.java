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

import com.lunarclient.apollo.example.listeners.PlayerDetectionListener;
import com.lunarclient.apollo.example.modules.json.BeamJsonExample;
import com.lunarclient.apollo.example.modules.json.ChatJsonExample;
import com.lunarclient.apollo.example.modules.json.ColoredFireJsonExample;
import com.lunarclient.apollo.example.modules.json.CombatJsonExample;
import com.lunarclient.apollo.example.modules.json.CooldownJsonExample;
import com.lunarclient.apollo.example.modules.json.EntityJsonExample;
import com.lunarclient.apollo.example.modules.json.GlowJsonExample;
import com.lunarclient.apollo.example.modules.json.HologramJsonExample;
import com.lunarclient.apollo.example.modules.json.NickHiderJsonExample;
import com.lunarclient.apollo.example.modules.json.StopwatchJsonExample;
import com.lunarclient.apollo.example.modules.json.TransferJsonExample;
import com.lunarclient.apollo.example.modules.json.VignetteJsonExample;
import com.lunarclient.apollo.example.modules.json.WaypointJsonExample;
import com.lunarclient.apollo.example.modules.proto.BeamProtoExample;
import com.lunarclient.apollo.example.modules.proto.ChatProtoExample;
import com.lunarclient.apollo.example.modules.proto.ColoredFireProtoExample;
import com.lunarclient.apollo.example.modules.proto.CombatProtoExample;
import com.lunarclient.apollo.example.modules.proto.CooldownProtoExample;
import com.lunarclient.apollo.example.modules.proto.EntityProtoExample;
import com.lunarclient.apollo.example.modules.proto.GlowProtoExample;
import com.lunarclient.apollo.example.modules.proto.HologramProtoExample;
import com.lunarclient.apollo.example.modules.proto.NickHiderProtoExample;
import com.lunarclient.apollo.example.modules.proto.StopwatchProtoExample;
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

    @Getter
    private static ApolloExamplePlugin plugin;

    private BeamJsonExample beamJsonExample;
    private ChatJsonExample chatJsonExample;
    private ColoredFireJsonExample coloredFireJsonExample;
    private CombatJsonExample combatJsonExample;
    private CooldownJsonExample cooldownJsonExample;
    private EntityJsonExample entityJsonExample;
    private GlowJsonExample glowJsonExample;
    private HologramJsonExample hologramJsonExample;
    private NickHiderJsonExample nickHiderJsonExample;
    private StopwatchJsonExample stopwatchJsonExample;
    private TransferJsonExample transferJsonExample;
    private VignetteJsonExample vignetteJsonExample;
    private WaypointJsonExample waypointJsonExample;

    private BeamProtoExample beamProtoExample;
    private ChatProtoExample chatProtoExample;
    private ColoredFireProtoExample coloredFireProtoExample;
    private CombatProtoExample combatProtoExample;
    private CooldownProtoExample cooldownProtoExample;
    private EntityProtoExample entityProtoExample;
    private GlowProtoExample glowProtoExample;
    private HologramProtoExample hologramProtoExample;
    private NickHiderProtoExample nickHiderProtoExample;
    private StopwatchProtoExample stopwatchProtoExample;
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
        this.chatJsonExample = new ChatJsonExample();
        this.coloredFireJsonExample = new ColoredFireJsonExample();
        this.combatJsonExample = new CombatJsonExample();
        this.cooldownJsonExample = new CooldownJsonExample();
        this.entityJsonExample = new EntityJsonExample();
        this.glowJsonExample = new GlowJsonExample();
        this.hologramJsonExample = new HologramJsonExample();
        this.nickHiderJsonExample = new NickHiderJsonExample();
        this.stopwatchJsonExample = new StopwatchJsonExample();
        this.transferJsonExample = new TransferJsonExample();
        this.vignetteJsonExample = new VignetteJsonExample();
        this.waypointJsonExample = new WaypointJsonExample();

        this.beamProtoExample = new BeamProtoExample();
        this.chatProtoExample = new ChatProtoExample();
        this.coloredFireProtoExample = new ColoredFireProtoExample();
        this.combatProtoExample = new CombatProtoExample();
        this.cooldownProtoExample = new CooldownProtoExample();
        this.entityProtoExample = new EntityProtoExample();
        this.glowProtoExample = new GlowProtoExample();
        this.hologramProtoExample = new HologramProtoExample();
        this.nickHiderProtoExample = new NickHiderProtoExample();
        this.stopwatchProtoExample = new StopwatchProtoExample();
        this.transferProtoExample = new TransferProtoExample();
        this.vignetteProtoExample = new VignetteProtoExample();
        this.waypointProtoExample = new WaypointProtoExample();
    }

    private void registerCommands() {

    }

    private void registerListeners() {
        new PlayerDetectionListener(this);
    }

}

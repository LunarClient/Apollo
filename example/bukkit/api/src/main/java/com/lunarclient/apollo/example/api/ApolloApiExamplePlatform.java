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
package com.lunarclient.apollo.example.api;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.ApolloExampleType;
import com.lunarclient.apollo.example.api.debug.DebugManager;
import com.lunarclient.apollo.example.api.debug.command.ApolloDebugCommand;
import com.lunarclient.apollo.example.api.debug.impl.SpamPacketDebug;
import com.lunarclient.apollo.example.api.listener.ApolloPlayerApiListener;
import com.lunarclient.apollo.example.api.mods.ApolloModStatusExample;
import com.lunarclient.apollo.example.api.mods.ModStatusCommand;
import com.lunarclient.apollo.example.api.module.AutoTextHotkeyApiExample;
import com.lunarclient.apollo.example.api.module.BeamApiExample;
import com.lunarclient.apollo.example.api.module.BorderApiExample;
import com.lunarclient.apollo.example.api.module.ChatApiExample;
import com.lunarclient.apollo.example.api.module.ColoredFireApiExample;
import com.lunarclient.apollo.example.api.module.CombatApiExample;
import com.lunarclient.apollo.example.api.module.CooldownApiExample;
import com.lunarclient.apollo.example.api.module.EntityApiExample;
import com.lunarclient.apollo.example.api.module.GlowApiExample;
import com.lunarclient.apollo.example.api.module.HologramApiExample;
import com.lunarclient.apollo.example.api.module.LimbApiExample;
import com.lunarclient.apollo.example.api.module.ModSettingsApiExample;
import com.lunarclient.apollo.example.api.module.NametagApiExample;
import com.lunarclient.apollo.example.api.module.NickHiderApiExample;
import com.lunarclient.apollo.example.api.module.NotificationApiExample;
import com.lunarclient.apollo.example.api.module.RichPresenceApiExample;
import com.lunarclient.apollo.example.api.module.ServerRuleApiExample;
import com.lunarclient.apollo.example.api.module.StaffModApiExample;
import com.lunarclient.apollo.example.api.module.StopwatchApiExample;
import com.lunarclient.apollo.example.api.module.TeamApiExample;
import com.lunarclient.apollo.example.api.module.TebexApiExample;
import com.lunarclient.apollo.example.api.module.TitleApiExample;
import com.lunarclient.apollo.example.api.module.TntCountdownApiExample;
import com.lunarclient.apollo.example.api.module.TransferApiExample;
import com.lunarclient.apollo.example.api.module.VignetteApiExample;
import com.lunarclient.apollo.example.api.module.WaypointApiExample;
import lombok.Getter;

@Getter
public class ApolloApiExamplePlatform extends ApolloExamplePlugin {

    @Getter
    private static ApolloApiExamplePlatform instance;

    private SpamPacketDebug spamPacketDebug;

    @Override
    public void enable() {
        instance = this;
    }

    @Override
    public void registerCommands() {
        this.getCommand("apollodebug").setExecutor(new ApolloDebugCommand());
        this.getCommand("modstatus").setExecutor(new ModStatusCommand());
    }

    @Override
    public void registerModuleExamples() {
        this.setAutoTextHotkeyExample(new AutoTextHotkeyApiExample());
        this.setBeamExample(new BeamApiExample());
        this.setBorderExample(new BorderApiExample());
        this.setChatExample(new ChatApiExample());
        this.setColoredFireExample(new ColoredFireApiExample());
        this.setCombatExample(new CombatApiExample());
        this.setCooldownExample(new CooldownApiExample());
        this.setEntityExample(new EntityApiExample());
        this.setGlowExample(new GlowApiExample());
        this.setHologramExample(new HologramApiExample());
        this.setLimbExample(new LimbApiExample());
        this.setModSettingsExample(new ModSettingsApiExample());
        this.setNametagExample(new NametagApiExample());
        this.setNickHiderExample(new NickHiderApiExample());
        this.setNotificationExample(new NotificationApiExample());
        this.setRichPresenceExample(new RichPresenceApiExample());
        this.setServerRuleExample(new ServerRuleApiExample());
        this.setStaffModExample(new StaffModApiExample());
        this.setStopwatchExample(new StopwatchApiExample());
        this.setTeamExample(new TeamApiExample());
        this.setTebexExample(new TebexApiExample());
        this.setTitleExample(new TitleApiExample());
        this.setTntCountdownExample(new TntCountdownApiExample());
        this.setTransferExample(new TransferApiExample());
        this.setVignetteExample(new VignetteApiExample());
        this.setWaypointExample(new WaypointApiExample());
    }

    @Override
    public void registerListeners() {
        this.spamPacketDebug = new SpamPacketDebug();

        new DebugManager();
        new ApolloPlayerApiListener(this);
        new ApolloModStatusExample(this);
    }

    @Override
    public ApolloExampleType getType() {
        return ApolloExampleType.API;
    }

}

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
package com.lunarclient.apollo.example.json;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.ApolloExampleType;
import com.lunarclient.apollo.example.json.listener.ApolloPlayerJsonListener;
import com.lunarclient.apollo.example.json.module.AutoTextHotkeyJsonExample;
import com.lunarclient.apollo.example.json.module.BeamJsonExample;
import com.lunarclient.apollo.example.json.module.BorderJsonExample;
import com.lunarclient.apollo.example.json.module.ChatJsonExample;
import com.lunarclient.apollo.example.json.module.ColoredFireJsonExample;
import com.lunarclient.apollo.example.json.module.CombatJsonExample;
import com.lunarclient.apollo.example.json.module.CooldownJsonExample;
import com.lunarclient.apollo.example.json.module.EntityJsonExample;
import com.lunarclient.apollo.example.json.module.GlowJsonExample;
import com.lunarclient.apollo.example.json.module.HologramJsonExample;
import com.lunarclient.apollo.example.json.module.LimbJsonExample;
import com.lunarclient.apollo.example.json.module.ModSettingsJsonExample;
import com.lunarclient.apollo.example.json.module.NametagJsonExample;
import com.lunarclient.apollo.example.json.module.NickHiderJsonExample;
import com.lunarclient.apollo.example.json.module.NotificationJsonExample;
import com.lunarclient.apollo.example.json.module.RichPresenceJsonExample;
import com.lunarclient.apollo.example.json.module.ServerRuleJsonExample;
import com.lunarclient.apollo.example.json.module.StaffModJsonExample;
import com.lunarclient.apollo.example.json.module.StopwatchJsonExample;
import com.lunarclient.apollo.example.json.module.TeamJsonExample;
import com.lunarclient.apollo.example.json.module.TebexJsonExample;
import com.lunarclient.apollo.example.json.module.TitleJsonExample;
import com.lunarclient.apollo.example.json.module.TntCountdownJsonExample;
import com.lunarclient.apollo.example.json.module.TransferJsonExample;
import com.lunarclient.apollo.example.json.module.VignetteJsonExample;
import com.lunarclient.apollo.example.json.module.WaypointJsonExample;
import lombok.Getter;

@Getter
public class ApolloJsonExamplePlatform extends ApolloExamplePlugin {

    @Getter
    private static ApolloJsonExamplePlatform instance;

    @Override
    public void enable() {
        instance = this;
    }

    @Override
    public void registerModuleExamples() {
        this.setAutoTextHotkeyExample(new AutoTextHotkeyJsonExample());
        this.setBeamExample(new BeamJsonExample());
        this.setBorderExample(new BorderJsonExample());
        this.setChatExample(new ChatJsonExample());
        this.setColoredFireExample(new ColoredFireJsonExample());
        this.setCombatExample(new CombatJsonExample());
        this.setCooldownExample(new CooldownJsonExample());
        this.setEntityExample(new EntityJsonExample());
        this.setGlowExample(new GlowJsonExample());
        this.setHologramExample(new HologramJsonExample());
        this.setLimbExample(new LimbJsonExample());
        this.setModSettingsExample(new ModSettingsJsonExample());
        this.setNametagExample(new NametagJsonExample());
        this.setNickHiderExample(new NickHiderJsonExample());
        this.setNotificationExample(new NotificationJsonExample());
        this.setRichPresenceExample(new RichPresenceJsonExample());
        this.setServerRuleExample(new ServerRuleJsonExample());
        this.setStaffModExample(new StaffModJsonExample());
        this.setStopwatchExample(new StopwatchJsonExample());
        this.setTeamExample(new TeamJsonExample());
        this.setTebexExample(new TebexJsonExample());
        this.setTitleExample(new TitleJsonExample());
        this.setTntCountdownExample(new TntCountdownJsonExample());
        this.setTransferExample(new TransferJsonExample());
        this.setVignetteExample(new VignetteJsonExample());
        this.setWaypointExample(new WaypointJsonExample());
    }

    @Override
    public void registerListeners() {
        new ApolloPlayerJsonListener(this);
    }

    @Override
    public ApolloExampleType getType() {
        return ApolloExampleType.JSON;
    }

}

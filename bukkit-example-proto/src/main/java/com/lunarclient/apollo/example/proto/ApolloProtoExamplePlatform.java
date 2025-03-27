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
package com.lunarclient.apollo.example.proto;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.ApolloExampleType;
import com.lunarclient.apollo.example.proto.listener.ApolloPlayerProtoListener;
import com.lunarclient.apollo.example.proto.module.BeamProtoExample;
import com.lunarclient.apollo.example.proto.module.BorderProtoExample;
import com.lunarclient.apollo.example.proto.module.ChatProtoExample;
import com.lunarclient.apollo.example.proto.module.ColoredFireProtoExample;
import com.lunarclient.apollo.example.proto.module.CombatProtoExample;
import com.lunarclient.apollo.example.proto.module.CooldownProtoExample;
import com.lunarclient.apollo.example.proto.module.EntityProtoExample;
import com.lunarclient.apollo.example.proto.module.GlowProtoExample;
import com.lunarclient.apollo.example.proto.module.HologramProtoExample;
import com.lunarclient.apollo.example.proto.module.LimbProtoExample;
import com.lunarclient.apollo.example.proto.module.ModSettingsProtoExample;
import com.lunarclient.apollo.example.proto.module.NametagProtoExample;
import com.lunarclient.apollo.example.proto.module.NickHiderProtoExample;
import com.lunarclient.apollo.example.proto.module.NotificationProtoExample;
import com.lunarclient.apollo.example.proto.module.RichPresenceProtoExample;
import com.lunarclient.apollo.example.proto.module.ServerRuleProtoExample;
import com.lunarclient.apollo.example.proto.module.StaffModProtoExample;
import com.lunarclient.apollo.example.proto.module.StopwatchProtoExample;
import com.lunarclient.apollo.example.proto.module.TeamProtoExample;
import com.lunarclient.apollo.example.proto.module.TebexProtoExample;
import com.lunarclient.apollo.example.proto.module.TitleProtoExample;
import com.lunarclient.apollo.example.proto.module.TntCountdownProtoExample;
import com.lunarclient.apollo.example.proto.module.TransferProtoExample;
import com.lunarclient.apollo.example.proto.module.VignetteProtoExample;
import com.lunarclient.apollo.example.proto.module.WaypointProtoExample;
import lombok.Getter;

@Getter
public class ApolloProtoExamplePlatform extends ApolloExamplePlugin {

    @Getter
    private static ApolloProtoExamplePlatform instance;

    @Override
    public void enable() {
        instance = this;
    }

    @Override
    public void registerModuleExamples() {
        this.setBeamExample(new BeamProtoExample());
        this.setBorderExample(new BorderProtoExample());
        this.setChatExample(new ChatProtoExample());
        this.setColoredFireExample(new ColoredFireProtoExample());
        this.setCombatExample(new CombatProtoExample());
        this.setCooldownExample(new CooldownProtoExample());
        this.setEntityExample(new EntityProtoExample());
        this.setGlowExample(new GlowProtoExample());
        this.setHologramExample(new HologramProtoExample());
        this.setLimbExample(new LimbProtoExample());
        this.setModSettingsExample(new ModSettingsProtoExample());
        this.setNametagExample(new NametagProtoExample());
        this.setNickHiderExample(new NickHiderProtoExample());
        this.setNotificationExample(new NotificationProtoExample());
        this.setRichPresenceExample(new RichPresenceProtoExample());
        this.setServerRuleExample(new ServerRuleProtoExample());
        this.setStaffModExample(new StaffModProtoExample());
        this.setStopwatchExample(new StopwatchProtoExample());
        this.setTeamExample(new TeamProtoExample());
        this.setTebexExample(new TebexProtoExample());
        this.setTitleExample(new TitleProtoExample());
        this.setTntCountdownExample(new TntCountdownProtoExample());
        this.setTransferExample(new TransferProtoExample());
        this.setVignetteExample(new VignetteProtoExample());
        this.setWaypointExample(new WaypointProtoExample());
    }

    @Override
    public void registerListeners() {
        new ApolloPlayerProtoListener(this);
    }

    @Override
    public ApolloExampleType getType() {
        return ApolloExampleType.PROTO;
    }

}

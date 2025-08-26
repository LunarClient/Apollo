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
package com.lunarclient.apollo.example.proto.module;

import com.google.protobuf.Value;
import com.lunarclient.apollo.configurable.v1.ConfigurableSettings;
import com.lunarclient.apollo.example.module.impl.ServerRuleExample;
import com.lunarclient.apollo.example.proto.util.ProtobufPacketUtil;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;

public class ServerRuleProtoExample extends ServerRuleExample {

    @Override
    public void setAntiPortalTraps(boolean value) {
        Map<String, Value> properties = new HashMap<>();
        properties.put("anti-portal-traps", Value.newBuilder().setBoolValue(value).build());

        ConfigurableSettings settings = ProtobufPacketUtil.createModuleMessage("server_rule", properties);
        ProtobufPacketUtil.broadcastPacket(settings);
    }

    @Override
    public void setOverrideNametagRenderDistance(Player viewer, boolean value) {
        Map<String, Value> properties = new HashMap<>();
        properties.put("override-nametag-render-distance", Value.newBuilder().setBoolValue(value).build());

        ConfigurableSettings settings = ProtobufPacketUtil.createModuleMessage("server_rule", properties);
        ProtobufPacketUtil.sendPacket(viewer, settings);
    }

    @Override
    public void setNametagRenderDistanceExample(int value) {
        Map<String, Value> properties = new HashMap<>();
        properties.put("nametag-render-distance", Value.newBuilder().setNumberValue(value).build());

        ConfigurableSettings settings = ProtobufPacketUtil.createModuleMessage("server_rule", properties);
        ProtobufPacketUtil.broadcastPacket(settings);
    }

}

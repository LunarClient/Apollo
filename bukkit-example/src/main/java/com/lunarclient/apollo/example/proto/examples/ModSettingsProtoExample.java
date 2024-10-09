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
package com.lunarclient.apollo.example.proto.examples;

import com.google.protobuf.NullValue;
import com.google.protobuf.Value;
import com.lunarclient.apollo.configurable.v1.ConfigurableSettings;
import com.lunarclient.apollo.example.common.modules.impl.ModSettingsExample;
import com.lunarclient.apollo.example.proto.ProtobufPacketUtil;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;

public class ModSettingsProtoExample extends ModSettingsExample {

    @Override
    public void disableLightingModExample(Player viewer) {
        Map<String, Value> properties = new HashMap<>();
        properties.put("lighting.enabled", Value.newBuilder().setBoolValue(false).build());

        ConfigurableSettings settings = ProtobufPacketUtil.createModuleMessage("mod_setting", properties);
        ProtobufPacketUtil.sendPacket(viewer, settings);
    }

    @Override
    public void rollbackLightingModEnabledState(Player viewer) {
        Map<String, Value> properties = new HashMap<>();
        // To rollback the server override value of the setting, simply set the value to "null"
        properties.put("lighting.enabled", Value.newBuilder().setNullValue(NullValue.NULL_VALUE).build());

        ConfigurableSettings settings = ProtobufPacketUtil.createModuleMessage("mod_setting", properties);
        ProtobufPacketUtil.sendPacket(viewer, settings);
    }

    @Override
    public void broadcastDisableLightingModExample() {
        Map<String, Value> properties = new HashMap<>();
        properties.put("lighting.enabled", Value.newBuilder().setBoolValue(false).build());

        ConfigurableSettings settings = ProtobufPacketUtil.createModuleMessage("mod_setting", properties);
        ProtobufPacketUtil.broadcastPacket(settings);
    }

}

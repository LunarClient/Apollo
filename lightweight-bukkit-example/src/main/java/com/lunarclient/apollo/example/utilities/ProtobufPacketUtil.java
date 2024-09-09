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
package com.lunarclient.apollo.example.utilities;

import com.google.protobuf.Any;
import com.google.protobuf.GeneratedMessageV3;
import com.lunarclient.apollo.configurable.v1.ConfigurableSettings;
import com.lunarclient.apollo.configurable.v1.OverrideConfigurableSettingsMessage;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class ProtobufPacketUtil {

    public static void enableModule(Player player, String module) {
        ProtobufPacketUtil.enableModules(player, Collections.singletonList(module));
    }

    public static void enableModules(Player player, List<String> modules) {
        List<ConfigurableSettings> settings = modules.stream()
            .map(ProtobufPacketUtil::createEnableModuleMessage)
            .collect(Collectors.toList());

        OverrideConfigurableSettingsMessage message = OverrideConfigurableSettingsMessage
            .newBuilder()
            .addAllConfigurableSettings(settings)
            .build();

        ProtobufPacketUtil.sendPacket(player, message);
    }

    private static ConfigurableSettings createEnableModuleMessage(String module) {
        return ConfigurableSettings.newBuilder()
            .setApolloModule(module)
            .setEnable(true)
            .build();
    }

    public static void sendPacket(Player player, GeneratedMessageV3 message) {
        byte[] bytes = Any.pack(message).toByteArray();
        player.sendPluginMessage(ApolloExamplePlugin.getPlugin(), "lunar:apollo", bytes);
    }

    public static void broadcastPacket(GeneratedMessageV3 message) {
        byte[] bytes = Any.pack(message).toByteArray();

        Bukkit.getOnlinePlayers().forEach(player ->
            player.sendPluginMessage(ApolloExamplePlugin.getPlugin(), "lunar:apollo", bytes));
    }

    private ProtobufPacketUtil() {
    }

}

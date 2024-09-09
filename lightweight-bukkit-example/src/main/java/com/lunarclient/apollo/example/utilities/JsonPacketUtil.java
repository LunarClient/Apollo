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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import java.util.Collections;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class JsonPacketUtil {

    public static void enableModule(Player player, String module) {
        JsonPacketUtil.enableModules(player, Collections.singletonList(module));
    }

    public static void enableModules(Player player, List<String> modules) {
        JsonArray settings = modules.stream()
            .map(JsonPacketUtil::createEnableModuleObject)
            .collect(JsonArray::new, JsonArray::add, JsonArray::addAll);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.configurable.v1.OverrideConfigurableSettingsMessage");
        message.add("configurable_settings", settings);

        JsonPacketUtil.sendPacket(player, message);
    }

    private static JsonObject createEnableModuleObject(String module) {
        JsonObject message = new JsonObject();
        // message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.configurable.v1.ConfigurableSettings");
        message.addProperty("apollo_module", module);
        message.addProperty("enable", true);

        return message;
    }

    public static void sendPacket(Player player, JsonObject message) {
        player.sendPluginMessage(ApolloExamplePlugin.getPlugin(), "apollo:json", message.toString().getBytes());
    }

    public static void broadcastPacket(JsonObject message) {
        byte[] data = message.toString().getBytes();

        Bukkit.getOnlinePlayers().forEach(player ->
            player.sendPluginMessage(ApolloExamplePlugin.getPlugin(), "apollo:json", data));
    }

    private JsonPacketUtil() {
    }

}

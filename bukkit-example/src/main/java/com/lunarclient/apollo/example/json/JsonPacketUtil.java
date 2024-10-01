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

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class JsonPacketUtil {

    private static final List<String> APOLLO_MODULES = Arrays.asList("limb", "beam", "border", "chat", "colored_fire", "combat", "cooldown",
        "entity", "glow", "hologram", "mod_setting", "nametag", "nick_hider", "notification", "packet_enrichment", "rich_presence",
        "server_rule", "staff_mod", "stopwatch", "team", "title", "tnt_countdown", "transfer", "vignette", "waypoint"
    );

    // Module Id -> Option key -> Object
    private static final Table<String, String, Object> CONFIG_MODULE_PROPERTIES = HashBasedTable.create();

    static {
        // Module Options that the client needs to notified about, these properties are sent with the enable module packet
        // While using the Apollo plugin this would be equivalent to modifying the config.yml
        CONFIG_MODULE_PROPERTIES.put("combat", "disable-miss-penalty", false);
        CONFIG_MODULE_PROPERTIES.put("server_rule", "competitive-game", false);
        CONFIG_MODULE_PROPERTIES.put("server_rule", "competitive-commands", Arrays.asList("/server", "/servers", "/hub"));
        CONFIG_MODULE_PROPERTIES.put("server_rule", "disable-shaders", false);
        CONFIG_MODULE_PROPERTIES.put("server_rule", "disable-chunk-reloading", false);
        CONFIG_MODULE_PROPERTIES.put("server_rule", "disable-broadcasting", false);
        CONFIG_MODULE_PROPERTIES.put("server_rule", "anti-portal-traps", true);
        CONFIG_MODULE_PROPERTIES.put("server_rule", "override-brightness", false);
        CONFIG_MODULE_PROPERTIES.put("server_rule", "brightness", 50);
        CONFIG_MODULE_PROPERTIES.put("server_rule", "override-nametag-render-distance", false);
        CONFIG_MODULE_PROPERTIES.put("server_rule", "nametag-render-distance", 64);
        CONFIG_MODULE_PROPERTIES.put("server_rule", "override-max-chat-length", false);
        CONFIG_MODULE_PROPERTIES.put("server_rule", "max-chat-length", 256);
        CONFIG_MODULE_PROPERTIES.put("tnt_countdown", "tnt-ticks", 80);
        CONFIG_MODULE_PROPERTIES.put("waypoint", "server-handles-waypoints", false);
    }

    public static void sendPacket(Player player, JsonObject message) {
        player.sendPluginMessage(ApolloExamplePlugin.getPlugin(), "apollo:json", message.toString().getBytes());
    }

    public static void broadcastPacket(JsonObject message) {
        byte[] data = message.toString().getBytes();

        Bukkit.getOnlinePlayers().forEach(player ->
            player.sendPluginMessage(ApolloExamplePlugin.getPlugin(), "apollo:json", data));
    }

    public static JsonObject createEnableModuleObject(@NotNull String module, Map<String, Object> properties) {
        JsonObject enableModuleObject = new JsonObject();
        enableModuleObject.addProperty("apollo_module", module);
        enableModuleObject.addProperty("enable", true);

        if (properties != null) {
            JsonObject propertiesObject = new JsonObject();
            for (Map.Entry<String, Object> entry : properties.entrySet()) {
                propertiesObject.add(entry.getKey(), JsonPacketUtil.convertToJsonElement(entry.getValue()));
            }

            enableModuleObject.add("properties", propertiesObject);
        }

        return enableModuleObject;
    }

    private static JsonElement convertToJsonElement(Object value) {
        if (value == null) {
            return JsonNull.INSTANCE;
        } else if (value instanceof String) {
            return new JsonPrimitive((String) value);
        } else if (value instanceof Number) {
            return new JsonPrimitive((Number) value);
        } else if (value instanceof Boolean) {
            return new JsonPrimitive((Boolean) value);
        } else if (value instanceof List) {
            JsonArray jsonArray = new JsonArray();
            for (Object item : (List<?>) value) {
                jsonArray.add(convertToJsonElement(item));
            }
            return jsonArray;
        }

        throw new RuntimeException("Unable to wrap value of type '" + value.getClass().getSimpleName() + "'!");
    }

    public static void enableModules(Player player) {
        JsonArray settings = APOLLO_MODULES.stream()
            .map(module -> JsonPacketUtil.createEnableModuleObject(module, CONFIG_MODULE_PROPERTIES.row(module)))
            .collect(JsonArray::new, JsonArray::add, JsonArray::addAll);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.configurable.v1.OverrideConfigurableSettingsMessage");
        message.add("configurable_settings", settings);

        JsonPacketUtil.sendPacket(player, message);
    }

    private JsonPacketUtil() {
    }

}

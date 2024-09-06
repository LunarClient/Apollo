package com.lunarclient.apollo.example.utilities;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

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
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.configurable.v1.ConfigurableSettings");
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

}

package com.lunarclient.apollo.example.utilities;

import com.google.protobuf.Any;
import com.google.protobuf.GeneratedMessageV3;
import com.lunarclient.apollo.configurable.v1.ConfigurableSettings;
import com.lunarclient.apollo.configurable.v1.OverrideConfigurableSettingsMessage;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

}

package com.lunarclient.apollo.example.utilities;

import com.google.common.reflect.ClassPath;
import com.google.protobuf.Any;
import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.TypeRegistry;
import com.google.protobuf.util.JsonFormat;
import com.lunarclient.apollo.configurable.v1.ConfigurableSettings;
import com.lunarclient.apollo.configurable.v1.OverrideConfigurableSettingsMessage;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.waypoint.v1.DisplayWaypointMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public final class PacketUtil {

    private static final Pattern PROTO_MESSAGE_PATTERN = Pattern.compile("^com\\.lunarclient\\.apollo.*Message$");
    private static final JsonFormat.Printer PRINTER;

    static {
        // Create a TypeRegistry with packets we are using
        // The registry is needed for serialization & deserialization
//        TypeRegistry.Builder registryBuilder = TypeRegistry.newBuilder()
//            .add(OverrideConfigurableSettingsMessage.getDescriptor())
//            .add(ConfigurableSettings.getDescriptor())
//            .add(DisplayWaypointMessage.getDescriptor());
//
//        PRINTER = JsonFormat.printer().usingTypeRegistry(registryBuilder.build());

        // Or you can scan for all Apollo protos classes and create a TypeRegistry
        TypeRegistry.Builder registryBuilder = TypeRegistry.newBuilder();
        try {
            ClassPath.from(DisplayWaypointMessage.class.getClassLoader())
                .getAllClasses()
                .stream()
                .map(ClassPath.ClassInfo::getName)
                .filter(name -> PROTO_MESSAGE_PATTERN.matcher(name).matches())
                .forEach(className -> {
                    try {
                        Class<?> clazz = Class.forName(className);
                        Descriptors.Descriptor descriptor = (Descriptors.Descriptor) clazz
                            .getMethod("getDescriptor")
                            .invoke(null);

                        registryBuilder.add(descriptor);
                    } catch (Exception ignored) { }
                });
        } catch (IOException ignored) { }

        // Create the protobuf printer with the registry
        PRINTER = JsonFormat.printer().usingTypeRegistry(registryBuilder.build());
    }

    public static void enableModule(Player player, String module) {
        // Create a message to enable a specific module
        OverrideConfigurableSettingsMessage enableModuleMessage = OverrideConfigurableSettingsMessage.newBuilder()
            .addConfigurableSettings(
                ConfigurableSettings.newBuilder()
                    .setApolloModule(module)
                    .setEnable(true)
                    .build()
            ).build();

        sendPacket(player, enableModuleMessage);
    }

    public static void enableModules(Player player, List<String> modules) {
        OverrideConfigurableSettingsMessage.Builder builder = OverrideConfigurableSettingsMessage.newBuilder();

        for (String module : modules) {
            builder.addConfigurableSettings(
                ConfigurableSettings.newBuilder()
                    .setApolloModule(module)
                    .setEnable(true)
                    .build()
            );
        }

        sendPacket(player, builder.build());
    }


    public static void sendPacket(Player player, GeneratedMessageV3 message) {
        Any any = Any.pack(message);

        try {
            // Serialize and convert to byte[]
            byte[] enableModuleBytes = PRINTER.print(any).getBytes();

            // Finally, send the data through the "apollo:json" lightweight channel
            player.sendPluginMessage(ApolloExamplePlugin.getPlugin(), "apollo:json", enableModuleBytes);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

    public static void broadcastPacket(GeneratedMessageV3 message) {
        Any any = Any.pack(message);

        try {
            // Serialize and convert to byte[]
            byte[] enableModuleBytes = PRINTER.print(any).getBytes();

            // Finally, send the data through the "apollo:json" lightweight channel
            Bukkit.getOnlinePlayers().forEach(player -> player.sendPluginMessage(ApolloExamplePlugin.getPlugin(), "apollo:json", enableModuleBytes));
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

}

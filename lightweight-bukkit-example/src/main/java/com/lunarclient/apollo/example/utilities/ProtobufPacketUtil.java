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
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class ProtobufPacketUtil {

    // TODO: no need to serialize

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
        TypeRegistry registry = registryBuilder.build(); // TODO: Remove
        PRINTER = JsonFormat.printer().usingTypeRegistry(registryBuilder.build());

        try {
            Field field = TypeRegistry.class.getDeclaredField("types");
            field.setAccessible(true);
            System.out.println(field.get(registry));;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

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
            Bukkit.getOnlinePlayers().forEach(player ->
                player.sendPluginMessage(ApolloExamplePlugin.getPlugin(), "apollo:json", enableModuleBytes));
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

}

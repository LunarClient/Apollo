package com.moonsworth.apollo.api;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.events.EventBus;
import com.moonsworth.apollo.api.events.impl.packet.EventApolloReceivePacket;
import com.moonsworth.apollo.api.events.impl.packet.EventApolloSendPacket;
import com.moonsworth.apollo.api.module.ApolloModule;
import com.moonsworth.apollo.api.module.ApolloModuleManager;
import com.moonsworth.apollo.api.packet.ApolloPacketManager;
import com.moonsworth.apollo.api.player.ApolloPlayerManager;
import lombok.Getter;

import java.util.function.Consumer;

/**
 * Main API class for Apollo.
 */
public class Apollo {

    /**
     * The plugin message channel that Lunar Client and Apollo talk over.
     */
    public static final String PLUGIN_MESSAGE_CHANNEL = "lunarclient:apollo";

    /**
     * The currently loaded platform. This is set as early as possible.
     */
    @Getter
    private static ApolloPlatform platform = null;
    @Getter
    private static ApolloModuleManager apolloModuleManager = null;
    @Getter
    private static ApolloPacketManager apolloPacketManager = null;
    @Getter
    private static ApolloPlayerManager apolloPlayerManager = null;

    public static void setPlatform(ApolloPlatform platform) {
        Apollo.platform = platform;
        apolloModuleManager = new ApolloModuleManager();
        apolloPacketManager = new ApolloPacketManager(platform);
        apolloPlayerManager = new ApolloPlayerManager();
    }

    public static void handleIncomingPacket(Object playerObject, byte[] packet) {
        ApolloPlayer player = platform.tryWrapPlayer(playerObject);
        try {
            EventBus.getBus().post(new EventApolloReceivePacket(player, Any.parseFrom(packet)));
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    public static void handleOutgoingPacket(Object playerObject, byte[] packet) {
        ApolloPlayer player = platform.tryWrapPlayer(playerObject);
        try {
            EventBus.getBus().post(new EventApolloSendPacket(player, Any.parseFrom(packet)));
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }


    /**
     * Registers modules for Apollo.
     * This needs to be done very close to the start of the platform.
     * @param clazz The module to register.
     */
    public static <T extends ApolloModule> void using(Class<T> clazz) {
        apolloModuleManager.register(clazz);
    }

    public void withPlayer(Object o, Consumer<ApolloPlayer> consumer) {
        var apolloPlayer = platform.tryWrapPlayer(o);
        if (apolloPlayer != null) {
            consumer.accept(apolloPlayer);
        }
    }
}

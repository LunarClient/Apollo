package com.moonsworth.apollo.api.bridge;

import com.google.protobuf.Any;
import com.google.protobuf.GeneratedMessageV3;
import com.moonsworth.apollo.api.Apollo;

import java.util.UUID;

/**
 * A player that supports Apollo.
 */
public interface ApolloPlayer {

    /**
     * Gets the players UUID from their Platform
     * @return The Id of the player
     */
    UUID getUniqueId();

    /**
     * Sends a compiled message to an ApolloPlayer
     *
     * @param compiledProtobufMessage A byte array representing a {@link GeneratedMessageV3}
     */
    void sendPacket(byte[] compiledProtobufMessage);

    /**
     * Determines if an apoolo player has permission to a specific permission node.
     *
     * @param permissionNode The node
     * @return The value of the permission
     */
    boolean hasPermission(String permissionNode);

    /**
     * A helper method to send a Protobuf packet easier.
     *
     * @param packet The packet send to the player.
     */
    default void sendPacket(GeneratedMessageV3 packet) {
        byte[] bytes = Any.pack(packet).toByteArray();
        Apollo.getApolloPacketManager().handleOutgoingPacket(this, bytes);
    }

    /**
     * Gets the player's location in the world.
     * Only available on the SERVER, not the proxy.
     * @return the location of the player in the world
     */
    ApolloLocation getWorldLocation();

    /**
     * Send a message to the player. Used for debug only!
     * @param message The message to send to the player.
     */
    void sendMessage(String message);
}

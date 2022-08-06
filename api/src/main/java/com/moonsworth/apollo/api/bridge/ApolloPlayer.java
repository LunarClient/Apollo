package com.moonsworth.apollo.api.bridge;

import com.google.protobuf.Any;
import com.google.protobuf.GeneratedMessageV3;

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
        sendPacket(Any.pack(packet).toByteArray());
    }

}

package com.moonsworth.apollo.api.bridge;

import com.google.protobuf.GeneratedMessageV3;

/**
 * A player that supports Apollo.
 */
public interface ApolloPlayer {

    /**
     * Sends a compiled message to an ApolloPlayer
     *
     * @param compiledProtobufMessage A byte array representing a {@link GeneratedMessageV3}
     */
    void sendPacket(byte[] compiledProtobufMessage);

    /**
     * A helper method to send a Protobuf packet easier.
     *
     * @param packet The packet send to the player.
     */
    default void sendPacket(GeneratedMessageV3 packet) {
        sendPacket(packet.toByteArray());
    }

}

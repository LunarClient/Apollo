package com.moonsworth.apollo.api.bridge;

import com.moonsworth.apollo.api.network.Packet;

/**
 * A player that supports Apollo.
 */
public interface ApolloPlayer {

    /**
     * Send a packet to the player.
     *
     * @param packet Packet to send
     */
    void sendPacket(Packet packet);

}

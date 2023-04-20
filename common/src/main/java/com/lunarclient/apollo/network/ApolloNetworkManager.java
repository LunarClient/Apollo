package com.lunarclient.apollo.network;

import com.google.protobuf.Any;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.network.ApolloReceivePacketEvent;
import com.lunarclient.apollo.event.network.ApolloSendPacketEvent;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import lombok.NoArgsConstructor;

/**
 * Provides the implementation for the {@link ApolloNetworkManager}.
 *
 * @since 1.0.0
 */
@NoArgsConstructor
public final class ApolloNetworkManager {

    /**
     * Sends an {@link Any} message packet to the provided player.
     *
     * @param player the player to send the packet to
     * @param message the message to send
     * @since 1.0.0
     */
    public void sendPacket(ApolloPlayer player, Any message) {
        EventBus.EventResult<ApolloSendPacketEvent> result = EventBus.getBus().post(new ApolloSendPacketEvent(player, message));
        if(!result.getEvent().isCancelled()) {
            ((AbstractApolloPlayer) player).sendPacket(message.toByteArray());
        }
        for(Throwable throwable : result.getThrowing()) {
            throwable.printStackTrace();
        }
    }

    /**
     * Receives an {@link Any} message packet from the provided player.
     *
     * @param player the player to receive the packet from
     * @param message the message to receive
     * @since 1.0.0
     */
    public void receivePacket(ApolloPlayer player, Any message) {
        EventBus.EventResult<ApolloReceivePacketEvent> result = EventBus.getBus().post(new ApolloReceivePacketEvent(player, message));
        for(Throwable throwable : result.getThrowing()) {
            throwable.printStackTrace();
        }
    }

}

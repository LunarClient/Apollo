package com.moonsworth.apollo.network;

import com.google.protobuf.Any;
import com.moonsworth.apollo.event.EventBus;
import com.moonsworth.apollo.event.network.ApolloReceivePacketEvent;
import com.moonsworth.apollo.event.network.ApolloSendPacketEvent;
import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayer;

public final class ApolloNetworkManager {

    public void sendPacket(final ApolloPlayer player, final Any message) {
        final EventBus.EventResult<ApolloSendPacketEvent> result = EventBus.getBus().post(new ApolloSendPacketEvent(player, message));
        if(!result.getEvent().isCancelled()) {
            ((AbstractApolloPlayer) player).sendPacket(message.toByteArray());
        }
        for(final Throwable throwable : result.getThrowing()) {
            throwable.printStackTrace();
        }
    }

    public void receivePacket(final ApolloPlayer player, final Any message) {
        final EventBus.EventResult<ApolloReceivePacketEvent> result = EventBus.getBus().post(new ApolloReceivePacketEvent(player, message));
        for(final Throwable throwable : result.getThrowing()) {
            throwable.printStackTrace();
        }
    }

}

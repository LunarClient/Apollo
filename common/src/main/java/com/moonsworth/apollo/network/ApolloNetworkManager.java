package com.moonsworth.apollo.network;

import com.google.protobuf.Any;
import com.moonsworth.apollo.event.EventBus;
import com.moonsworth.apollo.event.network.ApolloReceivePacketEvent;
import com.moonsworth.apollo.event.network.ApolloSendPacketEvent;
import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayer;

public final class ApolloNetworkManager {

    public void sendPacket(ApolloPlayer player, Any message) {
        EventBus.EventResult<ApolloSendPacketEvent> result = EventBus.getBus().post(new ApolloSendPacketEvent(player, message));
        if(!result.getEvent().isCancelled()) {
            ((AbstractApolloPlayer) player).sendPacket(message.toByteArray());
        }
        for(Throwable throwable : result.getThrowing()) {
            throwable.printStackTrace();
        }
    }

    public void receivePacket(ApolloPlayer player, Any message) {
        EventBus.EventResult<ApolloReceivePacketEvent> result = EventBus.getBus().post(new ApolloReceivePacketEvent(player, message));
        for(Throwable throwable : result.getThrowing()) {
            throwable.printStackTrace();
        }
    }

}

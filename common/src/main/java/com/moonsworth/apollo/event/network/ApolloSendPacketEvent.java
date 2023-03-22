package com.moonsworth.apollo.event.network;

import com.google.protobuf.Any;
import com.moonsworth.apollo.event.EventCancellable;
import com.moonsworth.apollo.player.ApolloPlayer;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
public class ApolloSendPacketEvent implements EventCancellable {

    ApolloPlayer player;
    Any packet;

    @NonFinal boolean cancelled;

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}

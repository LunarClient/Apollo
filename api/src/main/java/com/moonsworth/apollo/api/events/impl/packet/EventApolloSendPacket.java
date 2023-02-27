package com.moonsworth.apollo.api.events.impl.packet;

import com.google.protobuf.Any;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.events.Event;
import com.moonsworth.apollo.api.events.EventCancellable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EventApolloSendPacket extends Event implements EventCancellable {

    private boolean cancelled;

    private final ApolloPlayer player;
    private final Any packet;

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}

package com.moonsworth.apollo.event.network;

import com.google.protobuf.Any;
import com.moonsworth.apollo.event.EventCancellable;
import com.moonsworth.apollo.player.ApolloPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public final class ApolloSendPacketEvent implements EventCancellable {

    private final ApolloPlayer player;
    private final Any packet;

    @Setter private boolean cancelled;

}

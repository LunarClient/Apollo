package com.moonsworth.apollo.event.network;

import com.google.protobuf.Any;
import com.moonsworth.apollo.event.Event;
import com.moonsworth.apollo.player.ApolloPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class ApolloReceivePacketEvent implements Event {

    private final ApolloPlayer player;
    private final Any packet;

}

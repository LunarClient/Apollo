package com.moonsworth.apollo.api.events.impl.packet;

import com.google.protobuf.Any;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.events.Event;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EventApolloReceivePacket extends Event {

    private final ApolloPlayer player;
    private final Any packet;
}

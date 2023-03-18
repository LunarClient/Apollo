package com.moonsworth.apollo.event.network;

import com.google.protobuf.Any;
import com.moonsworth.apollo.event.Event;
import com.moonsworth.apollo.player.ApolloPlayer;
import lombok.Value;

@Value
public class ApolloReceivePacketEvent implements Event {

    ApolloPlayer player;
    Any packet;

}

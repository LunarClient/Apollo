package com.moonsworth.apollo.event.network;

import com.google.protobuf.Any;
import com.moonsworth.apollo.event.EventCancellable;
import com.moonsworth.apollo.player.ApolloPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public final class ApolloSendPacketEvent implements EventCancellable {

    ApolloPlayer player;
    Any packet;

    @NonFinal @Setter boolean cancelled;

}

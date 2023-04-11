package com.moonsworth.apollo.event.network;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.moonsworth.apollo.event.Event;
import com.moonsworth.apollo.player.ApolloPlayer;
import lombok.Value;

import java.util.Optional;

@Value
public class ApolloReceivePacketEvent implements Event {

    ApolloPlayer player;
    Any packet;

    public <T extends Message> Optional<T> unpackIf(Class<T> packet) {
        if (!this.packet.is(packet)) {
            return Optional.empty();
        }

        Optional<T> value;
        try {
            value = Optional.ofNullable(this.packet.unpack(packet));
        } catch (InvalidProtocolBufferException e) {
            value = Optional.empty();
        }

        return value;
    }
}

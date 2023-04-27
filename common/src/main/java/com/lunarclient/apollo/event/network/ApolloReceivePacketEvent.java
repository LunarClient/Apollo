package com.lunarclient.apollo.event.network;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.lunarclient.apollo.event.Event;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Optional;
import lombok.Value;

/**
 * Represents an event that is fired when a packet is received from the client.
 *
 * @since 1.0.0
 */
@Value
public class ApolloReceivePacketEvent implements Event {

    /**
     * The player that sent the packet.
     *
     * @return the player
     * @since 1.0.0
     */
    ApolloPlayer player;

    /**
     * The packet that was sent.
     *
     * @return the packet
     * @since 1.0.0
     */
    Any packet;

    /**
     * Unpacks the provided packet class.
     *
     * @param <T> the packet optional
     * @param packet the apollo packet
     * @return the message
     * @since 1.0.0
     */
    public <T extends Message> Optional<T> unpack(Class<T> packet) {
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

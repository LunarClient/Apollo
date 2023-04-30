package com.lunarclient.apollo.event;

import com.google.protobuf.Any;
import com.lunarclient.apollo.event.EventCancellable;
import com.lunarclient.apollo.player.ApolloPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

/**
 * Represents an event that is fired when a packet is sent to the client.
 *
 * @since 1.0.0
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public final class ApolloSendPacketEvent implements EventCancellable {

    /**
     * The player that the packet is being sent to.
     *
     * @return the player
     * @since 1.0.0
     */
    ApolloPlayer player;

    /**
     * The packet that is being sent.
     *
     * @return the packet
     * @since 1.0.0
     */
    Any packet;

    @NonFinal @Setter boolean cancelled;

}

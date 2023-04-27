package com.lunarclient.apollo.player;

import com.google.protobuf.Any;
import com.google.protobuf.Message;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.roundtrip.ApolloRequest;
import com.lunarclient.apollo.roundtrip.ApolloResponse;
import com.lunarclient.apollo.roundtrip.async.Future;
import com.lunarclient.apollo.roundtrip.async.future.UncertainFuture;

/**
 * Provides convenience methods for sending packets to the client.
 *
 * @since 1.0.0
 */
public abstract class AbstractApolloPlayer implements ApolloPlayer {

    /**
     * Sends the provided message packet to the client
     * with an expected {@link Future} response.
     *
     * @param <T> the future representing the response
     * @param request the apollo request
     * @param message the message
     * @return the apollo response
     * @since 1.0.0
     */
    public <T extends ApolloResponse> Future<T> sendRoundTripPacket(ApolloRequest<T> request, Message message) {
        this.sendPacket(message);

        UncertainFuture<T> future = new UncertainFuture<>();
        Apollo.getRoundtripManager().registerListener(request, future);
        return future;
    }

    /**
     * Sends the provided message packet to the client.
     *
     * @param message the message
     * @since 1.0.0
     */
    public void sendPacket(Message message) {
        ApolloManager.getNetworkManager().sendPacket(this, Any.pack(message));
    }

    /**
     * Sends the provided raw packet to the client.
     *
     * @param messages the messages
     * @since 1.0.0
     */
    public abstract void sendPacket(byte[] messages);

}

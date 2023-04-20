package com.lunarclient.apollo.player;

import com.google.protobuf.Any;
import com.google.protobuf.GeneratedMessageV3;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.roundtrip.ApolloRequest;
import com.lunarclient.apollo.roundtrip.ApolloResponse;
import com.lunarclient.apollo.roundtrip.async.Future;
import com.lunarclient.apollo.roundtrip.async.future.UncertainFuture;
import lunarclient.apollo.common.MessageOperation;
import lunarclient.apollo.common.OptionOperation;

/**
 * Provides convenience methods for sending packets to the client.
 *
 * @since 1.0.0
 */
public abstract class AbstractApolloPlayer implements ApolloPlayer {

    /**
     * Sends the provided message packet to the client from the provided
     * {@link ApolloModule}.
     *
     * @param module the module
     * @param operation the operation
     * @param message the message
     * @since 1.0.0
     */
    public void sendPacket(ApolloModule module, OptionOperation operation, GeneratedMessageV3 message) {
        this.sendPacket(MessageOperation.newBuilder()
                .setModule(module.getName())
                .setOperation(operation)
                .setValue(Any.pack(message))
                .build()
        );
    }

    /**
     * Sends the provided operation packet to the client.
     *
     * @param module the module
     * @param operation the operation
     * @since 1.0.0
     */
    public void sendPacket(ApolloModule module, OptionOperation operation) {
        this.sendPacket(MessageOperation.newBuilder()
                .setModule(module.getName())
                .setOperation(operation)
                .build()
        );
    }

    public <T extends ApolloResponse> Future<T> sendRoundTripPacket(ApolloRequest<T> request, ApolloModule module,
                                                                    OptionOperation operation, GeneratedMessageV3 message) {
        this.sendPacket(MessageOperation.newBuilder()
            .setModule(module.getName())
            .setOperation(operation)
            .setValue(Any.pack(message))
            .build()
        );

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
    public void sendPacket(GeneratedMessageV3 message) {
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

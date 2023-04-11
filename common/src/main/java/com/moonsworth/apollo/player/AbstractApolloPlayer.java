package com.moonsworth.apollo.player;

import com.google.protobuf.Any;
import com.google.protobuf.GeneratedMessageV3;
import com.moonsworth.apollo.Apollo;
import com.moonsworth.apollo.ApolloManager;
import com.moonsworth.apollo.module.ApolloModule;
import com.moonsworth.apollo.roundtrip.ApolloRequest;
import com.moonsworth.apollo.roundtrip.ApolloResponse;
import com.moonsworth.apollo.roundtrip.async.Future;
import com.moonsworth.apollo.roundtrip.async.future.UncertainFuture;
import lunarclient.apollo.common.MessageOperation;
import lunarclient.apollo.common.OptionOperation;

public abstract class AbstractApolloPlayer implements ApolloPlayer {

    public void sendPacket(ApolloModule module, OptionOperation operation, GeneratedMessageV3 message) {
        this.sendPacket(MessageOperation.newBuilder()
                .setModule(module.getName())
                .setOperation(operation)
                .setValue(Any.pack(message))
                .build()
        );
    }

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

    public void sendPacket(GeneratedMessageV3 message) {
        ApolloManager.getNetworkManager().sendPacket(this, Any.pack(message));
    }

    public abstract void sendPacket(byte[] messages);

}

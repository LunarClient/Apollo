package com.moonsworth.apollo.player;

import com.google.protobuf.Any;
import com.google.protobuf.GeneratedMessageV3;
import com.moonsworth.apollo.ApolloManager;

public abstract class AbstractApolloPlayer implements ApolloPlayer {

    public void sendPacket(final GeneratedMessageV3 message) {
        ApolloManager.getNetworkManager().sendPacket(this, Any.pack(message));
    }

    public abstract void sendPacket(final byte[] messages);

}

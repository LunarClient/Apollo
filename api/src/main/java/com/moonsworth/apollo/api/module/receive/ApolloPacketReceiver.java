package com.moonsworth.apollo.api.module.receive;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public abstract class ApolloPacketReceiver {

    //Key is the fully qualified name of the protobuf message, left-side of the pair is the packet class and the right side is the handler
    private Map<String, ApolloPacketPair> packetHandlers = new HashMap<>();

    public <M extends Message> void registerPacket(M defaultInstance, BiConsumer<ApolloPlayer, M> handler) {
        this.packetHandlers.put(defaultInstance.getDescriptorForType().getFullName(),
                new ApolloPacketPair(defaultInstance.getClass(), handler));
    }

    public void handle(ApolloPlayer apolloPlayer, Any any) {
        //very hacky way to get the message type
        ApolloPacketPair pair = packetHandlers.get(any.getTypeUrl().split("type\\.googleapis\\.com/")[1]);
        if (pair != null) {
            try {
                pair.getConsumer().accept(apolloPlayer, any.unpack(pair.getClazz()));
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        }
    }
}

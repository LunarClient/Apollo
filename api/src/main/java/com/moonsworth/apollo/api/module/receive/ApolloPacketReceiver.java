package com.moonsworth.apollo.api.module.receive;

import com.google.protobuf.Any;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class ApolloPacketReceiver {

    //Key is the fully qualified name of the protobuf message, left-side of the pair is the packet class and the right side is the handler
    private Map<String, ApolloPacketPair> packetHandlers = new HashMap<>();

    public void registerPacket(Message defaultInstance, BiConsumer<ApolloPlayer, Message> handler) {
        this.packetHandlers.put(defaultInstance.getDescriptorForType().getFullName(), new ApolloPacketPair(defaultInstance.getClass(), handler));
    }

    public void handle(ApolloPlayer apolloPlayer, Any any) {
        ApolloPacketPair pair = packetHandlers.get(any.getDescriptorForType().getFullName());
        if(pair != null) {
            try {
                pair.getConsumer().accept(apolloPlayer, any.unpack(pair.getClazz()));
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        }
    }
}

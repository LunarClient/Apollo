package com.moonsworth.apollo.api.module.receive;

import com.google.protobuf.Message;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.BiConsumer;

@Getter
@RequiredArgsConstructor
public class ApolloPacketPair<M extends Message> {

    private final Class<? extends M> clazz;
    private final BiConsumer<ApolloPlayer, M> consumer;
}

package com.moonsworth.apollo.api.module.receive;

import com.google.protobuf.Message;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.BiConsumer;

@Getter
@RequiredArgsConstructor
public class ApolloPacketPair {

    private final Class<? extends Message> clazz;
    private final BiConsumer<ApolloPlayer, Message> consumer;
}

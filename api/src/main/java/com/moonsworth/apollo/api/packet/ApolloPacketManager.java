package com.moonsworth.apollo.api.packet;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.events.EventBus;
import com.moonsworth.apollo.api.events.impl.packet.EventApolloReceivePacket;
import com.moonsworth.apollo.api.events.impl.packet.EventApolloSendPacket;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApolloPacketManager {

    private final ApolloPlatform platform;

    public void handleIncomingPacket(Object playerObject, byte[] packet) {
        ApolloPlayer player = this.platform.tryWrapPlayer(playerObject);

        try {
            EventBus.getBus().post(new EventApolloReceivePacket(player, Any.parseFrom(packet)));
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    public void handleOutgoingPacket(ApolloPlayer player, byte[] packet) {
        try {
            EventBus.getBus().post(new EventApolloSendPacket(player, Any.parseFrom(packet)));
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
}

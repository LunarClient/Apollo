package com.moonsworth.apollo.api.network;

import com.moonsworth.apollo.api.network.clientbound.ClientPacketHandler;
import com.moonsworth.apollo.api.network.serverbound.ServerPacketHandler;

import java.io.IOException;

public abstract class Packet {

    public abstract void write(ExtendedByteBuf buf) throws IOException;

    public abstract void read(ExtendedByteBuf buf) throws IOException;

    public void processClient(ClientPacketHandler packetHandler) {
    }

    public void processServer(ServerPacketHandler serverPacketHandler) {
    }

}

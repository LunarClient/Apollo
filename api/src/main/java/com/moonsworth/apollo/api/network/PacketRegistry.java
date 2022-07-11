package com.moonsworth.apollo.api.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PacketRegistry {

    // TODO: fastutil - figure out what version works for all platforms?
    private static final Map<Class<? extends Packet>, Integer> classToId = new HashMap<>();
    private static final Map<Integer, Class<? extends Packet>> idToClass = new HashMap<>();

    public static Optional<Packet> parse(byte[] data) {
        var buf = ExtendedByteBuf.from(data);

        int packetId = buf.readVarInt();
        Class<? extends Packet> packetClass = idToClass.get(packetId);

        if (packetClass != null) {
            try {
                Packet packet = packetClass.newInstance();
                packet.read(buf);
                return Optional.of(packet);
            } catch (IOException | ReflectiveOperationException ex) {
                // TODO: better error logging?
                ex.printStackTrace();
            }
        }

        return Optional.empty();
    }

    public static int getId(Packet packet) {
        return classToId.get(packet);
    }

    public static byte[] getPacketData(Packet packet) {
        return getPacketBuf(packet).array();
    }

    public static ByteBuf getPacketBuf(Packet packet) {
        var wrappedBuffer = new ExtendedByteBuf(Unpooled.buffer());
        wrappedBuffer.writeVarInt(classToId.get(packet.getClass()));

        try {
            packet.write(wrappedBuffer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return wrappedBuffer.buf();
    }
}

package com.moonsworth.apollo.api.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * A ByteBuf with extra methods for reading and writing common data types.
 *
 * @param buf original ByteBuf to wrap
 */
public record ExtendedByteBuf(ByteBuf buf) {

    /**
     * Build an ExtendedByteBuf from an array of bytes
     *
     * @param bytes array of bytes
     * @return new ExtendedByteBuf
     */
    public static ExtendedByteBuf from(byte[] bytes) {
        return new ExtendedByteBuf(Unpooled.wrappedBuffer(bytes));
    }

    public void writeVarInt(int b) {
        while ((b & -128) != 0) {
            this.buf.writeByte(b & 127 | 128);
            b >>>= 7;
        }

        this.buf.writeByte(b);
    }

    public int readVarInt() {
        int i = 0;
        int chunk = 0;
        byte b;

        do {
            b = this.buf.readByte();
            i |= (b & 127) << chunk++ * 7;

            if (chunk > 5) {
                throw new RuntimeException("VarInt too big");
            }
        } while ((b & 128) == 128);

        return i;
    }

    public void writeString(String s) {
        byte[] arr = s.getBytes(StandardCharsets.UTF_8);

        this.writeVarInt(arr.length);
        this.buf.writeBytes(arr);
    }

    public String readString() {
        int len = readVarInt();

        byte[] buffer = new byte[len];
        buf.readBytes(buffer);

        return new String(buffer, StandardCharsets.UTF_8);
    }

    public void writeUUID(UUID uuid) {
        this.buf.writeLong(uuid.getMostSignificantBits());
        this.buf.writeLong(uuid.getLeastSignificantBits());
    }

    public UUID readUUID() {
        long mostSigBits = this.buf.readLong();
        long leastSigBits = this.buf.readLong();

        return new UUID(mostSigBits, leastSigBits);
    }

}
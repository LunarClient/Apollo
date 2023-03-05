package com.moonsworth.apollo.api.utils;


import com.google.protobuf.ByteString;
import com.moonsworth.apollo.api.protocol.NametagMessage;
import com.moonsworth.apollo.api.protocol.UUIDMessage;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class UUIDUtils {
    /**
     * Generates a UUIDMessage from a UUID provided
     * @param uuid The uniqueId of the object
     * @return The API wrapper of the uuid provided
     */
    public UUIDMessage fromUUID(UUID uuid) {
        return UUIDMessage.newBuilder().addValue(uuid.toString()).build();
    }

    /**
     * Grabs the UUID from the API UUID wrapper
     * @param message The API UUID wrapper
     * @return The UUID provided in the wrapper
     */
    public UUID fromMessage(UUIDMessage message) {
        return UUID.fromString(message.getValue(0));
    }

    /**
     * Grabs the UUID from the ByteString
     * @param bytes The bytes of the UUID
     * @return The UUID contained in the bytes provided
     */
    public UUID fromBytes(ByteString bytes) {
        return UUID.fromString(bytes.toStringUtf8());
    }

    public ByteString fromUUIDLegacy(UUID uuid) {
        return ByteString.copyFromUtf8(uuid.toString());
    }
}

package com.moonsworth.apollo.api.utils;


import com.moonsworth.apollo.api.protocol.UUIDMessage;

import java.util.UUID;

public class UUIDUtils {
    public static UUIDMessage fromUUID(UUID uuid) {
        return UUIDMessage.newBuilder().addValue(uuid.toString()).build();
    }

    public static UUID fromMessage(UUIDMessage message) {
        return UUID.fromString(message.getValue(0));
    }
}

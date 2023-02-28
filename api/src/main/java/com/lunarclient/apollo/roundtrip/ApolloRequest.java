package com.lunarclient.apollo.roundtrip;

import java.util.UUID;
import lombok.Getter;

@Getter
public class ApolloRequest<T extends ApolloResponse> {

    public static final long TIMEOUT = 5_000L;

    private final UUID packetId;
    private final long sentTime;

    public ApolloRequest() {
        this.packetId = UUID.randomUUID();
        this.sentTime = System.currentTimeMillis();
    }

    public void respond(ApolloResponse response) {
        if(System.currentTimeMillis() - this.sentTime > TIMEOUT) {
            return;
        }

        response.setPacketId(this.packetId);
    }
}
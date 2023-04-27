package com.lunarclient.apollo.roundtrip;

import java.util.UUID;
import lombok.Getter;

/**
 * Represents an Apollo Request.
 *
 * @param <T> the expected {@link ApolloResponse}
 */
@Getter
public class ApolloRequest<T extends ApolloResponse> {

    /**
     * The timeout for the {@link ApolloRequest}.
     */
    public static final long TIMEOUT = 5_000L;

    /**
     * The unique identifier for the {@link ApolloRequest}
     */
    private final UUID packetId;

    /**
     * The time when the {@link ApolloRequest} was sent.
     */
    private final long sentTime;

    /**
     * Constructs a new {@link ApolloRequest}.
     */
    public ApolloRequest() {
        this.packetId = UUID.randomUUID();
        this.sentTime = System.currentTimeMillis();
    }

    /**
     * Responds to the {@link ApolloRequest} with an {@link ApolloRequest}.
     *
     * @param response the response
     */
    public void respond(ApolloResponse response) {
        if(System.currentTimeMillis() - this.sentTime > TIMEOUT) {
            return;
        }

        response.setPacketId(this.packetId);
    }
}

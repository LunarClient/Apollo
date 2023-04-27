package com.lunarclient.apollo.roundtrip;

import java.util.UUID;
import lombok.Getter;

/**
 * Represents an Apollo Request.
 *
 * @param <T> the expected {@link ApolloResponse}
 * @since 1.0.0
 */
@Getter
public class ApolloRequest<T extends ApolloResponse> {

    /**
     * The timeout for the {@link ApolloRequest}.
     *
     * @since 1.0.0
     */
    public static final long TIMEOUT = 5_000L;

    /**
     * The unique identifier for the {@link ApolloRequest}.
     *
     * @since 1.0.0
     */
    private final UUID packetId;

    /**
     * The time when the {@link ApolloRequest} was sent.
     *
     * @since 1.0.0
     */
    private final long sentTime;

    /**
     * Constructs a new {@link ApolloRequest}.
     *
     * @since 1.0.0
     */
    public ApolloRequest() {
        this.packetId = UUID.randomUUID();
        this.sentTime = System.currentTimeMillis();
    }

    /**
     * Responds to the {@link ApolloRequest} with an {@link ApolloRequest}.
     *
     * @param response the response
     * @since 1.0.0
     */
    public void respond(ApolloResponse response) {
        if(System.currentTimeMillis() - this.sentTime > TIMEOUT) {
            return;
        }

        response.setPacketId(this.packetId);
    }
}

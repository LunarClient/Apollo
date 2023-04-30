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
    @Getter private final UUID requestId;

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
        this.requestId = UUID.randomUUID();
        this.sentTime = System.currentTimeMillis();
    }

}

package com.lunarclient.apollo.roundtrip;

import java.util.UUID;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Represents an Apollo Response.
 *
 * @since 1.0.0
 */
@Getter
@SuperBuilder
public class ApolloResponse {

    /**
     * The {@link ApolloRequest} identifier.
     *
     * @since 1.0.0
     */
    UUID packetId;

}

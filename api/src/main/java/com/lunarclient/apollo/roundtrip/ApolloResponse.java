package com.lunarclient.apollo.roundtrip;

import java.util.UUID;
import lombok.Data;

/**
 * Represents an Apollo Response.
 *
 * @since 1.0.0
 */
@Data
public class ApolloResponse {

    /**
     * The {@link ApolloRequest} identifier.
     *
     * @since 1.0.0
     */
    private UUID packetId;

}

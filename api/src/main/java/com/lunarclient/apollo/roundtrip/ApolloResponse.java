package com.lunarclient.apollo.roundtrip;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents an Apollo Response.
 */
@Getter @Setter
public class ApolloResponse {

    /**
     * The {@link ApolloRequest} identifier.
     */
    private UUID packetId;
}

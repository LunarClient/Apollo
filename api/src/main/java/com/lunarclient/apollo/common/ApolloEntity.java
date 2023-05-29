package com.lunarclient.apollo.common;

import java.util.UUID;
import lombok.Value;

/**
 * Represents an entity which can be shown on the client.
 *
 * @since 1.0.0
 */
@Value
public class ApolloEntity {

    /**
     * Returns the {@link int} entity identifier.
     *
     * @return the entity identifier
     * @since 1.0.0
     */
    int entityId;

    /**
     * Returns the {@link UUID} entity identifier.
     *
     * @return the entity unique identifier
     * @since 1.0.0
     */
    UUID entityUuid;

}

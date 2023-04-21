package com.lunarclient.apollo.player.ui.misc;

import lombok.Value;

import java.util.UUID;

/**
 * Represents an entity
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class Entity {

    /**
     * Returns the entity {@link UUID} uuid.
     *
     * @return the entity uuid
     * @since 1.0.0
     */
    UUID id;

}

package com.moonsworth.apollo.player.ui.misc;

import java.util.UUID;
import lombok.Value;

/**
 * Represents an entity which can be flipped.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class FlippedEntity {

    /**
     * Returns the flipped entity {@link UUID} uuid.
     *
     * @return the entity uuid
     * @since 1.0.0
     */
    UUID entity;

    /**
     * Returns the entity {@link Boolean} flipped state.
     *
     * @return the flipped state
     * @since 1.0.0
     */
    boolean flipped;

}

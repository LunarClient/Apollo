package com.moonsworth.apollo.player.ui.misc;

import lombok.Value;

import java.util.UUID;

/**
 * Represents a rainbow sheep.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class RainbowSheep {

    /**
     * Returns the rainbow sheep {@link UUID} uuid.
     *
     * @return the sheep uuid
     * @since 1.0.0
     */
    UUID entity;

    /**
     * Returns the rainbow sheep {@link Boolean} rainbow state.
     *
     * @return the rainbow state
     * @since 1.0.0
     */
    boolean rainbow;

}

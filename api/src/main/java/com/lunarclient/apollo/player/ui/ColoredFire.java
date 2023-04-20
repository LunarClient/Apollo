package com.lunarclient.apollo.player.ui;

import java.awt.Color;
import java.util.UUID;
import lombok.Value;

/**
 * Represents a colored fire for a player which can be shown on the client.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class ColoredFire {

    /**
     * Returns the colored fire player {@link UUID} uuid.
     *
     * @return the player uuid
     * @since 1.0.0
     */
    UUID player;

    /**
     * Returns the fire {@link Color}.
     *
     * @return the fire color
     * @since 1.0.0
     */
    Color color;
}

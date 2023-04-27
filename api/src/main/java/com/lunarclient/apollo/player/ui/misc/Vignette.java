package com.lunarclient.apollo.player.ui.misc;

import lombok.Value;

/**
 * Represents a vignette which can be shown on the client.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class Vignette {

    /**
     * Returns the vignette {@link String} resource location.
     *
     * <p>Represents an icon that will appear for the player.</p>
     *
     * @return the resource location
     * @since 1.0.0
     */
    String resourceLocation;

    /**
     * Returns the vignette {@link Float} opacity.
     *
     * <p>Set to 0 to hide the vignette.</p>
     *
     * @return the opacity
     * @since 1.0.0
     */
    float opacity;

}

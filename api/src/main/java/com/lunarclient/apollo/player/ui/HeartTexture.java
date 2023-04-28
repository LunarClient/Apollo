package com.lunarclient.apollo.player.ui;

import lombok.Builder;
import lombok.Getter;

/**
 * Represents a heart texture which can be shown on the client.
 *
 * @since 1.0.0
 */
@Getter
@Builder(setterPrefix = "with")
public class HeartTexture {

    /**
     * Returns the heart texture {@link HeartTexture.Type} type.
     *
     * @return the heart texture type
     * @since 1.0.0
     */
    HeartTexture.Type type;

    /**
     * Returns the heart texture {@link Integer} x location.
     *
     * <p>Represents the start of the x coordinate in the 'icons.png' for your
     * extra hearts. The image can be extended as much as needed, but keep in
     * mind that a 1/2 heart and damage taken variation of every heart is required.</p>
     *
     * <p>The location will be calculated as follows: startOfImage + (location * 2 + healing) * 9
     * With the startOfImage being set to 16 by default.</p>
     *
     * <p>For example: Poison hearts location is 4, while for Wither hearts is 7.</p>
     *
     * @return the heart texture x location
     * @since 1.0.0
     */
    int locationX;

    /**
     * Represents the heart texture type.
     *
     * @since 1.0.0
     */
    public enum Type {
        NONE,
        ABSORPTION,
        HEALING,
        HEALING_AND_ABSORPTION
    }

}

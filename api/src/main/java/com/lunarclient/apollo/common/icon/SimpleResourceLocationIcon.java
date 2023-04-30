package com.lunarclient.apollo.common.icon;

import lombok.Builder;
import lombok.Getter;

/**
 * Represents a simple resource location icon.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public class SimpleResourceLocationIcon extends Icon {

    /**
     * Returns the icon {@link String} resource location.
     *
     * <p>Represents an icon that will appear for the player.</p>
     *
     * @return the icon resource location
     * @since 1.0.0
     */
    String resourceLocation;

    /**
     * Returns the icon {@link Integer} size.
     *
     * @return the icon size
     * @since 1.0.0
     */
    int size;

}

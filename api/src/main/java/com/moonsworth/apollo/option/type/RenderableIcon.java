package com.moonsworth.apollo.option.type;

import lombok.Value;

/**
 * Represents a renderable icon which can be shown on the client.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class RenderableIcon {

    /**
     * Returns the renderable icon {@link String} resource location.
     * <p>
     * Represents an icon that will appear for the player
     *
     * @return the renderable icon resource location
     * @since 1.0.0
     */
    String resourceLocation;

    /**
     * Returns the renderable icon {@link Integer} size.
     *
     * @return the renderale icon size
     * @since 1.0.0
     */
    int size;

    /**
     * Returns the renderable icon {@link IconSpecifications} specifications.
     *
     * @return the renderable icon specifications
     * @since 1.0.0
     */
    IconSpecifications specifications;
}

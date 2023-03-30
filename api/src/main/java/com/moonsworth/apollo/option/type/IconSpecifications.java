package com.moonsworth.apollo.option.type;

import lombok.Value;

/**
 * Represents a renderable icon specifications.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class IconSpecifications {

    /**
     * Returns the icon width {@link Float}.
     * <p>
     * Size of the image width (in pixels)
     *
     * @return the icon width
     * @since 1.0.0
     */
    float width;

    /**
     * Returns the icon height {@link Float}.
     * <p>
     * Size of the image height (in pixels)
     *
     * @return the icon height
     * @since 1.0.0
     */
    float height;

    /**
     * Returns the icon min u {@link Float}.
     * <p>
     * Range of 0-1 (the x location on a TextureAtlas)
     *
     * @return the icon min u
     * @since 1.0.0
     */
    float minU;

    /**
     * Returns the icon max u {@link Float}.
     * <p>
     * Range of 0-1 (the x location on a TextureAtlas)
     *
     * @return the icon max u
     * @since 1.0.0
     */
    float maxU;

    /**
     * Returns the icon min v {@link Float}.
     * <p>
     * Range of 0-1 (the y location on a TextureAtlas)
     *
     * @return the icon min v
     * @since 1.0.0
     */
    float minV;

    /**
     * Returns the icon max v {@link Float}.
     * <p>
     * Range of 0-1 (the y location on a TextureAtlas)
     *
     * @return the icon max v
     * @since 1.0.0
     */
    float maxV;
}

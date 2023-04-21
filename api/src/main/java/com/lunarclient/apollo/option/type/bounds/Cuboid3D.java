package com.lunarclient.apollo.option.type.bounds;

import lombok.Value;

/**
 * Represents a 3D cuboid.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class Cuboid3D {

    /**
     * Returns the cuboid {@link Double} start x.
     *
     * @return the cuboid start x
     * @since 1.0.0
     */
    double minX;

    /**
     * Returns the cuboid {@link Double} start y.
     *
     * @return the cuboid start y
     * @since 1.0.0
     */
    double minY;

    /**
     * Returns the cuboid {@link Double} start z.
     *
     * @return the cuboid start z
     * @since 1.0.0
     */
    double minZ;

    /**
     * Returns the cuboid {@link Double} end x.
     *
     * @return the cuboid end x
     * @since 1.0.0
     */
    double maxX;

    /**
     * Returns the cuboid {@link Double} end y.
     *
     * @return the cuboid end y
     * @since 1.0.0
     */
    double maxY;

    /**
     * Returns the cuboid {@link Double} end z.
     *
     * @return the cuboid end z
     * @since 1.0.0
     */
    double maxZ;
}

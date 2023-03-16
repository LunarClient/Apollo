package com.moonsworth.apollo.world;

import lombok.Value;

/**
 * Represents a location in the world.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class ApolloLocation {

    /**
     * Returns the world name for this location.
     *
     * @return the world name
     * @since 1.0.0
     */
    String world;

    /**
     * Returns the {@code double} X coordinate for this location.
     *
     * @return the x coordinate
     * @since 1.0.0
     */
    double x;

    /**
     * Returns the {@code double} Y coordinate for this location.
     *
     * @return the y coordinate
     * @since 1.0.0
     */
    double y;

    /**
     * Returns the {@code double} Z coordinate for this location.
     *
     * @return the z coordinate
     * @since 1.0.0
     */
    double z;

}

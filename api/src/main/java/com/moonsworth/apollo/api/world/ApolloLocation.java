package com.moonsworth.apollo.api.world;

/**
 * Represents a location in the world.
 *
 * @since 1.0.0
 */
public interface ApolloLocation {

    /**
     * Returns the world name for this location.
     *
     * @return the world name
     * @since 1.0.0
     */
    String getWorld();

    /**
     * Returns the {@code double} X coordinate for this location.
     *
     * @return the x coordinate
     * @since 1.0.0
     */
    double getX();

    /**
     * Returns the {@code double} Y coordinate for this location.
     *
     * @return the y coordinate
     * @since 1.0.0
     */
    double getY();

    /**
     * Returns the {@code double} Z coordinate for this location.
     *
     * @return the z coordinate
     * @since 1.0.0
     */
    double getZ();

}

package com.moonsworth.apollo.player.ui;

import com.moonsworth.apollo.world.ApolloBlockLocation;
import lombok.Value;

import java.awt.*;

/**
 * Represents a waypoint which can be shown on the client.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class Waypoint {

    /**
     * Returns the waypoint {@link String} name.
     *
     * @return the waypoint name
     * @since 1.0.0
     */
    String name;

    /**
     * Returns the waypoint {@link ApolloBlockLocation}.
     *
     * @return the waypoint block location
     * @since 1.0.0
     */
    ApolloBlockLocation location;

    /**
     * Returns the waypoint {@link Color}.
     *
     * @return the waypoint color
     * @since 1.0.0
     */
    Color color;

    /**
     * Returns the waypoint {@link Boolean} forced state.
     * <p>
     * If the waypoint is forced, the player can't remove
     * the waypoint but can hide it
     *
     * @return the waypoint forced state
     * @since 1.0.0
     */
    boolean forced;

    /**
     * Returns the waypoint {@link Boolean} visible state.
     *
     * @return the waypoint visibility state
     * @since 1.0.0
     */
    boolean visible;
}

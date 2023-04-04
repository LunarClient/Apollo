package com.moonsworth.apollo.player.ui;

import java.awt.Color;
import lombok.Value;

/**
 * Represents a border which can be shown on the client.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class Border {

    /**
     * Returns the border {@link String} id.
     *
     * @return the border id
     * @since 1.0.0
     */
    String id;

    /**
     * Returns the border {@link String} world name.
     *
     * @return the border world name
     * @since 1.0.0
     */
    String world;

    /**
     * Returns the border {@link Boolean} cancel entry state.
     *
     * @return the border cancel entry state
     * @since 1.0.0
     */
    boolean cancelEntry;

    /**
     * Returns the border {@link Boolean} cancel exit state.
     *
     * @return the border cancel exit state
     * @since 1.0.0
     */
    boolean cancelExit;

    /**
     * Returns the border {@link Boolean} can shrink or expand state.
     *
     * @return the border can shrink or expand state
     * @since 1.0.0
     */
    boolean canShrinkOrExpand;

    /**
     * Returns the border {@link Color}.
     *
     * @return the border color
     * @since 1.0.0
     */
    Color color;

    // TODO Do we want to use a Cuboid for this?
    /**
     * Returns the border {@link Double} start x.
     *
     * @return the border start x
     * @since 1.0.0
     */
    double minX;

    /**
     * Returns the border {@link Double} start z.
     *
     * @return the border start z
     * @since 1.0.0
     */
    double minZ;

    /**
     * Returns the border {@link Double} end x.
     *
     * @return the border end x
     * @since 1.0.0
     */
    double maxX;

    /**
     * Returns the border {@link Double} end z.
     *
     * @return the border end z
     * @since 1.0.0
     */
    double maxZ;

    /**
     * Returns the border {@link Integer} duration represented in ticks
     *
     * @return the border duration
     * @since 1.0.0
     */
    int duration;
}

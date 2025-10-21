/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2023 Moonsworth
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.lunarclient.apollo.mods.impl;

import com.lunarclient.apollo.option.NumberOption;
import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * Create a waypoint anywhere in the world that will show how for away from a location you are as well as optionally render the name and a beam.
 *
 * @since 1.0.0
 */
public final class ModWaypoints {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("waypoints", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Allows you to click coordinates in chat and create a new waypoint from them.
     *
     * @since 1.1.6
     */
    public static final SimpleOption<Boolean> ADD_WAYPOINTS_FROM_CHAT = SimpleOption.<Boolean>builder()
        .comment("Allows you to click coordinates in chat and create a new waypoint from them")
        .node("waypoints", "add-waypoints-from-chat").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Automatically create a waypoint when you die.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DEATH_WAYPOINT = SimpleOption.<Boolean>builder()
        .comment("Automatically create a waypoint when you die")
        .node("waypoints", "death-waypoint").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Limit the amount of waypoints that can be created, removing the oldest one when going over the limit.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> LIMIT_DEATH_WAYPOINTS = SimpleOption.<Boolean>builder()
        .comment("Limit the amount of waypoints that can be created, removing the oldest one when going over the limit")
        .node("waypoints", "limit-death-waypoints").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Maximum amount of Death Waypoints you will see at one time.
     *
     * @since 1.1.9
     */
    public static final NumberOption<Integer> MAX_DEATH_WAYPOINTS = NumberOption.<Integer>number()
        .comment("Maximum amount of Death Waypoints you will see at one time")
        .node("waypoints", "max-death-waypoints").type(TypeToken.get(Integer.class))
        .min(1).max(20)
        .defaultValue(3)
        .notifyClient()
        .build();

    /**
     * Enables beams for waypoints in the world.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> WAYPOINT_BEAMS = SimpleOption.<Boolean>builder()
        .comment("Enables beams for waypoints in the world")
        .node("waypoints", "waypoint-beams").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Adds a box around the waypoint label in the world.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BOX_BORDER = SimpleOption.<Boolean>builder()
        .comment("Adds a box around the waypoint label in the world")
        .node("waypoints", "box-border").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Adds a shadow to text.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> TEXT_SHADOW = SimpleOption.<Boolean>builder()
        .comment("Adds a shadow to text")
        .node("waypoints", "text-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Increase the size of the labels box.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> BOX_PADDING = NumberOption.<Float>number()
        .comment("Increase the size of the labels box")
        .node("waypoints", "box-padding").type(TypeToken.get(Float.class))
        .min(1.0F).max(8.0F)
        .defaultValue(4.0F)
        .notifyClient()
        .build();

    /**
     * Show simpler text icons above each waypoint.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_ICONS = SimpleOption.<Boolean>builder()
        .comment("Show simpler text icons above each waypoint")
        .node("waypoints", "show-icons").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Scale the box containing the waypoints name in the world.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> LABEL_SCALE = NumberOption.<Float>number()
        .comment("Scale the box containing the waypoints name in the world")
        .node("waypoints", "label-scale").type(TypeToken.get(Float.class))
        .min(0.1F).max(2.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * Scale the box containing the waypoints icon in the world.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> ICON_SCALE = NumberOption.<Float>number()
        .comment("Scale the box containing the waypoints icon in the world")
        .node("waypoints", "icon-scale").type(TypeToken.get(Float.class))
        .min(0.1F).max(3.0F)
        .defaultValue(1.5F)
        .notifyClient()
        .build();

    /**
     * Show an outline around the block the waypoint is positioned on.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HIGHLIGHT_WAYPOINT_BLOCK = SimpleOption.<Boolean>builder()
        .comment("Show an outline around the block the waypoint is positioned on")
        .node("waypoints", "highlight-waypoint-block").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * The width of the outline around the block being highlighted.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> HIGHLIGHT_WAYPOINT_BLOCK_LINE_WIDTH = NumberOption.<Float>number()
        .comment("The width of the outline around the block being highlighted")
        .node("waypoints", "highlight-waypoint-block-line-width").type(TypeToken.get(Float.class))
        .min(1.5F).max(7.5F)
        .defaultValue(4.0F)
        .notifyClient()
        .build();

    /**
     * Show the distance of a waypoint in the world.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> WAYPOINT_SHOW_DISTANCE = SimpleOption.<Boolean>builder()
        .comment("Show the distance of a waypoint in the world")
        .node("waypoints", "waypoint-show-distance").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Waypoints will only be displayed when your camera is facing towards their general direction.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ONLY_SHOW_WHEN_LOOKING_NEAR = SimpleOption.<Boolean>builder()
        .comment("Waypoints will only be displayed when your camera is facing towards their general direction")
        .node("waypoints", "only-show-when-looking-near").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Adds a waypoint if coordinates are clicked in chat.
     *
     * @since 1.0.0
     */
    @Deprecated
    public static final SimpleOption<Boolean> ADD_FROM_CHAT = SimpleOption.<Boolean>builder()
        .comment("Adds a waypoint if coordinates are clicked in chat.")
        .node("waypoints", "add-from-chat").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    private ModWaypoints() {
    }

}

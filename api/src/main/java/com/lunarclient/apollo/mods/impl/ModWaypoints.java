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
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DEATH_WAYPOINT = SimpleOption.<Boolean>builder()
        .node("waypoints", "death-waypoint").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> WAYPOINT_BEAMS = SimpleOption.<Boolean>builder()
        .node("waypoints", "waypoint-beams").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BOX_BORDER = SimpleOption.<Boolean>builder()
        .node("waypoints", "box-border").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> TEXT_SHADOW = SimpleOption.<Boolean>builder()
        .node("waypoints", "text-shadow").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> BOX_PADDING = NumberOption.<Float>number()
        .node("waypoints", "box-padding").type(TypeToken.get(Float.class))
        .min(1.0F).max(8.0F)
        .notifyClient()
        .build();

    /**
     * Only show each waypoint when looking near then in the world.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ONLY_SHOW_WHEN_LOOKING_NEAR = SimpleOption.<Boolean>builder()
        .comment("Only show each waypoint when looking near then in the world")
        .node("waypoints", "only-show-when-looking-near").type(TypeToken.get(Boolean.class))
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
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> LABEL_SCALE = NumberOption.<Float>number()
        .node("waypoints", "label-scale").type(TypeToken.get(Float.class))
        .min(0.1F).max(2.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> ICON_SCALE = NumberOption.<Float>number()
        .node("waypoints", "icon-scale").type(TypeToken.get(Float.class))
        .min(0.1F).max(3.0F)
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

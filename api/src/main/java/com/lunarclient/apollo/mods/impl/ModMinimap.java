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
import java.awt.Color;

/**
 * Display a miniature map of the world on your HUD.
 *
 * @since 1.0.0
 */
public final class ModMinimap {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("minimap", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> SCALE = NumberOption.<Float>number()
        .node("minimap", "scale").type(TypeToken.get(Float.class))
        .min(0.25F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Integer> MAP_WIDTH = NumberOption.<Integer>number()
        .node("minimap", "map-width").type(TypeToken.get(Integer.class))
        .min(1).max(16)
        .defaultValue(8)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Integer> MAP_HEIGHT = NumberOption.<Integer>number()
        .node("minimap", "map-height").type(TypeToken.get(Integer.class))
        .min(1).max(16)
        .defaultValue(8)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> MAP_ZOOM = NumberOption.<Float>number()
        .node("minimap", "map-zoom").type(TypeToken.get(Float.class))
        .min(1.0F).max(2.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ROTATE_WITH_PLAYER = SimpleOption.<Boolean>builder()
        .node("minimap", "rotate-with-player").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * With this enabled, the info text is scaled down to the width of the minimap.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> FIT_TEXT_TO_WIDTH = SimpleOption.<Boolean>builder()
        .comment("With this enabled, the info text is scaled down to the width of the minimap")
        .node("minimap", "fit-text-to-width").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BORDER = SimpleOption.<Boolean>builder()
        .node("minimap", "border").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> BORDER_COLOR = SimpleOption.<Color>builder()
        .node("minimap", "border-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(0, 0, 0, 159))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> BORDER_THICKNESS = NumberOption.<Float>number()
        .node("minimap", "border-thickness").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> COMPASS = SimpleOption.<Boolean>builder()
        .node("minimap", "compass").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> COMPASS_COLOR = SimpleOption.<Color>builder()
        .node("minimap", "compass-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> COMPASS_SHADOW = SimpleOption.<Boolean>builder()
        .node("minimap", "compass-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> PLAYER_MARKER_COLOR = SimpleOption.<Color>builder()
        .node("minimap", "player-marker-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(0, 0, 255))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> PLAYER_MARKER_SIZE = NumberOption.<Float>number()
        .node("minimap", "player-marker-size").type(TypeToken.get(Float.class))
        .min(1.0F).max(10.0F)
        .defaultValue(5.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> ENTITY_MARKER_OPACITY = NumberOption.<Float>number()
        .node("minimap", "entity-marker-opacity").type(TypeToken.get(Float.class))
        .min(0.0F).max(1.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> ENTITY_MARKER_SIZE = NumberOption.<Float>number()
        .node("minimap", "entity-marker-size").type(TypeToken.get(Float.class))
        .min(1.0F).max(10.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENTITY_MARKER_SHADOW = SimpleOption.<Boolean>builder()
        .node("minimap", "entity-marker-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_COORDINATES = SimpleOption.<Boolean>builder()
        .node("minimap", "show-coordinates").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_BIOME = SimpleOption.<Boolean>builder()
        .node("minimap", "show-biome").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> PRESET_BIOME_COLOR = SimpleOption.<Boolean>builder()
        .node("minimap", "preset-biome-color").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_CLOCK = SimpleOption.<Boolean>builder()
        .node("minimap", "show-clock").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_AM_PM = SimpleOption.<Boolean>builder()
        .node("minimap", "show-am-pm").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> MILITARY_TIME = SimpleOption.<Boolean>builder()
        .node("minimap", "military-time").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.4
     */
    public static final SimpleOption<Boolean> SHOW_DISTANT_WAYPOINTS = SimpleOption.<Boolean>builder()
        .node("minimap", "show-distant-waypoints").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    private ModMinimap() {
    }

}

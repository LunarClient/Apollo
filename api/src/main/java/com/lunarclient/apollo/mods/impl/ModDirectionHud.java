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
 * Display your cardinal direction on the HUD.
 *
 * @since 1.0.0
 */
public final class ModDirectionHud {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("direction-hud", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> SCALE = NumberOption.<Float>number()
        .node("direction-hud", "scale").type(TypeToken.get(Float.class))
        .min(0.25F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> WIDTH = NumberOption.<Float>number()
        .node("direction-hud", "width").type(TypeToken.get(Float.class))
        .min(168.0F).max(448.0F)
        .defaultValue(280.0F)
        .notifyClient()
        .build();

    /**
     * Adds a shadow to text.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> TEXT_SHADOW = SimpleOption.<Boolean>builder()
        .comment("Adds a shadow to text")
        .node("direction-hud", "text-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Show the cardinal directions in BOLD.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BOLD_DIRECTIONS = SimpleOption.<Boolean>builder()
        .comment("Show the cardinal directions in BOLD.")
        .node("direction-hud", "bold-directions").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BACKGROUND = SimpleOption.<Boolean>builder()
        .node("direction-hud", "background").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BORDER = SimpleOption.<Boolean>builder()
        .node("direction-hud", "border").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> BORDER_THICKNESS = NumberOption.<Float>number()
        .node("direction-hud", "border-thickness").type(TypeToken.get(Float.class))
        .min(0.5F).max(3.0F)
        .defaultValue(0.5F)
        .notifyClient()
        .build();

    /**
     * Choose whether to show the Direction HUD when TAB is open.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> SHOW_WITH_TAB = SimpleOption.<Boolean>builder()
        .comment("Choose whether to show the Direction HUD when TAB is open.")
        .node("direction-hud", "show-with-tab").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_MARKER = SimpleOption.<Boolean>builder()
        .node("direction-hud", "show-marker").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_MARKER_VALUE = SimpleOption.<Boolean>builder()
        .node("direction-hud", "show-marker-value").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> SHOW_WAYPOINTS = SimpleOption.<Boolean>builder()
        .node("direction-hud", "show-waypoints").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> SHOW_TEAMMATES = SimpleOption.<Boolean>builder()
        .node("direction-hud", "show-teammates").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Show markers from the Markers Mod.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> SHOW_EXTERNAL_MARKERS = SimpleOption.<Boolean>builder()
        .comment("Show markers from the Markers Mod.")
        .node("direction-hud", "show-external-markers").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> BACKGROUND_COLOR = SimpleOption.<Color>builder()
        .node("direction-hud", "background-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(0, 0, 0, 111))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> BORDER_COLOR = SimpleOption.<Color>builder()
        .node("direction-hud", "border-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(0, 0, 0, 159))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> MARKER_COLOR = SimpleOption.<Color>builder()
        .node("direction-hud", "marker-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> DIRECTION_COLOR = SimpleOption.<Color>builder()
        .node("direction-hud", "direction-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    @Deprecated
    public static final SimpleOption<Boolean> USE_LEGACY_STYLE = SimpleOption.<Boolean>builder()
        .node("direction-hud", "use-legacy-style").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    private ModDirectionHud() {
    }

}

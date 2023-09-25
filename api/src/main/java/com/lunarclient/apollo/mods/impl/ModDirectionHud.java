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
    public static NumberOption<Float> SCALE = NumberOption.<Float>number()
        .node("direction-hud", "scale").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.5F).max(1.5F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Float> WIDTH = NumberOption.<Float>number()
        .node("direction-hud", "width").type(TypeToken.get(Float.class))
        .defaultValue(280.0F).min(168.0F).max(448.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> TEXT_SHADOW = SimpleOption.<Boolean>builder()
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
    public static NumberOption<Float> BORDER_THICKNESS = NumberOption.<Float>number()
        .node("direction-hud", "border-thickness").type(TypeToken.get(Float.class))
        .defaultValue(0.5F).min(0.5F).max(3.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> USE_LEGACY_STYLE = SimpleOption.<Boolean>builder()
        .node("direction-hud", "use-legacy-style").type(TypeToken.get(Boolean.class))
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
     * @since 1.0.0
     */
    public static NumberOption<Integer> BACKGROUND_COLOR = NumberOption.<Integer>number()
        .node("direction-hud", "background-color").type(TypeToken.get(Integer.class))
        .defaultValue(0x6F000000).min(0x80000000).max(0x7FFFFFFF)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> BORDER_COLOR = NumberOption.<Integer>number()
        .node("direction-hud", "border-color").type(TypeToken.get(Integer.class))
        .defaultValue(0x9F000000).min(0x80000000).max(0x7FFFFFFF)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> MARKER_COLOR = NumberOption.<Integer>number()
        .node("direction-hud", "marker-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> DIRECTION_COLOR = NumberOption.<Integer>number()
        .node("direction-hud", "direction-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .notifyClient()
        .build();

    private ModDirectionHud() {
    }

}

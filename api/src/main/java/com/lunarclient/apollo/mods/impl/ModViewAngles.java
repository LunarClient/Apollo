/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2026 Moonsworth
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
 * Display your yaw and pitch, with optional flight targets, speed, and altitude.
 *
 * @since %release_version%
 */
public final class ModViewAngles {

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("view-angles", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> SCALE = NumberOption.<Float>number()
        .node("view-angles", "scale").type(TypeToken.get(Float.class))
        .min(0.25F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Integer> DECIMAL_PLACES = NumberOption.<Integer>number()
        .node("view-angles", "decimal-places").type(TypeToken.get(Integer.class))
        .min(0).max(10)
        .defaultValue(2)
        .notifyClient()
        .build();

    /**
     * Shows your left and right rotation.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_YAW = SimpleOption.<Boolean>builder()
        .comment("Shows your left and right rotation.")
        .node("view-angles", "show-yaw").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Shows how far you look up or down.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_PITCH = SimpleOption.<Boolean>builder()
        .comment("Shows how far you look up or down.")
        .node("view-angles", "show-pitch").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> OPTIMAL_PITCH_INDICATOR = SimpleOption.<Boolean>builder()
        .node("view-angles", "optimal-pitch-indicator").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> ASCEND_TARGET = NumberOption.<Float>number()
        .node("view-angles", "ascend-target").type(TypeToken.get(Float.class))
        .min(-90.0F).max(0.0F)
        .defaultValue(-40.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> DESCEND_TARGET = NumberOption.<Float>number()
        .node("view-angles", "descend-target").type(TypeToken.get(Float.class))
        .min(0.0F).max(90.0F)
        .defaultValue(40.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_SPEED = SimpleOption.<Boolean>builder()
        .node("view-angles", "show-speed").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_ALTITUDE = SimpleOption.<Boolean>builder()
        .node("view-angles", "show-altitude").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_IN_FIRST_PERSON = SimpleOption.<Boolean>builder()
        .node("view-angles", "show-in-first-person").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_IN_THIRD_PERSON = SimpleOption.<Boolean>builder()
        .node("view-angles", "show-in-third-person").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_WHILE_ELYTRA_FLYING_ONLY = SimpleOption.<Boolean>builder()
        .node("view-angles", "show-while-elytra-flying-only").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> REVERSE_ORDER = SimpleOption.<Boolean>builder()
        .node("view-angles", "reverse-order").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Adds a shadow to text.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TEXT_SHADOW = SimpleOption.<Boolean>builder()
        .comment("Adds a shadow to text")
        .node("view-angles", "text-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BRACKETS = SimpleOption.<Boolean>builder()
        .node("view-angles", "brackets").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> BRACKET_COLOR = SimpleOption.<Color>builder()
        .node("view-angles", "bracket-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BACKGROUND = SimpleOption.<Boolean>builder()
        .node("view-angles", "background").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * If this is disabled the background will change size with the text.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> STATIC_BACKGROUND_WIDTH = SimpleOption.<Boolean>builder()
        .comment("If this is disabled the background will change size with the text.")
        .node("view-angles", "static-background-width").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * If this is disabled the background will change size with the text.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> STATIC_BACKGROUND_HEIGHT = SimpleOption.<Boolean>builder()
        .comment("If this is disabled the background will change size with the text.")
        .node("view-angles", "static-background-height").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Integer> BACKGROUND_WIDTH = NumberOption.<Integer>number()
        .node("view-angles", "background-width").type(TypeToken.get(Integer.class))
        .min(20).max(200)
        .defaultValue(80)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Integer> BACKGROUND_HEIGHT = NumberOption.<Integer>number()
        .node("view-angles", "background-height").type(TypeToken.get(Integer.class))
        .min(20).max(60)
        .defaultValue(30)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BORDER = SimpleOption.<Boolean>builder()
        .node("view-angles", "border").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> BORDER_THICKNESS = NumberOption.<Float>number()
        .node("view-angles", "border-thickness").type(TypeToken.get(Float.class))
        .min(0.5F).max(3.0F)
        .defaultValue(0.5F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> BORDER_COLOR = SimpleOption.<Color>builder()
        .node("view-angles", "border-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(0, 0, 0, 159))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> BACKGROUND_COLOR = SimpleOption.<Color>builder()
        .node("view-angles", "background-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(0, 0, 0, 111))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> LABEL_COLOR = SimpleOption.<Color>builder()
        .node("view-angles", "label-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 170, 0))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> VALUE_COLOR = SimpleOption.<Color>builder()
        .node("view-angles", "value-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(170, 170, 170))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> TEXT_COLOR = SimpleOption.<Color>builder()
        .node("view-angles", "text-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    private ModViewAngles() {
    }

}

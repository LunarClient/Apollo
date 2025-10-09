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
 * Displays your total and free memory usage.
 *
 * @since 1.0.0
 */
public final class ModMemory {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("memory", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> SCALE = NumberOption.<Float>number()
        .node("memory", "scale").type(TypeToken.get(Float.class))
        .min(0.25F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.3
     */
    public static final SimpleOption<Boolean> COLOR_BASED_ON_USAGE = SimpleOption.<Boolean>builder()
        .node("memory", "color-based-on-usage").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.3
     */
    public static final SimpleOption<Color> LOW_MEM_COLOR = SimpleOption.<Color>builder()
        .node("memory", "low-mem-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 0, 0))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.3
     */
    public static final SimpleOption<Color> MED_MEM_COLOR = SimpleOption.<Color>builder()
        .node("memory", "med-mem-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 0))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.3
     */
    public static final SimpleOption<Color> HIGH_MEM_COLOR = SimpleOption.<Color>builder()
        .node("memory", "high-mem-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(0, 255, 0))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> TEXT_COLOR = SimpleOption.<Color>builder()
        .node("memory", "text-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    /**
     * Adds a shadow to text.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> TEXT_SHADOW = SimpleOption.<Boolean>builder()
        .comment("Adds a shadow to text")
        .node("memory", "text-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BRACKETS = SimpleOption.<Boolean>builder()
        .node("memory", "brackets").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.6
     */
    public static final SimpleOption<Color> BRACKET_COLOR = SimpleOption.<Color>builder()
        .node("memory", "bracket-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BACKGROUND = SimpleOption.<Boolean>builder()
        .node("memory", "background").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * If this is disabled the background will change size with the text.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> STATIC_BACKGROUND_WIDTH = SimpleOption.<Boolean>builder()
        .comment("If this is disabled the background will change size with the text.")
        .node("memory", "static-background-width").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * If this is disabled the background will change size with the text.
     *
     * @since 1.1.6
     */
    public static final SimpleOption<Boolean> STATIC_BACKGROUND_HEIGHT = SimpleOption.<Boolean>builder()
        .comment("If this is disabled the background will change size with the text.")
        .node("memory", "static-background-height").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Integer> BACKGROUND_WIDTH = NumberOption.<Integer>number()
        .node("memory", "background-width").type(TypeToken.get(Integer.class))
        .min(46).max(62)
        .defaultValue(56)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Integer> BACKGROUND_HEIGHT = NumberOption.<Integer>number()
        .node("memory", "background-height").type(TypeToken.get(Integer.class))
        .min(10).max(22)
        .defaultValue(18)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BORDER = SimpleOption.<Boolean>builder()
        .node("memory", "border").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> BORDER_THICKNESS = NumberOption.<Float>number()
        .node("memory", "border-thickness").type(TypeToken.get(Float.class))
        .min(0.5F).max(3.0F)
        .defaultValue(0.5F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> BORDER_COLOR = SimpleOption.<Color>builder()
        .node("memory", "border-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(0, 0, 0, 159))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> BACKGROUND_COLOR = SimpleOption.<Color>builder()
        .node("memory", "background-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(0, 0, 0, 111))
        .notifyClient()
        .build();

    private ModMemory() {
    }

}

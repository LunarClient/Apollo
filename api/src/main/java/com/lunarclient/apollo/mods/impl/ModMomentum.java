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
 * Shows your current velocity.
 *
 * @since 1.0.0
 */
public final class ModMomentum {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("momentum", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Float> SCALE = NumberOption.<Float>number()
        .node("momentum", "scale").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.5F).max(1.5F)
        .notifyClient()
        .build();

    /**
     * If enabled then Y velocity is not used in the final speed.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> USE_GROUND_SPEED = SimpleOption.<Boolean>builder()
        .comment("If enabled then Y velocity is not used in the final speed.")
        .node("momentum", "use-ground-speed").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * If this is disabled then instant velocity is used.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> USE_AVERAGE_VELOCITY = SimpleOption.<Boolean>builder()
        .comment("If this is disabled then instant velocity is used")
        .node("momentum", "use-average-velocity").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Determines how many times a second velocity is calculated. Lower values will show a smoother velocity.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> AVERAGING_PERIOD = NumberOption.<Integer>number()
        .comment("Determines how many times a second velocity is calculated. Lower values will show a smoother velocity")
        .node("momentum", "averaging-period").type(TypeToken.get(Integer.class))
        .defaultValue(8).min(1).max(50)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> TEXT_SHADOW = SimpleOption.<Boolean>builder()
        .node("momentum", "text-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BACKGROUND = SimpleOption.<Boolean>builder()
        .node("momentum", "background").type(TypeToken.get(Boolean.class))
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
        .node("momentum", "static-background-width").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> BACKGROUND_WIDTH = NumberOption.<Integer>number()
        .node("momentum", "background-width").type(TypeToken.get(Integer.class))
        .defaultValue(56).min(50).max(62)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> BACKGROUND_HEIGHT = NumberOption.<Integer>number()
        .node("momentum", "background-height").type(TypeToken.get(Integer.class))
        .defaultValue(18).min(10).max(22)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BRACKETS = SimpleOption.<Boolean>builder()
        .node("momentum", "brackets").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BORDER = SimpleOption.<Boolean>builder()
        .node("momentum", "border").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Float> BORDER_THICKNESS = NumberOption.<Float>number()
        .node("momentum", "border-thickness").type(TypeToken.get(Float.class))
        .defaultValue(0.5F).min(0.5F).max(3.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> TEXT_COLOR = NumberOption.<Integer>number()
        .node("momentum", "text-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> BACKGROUND_COLOR = NumberOption.<Integer>number()
        .node("momentum", "background-color").type(TypeToken.get(Integer.class))
        .defaultValue(0x6F000000).min(0x80000000).max(0x7FFFFFFF)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> BORDER_COLOR = NumberOption.<Integer>number()
        .node("momentum", "border-color").type(TypeToken.get(Integer.class))
        .defaultValue(0x9F000000).min(0x80000000).max(0x7FFFFFFF)
        .notifyClient()
        .build();

    private ModMomentum() {
    }

}

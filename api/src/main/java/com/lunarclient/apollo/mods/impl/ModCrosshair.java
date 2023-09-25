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
 * Replace the default Minecraft crosshair with a customizable crosshair.
 *
 * @since 1.0.0
 */
public final class ModCrosshair {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("crosshair", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> CROSSHAIR_THICKNESS = NumberOption.<Integer>number()
        .node("crosshair", "crosshair-thickness").type(TypeToken.get(Integer.class))
        .defaultValue(1).min(1).max(5)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> CROSSHAIR_SIZE = NumberOption.<Integer>number()
        .node("crosshair", "crosshair-size").type(TypeToken.get(Integer.class))
        .defaultValue(4).min(0).max(8)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> CROSSHAIR_GAP = NumberOption.<Integer>number()
        .node("crosshair", "crosshair-gap").type(TypeToken.get(Integer.class))
        .defaultValue(0).min(0).max(8)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> CROSSHAIR_DOT = SimpleOption.<Boolean>builder()
        .node("crosshair", "crosshair-dot").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> CROSSHAIR_OUTLINE = SimpleOption.<Boolean>builder()
        .node("crosshair", "crosshair-outline").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Float> OUTLINE_THICKNESS = NumberOption.<Float>number()
        .node("crosshair", "outline-thickness").type(TypeToken.get(Float.class))
        .defaultValue(0.5F).min(0.0F).max(1.0F)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> OUTLINE_COLOR = NumberOption.<Integer>number()
        .node("crosshair", "outline-color").type(TypeToken.get(Integer.class))
        .defaultValue(0x88000000).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> CUSTOM_SCALE = SimpleOption.<Boolean>builder()
        .node("crosshair", "custom-scale").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> COLOR = NumberOption.<Integer>number()
        .node("crosshair", "color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> FRIENDLY_COLOR = NumberOption.<Integer>number()
        .node("crosshair", "friendly-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFF33FF33).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> ENEMY_COLOR = NumberOption.<Integer>number()
        .node("crosshair", "enemy-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFF3333).min(0x80000000).max(0x7FFFFFFF)
        .build();

    private ModCrosshair() {
    }

}

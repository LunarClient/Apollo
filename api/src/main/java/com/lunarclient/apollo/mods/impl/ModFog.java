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
 * Allows you to adjust the strength of fog for various fog types.
 *
 * @since 1.0.0
 */
public final class ModFog {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("fog", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> WATER_FOG_DENSITY = NumberOption.<Float>number()
        .node("fog", "water-fog-density").type(TypeToken.get(Float.class))
        .min(0.0F).max(1.8F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> RENDER_DISTANCE_FOG_DENSITY = NumberOption.<Float>number()
        .node("fog", "render-distance-fog-density").type(TypeToken.get(Float.class))
        .min(0.0F).max(1.95F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.1
     */
    public static final NumberOption<Float> ATMOSPHERIC_FOG_DENSITY = NumberOption.<Float>number()
        .node("fog", "atmospheric-fog-density").type(TypeToken.get(Float.class))
        .min(0.0F).max(1.95F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.1
     */
    public static final NumberOption<Float> DIMENSION_FOG_DENSITY = NumberOption.<Float>number()
        .node("fog", "dimension-fog-density").type(TypeToken.get(Float.class))
        .min(0.0F).max(1.95F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> RENDER_DISTANCE_FOG_COLOR_TOGGLE = SimpleOption.<Boolean>builder()
        .node("fog", "render-distance-fog-color-toggle").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> RENDER_DISTANCE_FOG_COLOR = SimpleOption.<Color>builder()
        .node("fog", "render-distance-fog-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(192, 216, 255, 0))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.1
     */
    public static final SimpleOption<Boolean> ATMOSPHERIC_FOG_COLOR_TOGGLE = SimpleOption.<Boolean>builder()
        .node("fog", "atmospheric-fog-color-toggle").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.1
     */
    public static final SimpleOption<Color> ATMOSPHERIC_FOG_COLOR = SimpleOption.<Color>builder()
        .node("fog", "atmospheric-fog-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(192, 208, 255, 0))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.1
     */
    public static final SimpleOption<Boolean> DIMENSION_FOG_COLOR_TOGGLE = SimpleOption.<Boolean>builder()
        .node("fog", "dimension-fog-color-toggle").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.1
     */
    public static final SimpleOption<Color> DIMENSION_FOG_COLOR = SimpleOption.<Color>builder()
        .node("fog", "dimension-fog-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(192, 208, 255, 0))
        .notifyClient()
        .build();

    private ModFog() {
    }

}

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
 * A mod class.
 *
 * @since 1.0.0
 */
public final class ModColorSaturation {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("color-saturation", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Float> COLOR_SATURATION_HUE = NumberOption.<Float>number()
        .node("color-saturation", "color-saturation-hue").type(TypeToken.get(Float.class))
        .defaultValue(0.0F).min(0.0F).max(10.0F)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Float> COLOR_SATURATION_SATURATION = NumberOption.<Float>number()
        .node("color-saturation", "color-saturation-saturation").type(TypeToken.get(Float.class))
        .defaultValue(5.0F).min(0.0F).max(10.0F)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Float> COLOR_SATURATION_BRIGHTNESS = NumberOption.<Float>number()
        .node("color-saturation", "color-saturation-brightness").type(TypeToken.get(Float.class))
        .defaultValue(5.0F).min(0.0F).max(10.0F)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Float> COLOR_SATURATION_CONTRAST = NumberOption.<Float>number()
        .node("color-saturation", "color-saturation-contrast").type(TypeToken.get(Float.class))
        .defaultValue(5.0F).min(0.0F).max(10.0F)
        .build();

    private ModColorSaturation() {
    }

}

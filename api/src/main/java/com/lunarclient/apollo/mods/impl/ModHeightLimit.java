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
 * Block overlay and HUD display to help you build up to map height limits.
 *
 * @since %release_version%
 */
public final class ModHeightLimit {

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("height-limit", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> SCALE = NumberOption.<Float>number()
        .node("height-limit", "scale").type(TypeToken.get(Float.class))
        .min(0.25F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * How far around you the block overlay is drawn.
     *
     * @since %release_version%
     */
    public static final NumberOption<Integer> RENDER_RANGE = NumberOption.<Integer>number()
        .comment("How far around you the block overlay is drawn")
        .node("height-limit", "render-range").type(TypeToken.get(Integer.class))
        .min(1).max(8)
        .defaultValue(4)
        .notifyClient()
        .build();

    /**
     * Display height limit information on the HUD.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HEIGHT_LIMIT_OVERLAY = SimpleOption.<Boolean>builder()
        .comment("Display height limit information on the HUD")
        .node("height-limit", "height-limit-overlay").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Adds a shadow to text.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TEXT_SHADOW = SimpleOption.<Boolean>builder()
        .comment("Adds a shadow to text")
        .node("height-limit", "text-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BACKGROUND = SimpleOption.<Boolean>builder()
        .node("height-limit", "background").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BORDER = SimpleOption.<Boolean>builder()
        .node("height-limit", "border").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> BORDER_THICKNESS = NumberOption.<Float>number()
        .node("height-limit", "border-thickness").type(TypeToken.get(Float.class))
        .min(0.5F).max(3.0F)
        .defaultValue(0.5F)
        .notifyClient()
        .build();

    /**
     * Automatically determine text alignment from position. Disable to manually set alignment.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> AUTO_ALIGN = SimpleOption.<Boolean>builder()
        .comment("Automatically determine text alignment from position. Disable to manually set alignment")
        .node("height-limit", "auto-align").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Distance changes color depending on how close you are to the height limit.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> AUTO_COLOR_DISTANCE = SimpleOption.<Boolean>builder()
        .comment("Distance changes color depending on how close you are to the height limit")
        .node("height-limit", "auto-color-distance").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> TITLE_COLOR = SimpleOption.<Color>builder()
        .node("height-limit", "title-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 85))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> TEXT_COLOR = SimpleOption.<Color>builder()
        .node("height-limit", "text-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> NUMBER_COLOR = SimpleOption.<Color>builder()
        .node("height-limit", "number-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(85, 255, 85))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> BACKGROUND_COLOR = SimpleOption.<Color>builder()
        .node("height-limit", "background-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(0, 0, 0, 111))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> BORDER_COLOR = SimpleOption.<Color>builder()
        .node("height-limit", "border-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(0, 0, 0, 159))
        .notifyClient()
        .build();

    private ModHeightLimit() {
    }

}

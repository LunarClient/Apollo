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
 * Use your scroll wheel to read overly long item descriptions.
 *
 * @since 1.0.0
 */
public final class ModScrollableTooltips {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("scrollable-tooltips", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * When a tooltip is sufficiently long, overflow off the bottom/right of the screen instead of the top/left.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> START_AT_TOP = SimpleOption.<Boolean>builder()
        .comment("When a tooltip is sufficiently long, overflow off the bottom/right of the screen instead of the top/left.")
        .node("scrollable-tooltips", "start-at-top").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> VERTICAL_KEYBIND = SimpleOption.<Boolean>builder()
        .node("scrollable-tooltips", "vertical-keybind").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> TOOLTIP_SCALE = NumberOption.<Float>number()
        .node("scrollable-tooltips", "tooltip-scale").type(TypeToken.get(Float.class))
        .min(0.25F).max(2.5F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * Let the user scroll whenever they want, regardless of whether or not a tooltip is sufficiently large.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TOOLTIP_FREE_SCROLL = SimpleOption.<Boolean>builder()
        .comment("Let the user scroll whenever they want, regardless of whether or not a tooltip is sufficiently large.")
        .node("scrollable-tooltips", "tooltip-free-scroll").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    @Deprecated
    public static final SimpleOption<Boolean> TEXT_SHADOW = SimpleOption.<Boolean>builder()
        .node("scrollable-tooltips", "text-shadow").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    @Deprecated
    public static final SimpleOption<Boolean> WRAP_TEXT = SimpleOption.<Boolean>builder()
        .node("scrollable-tooltips", "wrap-text").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    private ModScrollableTooltips() {
    }

}

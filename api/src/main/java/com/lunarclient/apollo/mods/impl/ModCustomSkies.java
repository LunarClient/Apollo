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

/**
 * Replace the overworld sky with a custom sky texture.
 *
 * @since %release_version%
 */
public final class ModCustomSkies {

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("custom-skies", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Integer> CUSTOM_SKY_PREVIEW = NumberOption.<Integer>number()
        .node("custom-skies", "custom-sky-preview").type(TypeToken.get(Integer.class))
        .min(-2147483648).max(2147483647)
        .defaultValue(0)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> DARKEN_AT_NIGHT = SimpleOption.<Boolean>builder()
        .node("custom-skies", "darken-at-night").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Picks another sky each time you enter a world or another dimension.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> RANDOM_SKY = SimpleOption.<Boolean>builder()
        .comment("Picks another sky each time you enter a world or another dimension.")
        .node("custom-skies", "random-sky").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_IN_OVERWORLD = SimpleOption.<Boolean>builder()
        .node("custom-skies", "show-in-overworld").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_IN_NETHER = SimpleOption.<Boolean>builder()
        .node("custom-skies", "show-in-nether").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_IN_END = SimpleOption.<Boolean>builder()
        .node("custom-skies", "show-in-end").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_IN_MODDED_DIMENSIONS = SimpleOption.<Boolean>builder()
        .node("custom-skies", "show-in-modded-dimensions").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Shows a notification when a sky is selected, saved or deleted.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_SKY_NOTIFICATIONS = SimpleOption.<Boolean>builder()
        .comment("Shows a notification when a sky is selected, saved or deleted.")
        .node("custom-skies", "show-sky-notifications").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    private ModCustomSkies() {
    }

}

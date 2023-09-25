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
 * Displays the fuse countdown above TNT.
 *
 * @since 1.0.0
 */
public final class ModTntCountdown {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("tnt-countdown", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Offset the default tnt delay in ticks. 1 second is 20 ticks.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> ADJUST_FUSE_TIME = NumberOption.<Integer>number()
        .comment("Offset the default tnt delay in ticks. 1 second is 20 ticks")
        .node("tnt-countdown", "adjust-fuse-time").type(TypeToken.get(Integer.class))
        .defaultValue(0).min(-80).max(80)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BACKGROUND = SimpleOption.<Boolean>builder()
        .node("tnt-countdown", "background").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> TEXT_SHADOW = SimpleOption.<Boolean>builder()
        .node("tnt-countdown", "text-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Should the color stay the same the entire time.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> STATIC_COUNTDOWN_COLOR = SimpleOption.<Boolean>builder()
        .comment("Should the color stay the same the entire time.")
        .node("tnt-countdown", "static-countdown-color").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> COLOR = NumberOption.<Integer>number()
        .node("tnt-countdown", "color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFF00FF00).min(0x80000000).max(0x7FFFFFFF)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> PREFIX_COLOR = NumberOption.<Integer>number()
        .node("tnt-countdown", "prefix-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .notifyClient()
        .build();

    private ModTntCountdown() {
    }

}

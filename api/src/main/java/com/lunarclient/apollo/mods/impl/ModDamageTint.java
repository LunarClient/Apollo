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
 * Adds a tint to the screen when you are low on health.
 *
 * @since 1.0.0
 */
public final class ModDamageTint {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("damage-tint", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> VIGNETTE_COLOR = SimpleOption.<Color>builder()
        .node("damage-tint", "vignette-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 0, 0))
        .notifyClient()
        .build();

    /**
     * Intensity of the tint.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> VIGNETTE_INTENSITY = NumberOption.<Float>number()
        .comment("Intensity of the tint.")
        .node("damage-tint", "vignette-intensity").type(TypeToken.get(Float.class))
        .min(0.0F).max(1.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * Show the tint when you have less than this amount of health as a percentage.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Integer> SHOW_VIGNETTE_BELOW = NumberOption.<Integer>number()
        .comment("Show the tint when you have less than this amount of health as a percentage.")
        .node("damage-tint", "show-vignette-below").type(TypeToken.get(Integer.class))
        .min(0).max(100)
        .defaultValue(100)
        .notifyClient()
        .build();

    /**
     * Plays a heartbeat, becoming faster the lower your health.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HEARTBEAT_AUDIO = SimpleOption.<Boolean>builder()
        .comment("Plays a heartbeat, becoming faster the lower your health.")
        .node("damage-tint", "heartbeat-audio").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    private ModDamageTint() {
    }

}

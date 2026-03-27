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
 * Allows servers to display items or abilities that are on cooldown on the HUD.
 *
 * @since 1.0.0
 */
public final class ModCooldowns {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("cooldowns", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> SCALE = NumberOption.<Float>number()
        .node("cooldowns", "scale").type(TypeToken.get(Float.class))
        .min(0.25F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> COOLDOWN_CIRCLE_START_COLOR = SimpleOption.<Color>builder()
        .node("cooldowns", "cooldown-circle-start-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(89, 89, 89, 153))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> COOLDOWN_CIRCLE_END_COLOR = SimpleOption.<Color>builder()
        .node("cooldowns", "cooldown-circle-end-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(229, 229, 229))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> COOLDOWN_EDGE_COLOR = SimpleOption.<Color>builder()
        .node("cooldowns", "cooldown-edge-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(64, 64, 64))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> TEXT_COLOR = SimpleOption.<Color>builder()
        .node("cooldowns", "text-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    private ModCooldowns() {
    }

}

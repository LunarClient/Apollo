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
 * Resize items that are commonly needed in UHC to make spotting them easier.
 *
 * @since 1.0.0
 */
public final class ModUhcOverlay {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("uhc-overlay", "enabled").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> GOLD_INGOT_SCALE = NumberOption.<Float>number()
        .node("uhc-overlay", "gold-ingot-scale").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> GOLD_NUGGET_SCALE = NumberOption.<Float>number()
        .node("uhc-overlay", "gold-nugget-scale").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> GOLD_ORE_SCALE = NumberOption.<Float>number()
        .node("uhc-overlay", "gold-ore-scale").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> GOLD_APPLE_SCALE = NumberOption.<Float>number()
        .node("uhc-overlay", "gold-apple-scale").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> SKULL_SCALE = NumberOption.<Float>number()
        .node("uhc-overlay", "skull-scale").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .notifyClient()
        .build();

    private ModUhcOverlay() {
    }

}

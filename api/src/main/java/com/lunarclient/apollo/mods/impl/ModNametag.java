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
 * A mod that allows you to modify nametags.
 *
 * @since 1.0.0
 */
public final class ModNametag {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("nametag", "enabled").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> NAMETAG_SHADOW = SimpleOption.<Boolean>builder()
        .node("nametag", "nametag-shadow").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * An option to see your own nametag in third person.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> NAMETAG = SimpleOption.<Boolean>builder()
        .comment("An option to see your own nametag in third person")
        .node("nametag", "nametag").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * Determines if we should send the message when nametags are toggled.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> TOGGLE_NAMETAGS = SimpleOption.<Boolean>builder()
        .comment("Determines if we should send the message when nametags are toggled.")
        .node("nametag", "toggle-nametags").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> NAMETAG_BACKGROUND_OPACITY = NumberOption.<Float>number()
        .node("nametag", "nametag-background-opacity").type(TypeToken.get(Float.class))
        .min(0.0F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * An option to toggle LC logos in nametags.
     *
     * @since 1.0.0
     */
    @Deprecated
    public static final SimpleOption<Boolean> NAMETAG_LOGO = SimpleOption.<Boolean>builder()
        .comment("An option to toggle LC logos in nametags")
        .node("nametag", "nametag-logo").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    private ModNametag() {
    }

}

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
 * Displays your coordinates on the HUD.
 *
 * @since 1.0.0
 */
public final class ModCoordinates {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("coordinates", "enabled").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> SCALE = NumberOption.<Float>number()
        .node("coordinates", "scale").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.5F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> TEXT_SHADOW = SimpleOption.<Boolean>builder()
        .node("coordinates", "text-shadow").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_WHILE_TYPING = SimpleOption.<Boolean>builder()
        .node("coordinates", "show-while-typing").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BACKGROUND = SimpleOption.<Boolean>builder()
        .node("coordinates", "background").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BORDER = SimpleOption.<Boolean>builder()
        .node("coordinates", "border").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> BORDER_THICKNESS = NumberOption.<Float>number()
        .node("coordinates", "border-thickness").type(TypeToken.get(Float.class))
        .min(0.5F).max(3.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> MOVE_CHILDREN_INDIVIDUALLY = SimpleOption.<Boolean>builder()
        .node("coordinates", "move-children-individually").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> BACKGROUND_COLOR = SimpleOption.<Color>builder()
        .node("coordinates", "background-color").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> BORDER_COLOR = SimpleOption.<Color>builder()
        .node("coordinates", "border-color").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    private ModCoordinates() {
    }

}

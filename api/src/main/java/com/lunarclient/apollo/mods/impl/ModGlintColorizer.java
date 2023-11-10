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

import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;
import java.awt.Color;

/**
 * Customize the color of enchantment glints.
 *
 * @since 1.0.0
 */
public final class ModGlintColorizer {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("glint-colorizer", "enabled").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * Use a custom blending algorithm which supports darker colours.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> USE_LUNAR_EQUATION = SimpleOption.<Boolean>builder()
        .comment("Use a custom blending algorithm which supports darker colours")
        .node("glint-colorizer", "use-lunar-equation").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * Recolor the glints of items.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> OVERRIDE_ITEM_GLINT = SimpleOption.<Boolean>builder()
        .comment("Recolor the glints of items")
        .node("glint-colorizer", "override-item-glint").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> ITEM_GLINT_LUNAR_COLOR = SimpleOption.<Color>builder()
        .node("glint-colorizer", "item-glint-lunar-color").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> ITEM_GLINT_VANILLA_COLOR = SimpleOption.<Color>builder()
        .node("glint-colorizer", "item-glint-vanilla-color").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * Recolor the glints of worn armor.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> OVERRIDE_ARMOR_GLINT = SimpleOption.<Boolean>builder()
        .comment("Recolor the glints of worn armor")
        .node("glint-colorizer", "override-armor-glint").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> ARMOR_GLINT_LUNAR = SimpleOption.<Color>builder()
        .node("glint-colorizer", "armor-glint-lunar").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> ARMOR_GLINT_VANILLA = SimpleOption.<Color>builder()
        .node("glint-colorizer", "armor-glint-vanilla").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    private ModGlintColorizer() {
    }

}

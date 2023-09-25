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
 * Allows you to customize the outline or add an overlay to the block you are pointing at.
 *
 * @since 1.0.0
 */
public final class ModBlockOutline {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("block-outline", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> BLOCK_OUTLINE_COLOR = NumberOption.<Integer>number()
        .node("block-outline", "block-outline-color").type(TypeToken.get(Integer.class))
        .defaultValue(0x66000000).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> BLOCK_OVERLAY_COLOR = NumberOption.<Integer>number()
        .node("block-outline", "block-overlay-color").type(TypeToken.get(Integer.class))
        .defaultValue(0x66000000).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Float> BLOCK_OUTLINE_WIDTH = NumberOption.<Float>number()
        .node("block-outline", "block-outline-width").type(TypeToken.get(Float.class))
        .defaultValue(2.0F).min(1.0F).max(10.0F)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BLOCK_OVERLAY = SimpleOption.<Boolean>builder()
        .node("block-outline", "block-overlay").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BLOCK_OUTLINE = SimpleOption.<Boolean>builder()
        .node("block-outline", "block-outline").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    private ModBlockOutline() {
    }

}

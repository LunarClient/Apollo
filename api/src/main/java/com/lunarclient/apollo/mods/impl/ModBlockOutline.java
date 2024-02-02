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
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BLOCK_OUTLINE = SimpleOption.<Boolean>builder()
        .node("block-outline", "block-outline").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> BLOCK_OUTLINE_WIDTH = NumberOption.<Float>number()
        .node("block-outline", "block-outline-width").type(TypeToken.get(Float.class))
        .min(1.0F).max(10.0F)
        .notifyClient()
        .build();

    /**
     * Primary block outline color.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> BLOCK_OUTLINE_COLOR = SimpleOption.<Color>builder()
        .comment("Primary block outline color")
        .node("block-outline", "block-outline-color").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * Secondary block outline color.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> BLOCK_OUTLINE_COLOR_END = SimpleOption.<Color>builder()
        .comment("Secondary block outline color")
        .node("block-outline", "block-outline-color-end").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * If the transparency should also blend between both outline colors.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BLOCK_OUTLINE_INTERPOLATE_ALPHA = SimpleOption.<Boolean>builder()
        .comment("If the transparency should also blend between both outline colors")
        .node("block-outline", "block-outline-interpolate-alpha").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * If the exact block bounding box should be used, this comes at a slight performance cost.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BLOCK_OUTLINE_ACCURATE = SimpleOption.<Boolean>builder()
        .comment("If the exact block bounding box should be used, this comes at a slight performance cost")
        .node("block-outline", "block-outline-accurate").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * If the outline colors should travel through the block.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BLOCK_OUTLINE_TRAVERSAL = SimpleOption.<Boolean>builder()
        .comment("If the outline colors should travel through the block")
        .node("block-outline", "block-outline-traversal").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> BLOCK_OUTLINE_TRAVERSAL_SPEED = NumberOption.<Float>number()
        .node("block-outline", "block-outline-traversal-speed").type(TypeToken.get(Float.class))
        .min(0.15F).max(5.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BLOCK_OVERLAY = SimpleOption.<Boolean>builder()
        .node("block-outline", "block-overlay").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * Primary block overlay color.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> BLOCK_OVERLAY_COLOR = SimpleOption.<Color>builder()
        .comment("Primary block overlay color")
        .node("block-outline", "block-overlay-color").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * Secondary block overlay color.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> BLOCK_OVERLAY_COLOR_END = SimpleOption.<Color>builder()
        .comment("Secondary block overlay color")
        .node("block-outline", "block-overlay-color-end").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * If the transparency should also blend between both overlay colors.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BLOCK_OVERLAY_INTERPOLATE_ALPHA = SimpleOption.<Boolean>builder()
        .comment("If the transparency should also blend between both overlay colors")
        .node("block-outline", "block-overlay-interpolate-alpha").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * If the exact block bounding box should be used, this comes at a slight performance cost.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BLOCK_OVERLAY_ACCURATE = SimpleOption.<Boolean>builder()
        .comment("If the exact block bounding box should be used, this comes at a slight performance cost")
        .node("block-outline", "block-overlay-accurate").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * If the overlay colors should travel through the block.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BLOCK_OVERLAY_TRAVERSAL = SimpleOption.<Boolean>builder()
        .comment("If the overlay colors should travel through the block")
        .node("block-outline", "block-overlay-traversal").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> BLOCK_OVERLAY_TRAVERSAL_SPEED = NumberOption.<Float>number()
        .node("block-outline", "block-overlay-traversal-speed").type(TypeToken.get(Float.class))
        .min(0.15F).max(5.0F)
        .notifyClient()
        .build();

    /**
     * If only a single side of the block should have outlines.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BLOCK_OUTLINE_SIDE = SimpleOption.<Boolean>builder()
        .comment("If only a single side of the block should have outlines")
        .node("block-outline", "block-outline-side").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * If using hide foliage, should outlines still show on foliage.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BLOCK_OUTLINE_SHOW_HIDDEN_FOLIAGE = SimpleOption.<Boolean>builder()
        .comment("If using hide foliage, should outlines still show on foliage")
        .node("block-outline", "block-outline-show-hidden-foliage").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * If blocks that connect together should share an outline.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BLOCK_OUTLINE_MULTI_BLOCK = SimpleOption.<Boolean>builder()
        .comment("If blocks that connect together should share an outline")
        .node("block-outline", "block-outline-multi-block").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * If outlines should be visible in spectator mode.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_IN_SPECTATOR = SimpleOption.<Boolean>builder()
        .comment("If outlines should be visible in spectator mode")
        .node("block-outline", "show-in-spectator").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    private ModBlockOutline() {
    }

}

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
 * View the border of the current chunk.
 *
 * @since 1.0.0
 */
public final class ModChunkBorders {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("chunk-borders", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> GRID = SimpleOption.<Boolean>builder()
        .node("chunk-borders", "grid").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final NumberOption<Float> GRID_SIZE = NumberOption.<Float>number()
        .node("chunk-borders", "grid-size").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final NumberOption<Float> GRID_LINE_THICKNESS = NumberOption.<Float>number()
        .node("chunk-borders", "grid-line-thickness").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> GRID_COLOR = SimpleOption.<Color>builder()
        .node("chunk-borders", "grid-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 0))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> INNER_CORNERS = SimpleOption.<Boolean>builder()
        .node("chunk-borders", "inner-corners").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final NumberOption<Float> INNER_CORNER_THICKNESS = NumberOption.<Float>number()
        .node("chunk-borders", "inner-corner-thickness").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> INNER_CHUNK_CORNER_COLOR = SimpleOption.<Color>builder()
        .node("chunk-borders", "inner-chunk-corner-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(0, 0, 255))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> OUTER_CORNERS = SimpleOption.<Boolean>builder()
        .node("chunk-borders", "outer-corners").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final NumberOption<Float> OUTER_CORNER_THICKNESS = NumberOption.<Float>number()
        .node("chunk-borders", "outer-corner-thickness").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> OUTER_CHUNK_CORNER_COLOR = SimpleOption.<Color>builder()
        .node("chunk-borders", "outer-chunk-corner-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 0, 0))
        .notifyClient()
        .build();

    private ModChunkBorders() {
    }

}

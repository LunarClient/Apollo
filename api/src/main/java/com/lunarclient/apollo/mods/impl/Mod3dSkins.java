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
 * Adds additional depth and detail to player and skull models.
 *
 * @since 1.0.0
 */
public final class Mod3dSkins {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("3d-skins", "enabled").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Integer> RENDER_DISTANCE_LOD = NumberOption.<Integer>number()
        .node("3d-skins", "render-distance-lod").type(TypeToken.get(Integer.class))
        .min(5).max(40)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> SHOW_OTHERS = SimpleOption.<Boolean>builder()
        .node("3d-skins", "show-others").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLE_HAT = SimpleOption.<Boolean>builder()
        .node("3d-skins", "enable-hat").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLE_JACKET = SimpleOption.<Boolean>builder()
        .node("3d-skins", "enable-jacket").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLE_LEFT_SLEEVE = SimpleOption.<Boolean>builder()
        .node("3d-skins", "enable-left-sleeve").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLE_RIGHT_SLEEVE = SimpleOption.<Boolean>builder()
        .node("3d-skins", "enable-right-sleeve").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLE_LEFT_PANTS = SimpleOption.<Boolean>builder()
        .node("3d-skins", "enable-left-pants").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLE_RIGHT_PANTS = SimpleOption.<Boolean>builder()
        .node("3d-skins", "enable-right-pants").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> BASE_VOXEL_SIZE = NumberOption.<Float>number()
        .node("3d-skins", "base-voxel-size").type(TypeToken.get(Float.class))
        .min(1.001F).max(1.4F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> BODY_VOXEL_WIDTH_SIZE = NumberOption.<Float>number()
        .node("3d-skins", "body-voxel-width-size").type(TypeToken.get(Float.class))
        .min(1.001F).max(1.4F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> HEAD_VOXEL_SIZE = NumberOption.<Float>number()
        .node("3d-skins", "head-voxel-size").type(TypeToken.get(Float.class))
        .min(1.001F).max(1.25F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final NumberOption<Float> FIRST_PERSON_VOXEL_SIZE = NumberOption.<Float>number()
        .node("3d-skins", "first-person-voxel-size").type(TypeToken.get(Float.class))
        .min(1.001F).max(1.3F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLE_SKULLS = SimpleOption.<Boolean>builder()
        .node("3d-skins", "enable-skulls").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLE_SKULLS_ITEMS = SimpleOption.<Boolean>builder()
        .node("3d-skins", "enable-skulls-items").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> SKULL_VOXEL_SIZE = NumberOption.<Float>number()
        .node("3d-skins", "skull-voxel-size").type(TypeToken.get(Float.class))
        .min(1.001F).max(1.2F)
        .notifyClient()
        .build();

    private Mod3dSkins() {
    }

}

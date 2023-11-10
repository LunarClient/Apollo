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
 * Allows you to customize your field of view for different potion or speed states.
 *
 * @since 1.0.0
 */
public final class ModFov {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("fov", "enabled").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * Determines if your FOV changes as you move.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> STATIC_FOV = SimpleOption.<Boolean>builder()
        .comment("Determines if your FOV changes as you move.")
        .node("fov", "static-f-o-v").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Integer> DEFAULT_FOV = NumberOption.<Integer>number()
        .node("fov", "default-fov").type(TypeToken.get(Integer.class))
        .min(30).max(110)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> AIMING_MODIFIER = NumberOption.<Float>number()
        .node("fov", "aiming-modifier").type(TypeToken.get(Float.class))
        .min(0.0F).max(5.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> AIMING_MIN = NumberOption.<Float>number()
        .node("fov", "aiming-min").type(TypeToken.get(Float.class))
        .min(-200.0F).max(200.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> AIMING_MAX = NumberOption.<Float>number()
        .node("fov", "aiming-max").type(TypeToken.get(Float.class))
        .min(-200.0F).max(200.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Integer> SPEED_FOV = NumberOption.<Integer>number()
        .node("fov", "speed-f-o-v").type(TypeToken.get(Integer.class))
        .min(30).max(110)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Integer> SPEED_TWO_FOV = NumberOption.<Integer>number()
        .node("fov", "speed-two-f-o-v").type(TypeToken.get(Integer.class))
        .min(30).max(110)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Integer> SLOWNESS_FOV = NumberOption.<Integer>number()
        .node("fov", "slowness-f-o-v").type(TypeToken.get(Integer.class))
        .min(30).max(110)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> MOVEMENT_MODIFIER = NumberOption.<Float>number()
        .node("fov", "movement-modifier").type(TypeToken.get(Float.class))
        .min(0.0F).max(5.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> MOVEMENT_MIN = NumberOption.<Float>number()
        .node("fov", "movement-min").type(TypeToken.get(Float.class))
        .min(-200.0F).max(200.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> MOVEMENT_MAX = NumberOption.<Float>number()
        .node("fov", "movement-max").type(TypeToken.get(Float.class))
        .min(-200.0F).max(200.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Integer> SPRINTING_FOV = NumberOption.<Integer>number()
        .node("fov", "sprinting-f-o-v").type(TypeToken.get(Integer.class))
        .min(30).max(110)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> SPRINT_MODIFIER = NumberOption.<Float>number()
        .node("fov", "sprint-modifier").type(TypeToken.get(Float.class))
        .min(0.0F).max(5.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> SPRINT_MIN = NumberOption.<Float>number()
        .node("fov", "sprint-min").type(TypeToken.get(Float.class))
        .min(-200.0F).max(200.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> SPRINT_MAX = NumberOption.<Float>number()
        .node("fov", "sprint-max").type(TypeToken.get(Float.class))
        .min(-200.0F).max(200.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Integer> FLYING_FOV = NumberOption.<Integer>number()
        .node("fov", "flying-fov").type(TypeToken.get(Integer.class))
        .min(30).max(110)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> FLYING_MODIFIER = NumberOption.<Float>number()
        .node("fov", "flying-modifier").type(TypeToken.get(Float.class))
        .min(0.0F).max(5.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> FLYING_MIN = NumberOption.<Float>number()
        .node("fov", "flying-min").type(TypeToken.get(Float.class))
        .min(-200.0F).max(200.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> FLYING_MAX = NumberOption.<Float>number()
        .node("fov", "flying-max").type(TypeToken.get(Float.class))
        .min(-200.0F).max(200.0F)
        .notifyClient()
        .build();

    private ModFov() {
    }

}

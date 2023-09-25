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
 * Customize the hitbox displayed in F3+B.
 *
 * @since 1.0.0
 */
public final class ModHitbox {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("hitbox", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Float> HITBOX_PLAYER_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-player-line-width").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(1.0F).max(5.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> HITBOX_PLAYER_LINE_COLOR = NumberOption.<Integer>number()
        .node("hitbox", "hitbox-player-line-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HITBOX_PLAYER_LOOK_VECTOR = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-player-look-vector").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HITBOX_PLAYER_SHOW = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-player-show").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Float> HITBOX_ITEM_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-item-line-width").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(1.0F).max(5.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> HITBOX_ITEM_LINE_COLOR = NumberOption.<Integer>number()
        .node("hitbox", "hitbox-item-line-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HITBOX_ITEM_LOOK_VECTOR = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-item-look-vector").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HITBOX_ITEM_SHOW = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-item-show").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Float> HITBOX_PROJECTILE_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-projectile-line-width").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(1.0F).max(5.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> HITBOX_PROJECTILE_LINE_COLOR = NumberOption.<Integer>number()
        .node("hitbox", "hitbox-projectile-line-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HITBOX_PROJECTILE_LOOK_VECTOR = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-projectile-look-vector").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HITBOX_PROJECTILE_SHOW = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-projectile-show").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Float> HITBOX_MOB_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-mob-line-width").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(1.0F).max(5.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> HITBOX_MOB_LINE_COLOR = NumberOption.<Integer>number()
        .node("hitbox", "hitbox-mob-line-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HITBOX_MOB_LOOK_VECTOR = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-mob-look-vector").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HITBOX_MOB_SHOW = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-mob-show").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Float> HITBOX_EXP_ORB_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-exp-orb-line-width").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(1.0F).max(5.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> HITBOX_EXP_ORB_LINE_COLOR = NumberOption.<Integer>number()
        .node("hitbox", "hitbox-exp-orb-line-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HITBOX_EXP_ORB_LOOK_VECTOR = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-exp-orb-look-vector").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HITBOX_EXP_ORB_SHOW = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-exp-orb-show").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    private ModHitbox() {
    }

}

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
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> MAX_DISTANCE_TOGGLE = SimpleOption.<Boolean>builder()
        .node("hitbox", "max-distance-toggle").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Maximum distance away from the entity to show hitboxes at.
     *
     * @since 1.2.2
     */
    public static final NumberOption<Integer> MAX_DISTANCE = NumberOption.<Integer>number()
        .comment("Maximum distance away from the entity to show hitboxes at")
        .node("hitbox", "max-distance").type(TypeToken.get(Integer.class))
        .min(1).max(128)
        .defaultValue(64)
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
    public static final NumberOption<Float> HITBOX_PLAYER_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-player-line-width").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> HITBOX_PLAYER_LINE_COLOR = SimpleOption.<Color>builder()
        .node("hitbox", "hitbox-player-line-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
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
    public static final NumberOption<Float> HITBOX_ITEM_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-item-line-width").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> HITBOX_ITEM_LINE_COLOR = SimpleOption.<Color>builder()
        .node("hitbox", "hitbox-item-line-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
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
    public static final SimpleOption<Boolean> HITBOX_EXP_ORB_SHOW = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-exp-orb-show").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> HITBOX_EXP_ORB_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-exp-orb-line-width").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> HITBOX_EXP_ORB_LINE_COLOR = SimpleOption.<Color>builder()
        .node("hitbox", "hitbox-exp-orb-line-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
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
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> HITBOX_ITEM_FRAME_SHOW = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-item-frame-show").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final NumberOption<Float> HITBOX_ITEM_FRAME_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-item-frame-line-width").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Color> HITBOX_ITEM_FRAME_LINE_COLOR = SimpleOption.<Color>builder()
        .node("hitbox", "hitbox-item-frame-line-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> HITBOX_ITEM_FRAME_LOOK_VECTOR = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-item-frame-look-vector").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> HITBOX_FIREWORK_SHOW = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-firework-show").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final NumberOption<Float> HITBOX_FIREWORK_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-firework-line-width").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Color> HITBOX_FIREWORK_LINE_COLOR = SimpleOption.<Color>builder()
        .node("hitbox", "hitbox-firework-line-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> HITBOX_FIREWORK_LOOK_VECTOR = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-firework-look-vector").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> HITBOX_WITHER_SKULL_SHOW = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-wither-skull-show").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final NumberOption<Float> HITBOX_WITHER_SKULL_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-wither-skull-line-width").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Color> HITBOX_WITHER_SKULL_LINE_COLOR = SimpleOption.<Color>builder()
        .node("hitbox", "hitbox-wither-skull-line-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> HITBOX_WITHER_SKULL_LOOK_VECTOR = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-wither-skull-look-vector").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> HITBOX_SNOWBALL_SHOW = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-snowball-show").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final NumberOption<Float> HITBOX_SNOWBALL_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-snowball-line-width").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Color> HITBOX_SNOWBALL_LINE_COLOR = SimpleOption.<Color>builder()
        .node("hitbox", "hitbox-snowball-line-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> HITBOX_SNOWBALL_LOOK_VECTOR = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-snowball-look-vector").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> HITBOX_FIREBALL_SHOW = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-fireball-show").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final NumberOption<Float> HITBOX_FIREBALL_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-fireball-line-width").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Color> HITBOX_FIREBALL_LINE_COLOR = SimpleOption.<Color>builder()
        .node("hitbox", "hitbox-fireball-line-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> HITBOX_FIREBALL_LOOK_VECTOR = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-fireball-look-vector").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> HITBOX_ARROW_SHOW = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-arrow-show").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final NumberOption<Float> HITBOX_ARROW_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-arrow-line-width").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Color> HITBOX_ARROW_LINE_COLOR = SimpleOption.<Color>builder()
        .node("hitbox", "hitbox-arrow-line-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> HITBOX_ARROW_LOOK_VECTOR = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-arrow-look-vector").type(TypeToken.get(Boolean.class))
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
    public static final NumberOption<Float> HITBOX_PROJECTILE_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-projectile-line-width").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> HITBOX_PROJECTILE_LINE_COLOR = SimpleOption.<Color>builder()
        .node("hitbox", "hitbox-projectile-line-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
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
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> HITBOX_MONSTER_SHOW = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-monster-show").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final NumberOption<Float> HITBOX_MONSTER_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-monster-line-width").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Color> HITBOX_MONSTER_LINE_COLOR = SimpleOption.<Color>builder()
        .node("hitbox", "hitbox-monster-line-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> HITBOX_MONSTER_LOOK_VECTOR = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-monster-look-vector").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> HITBOX_PASSIVE_SHOW = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-passive-show").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final NumberOption<Float> HITBOX_PASSIVE_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-passive-line-width").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Color> HITBOX_PASSIVE_LINE_COLOR = SimpleOption.<Color>builder()
        .node("hitbox", "hitbox-passive-line-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> HITBOX_PASSIVE_LOOK_VECTOR = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-passive-look-vector").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> HITBOX_OTHER_SHOW = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-other-show").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final NumberOption<Float> HITBOX_OTHER_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-other-line-width").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Color> HITBOX_OTHER_LINE_COLOR = SimpleOption.<Color>builder()
        .node("hitbox", "hitbox-other-line-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> HITBOX_OTHER_LOOK_VECTOR = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-other-look-vector").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    @Deprecated
    public static final NumberOption<Float> HITBOX_MOB_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-mob-line-width").type(TypeToken.get(Float.class))
        .min(1.0F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    @Deprecated
    public static final SimpleOption<Color> HITBOX_MOB_LINE_COLOR = SimpleOption.<Color>builder()
        .node("hitbox", "hitbox-mob-line-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    @Deprecated
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
    @Deprecated
    public static final SimpleOption<Boolean> HITBOX_MOB_SHOW = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-mob-show").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    private ModHitbox() {
    }

}

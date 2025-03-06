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
 * Display your currently active potion effects on the HUD.
 *
 * @since 1.0.0
 */
public final class ModPotionEffects {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("potion-effects", "enabled").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> SCALE = NumberOption.<Float>number()
        .node("potion-effects", "scale").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.5F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_IN_INVENTORY = SimpleOption.<Boolean>builder()
        .node("potion-effects", "show-in-inventory").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_WHILE_TYPING = SimpleOption.<Boolean>builder()
        .node("potion-effects", "show-while-typing").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> EFFECT_NAME = SimpleOption.<Boolean>builder()
        .node("potion-effects", "effect-name").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> TEXT_SHADOW = SimpleOption.<Boolean>builder()
        .node("potion-effects", "text-shadow").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_MODERN_ICONS = SimpleOption.<Boolean>builder()
        .node("potion-effects", "hide-modern-icons").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> POTION_BLINK = SimpleOption.<Boolean>builder()
        .node("potion-effects", "potion-blink").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Integer> BLINK_DURATION = NumberOption.<Integer>number()
        .node("potion-effects", "blink-duration").type(TypeToken.get(Integer.class))
        .min(2).max(20)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> COLOR_NAME_BASED_ON_EFFECT = SimpleOption.<Boolean>builder()
        .node("potion-effects", "color-name-based-on-effect").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> TEXT_COLOR = SimpleOption.<Color>builder()
        .node("potion-effects", "text-color").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> DURATION_COLOR = SimpleOption.<Color>builder()
        .node("potion-effects", "duration-color").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> EXCLUDE_PERM = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-perm").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.1
     */
    public static final SimpleOption<Boolean> EXCLUDE_ABSORPTION = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-absorption").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.1
     */
    public static final SimpleOption<Boolean> EXCLUDE_BLINDNESS = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-blindness").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> EXCLUDE_FIRE_RES = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-fire-res").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> EXCLUDE_HASTE = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-haste").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.1
     */
    public static final SimpleOption<Boolean> EXCLUDE_HEALTH_BOOST = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-health-boost").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.1
     */
    public static final SimpleOption<Boolean> EXCLUDE_HUNGER = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-hunger").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.1
     */
    public static final SimpleOption<Boolean> EXCLUDE_INSTANT_DAMAGE = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-instant-damage").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.1
     */
    public static final SimpleOption<Boolean> EXCLUDE_INSTANT_HEALTH = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-instant-health").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> EXCLUDE_INVIS = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-invis").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> EXCLUDE_JUMP_BOOST = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-jump-boost").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.1
     */
    public static final SimpleOption<Boolean> EXCLUDE_MINING_FATIGUE = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-mining-fatigue").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.1
     */
    public static final SimpleOption<Boolean> EXCLUDE_NAUSEA = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-nausea").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> EXCLUDE_NIGHT_VISION = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-night-vision").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> EXCLUDE_POISON = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-poison").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> EXCLUDE_REGEN = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-regen").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> EXCLUDE_RESISTANCE = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-resistance").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.1
     */
    public static final SimpleOption<Boolean> EXCLUDE_SATURATION = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-saturation").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> EXCLUDE_SLOWNESS = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-slowness").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> EXCLUDE_SPEED = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-speed").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> EXCLUDE_STRENGTH = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-strength").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> EXCLUDE_WATER_BREATHING = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-water-breathing").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> EXCLUDE_WEAKNESS = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-weakness").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.1
     */
    public static final SimpleOption<Boolean> EXCLUDE_WITHER = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-wither").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    private ModPotionEffects() {
    }

}

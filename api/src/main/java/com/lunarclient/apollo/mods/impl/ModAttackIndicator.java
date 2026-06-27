/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2026 Moonsworth
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
 * Allows you to customize the crosshair attack indicator.
 *
 * @since 1.2.8
 */
public final class ModAttackIndicator {

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("attack-indicator", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> HORIZONTAL = SimpleOption.<Boolean>builder()
        .node("attack-indicator", "horizontal").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final NumberOption<Float> SCALE = NumberOption.<Float>number()
        .node("attack-indicator", "scale").type(TypeToken.get(Float.class))
        .min(0.25F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> SHOW_IN_HUD_EDITOR = SimpleOption.<Boolean>builder()
        .node("attack-indicator", "show-in-hud-editor").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> ALWAYS_SHOW = SimpleOption.<Boolean>builder()
        .node("attack-indicator", "always-show").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> RENDER_ICON = SimpleOption.<Boolean>builder()
        .node("attack-indicator", "render-icon").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> BACKGROUND = SimpleOption.<Boolean>builder()
        .node("attack-indicator", "background").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Color> BACKGROUND_COLOR = SimpleOption.<Color>builder()
        .node("attack-indicator", "background-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(0, 0, 0, 111))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> BORDER = SimpleOption.<Boolean>builder()
        .node("attack-indicator", "border").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final NumberOption<Float> BORDER_THICKNESS = NumberOption.<Float>number()
        .node("attack-indicator", "border-thickness").type(TypeToken.get(Float.class))
        .min(0.5F).max(3.0F)
        .defaultValue(0.5F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Color> BORDER_COLOR = SimpleOption.<Color>builder()
        .node("attack-indicator", "border-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(0, 0, 0, 159))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> VANILLA_BLENDING = SimpleOption.<Boolean>builder()
        .node("attack-indicator", "vanilla-blending").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> DYNAMIC_COLOR = SimpleOption.<Boolean>builder()
        .node("attack-indicator", "dynamic-color").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Color> COLOR_LOW = SimpleOption.<Color>builder()
        .node("attack-indicator", "color-low").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 0, 0))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Color> COLOR_HIGH = SimpleOption.<Color>builder()
        .node("attack-indicator", "color-high").type(TypeToken.get(Color.class))
        .defaultValue(new Color(0, 255, 0))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Color> COLOR = SimpleOption.<Color>builder()
        .node("attack-indicator", "color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> SWORDS = SimpleOption.<Boolean>builder()
        .node("attack-indicator", "swords").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> AXES = SimpleOption.<Boolean>builder()
        .node("attack-indicator", "axes").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> PICKAXES = SimpleOption.<Boolean>builder()
        .node("attack-indicator", "pickaxes").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> SHOVEL = SimpleOption.<Boolean>builder()
        .node("attack-indicator", "shovel").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Show the generic attack swing cooldown when switching items (includes hand).
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> NON_WEAPONS = SimpleOption.<Boolean>builder()
        .comment("Show the generic attack swing cooldown when switching items (includes hand)")
        .node("attack-indicator", "non-weapons").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Play a ding when the weapon cooldowns are done.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> ATTACK_INDICATOR_PLAY_SOUND_VANILLA = SimpleOption.<Boolean>builder()
        .comment("Play a ding when the weapon cooldowns are done")
        .node("attack-indicator", "attack-indicator-play-sound-vanilla").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> SLEEP = SimpleOption.<Boolean>builder()
        .node("attack-indicator", "sleep").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> BLOCK_BREAKING = SimpleOption.<Boolean>builder()
        .node("attack-indicator", "block-breaking").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> ENDER_PEARL = SimpleOption.<Boolean>builder()
        .node("attack-indicator", "ender-pearl").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> CHORUS_FRUIT = SimpleOption.<Boolean>builder()
        .node("attack-indicator", "chorus-fruit").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> BOWS = SimpleOption.<Boolean>builder()
        .node("attack-indicator", "bows").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> SHIELDS = SimpleOption.<Boolean>builder()
        .node("attack-indicator", "shields").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Foods & Potions.
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> CONSUMABLES = SimpleOption.<Boolean>builder()
        .comment("Foods & Potions")
        .node("attack-indicator", "consumables").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Show capacity of storage items (bundles, shulker boxes).
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> STORAGE = SimpleOption.<Boolean>builder()
        .comment("Show capacity of storage items (bundles, shulker boxes)")
        .node("attack-indicator", "storage").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Play a ding when the cooldowns/indicators are done (excluding Sleep Progress, Block Breaking, Consumables and Storage Items).
     *
     * @since 1.2.8
     */
    public static final SimpleOption<Boolean> ATTACK_INDICATOR_PLAY_SOUND_CUSTOM = SimpleOption.<Boolean>builder()
        .comment("Play a ding when the cooldowns/indicators are done (excluding Sleep Progress, Block Breaking, Consumables and Storage Items)")
        .node("attack-indicator", "attack-indicator-play-sound-custom").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    private ModAttackIndicator() {
    }

}

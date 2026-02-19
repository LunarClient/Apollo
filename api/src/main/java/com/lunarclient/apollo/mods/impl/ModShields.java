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
 * Some QoL features for Minecraft shields.
 *
 * @since 1.2.2
 */
public final class ModShields {

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("shields", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> ENABLE_SHIELD_COLORS = SimpleOption.<Boolean>builder()
        .node("shields", "enable-shield-colors").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Color> SHIELD_ACTIVE_COLOR = SimpleOption.<Color>builder()
        .node("shields", "shield-active-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(0, 255, 0))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Color> SHIELD_INACTIVE_COLOR = SimpleOption.<Color>builder()
        .node("shields", "shield-inactive-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 0, 0))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> ANIMATE_COLOR_TRANSITION = SimpleOption.<Boolean>builder()
        .node("shields", "animate-color-transition").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Always play a sound whenever a player's shield goes inactive.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> SHIELD_INACTIVE_SOUND = SimpleOption.<Boolean>builder()
        .comment("Always play a sound whenever a player's shield goes inactive.")
        .node("shields", "shield-inactive-sound").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Always play a sound whenever a player's shield is damaged by any source.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> SHIELD_USE_SOUND = SimpleOption.<Boolean>builder()
        .comment("Always play a sound whenever a player's shield is damaged by any source.")
        .node("shields", "shield-use-sound").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Ignore the 5 tick deactivation period when a shield is hit with any item other than the axe.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> IGNORE_5TICKS = SimpleOption.<Boolean>builder()
        .comment("Ignore the 5 tick deactivation period when a shield is hit with any item other than the axe.")
        .node("shields", "ignore5-ticks").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Fix the bug where shields don't show as blocking sometimes, even though the player is blocking.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> BETTER_BLOCKING = SimpleOption.<Boolean>builder()
        .comment("Fix the bug where shields don't show as blocking sometimes, even though the player is blocking.")
        .node("shields", "better-blocking").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    private ModShields() {
    }

}

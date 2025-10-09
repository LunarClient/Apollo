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
 * Various enhancements to assist you when playing Hypixel Bedwars.
 *
 * @since 1.0.0
 */
public final class ModHypixelBedwars {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("hypixel-bedwars", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Recolor the beds in-game according to the team's color.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> COLORED_BEDS = SimpleOption.<Boolean>builder()
        .comment("Recolor the beds in-game according to the team's color")
        .node("hypixel-bedwars", "colored-beds").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Recolor the beds in bedwars practice mode.
     *
     * @since 1.2.1
     */
    public static final SimpleOption<Boolean> ENABLE_PRACTICE_COLOR = SimpleOption.<Boolean>builder()
        .comment("Recolor the beds in bedwars practice mode")
        .node("hypixel-bedwars", "enable-practice-color").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Replaces normal hearts with hardcore hearts once your bed is lost.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BW_HARDCORE_HEARTS = SimpleOption.<Boolean>builder()
        .comment("Replaces normal hearts with hardcore hearts once your bed is lost")
        .node("hypixel-bedwars", "bw-hardcore-hearts").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.1
     */
    public static final SimpleOption<Boolean> CUSTOM_TRAP_ALERT = SimpleOption.<Boolean>builder()
        .node("hypixel-bedwars", "custom-trap-alert").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.1
     */
    public static final SimpleOption<Boolean> MUTE_ALERT_SOUND = SimpleOption.<Boolean>builder()
        .node("hypixel-bedwars", "mute-alert-sound").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.1
     */
    public static final SimpleOption<Color> ALERT_PRIMARY_COLOR = SimpleOption.<Color>builder()
        .node("hypixel-bedwars", "alert-primary-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.1
     */
    public static final SimpleOption<Color> ALERT_SUB_COLOR = SimpleOption.<Color>builder()
        .node("hypixel-bedwars", "alert-sub-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    private ModHypixelBedwars() {
    }

}

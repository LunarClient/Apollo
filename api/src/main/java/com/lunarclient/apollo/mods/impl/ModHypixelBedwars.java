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
        .notifyClient()
        .build();

    private ModHypixelBedwars() {
    }

}

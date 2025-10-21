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
 * Keep track of players' totem usages. Totem pops aren't tracked when the mod is disabled, so toggling it on mid-game could result in a mismatch.
 *
 * @since 1.1.9
 */
public final class ModTotemCounter {

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("totem-counter", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> SHOW_FOR_SELF = SimpleOption.<Boolean>builder()
        .node("totem-counter", "show-for-self").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> SHOW_TAB_LIST = SimpleOption.<Boolean>builder()
        .node("totem-counter", "show-tab-list").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> FLIP = SimpleOption.<Boolean>builder()
        .node("totem-counter", "flip").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> COUNT_COLOR = SimpleOption.<Boolean>builder()
        .node("totem-counter", "count-color").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> SHOW_NAMETAG = SimpleOption.<Boolean>builder()
        .node("totem-counter", "show-nametag").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> SHOW_PREFIX = SimpleOption.<Boolean>builder()
        .node("totem-counter", "show-prefix").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> SEPARATE_NAMETAG = SimpleOption.<Boolean>builder()
        .node("totem-counter", "separate-nametag").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> RENDER_FIRST = SimpleOption.<Boolean>builder()
        .node("totem-counter", "render-first").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Color> PREFIX_COLOR = SimpleOption.<Color>builder()
        .node("totem-counter", "prefix-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 85))
        .notifyClient()
        .build();

    private ModTotemCounter() {
    }

}

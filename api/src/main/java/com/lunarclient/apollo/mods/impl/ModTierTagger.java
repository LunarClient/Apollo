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
 * Show player's PvP tier on their Name Tag.
 *
 * @since 1.1.9
 */
public final class ModTierTagger {

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("tier-tagger", "enabled").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> MC_TIERS_FORMAT = SimpleOption.<Boolean>builder()
        .node("tier-tagger", "mc-tiers-format").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> SUFFIX = SimpleOption.<Boolean>builder()
        .node("tier-tagger", "suffix").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> SEPARATE_NAMETAG = SimpleOption.<Boolean>builder()
        .node("tier-tagger", "separate-nametag").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> SHOW_GM_ICONS = SimpleOption.<Boolean>builder()
        .node("tier-tagger", "show-gm-icons").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> SHOW_RETIRED = SimpleOption.<Boolean>builder()
        .node("tier-tagger", "show-retired").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> SHOW_REGION = SimpleOption.<Boolean>builder()
        .node("tier-tagger", "show-region").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> COLOR_HT1 = SimpleOption.<Color>builder()
        .node("tier-tagger", "color-h-t1").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> COLOR_LT1 = SimpleOption.<Color>builder()
        .node("tier-tagger", "color-l-t1").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> COLOR_HT2 = SimpleOption.<Color>builder()
        .node("tier-tagger", "color-h-t2").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> COLOR_LT2 = SimpleOption.<Color>builder()
        .node("tier-tagger", "color-l-t2").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> COLOR_HT3 = SimpleOption.<Color>builder()
        .node("tier-tagger", "color-h-t3").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> COLOR_LT3 = SimpleOption.<Color>builder()
        .node("tier-tagger", "color-l-t3").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> COLOR_HT4 = SimpleOption.<Color>builder()
        .node("tier-tagger", "color-h-t4").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> COLOR_LT4 = SimpleOption.<Color>builder()
        .node("tier-tagger", "color-l-t4").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> COLOR_HT5 = SimpleOption.<Color>builder()
        .node("tier-tagger", "color-h-t5").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> COLOR_LT5 = SimpleOption.<Color>builder()
        .node("tier-tagger", "color-l-t5").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    private ModTierTagger() {
    }

}

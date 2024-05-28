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
 * Adjust the minecraft title and subtitle scale and location.
 *
 * @since 1.0.0
 */
public final class ModTitles {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("titles", "enabled").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> SCALE = NumberOption.<Float>number()
        .node("titles", "scale").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.5F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_IN_HUD_EDITOR = SimpleOption.<Boolean>builder()
        .node("titles", "show-in-hud-editor").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * Keep the title centered around the center of the mod on-screen.
     *
     * @since 1.1.2
     */
    public static final SimpleOption<Boolean> KEEP_TITLE_CENTERED = SimpleOption.<Boolean>builder()
        .comment("Keep the title centered around the center of the mod on-screen")
        .node("titles", "keep-title-centered").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> TITLE_COLOR = SimpleOption.<Color>builder()
        .node("titles", "title-color").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> SUBTITLE_COLOR = SimpleOption.<Color>builder()
        .node("titles", "subtitle-color").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> USE_MINECRAFT_GUISCALE = SimpleOption.<Boolean>builder()
        .node("titles", "use-minecraft-g-u-i-scale").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    private ModTitles() {
    }

}

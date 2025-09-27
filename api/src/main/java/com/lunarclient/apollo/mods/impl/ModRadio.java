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
 * Enjoy some of your favorite songs provided by Styngr.
 *
 * @since 1.1.7
 */
public final class ModRadio {

    /**
     * No documentation available.
     *
     * @since 1.1.7
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("radio", "enabled").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.7
     */
    public static final NumberOption<Integer> VOLUME = NumberOption.<Integer>number()
        .node("radio", "volume").type(TypeToken.get(Integer.class))
        .min(0).max(100)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.7
     */
    public static final SimpleOption<Boolean> MUTE_RADIO = SimpleOption.<Boolean>builder()
        .node("radio", "mute-radio").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.7
     */
    public static final NumberOption<Float> SCALE = NumberOption.<Float>number()
        .node("radio", "scale").type(TypeToken.get(Float.class))
        .min(0.25F).max(5.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.7
     */
    public static final SimpleOption<Boolean> BACKGROUND = SimpleOption.<Boolean>builder()
        .node("radio", "background").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * If this is disabled the background will change size with the text.
     *
     * @since 1.1.7
     */
    public static final SimpleOption<Boolean> STATIC_BACKGROUND_WIDTH = SimpleOption.<Boolean>builder()
        .comment("If this is disabled the background will change size with the text.")
        .node("radio", "static-background-width").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * If this is disabled the background will change size with the text.
     *
     * @since 1.1.7
     */
    public static final SimpleOption<Boolean> STATIC_BACKGROUND_HEIGHT = SimpleOption.<Boolean>builder()
        .comment("If this is disabled the background will change size with the text.")
        .node("radio", "static-background-height").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.7
     */
    public static final NumberOption<Integer> BACKGROUND_WIDTH = NumberOption.<Integer>number()
        .node("radio", "background-width").type(TypeToken.get(Integer.class))
        .min(60).max(300)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.7
     */
    public static final NumberOption<Integer> BACKGROUND_HEIGHT = NumberOption.<Integer>number()
        .node("radio", "background-height").type(TypeToken.get(Integer.class))
        .min(22).max(64)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.7
     */
    public static final SimpleOption<Boolean> BORDER = SimpleOption.<Boolean>builder()
        .node("radio", "border").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.7
     */
    public static final NumberOption<Float> BORDER_THICKNESS = NumberOption.<Float>number()
        .node("radio", "border-thickness").type(TypeToken.get(Float.class))
        .min(0.5F).max(3.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.7
     */
    public static final SimpleOption<Color> BORDER_COLOR = SimpleOption.<Color>builder()
        .node("radio", "border-color").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.7
     */
    public static final SimpleOption<Color> BACKGROUND_COLOR = SimpleOption.<Color>builder()
        .node("radio", "background-color").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.7
     */
    public static final SimpleOption<Boolean> SHOW_COVER_ART = SimpleOption.<Boolean>builder()
        .node("radio", "show-cover-art").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.7
     */
    public static final SimpleOption<Boolean> SHOW_PROGRESS = SimpleOption.<Boolean>builder()
        .node("radio", "show-progress").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.7
     */
    public static final SimpleOption<Boolean> SONG_SCROLL_ANIMATION = SimpleOption.<Boolean>builder()
        .node("radio", "song-scroll-animation").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.7
     */
    public static final SimpleOption<Boolean> SHOW_WHEN_NOTHING_PLAYING = SimpleOption.<Boolean>builder()
        .node("radio", "show-when-nothing-playing").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.7
     */
    public static final SimpleOption<Boolean> BOLD_ARIST_NAME = SimpleOption.<Boolean>builder()
        .node("radio", "bold-arist-name").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.7
     */
    public static final SimpleOption<Boolean> BOLD_SONG_NAME = SimpleOption.<Boolean>builder()
        .node("radio", "bold-song-name").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.7
     */
    public static final SimpleOption<Boolean> SHOW_DURATION = SimpleOption.<Boolean>builder()
        .node("radio", "show-duration").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.7
     */
    public static final SimpleOption<Boolean> SHOW_PLAY_BUTTON = SimpleOption.<Boolean>builder()
        .node("radio", "show-play-button").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.7
     */
    public static final SimpleOption<Color> SONG_NAME_TEXT_COLOR = SimpleOption.<Color>builder()
        .node("radio", "song-name-text-color").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.7
     */
    public static final SimpleOption<Color> ARTIST_NAME_TEXT_COLOR = SimpleOption.<Color>builder()
        .node("radio", "artist-name-text-color").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.7
     */
    public static final SimpleOption<Color> RADIO_PROGRESS_BAR_COLOR = SimpleOption.<Color>builder()
        .node("radio", "radio-progress-bar-color").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    private ModRadio() {
    }

}

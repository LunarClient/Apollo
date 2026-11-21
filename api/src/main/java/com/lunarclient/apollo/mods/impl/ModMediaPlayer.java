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
 * See and control the media playing on your computer, like Spotify or your browser.
 *
 * @since %release_version%
 */
public final class ModMediaPlayer {

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("media-player", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_HUD = SimpleOption.<Boolean>builder()
        .node("media-player", "show-hud").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> SCALE = NumberOption.<Float>number()
        .node("media-player", "scale").type(TypeToken.get(Float.class))
        .min(0.25F).max(5.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * Draws the text in Minecraft's font, so it matches your resource pack.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> USE_MINECRAFT_FONT = SimpleOption.<Boolean>builder()
        .comment("Draws the text in Minecraft's font, so it matches your resource pack.")
        .node("media-player", "use-minecraft-font").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_COVER_ART = SimpleOption.<Boolean>builder()
        .node("media-player", "show-cover-art").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_SONG_NAME = SimpleOption.<Boolean>builder()
        .node("media-player", "show-song-name").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BOLD_SONG_NAME = SimpleOption.<Boolean>builder()
        .node("media-player", "bold-song-name").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SONG_SCROLL_ANIMATION = SimpleOption.<Boolean>builder()
        .node("media-player", "song-scroll-animation").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_ARTIST_NAME = SimpleOption.<Boolean>builder()
        .node("media-player", "show-artist-name").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BOLD_ARTIST_NAME = SimpleOption.<Boolean>builder()
        .node("media-player", "bold-artist-name").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_PROGRESS = SimpleOption.<Boolean>builder()
        .node("media-player", "show-progress").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_DURATION = SimpleOption.<Boolean>builder()
        .node("media-player", "show-duration").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_MEDIA_CONTROLS = SimpleOption.<Boolean>builder()
        .node("media-player", "show-media-controls").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ROUNDED_CORNERS = SimpleOption.<Boolean>builder()
        .node("media-player", "rounded-corners").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_WHEN_NOTHING_PLAYING = SimpleOption.<Boolean>builder()
        .node("media-player", "show-when-nothing-playing").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BACKGROUND = SimpleOption.<Boolean>builder()
        .node("media-player", "background").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * If this is disabled the background will change size with the text.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> STATIC_BACKGROUND_WIDTH = SimpleOption.<Boolean>builder()
        .comment("If this is disabled the background will change size with the text.")
        .node("media-player", "static-background-width").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * If this is disabled the background will change size with the text.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> STATIC_BACKGROUND_HEIGHT = SimpleOption.<Boolean>builder()
        .comment("If this is disabled the background will change size with the text.")
        .node("media-player", "static-background-height").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Integer> BACKGROUND_WIDTH = NumberOption.<Integer>number()
        .node("media-player", "background-width").type(TypeToken.get(Integer.class))
        .min(60).max(300)
        .defaultValue(148)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Integer> BACKGROUND_HEIGHT = NumberOption.<Integer>number()
        .node("media-player", "background-height").type(TypeToken.get(Integer.class))
        .min(22).max(64)
        .defaultValue(24)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BORDER = SimpleOption.<Boolean>builder()
        .node("media-player", "border").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> BORDER_THICKNESS = NumberOption.<Float>number()
        .node("media-player", "border-thickness").type(TypeToken.get(Float.class))
        .min(0.5F).max(3.0F)
        .defaultValue(0.5F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> BORDER_COLOR = SimpleOption.<Color>builder()
        .node("media-player", "border-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255, 20))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> BACKGROUND_COLOR = SimpleOption.<Color>builder()
        .node("media-player", "background-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(20, 23, 26, 219))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> SONG_NAME_TEXT_COLOR = SimpleOption.<Color>builder()
        .node("media-player", "song-name-text-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> ARTIST_NAME_TEXT_COLOR = SimpleOption.<Color>builder()
        .node("media-player", "artist-name-text-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(139, 150, 155))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> DURATION_TEXT_COLOR = SimpleOption.<Color>builder()
        .node("media-player", "duration-text-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(139, 150, 155))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> MEDIA_PROGRESS_COLOR = SimpleOption.<Color>builder()
        .node("media-player", "media-progress-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(0, 212, 101))
        .notifyClient()
        .build();

    private ModMediaPlayer() {
    }

}

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

/**
 * Customize the locator bar's position, size, and displayed information.
 *
 * @since %release_version%
 */
public final class ModLocatorBar {

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("locator-bar", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HIDE_LOCATOR_BAR = SimpleOption.<Boolean>builder()
        .node("locator-bar", "hide-locator-bar").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Move and scale the locator bar like a HUD mod instead of keeping it in the hotbar.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> DETACHED = SimpleOption.<Boolean>builder()
        .comment("Move and scale the locator bar like a HUD mod instead of keeping it in the hotbar")
        .node("locator-bar", "detached").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> SCALE = NumberOption.<Float>number()
        .node("locator-bar", "scale").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.5F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * Draw the head of a player instead of the colored dot.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> USE_PLAYER_HEADS = SimpleOption.<Boolean>builder()
        .comment("Draw the head of a player instead of the colored dot")
        .node("locator-bar", "use-player-heads").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> PLAYER_HEAD_SCALE = NumberOption.<Float>number()
        .node("locator-bar", "player-head-scale").type(TypeToken.get(Float.class))
        .min(0.5F).max(2.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * Draw an outline around the head with the dot color.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> PLAYER_HEAD_HIGHLIGHT = SimpleOption.<Boolean>builder()
        .comment("Draw an outline around the head with the dot color")
        .node("locator-bar", "player-head-highlight").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Players beyond the range show as the colored dot.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> LIMIT_HEAD_RANGE = SimpleOption.<Boolean>builder()
        .comment("Players beyond the range show as the colored dot")
        .node("locator-bar", "limit-head-range").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * The maximum distance in blocks at which player heads show.
     *
     * @since %release_version%
     */
    public static final NumberOption<Integer> PLAYER_HEAD_DISPLAY_RANGE = NumberOption.<Integer>number()
        .comment("The maximum distance in blocks at which player heads show")
        .node("locator-bar", "player-head-display-range").type(TypeToken.get(Integer.class))
        .min(16).max(332)
        .defaultValue(100)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_PLAYER_NAME = SimpleOption.<Boolean>builder()
        .node("locator-bar", "show-player-name").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> PLAYER_NAME_SCALE = NumberOption.<Float>number()
        .node("locator-bar", "player-name-scale").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * Only draw the name of a player when you look toward them.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ONLY_SHOW_NAME_WHEN_LOOKING_NEAR = SimpleOption.<Boolean>builder()
        .comment("Only draw the name of a player when you look toward them")
        .node("locator-bar", "only-show-name-when-looking-near").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Only draw the names of players within the range.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> LIMIT_NAME_RANGE = SimpleOption.<Boolean>builder()
        .comment("Only draw the names of players within the range")
        .node("locator-bar", "limit-name-range").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * The maximum distance in blocks at which player names show.
     *
     * @since %release_version%
     */
    public static final NumberOption<Integer> PLAYER_NAME_DISPLAY_RANGE = NumberOption.<Integer>number()
        .comment("The maximum distance in blocks at which player names show")
        .node("locator-bar", "player-name-display-range").type(TypeToken.get(Integer.class))
        .min(16).max(332)
        .defaultValue(100)
        .notifyClient()
        .build();

    /**
     * Color the player name with their team color, or with their dot color when they have no team.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> COLORED_PLAYER_NAME = SimpleOption.<Boolean>builder()
        .comment("Color the player name with their team color, or with their dot color when they have no team")
        .node("locator-bar", "colored-player-name").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * When names overlap, only draw the name closest to the middle of the bar.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HIDE_OVERLAPPING_NAMES = SimpleOption.<Boolean>builder()
        .comment("When names overlap, only draw the name closest to the middle of the bar")
        .node("locator-bar", "hide-overlapping-names").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Cut names longer than 8 characters, but keep them different from other players.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> CUT_LONG_NAMES = SimpleOption.<Boolean>builder()
        .comment("Cut names longer than 8 characters, but keep them different from other players")
        .node("locator-bar", "cut-long-names").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_DIRECTION = SimpleOption.<Boolean>builder()
        .node("locator-bar", "show-direction").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Show +Z, -Z, +X and -X instead of the direction letters.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> USE_COORDINATE_DIRECTIONS = SimpleOption.<Boolean>builder()
        .comment("Show +Z, -Z, +X and -X instead of the direction letters")
        .node("locator-bar", "use-coordinate-directions").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Draw the dots or player heads. Turn this off to only show the names.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_BAR_ICONS = SimpleOption.<Boolean>builder()
        .comment("Draw the dots or player heads. Turn this off to only show the names")
        .node("locator-bar", "show-bar-icons").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Draw the waypoints on the xp bar instead of the locator bar.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> USE_XP_BAR = SimpleOption.<Boolean>builder()
        .comment("Draw the waypoints on the xp bar instead of the locator bar")
        .node("locator-bar", "use-xp-bar").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Adds a shadow to text.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TEXT_SHADOW = SimpleOption.<Boolean>builder()
        .comment("Adds a shadow to text")
        .node("locator-bar", "text-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Only show the extra info while the peek keybind is held down.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> LOCATOR_BAR_PEEK = SimpleOption.<Boolean>builder()
        .comment("Only show the extra info while the peek keybind is held down")
        .node("locator-bar", "locator-bar-peek").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Hide the locator bar until you hold the peek keybind.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ONLY_SHOW_WHILE_PEEKING = SimpleOption.<Boolean>builder()
        .comment("Hide the locator bar until you hold the peek keybind")
        .node("locator-bar", "only-show-while-peeking").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    private ModLocatorBar() {
    }

}

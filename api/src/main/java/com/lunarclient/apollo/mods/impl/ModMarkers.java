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
 * Allows you and your team to mark things in the world, like in various FPS games.
 *
 * @since %release_version%
 */
public final class ModMarkers {

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("markers", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> DING_VOLUME_SELF = NumberOption.<Float>number()
        .node("markers", "ding-volume-self").type(TypeToken.get(Float.class))
        .min(0.0F).max(1.0F)
        .defaultValue(0.5F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> DING_VOLUME_OTHERS = NumberOption.<Float>number()
        .node("markers", "ding-volume-others").type(TypeToken.get(Float.class))
        .min(0.0F).max(1.0F)
        .defaultValue(0.5F)
        .notifyClient()
        .build();

    /**
     * Hit the middle mouse button while hovering over your marker to delete it.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> MIDDLE_CLICK_REMOVE = SimpleOption.<Boolean>builder()
        .comment("Hit the middle mouse button while hovering over your marker to delete it.")
        .node("markers", "middle-click-remove").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> CHAT_NOTIFY = SimpleOption.<Boolean>builder()
        .node("markers", "chat-notify").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> LUNAR_NOTIFY = SimpleOption.<Boolean>builder()
        .node("markers", "lunar-notify").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Try to detect your current team, and see and broadcast markers from and to members of your team.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TEAM_MEMBERS = SimpleOption.<Boolean>builder()
        .comment("Try to detect your current team, and see and broadcast markers from and to members of your team.")
        .node("markers", "team-members").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Classic team detection which uses Minecraft's scoreboard teams.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SCOREBOARD_TEAMS = SimpleOption.<Boolean>builder()
        .comment("Classic team detection which uses Minecraft's scoreboard teams.")
        .node("markers", "scoreboard-teams").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * On supported servers, use our Apollo plugin for team detection.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> APOLLO_TEAMS = SimpleOption.<Boolean>builder()
        .comment("On supported servers, use our Apollo plugin for team detection.")
        .node("markers", "apollo-teams").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> COLOR_TEAMS = SimpleOption.<Boolean>builder()
        .node("markers", "color-teams").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * See and broadcast markers from and to members of your Hypixel party.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HYPIXEL_PARTY = SimpleOption.<Boolean>builder()
        .comment("See and broadcast markers from and to members of your Hypixel party.")
        .node("markers", "hypixel-party").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * See and broadcast markers from and to people on your Lunar friends list.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> LUNAR_FRIENDS = SimpleOption.<Boolean>builder()
        .comment("See and broadcast markers from and to people on your Lunar friends list.")
        .node("markers", "lunar-friends").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> SCALE = NumberOption.<Float>number()
        .node("markers", "scale").type(TypeToken.get(Float.class))
        .min(0.5F).max(2.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Integer> VISIBLE_DURATION = NumberOption.<Integer>number()
        .node("markers", "visible-duration").type(TypeToken.get(Integer.class))
        .min(5).max(120)
        .defaultValue(20)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ANIMATE_MARKER = SimpleOption.<Boolean>builder()
        .node("markers", "animate-marker").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> COMPACT_MODE = SimpleOption.<Boolean>builder()
        .node("markers", "compact-mode").type(TypeToken.get(Boolean.class))
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
        .node("markers", "text-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BACKGROUND = SimpleOption.<Boolean>builder()
        .node("markers", "background").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> BACKGROUND_COLOR = SimpleOption.<Color>builder()
        .node("markers", "background-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(0, 0, 0, 64))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> MARKER_COLOR = SimpleOption.<Color>builder()
        .node("markers", "marker-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(174, 225, 252, 190))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> DANGER_MARKER_COLOR = SimpleOption.<Color>builder()
        .node("markers", "danger-marker-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 66, 0, 190))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> INFO_MARKER_COLOR = SimpleOption.<Color>builder()
        .node("markers", "info-marker-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(52, 158, 235))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> INTEREST_MARKER_COLOR = SimpleOption.<Color>builder()
        .node("markers", "interest-marker-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 170, 0))
        .notifyClient()
        .build();

    private ModMarkers() {
    }

}

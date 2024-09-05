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
 * A collection of mods that directly integrate with the Hypixel server network.
 *
 * @since 1.0.0
 */
public final class ModHypixelMod {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("hypixel-mod", "enabled").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> REMOVE_GUILD_ON_TAB = SimpleOption.<Boolean>builder()
        .node("hypixel-mod", "remove-guild-on-tab").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> REMOVE_GUILD_MOTD = SimpleOption.<Boolean>builder()
        .node("hypixel-mod", "remove-guild-motd").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHORT_CHANNEL_NAMES = SimpleOption.<Boolean>builder()
        .node("hypixel-mod", "short-channel-names").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> AUTO_FRIEND = SimpleOption.<Boolean>builder()
        .node("hypixel-mod", "auto-friend").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> AUTO_TIP = SimpleOption.<Boolean>builder()
        .node("hypixel-mod", "auto-tip").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> AUTO_GG = SimpleOption.<Boolean>builder()
        .node("hypixel-mod", "auto-g-g").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ANTI_GG = SimpleOption.<Boolean>builder()
        .node("hypixel-mod", "anti-g-g").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> AUTO_WHO = SimpleOption.<Boolean>builder()
        .node("hypixel-mod", "auto-who").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> LEVEL_HEAD = SimpleOption.<Boolean>builder()
        .node("hypixel-mod", "level-head").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HYPIXEL_AUTOCOMPLETE = SimpleOption.<Boolean>builder()
        .node("hypixel-mod", "hypixel-autocomplete").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> LEVEL_COLOR = SimpleOption.<Color>builder()
        .node("hypixel-mod", "level-color").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> LEVEL_HEAD_NUMBER_COLOR = SimpleOption.<Color>builder()
        .node("hypixel-mod", "level-head-number-color").type(TypeToken.get(Color.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> LEVEL_ABOVE = SimpleOption.<Boolean>builder()
        .node("hypixel-mod", "level-above").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_PRIVATE_MESSAGES = SimpleOption.<Boolean>builder()
        .node("hypixel-mod", "hide-private-messages").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_TEAM_CHAT = SimpleOption.<Boolean>builder()
        .node("hypixel-mod", "hide-team-chat").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_PARTY_CHAT = SimpleOption.<Boolean>builder()
        .node("hypixel-mod", "hide-party-chat").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_GUILD_CHAT = SimpleOption.<Boolean>builder()
        .node("hypixel-mod", "hide-guild-chat").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_SHOUT = SimpleOption.<Boolean>builder()
        .node("hypixel-mod", "hide-shout").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_SPECTATOR_CHAT = SimpleOption.<Boolean>builder()
        .node("hypixel-mod", "hide-spectator-chat").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> REMOVE_LOBBY_STATUSES = SimpleOption.<Boolean>builder()
        .node("hypixel-mod", "remove-lobby-statuses").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_JOIN_MESSAGES = SimpleOption.<Boolean>builder()
        .node("hypixel-mod", "hide-join-messages").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_LEAVE_MESSAGES = SimpleOption.<Boolean>builder()
        .node("hypixel-mod", "hide-leave-messages").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_SOUL_WELL_ANNOUNCEMENTS = SimpleOption.<Boolean>builder()
        .node("hypixel-mod", "hide-soul-well-announcements").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.4
     */
    public static final SimpleOption<Boolean> HIDE_MYSTERY_BOX_ANNOUNCEMENTS = SimpleOption.<Boolean>builder()
        .node("hypixel-mod", "hide-mystery-box-announcements").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    private ModHypixelMod() {
    }

}

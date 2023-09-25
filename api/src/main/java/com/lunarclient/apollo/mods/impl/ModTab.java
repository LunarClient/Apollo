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

/**
 * Allows you to customize the in-game tab.
 *
 * @since 1.0.0
 */
public final class ModTab {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("tab", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * Quickly pressing and releasing the key will toggle tab.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> TOGGLE_KEY_TAB = SimpleOption.<Boolean>builder()
        .comment("Quickly pressing and releasing the key will toggle tab")
        .node("tab", "toggle-key-tab").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> HEADER_COLOR = NumberOption.<Integer>number()
        .node("tab", "header-color").type(TypeToken.get(Integer.class))
        .defaultValue(0x80000000).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> FOOTER_COLOR = NumberOption.<Integer>number()
        .node("tab", "footer-color").type(TypeToken.get(Integer.class))
        .defaultValue(0x80000000).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> BACKGROUND_COLOR = NumberOption.<Integer>number()
        .node("tab", "background-color").type(TypeToken.get(Integer.class))
        .defaultValue(0x80000000).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> PING_ROW = SimpleOption.<Boolean>builder()
        .node("tab", "ping-row").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> ROWS_COLOR = NumberOption.<Integer>number()
        .node("tab", "rows-color").type(TypeToken.get(Integer.class))
        .defaultValue(0x20000000).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DISABLE_HEADER = SimpleOption.<Boolean>builder()
        .node("tab", "disable-header").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DISPLAY_PLAYER_HEAD = SimpleOption.<Boolean>builder()
        .node("tab", "display-player-head").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DISABLE_FOOTER = SimpleOption.<Boolean>builder()
        .node("tab", "disable-footer").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * Hide Hypixel NPC on tab.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_NPC = SimpleOption.<Boolean>builder()
        .comment("Hide Hypixel NPC on tab")
        .node("tab", "hide-npc").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * An option to toggle LC logos in player list tab overlay.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> TAB_LOGO = SimpleOption.<Boolean>builder()
        .comment("An option to toggle LC logos in player list tab overlay.")
        .node("tab", "tab-logo").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIGHLIGHT_OWN_NAME = SimpleOption.<Boolean>builder()
        .node("tab", "highlight-own-name").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> NAME_COLOR = NumberOption.<Integer>number()
        .node("tab", "name-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFF0000).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> NAME_SHADOW = SimpleOption.<Boolean>builder()
        .node("tab", "name-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_PING = SimpleOption.<Boolean>builder()
        .node("tab", "hide-ping").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_PING_IF_OVER_500 = SimpleOption.<Boolean>builder()
        .node("tab", "hide-ping-if-over500").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DISPLAY_PING_AS_NUMBER = SimpleOption.<Boolean>builder()
        .node("tab", "display-ping-as-number").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DYNAMIC_PING_COLOR = SimpleOption.<Boolean>builder()
        .node("tab", "dynamic-ping-color").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> PING_NUMBER_SHADOW = SimpleOption.<Boolean>builder()
        .node("tab", "ping-number-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> PING_NUMBER_COLOR = NumberOption.<Integer>number()
        .node("tab", "ping-number-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFF55).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> LOW_PING_NUMBER_COLOR = NumberOption.<Integer>number()
        .node("tab", "low-ping-number-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFF55FF55).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> MEDIUM_PING_NUMBER_COLOR = NumberOption.<Integer>number()
        .node("tab", "medium-ping-number-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFF55).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> HIGH_PING_NUMBER_COLOR = NumberOption.<Integer>number()
        .node("tab", "high-ping-number-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFF5555).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> EXTREME_PING_NUMBER_COLOR = NumberOption.<Integer>number()
        .node("tab", "extreme-ping-number-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFAA0000).min(0x80000000).max(0x7FFFFFFF)
        .build();

    private ModTab() {
    }

}

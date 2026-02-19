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
 * Customize your chat options to your liking.
 *
 * @since 1.0.0
 */
public final class ModChat {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("chat", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Choose whether or not you want to increase the message history in chat.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> UNLIMITED_CHAT = SimpleOption.<Boolean>builder()
        .comment("Choose whether or not you want to increase the message history in chat")
        .node("chat", "unlimited-chat").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> MODERN_CHAT_LENGTH_HYPIXEL = SimpleOption.<Boolean>builder()
        .node("chat", "modern-chat-length-hypixel").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> LONG_CHAT_SINGLEPLAYER = SimpleOption.<Boolean>builder()
        .node("chat", "long-chat-singleplayer").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Choose whether or not you want to stack multiple of the same messages in chat.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> STACK_MESSAGES = SimpleOption.<Boolean>builder()
        .comment("Choose whether or not you want to stack multiple of the same messages in chat")
        .node("chat", "stack-messages").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Choose whether or not you want to stack multiple of the same messages in chat within a timeframe.
     *
     * @since 1.1.8
     */
    public static final SimpleOption<Boolean> STACK_MESSAGES_TIME_BASED = SimpleOption.<Boolean>builder()
        .comment("Choose whether or not you want to stack multiple of the same messages in chat within a timeframe")
        .node("chat", "stack-messages-time-based").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.8
     */
    public static final NumberOption<Integer> TIME_BASED_STACK_MESSAGES_TIMEFRAME = NumberOption.<Integer>number()
        .node("chat", "time-based-stack-messages-timeframe").type(TypeToken.get(Integer.class))
        .min(1).max(60)
        .defaultValue(10)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.8
     */
    public static final SimpleOption<Boolean> CHAT_STACK_IGNORE_BLANK = SimpleOption.<Boolean>builder()
        .node("chat", "chat-stack-ignore-blank").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.8
     */
    public static final SimpleOption<Boolean> CHAT_STACK_IGNORE_BREAK = SimpleOption.<Boolean>builder()
        .node("chat", "chat-stack-ignore-break").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Moves the chat up 12 pixels so it doesn't block health bar.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> CHAT_HEIGHT = SimpleOption.<Boolean>builder()
        .comment("Moves the chat up 12 pixels so it doesn't block health bar")
        .node("chat", "chat-height").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Show text shadow on chat lines.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> CHAT_SHADOW = SimpleOption.<Boolean>builder()
        .comment("Show text shadow on chat lines")
        .node("chat", "chat-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DISABLE_CHAT = SimpleOption.<Boolean>builder()
        .node("chat", "disable-chat").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> NO_CLOSE_MY_CHAT = SimpleOption.<Boolean>builder()
        .node("chat", "no-close-my-chat").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> CHAT_PING_SOUND = SimpleOption.<Boolean>builder()
        .node("chat", "chat-ping-sound").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Requires the ping message to exactly contain your name.For example, if your name is Notch, this will ping on Notch but not Notch123.
     *
     * @since 1.1.6
     */
    public static final SimpleOption<Boolean> CHAT_PING_EXACT_MATCH = SimpleOption.<Boolean>builder()
        .comment("Requires the ping message to exactly contain your name.For example, if your name is Notch, this will ping on Notch but not Notch123.")
        .node("chat", "chat-ping-exact-match").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Copies the hovered chat message when holding the keybind and clicking.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> COPY_CHAT = SimpleOption.<Boolean>builder()
        .comment("Copies the hovered chat message when holding the keybind and clicking.")
        .node("chat", "copy-chat").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Allows you to simply right click a chat message to copy it, no keybind.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> COPY_CHAT_RIGHT_CLICK = SimpleOption.<Boolean>builder()
        .comment("Allows you to simply right click a chat message to copy it, no keybind")
        .node("chat", "copy-chat-right-click").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Displays a preview of an image when hovering over it.If a single message has more than one image, press CTRL to cycle through them.
     *
     * @since 1.1.3
     */
    public static final SimpleOption<Boolean> HOVER_IMAGE_PREVIEW = SimpleOption.<Boolean>builder()
        .comment("Displays a preview of an image when hovering over it.If a single message has more than one image, press CTRL to cycle through them.")
        .node("chat", "hover-image-preview").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * The minimum size for an image to be displayed, as a percentage of your screen.
     *
     * @since 1.1.3
     */
    public static final NumberOption<Double> MIN_IMAGE_SIZE = NumberOption.<Double>number()
        .comment("The minimum size for an image to be displayed, as a percentage of your screen.")
        .node("chat", "min-image-size").type(TypeToken.get(Double.class))
        .min(0.0D).max(100.0D)
        .defaultValue(0.0D)
        .notifyClient()
        .build();

    /**
     * The maximum size for an image to be displayed, as a percentage of your screen.
     *
     * @since 1.1.3
     */
    public static final NumberOption<Double> MAX_IMAGE_SIZE = NumberOption.<Double>number()
        .comment("The maximum size for an image to be displayed, as a percentage of your screen.")
        .node("chat", "max-image-size").type(TypeToken.get(Double.class))
        .min(0.0D).max(100.0D)
        .defaultValue(30.0D)
        .notifyClient()
        .build();

    /**
     * When your max image size is set to e.g. 50%, when you press shift, the image will expand to 100%.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> FULLSCREEN_IMAGE = SimpleOption.<Boolean>builder()
        .comment("When your max image size is set to e.g. 50%, when you press shift, the image will expand to 100%")
        .node("chat", "fullscreen-image").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SMOOTH_CHAT = SimpleOption.<Boolean>builder()
        .node("chat", "smooth-chat").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Integer> SMOOTH_CHAT_SPEED = NumberOption.<Integer>number()
        .node("chat", "smooth-chat-speed").type(TypeToken.get(Integer.class))
        .min(1).max(10)
        .defaultValue(3)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> CHAT_NAME_BOLD = SimpleOption.<Boolean>builder()
        .node("chat", "chat-name-bold").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> CHAT_NAME_ITALIC = SimpleOption.<Boolean>builder()
        .node("chat", "chat-name-italic").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> CHAT_NAME_UNDERLINE = SimpleOption.<Boolean>builder()
        .node("chat", "chat-name-underline").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> CHAT_NAME_STRIKETHROUGH = SimpleOption.<Boolean>builder()
        .node("chat", "chat-name-strikethrough").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> CHAT_NAME_OBFUSCATED = SimpleOption.<Boolean>builder()
        .node("chat", "chat-name-obfuscated").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> CHAT_BACKGROUND_OPACITY = NumberOption.<Float>number()
        .node("chat", "chat-background-opacity").type(TypeToken.get(Float.class))
        .min(0.0F).max(1.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> INPUT_FIELD_OPACITY = NumberOption.<Float>number()
        .node("chat", "input-field-opacity").type(TypeToken.get(Float.class))
        .min(0.0F).max(10.0F)
        .defaultValue(5.0F)
        .notifyClient()
        .build();

    /**
     * ENABLED: Completely hides filtered messages DISABLED: Replaces filtered words with asterisks.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> STOP_PROFANE_MESSAGES = SimpleOption.<Boolean>builder()
        .comment("ENABLED: Completely hides filtered messages DISABLED: Replaces filtered words with asterisks")
        .node("chat", "stop-profane-messages").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> CHAT_TIMESTAMPS = SimpleOption.<Boolean>builder()
        .node("chat", "chat-timestamps").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Makes the timestamp italics.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> TIMESTAMP_ITALICS = SimpleOption.<Boolean>builder()
        .comment("Makes the timestamp italics")
        .node("chat", "timestamp-italics").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Makes the timestamp bold.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> TIMESTAMP_BOLD = SimpleOption.<Boolean>builder()
        .comment("Makes the timestamp bold")
        .node("chat", "timestamp-bold").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Use a 12-hour clock, or a 24-hour clock if disabled.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> TWELVE_HOUR_CLOCK = SimpleOption.<Boolean>builder()
        .comment("Use a 12-hour clock, or a 24-hour clock if disabled")
        .node("chat", "twelve-hour-clock").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_AM_PM = SimpleOption.<Boolean>builder()
        .node("chat", "show-am-pm").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_SECONDS = SimpleOption.<Boolean>builder()
        .node("chat", "show-seconds").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Adds square brackets around the timestamp.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_BRACKETS = SimpleOption.<Boolean>builder()
        .comment("Adds square brackets around the timestamp")
        .node("chat", "show-brackets").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    private ModChat() {
    }

}

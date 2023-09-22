package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Customize your chat options to your liking.
 * 
 * @since %release_version%
 */
public final class ModChat {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("chat", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * Choose whether or not you want to increase the message history in chat
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> UNLIMITED_CHAT = SimpleOption.<Boolean>builder()
        .comment("Choose whether or not you want to increase the message history in chat")
        .node("chat", "unlimited-chat").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * Choose whether or not you want to stack multiple of the same messages in chat
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> STACK_MESSAGES = SimpleOption.<Boolean>builder()
        .comment("Choose whether or not you want to stack multiple of the same messages in chat")
        .node("chat", "stack-messages").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * Moves the chat up 12 pixels so it doesn't block health bar
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> CHAT_HEIGHT = SimpleOption.<Boolean>builder()
        .comment("Moves the chat up 12 pixels so it doesn't block health bar")
        .node("chat", "chat-height").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> CHAT_BACKGROUND_OPACITY = NumberOption.<Float>number()
        .node("chat", "chat-background-opacity").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.0F).max(1.0F)
        .build();

    /**
     * Show text shadow on chat lines
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> CHAT_SHADOW = SimpleOption.<Boolean>builder()
        .comment("Show text shadow on chat lines")
        .node("chat", "chat-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> DISABLE_CHAT = SimpleOption.<Boolean>builder()
        .node("chat", "disable-chat").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> NO_CLOSE_MY_CHAT = SimpleOption.<Boolean>builder()
        .node("chat", "no-close-my-chat").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * ENABLED: Completely hides filtered messages DISABLED: Replaces filtered words with asterisks
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> STOP_PROFANE_MESSAGES = SimpleOption.<Boolean>builder()
        .comment("ENABLED: Completely hides filtered messages DISABLED: Replaces filtered words with asterisks")
        .node("chat", "stop-profane-messages").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> INPUT_FIELD_OPACITY = NumberOption.<Float>number()
        .node("chat", "input-field-opacity").type(TypeToken.get(Float.class))
        .defaultValue(5.0F).min(0.0F).max(10.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> CHAT_PING_SOUND = SimpleOption.<Boolean>builder()
        .node("chat", "chat-ping-sound").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SMOOTH_CHAT = SimpleOption.<Boolean>builder()
        .node("chat", "smooth-chat").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> SMOOTH_CHAT_SPEED = NumberOption.<Integer>number()
        .node("chat", "smooth-chat-speed").type(TypeToken.get(Integer.class))
        .defaultValue(3).min(1).max(10)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> CHAT_TIMESTAMPS = SimpleOption.<Boolean>builder()
        .node("chat", "chat-timestamps").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * Makes the timestamp italics
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TIMESTAMP_ITALICS = SimpleOption.<Boolean>builder()
        .comment("Makes the timestamp italics")
        .node("chat", "timestamp-italics").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * Makes the timestamp bold
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TIMESTAMP_BOLD = SimpleOption.<Boolean>builder()
        .comment("Makes the timestamp bold")
        .node("chat", "timestamp-bold").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * Use a 12-hour clock, or a 24-hour clock if disabled
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TWELVE_HOUR_CLOCK = SimpleOption.<Boolean>builder()
        .comment("Use a 12-hour clock, or a 24-hour clock if disabled")
        .node("chat", "twelve-hour-clock").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_AM_PM = SimpleOption.<Boolean>builder()
        .node("chat", "show-am-pm").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_SECONDS = SimpleOption.<Boolean>builder()
        .node("chat", "show-seconds").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * Adds square brackets around the timestamp
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_BRACKETS = SimpleOption.<Boolean>builder()
        .comment("Adds square brackets around the timestamp")
        .node("chat", "show-brackets").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * Copies the hovered chat message when holding the keybind and clicking.
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> COPY_CHAT = SimpleOption.<Boolean>builder()
        .comment("Copies the hovered chat message when holding the keybind and clicking.")
        .node("chat", "copy-chat").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

}
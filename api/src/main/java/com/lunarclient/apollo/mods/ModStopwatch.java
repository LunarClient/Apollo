package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Adds a stopwatch to the HUD.
 * 
 * @since %release_version%
 */
public final class ModStopwatch {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("stopwatch", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> SCALE = NumberOption.<Float>number()
        .node("stopwatch", "scale").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.5F).max(1.5F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> RESET_EVERY_START = SimpleOption.<Boolean>builder()
        .node("stopwatch", "reset-every-start").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TEXT_SHADOW = SimpleOption.<Boolean>builder()
        .node("stopwatch", "text-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BACKGROUND = SimpleOption.<Boolean>builder()
        .node("stopwatch", "background").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * If this is disabled the background will change size with the text.
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> STATIC_BACKGROUND_WIDTH = SimpleOption.<Boolean>builder()
        .comment("If this is disabled the background will change size with the text.")
        .node("stopwatch", "static-background-width").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> BACKGROUND_WIDTH = NumberOption.<Integer>number()
        .node("stopwatch", "background-width").type(TypeToken.get(Integer.class))
        .defaultValue(56).min(44).max(120)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> BACKGROUND_HEIGHT = NumberOption.<Integer>number()
        .node("stopwatch", "background-height").type(TypeToken.get(Integer.class))
        .defaultValue(18).min(10).max(22)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BRACKETS = SimpleOption.<Boolean>builder()
        .node("stopwatch", "brackets").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BORDER = SimpleOption.<Boolean>builder()
        .node("stopwatch", "border").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> BORDER_THICKNESS = NumberOption.<Float>number()
        .node("stopwatch", "border-thickness").type(TypeToken.get(Float.class))
        .defaultValue(0.5F).min(0.5F).max(3.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> TEXT_COLOR = NumberOption.<Integer>number()
        .node("stopwatch", "text-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> BACKGROUND_COLOR = NumberOption.<Integer>number()
        .node("stopwatch", "background-color").type(TypeToken.get(Integer.class))
        .defaultValue(0x6F000000).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> BORDER_COLOR = NumberOption.<Integer>number()
        .node("stopwatch", "border-color").type(TypeToken.get(Integer.class))
        .defaultValue(0x9F000000).min(0x80000000).max(0x7FFFFFFF)
        .build();

}
package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Shows your current velocity
 * 
 * @since %release_version%
 */
public final class ModMomentum {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("momentum", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> SCALE = NumberOption.<Float>number()
        .node("momentum", "scale").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.5F).max(1.5F)
        .build();

    /**
     * If enabled then Y velocity is not used in the final speed.
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> USE_GROUND_SPEED = SimpleOption.<Boolean>builder()
        .comment("If enabled then Y velocity is not used in the final speed.")
        .node("momentum", "use-ground-speed").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * If this is disabled then instant velocity is used
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> USE_AVERAGE_VELOCITY = SimpleOption.<Boolean>builder()
        .comment("If this is disabled then instant velocity is used")
        .node("momentum", "use-average-velocity").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * Determines how many times a second velocity is calculated. Lower values will show a smoother velocity
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Integer> AVERAGING_PERIOD = NumberOption.<Integer>number()
        .comment("Determines how many times a second velocity is calculated. Lower values will show a smoother velocity")
        .node("momentum", "averaging-period").type(TypeToken.get(Integer.class))
        .defaultValue(8).min(1).max(50)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TEXT_SHADOW = SimpleOption.<Boolean>builder()
        .node("momentum", "text-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BACKGROUND = SimpleOption.<Boolean>builder()
        .node("momentum", "background").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * If this is disabled the background will change size with the text.
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> STATIC_BACKGROUND_WIDTH = SimpleOption.<Boolean>builder()
        .comment("If this is disabled the background will change size with the text.")
        .node("momentum", "static-background-width").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> BACKGROUND_WIDTH = NumberOption.<Integer>number()
        .node("momentum", "background-width").type(TypeToken.get(Integer.class))
        .defaultValue(56).min(50).max(62)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> BACKGROUND_HEIGHT = NumberOption.<Integer>number()
        .node("momentum", "background-height").type(TypeToken.get(Integer.class))
        .defaultValue(18).min(10).max(22)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BRACKETS = SimpleOption.<Boolean>builder()
        .node("momentum", "brackets").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BORDER = SimpleOption.<Boolean>builder()
        .node("momentum", "border").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> BORDER_THICKNESS = NumberOption.<Float>number()
        .node("momentum", "border-thickness").type(TypeToken.get(Float.class))
        .defaultValue(0.5F).min(0.5F).max(3.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> TEXT_COLOR = NumberOption.<Integer>number()
        .node("momentum", "text-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> BACKGROUND_COLOR = NumberOption.<Integer>number()
        .node("momentum", "background-color").type(TypeToken.get(Integer.class))
        .defaultValue(0x6F000000).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> BORDER_COLOR = NumberOption.<Integer>number()
        .node("momentum", "border-color").type(TypeToken.get(Integer.class))
        .defaultValue(0x9F000000).min(0x80000000).max(0x7FFFFFFF)
        .build();

}
package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Adds a tint to the screen when you are low on health.
 * 
 * @since %release_version%
 */
public final class ModDamageTint {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("damage-tint", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> VIGNETTE_COLOR = NumberOption.<Integer>number()
        .node("damage-tint", "vignette-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFF0000).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * Intensity of the tint.
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Float> VIGNETTE_INTENSITY = NumberOption.<Float>number()
        .comment("Intensity of the tint.")
        .node("damage-tint", "vignette-intensity").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.0F).max(1.0F)
        .build();

    /**
     * Show the tint when you have less than this amount of health as a percentage.
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Integer> SHOW_VIGNETTE_BELOW = NumberOption.<Integer>number()
        .comment("Show the tint when you have less than this amount of health as a percentage.")
        .node("damage-tint", "show-vignette-below").type(TypeToken.get(Integer.class))
        .defaultValue(100).min(0).max(100)
        .build();

    /**
     * Plays a heartbeat, becoming faster the lower your health.
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HEARTBEAT_AUDIO = SimpleOption.<Boolean>builder()
        .comment("Plays a heartbeat, becoming faster the lower your health.")
        .node("damage-tint", "heartbeat-audio").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

}
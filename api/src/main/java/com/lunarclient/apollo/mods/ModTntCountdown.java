package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Displays the fuse countdown above TNT.
 * 
 * @since %release_version%
 */
public final class ModTntCountdown {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("tnt-countdown", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * Offset the default tnt delay in ticks. 1 second is 20 ticks
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Integer> ADJUST_FUSE_TIME = NumberOption.<Integer>number()
        .comment("Offset the default tnt delay in ticks. 1 second is 20 ticks")
        .node("tnt-countdown", "adjust-fuse-time").type(TypeToken.get(Integer.class))
        .defaultValue(0).min(-80).max(80)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BACKGROUND = SimpleOption.<Boolean>builder()
        .node("tnt-countdown", "background").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TEXT_SHADOW = SimpleOption.<Boolean>builder()
        .node("tnt-countdown", "text-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * Should the color stay the same the entire time.
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> STATIC_COUNTDOWN_COLOR = SimpleOption.<Boolean>builder()
        .comment("Should the color stay the same the entire time.")
        .node("tnt-countdown", "static-countdown-color").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> COLOR = NumberOption.<Integer>number()
        .node("tnt-countdown", "color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFF00FF00).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> PREFIX_COLOR = NumberOption.<Integer>number()
        .node("tnt-countdown", "prefix-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .build();

}
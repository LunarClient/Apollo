package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Customize the current time of day to a fixed position.
 * 
 * @since %release_version%
 */
public final class ModTimeChanger {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("time-changer", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> TIME_CHANGER_TIME = NumberOption.<Integer>number()
        .node("time-changer", "time-changer-time").type(TypeToken.get(Integer.class))
        .defaultValue(-12000).min(-18000).max(-6000)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> HORIZON_Y_LEVEL = NumberOption.<Integer>number()
        .node("time-changer", "horizon-y-level").type(TypeToken.get(Integer.class))
        .defaultValue(63).min(0).max(63)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> USE_REAL_TIME = SimpleOption.<Boolean>builder()
        .node("time-changer", "use-real-time").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

}
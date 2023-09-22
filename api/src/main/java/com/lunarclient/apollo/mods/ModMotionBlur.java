package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Blur your surroundings when moving.
 * 
 * @since %release_version%
 */
public final class ModMotionBlur {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("motion-blur", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> OLD_BLUR = SimpleOption.<Boolean>builder()
        .node("motion-blur", "old-blur").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> VALUE = NumberOption.<Integer>number()
        .node("motion-blur", "value").type(TypeToken.get(Integer.class))
        .defaultValue(1).min(1).max(10)
        .build();

}
package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Lets you see through blocks.
 * 
 * @since %release_version%
 */
public final class ModStaffXray {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("staff-xray", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> OPACITY = NumberOption.<Integer>number()
        .node("staff-xray", "opacity").type(TypeToken.get(Integer.class))
        .defaultValue(120).min(1).max(255)
        .build();

}
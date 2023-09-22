package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Changes the color of the damage indicator.
 * 
 * @since %release_version%
 */
public final class ModHitColor {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("hit-color", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> HIT_ARMOR_COLOR = NumberOption.<Integer>number()
        .node("hit-color", "hit-armor-color").type(TypeToken.get(Integer.class))
        .defaultValue(0x66990000).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOULD_COLOR_ARMOR = SimpleOption.<Boolean>builder()
        .node("hit-color", "should-color-armor").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

}
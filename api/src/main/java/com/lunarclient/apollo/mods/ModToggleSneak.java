package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Make the sprinting and sneaking keys toggleable instead of needing to be held down.
 * 
 * @since %release_version%
 */
public final class ModToggleSneak {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("toggle-sneak", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TOGGLE_SPRINT = SimpleOption.<Boolean>builder()
        .node("toggle-sneak", "toggle-sprint").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TOGGLE_SNEAK = SimpleOption.<Boolean>builder()
        .node("toggle-sneak", "toggle-sneak").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> DOUBLE_TAP = SimpleOption.<Boolean>builder()
        .node("toggle-sneak", "double-tap").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TOGGLE_SNEAK_CONTAINER = SimpleOption.<Boolean>builder()
        .node("toggle-sneak", "toggle-sneak-container").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> FLY_BOOST = SimpleOption.<Boolean>builder()
        .node("toggle-sneak", "fly-boost").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> FLY_BOOST_AMOUNT = NumberOption.<Integer>number()
        .node("toggle-sneak", "fly-boost-amount").type(TypeToken.get(Integer.class))
        .defaultValue(4).min(2).max(8)
        .build();

}
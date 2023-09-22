package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Blurs the background behind menus.
 * 
 * @since %release_version%
 */
public final class ModMenuBlur {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("menu-blur", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> BLUR_STRENGTH = NumberOption.<Integer>number()
        .node("menu-blur", "blur-strength").type(TypeToken.get(Integer.class))
        .defaultValue(4).min(0).max(20)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BLUR_LUNAR_TOGGLE = SimpleOption.<Boolean>builder()
        .node("menu-blur", "blur-lunar-toggle").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> BLUR_LUNAR_BACKGROUND = NumberOption.<Integer>number()
        .node("menu-blur", "blur-lunar-background").type(TypeToken.get(Integer.class))
        .defaultValue(0x0).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BLUR_INVENTORY_TOGGLE = SimpleOption.<Boolean>builder()
        .node("menu-blur", "blur-inventory-toggle").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> BLUR_INVENTORY_BACKGROUND = NumberOption.<Integer>number()
        .node("menu-blur", "blur-inventory-background").type(TypeToken.get(Integer.class))
        .defaultValue(0x6F000000).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BLUR_PAUSE_TOGGLE = SimpleOption.<Boolean>builder()
        .node("menu-blur", "blur-pause-toggle").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> BLUR_PAUSE_BACKGROUND = NumberOption.<Integer>number()
        .node("menu-blur", "blur-pause-background").type(TypeToken.get(Integer.class))
        .defaultValue(0x6F000000).min(0x80000000).max(0x7FFFFFFF)
        .build();

}
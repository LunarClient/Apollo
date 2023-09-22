package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * Saturation HUD with AppleSkin features
 * 
 * @since %release_version%
 */
public final class ModSaturation {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("saturation", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_SATURATION_OVERLAY = SimpleOption.<Boolean>builder()
        .node("saturation", "show-saturation-overlay").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_APPLESKIN_TOOLTIP = SimpleOption.<Boolean>builder()
        .node("saturation", "show-appleskin-tooltip").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_HELD_ITEM_HUNGER = SimpleOption.<Boolean>builder()
        .node("saturation", "show-held-item-hunger").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_HELD_ITEM_SATURATION = SimpleOption.<Boolean>builder()
        .node("saturation", "show-held-item-saturation").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

}
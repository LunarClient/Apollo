package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * Use your scroll wheel to read overly long item descriptions.
 * 
 * @since %release_version%
 */
public final class ModScrollableTooltips {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("scrollable-tooltips", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> START_AT_TOP = SimpleOption.<Boolean>builder()
        .node("scrollable-tooltips", "start-at-top").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> VERTICAL_KEYBIND = SimpleOption.<Boolean>builder()
        .node("scrollable-tooltips", "vertical-keybind").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TEXT_SHADOW = SimpleOption.<Boolean>builder()
        .node("scrollable-tooltips", "text-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> WRAP_TEXT = SimpleOption.<Boolean>builder()
        .node("scrollable-tooltips", "wrap-text").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

}
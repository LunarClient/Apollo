package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Display your cardinal direction on the HUD.
 * 
 * @since %release_version%
 */
public final class ModDirectionHud {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("direction-hud", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> SCALE = NumberOption.<Float>number()
        .node("direction-hud", "scale").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.5F).max(1.5F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> WIDTH = NumberOption.<Float>number()
        .node("direction-hud", "width").type(TypeToken.get(Float.class))
        .defaultValue(280.0F).min(168.0F).max(448.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TEXT_SHADOW = SimpleOption.<Boolean>builder()
        .node("direction-hud", "text-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * Show the cardinal directions in BOLD.
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BOLD_DIRECTIONS = SimpleOption.<Boolean>builder()
        .comment("Show the cardinal directions in BOLD.")
        .node("direction-hud", "bold-directions").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BACKGROUND = SimpleOption.<Boolean>builder()
        .node("direction-hud", "background").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BORDER = SimpleOption.<Boolean>builder()
        .node("direction-hud", "border").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> BORDER_THICKNESS = NumberOption.<Float>number()
        .node("direction-hud", "border-thickness").type(TypeToken.get(Float.class))
        .defaultValue(0.5F).min(0.5F).max(3.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> USE_LEGACY_STYLE = SimpleOption.<Boolean>builder()
        .node("direction-hud", "use-legacy-style").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_MARKER = SimpleOption.<Boolean>builder()
        .node("direction-hud", "show-marker").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_MARKER_VALUE = SimpleOption.<Boolean>builder()
        .node("direction-hud", "show-marker-value").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> BACKGROUND_COLOR = NumberOption.<Integer>number()
        .node("direction-hud", "background-color").type(TypeToken.get(Integer.class))
        .defaultValue(0x6F000000).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> BORDER_COLOR = NumberOption.<Integer>number()
        .node("direction-hud", "border-color").type(TypeToken.get(Integer.class))
        .defaultValue(0x9F000000).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> MARKER_COLOR = NumberOption.<Integer>number()
        .node("direction-hud", "marker-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> DIRECTION_COLOR = NumberOption.<Integer>number()
        .node("direction-hud", "direction-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .build();

}
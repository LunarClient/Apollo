package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Replace the default Minecraft crosshair with a customizable crosshair.
 * 
 * @since %release_version%
 */
public final class ModCrosshair {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("crosshair", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> CROSSHAIR_THICKNESS = NumberOption.<Integer>number()
        .node("crosshair", "crosshair-thickness").type(TypeToken.get(Integer.class))
        .defaultValue(1).min(1).max(5)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> CROSSHAIR_SIZE = NumberOption.<Integer>number()
        .node("crosshair", "crosshair-size").type(TypeToken.get(Integer.class))
        .defaultValue(4).min(0).max(8)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> CROSSHAIR_GAP = NumberOption.<Integer>number()
        .node("crosshair", "crosshair-gap").type(TypeToken.get(Integer.class))
        .defaultValue(0).min(0).max(8)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> CROSSHAIR_DOT = SimpleOption.<Boolean>builder()
        .node("crosshair", "crosshair-dot").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> CROSSHAIR_OUTLINE = SimpleOption.<Boolean>builder()
        .node("crosshair", "crosshair-outline").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> OUTLINE_THICKNESS = NumberOption.<Float>number()
        .node("crosshair", "outline-thickness").type(TypeToken.get(Float.class))
        .defaultValue(0.5F).min(0.0F).max(1.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> OUTLINE_COLOR = NumberOption.<Integer>number()
        .node("crosshair", "outline-color").type(TypeToken.get(Integer.class))
        .defaultValue(0x88000000).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> CUSTOM_SCALE = SimpleOption.<Boolean>builder()
        .node("crosshair", "custom-scale").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> COLOR = NumberOption.<Integer>number()
        .node("crosshair", "color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> FRIENDLY_COLOR = NumberOption.<Integer>number()
        .node("crosshair", "friendly-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFF33FF33).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> ENEMY_COLOR = NumberOption.<Integer>number()
        .node("crosshair", "enemy-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFF3333).min(0x80000000).max(0x7FFFFFFF)
        .build();

}
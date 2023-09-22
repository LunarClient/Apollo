package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Allows you to adjust the strength of fog for various fog types
 * 
 * @since %release_version%
 */
public final class ModFog {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("fog", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> WATER_FOG_DENSITY = NumberOption.<Float>number()
        .node("fog", "water-fog-density").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.0F).max(1.8F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> RENDER_DISTANCE_FOG_DENSITY = NumberOption.<Float>number()
        .node("fog", "render-distance-fog-density").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.0F).max(1.95F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> RENDER_DISTANCE_FOG_COLOR_TOGGLE = SimpleOption.<Boolean>builder()
        .node("fog", "render-distance-fog-color-toggle").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> RENDER_DISTANCE_FOG_COLOR = NumberOption.<Integer>number()
        .node("fog", "render-distance-fog-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xC0D8FF).min(0x80000000).max(0x7FFFFFFF)
        .build();

}
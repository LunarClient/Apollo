package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * A mod class
 * 
 * @since %release_version%
 */
public final class ModColorSaturation {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("color-saturation", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> COLOR_SATURATION_HUE = NumberOption.<Float>number()
        .node("color-saturation", "color-saturation-hue").type(TypeToken.get(Float.class))
        .defaultValue(0.0F).min(0.0F).max(10.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> COLOR_SATURATION_SATURATION = NumberOption.<Float>number()
        .node("color-saturation", "color-saturation-saturation").type(TypeToken.get(Float.class))
        .defaultValue(5.0F).min(0.0F).max(10.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> COLOR_SATURATION_BRIGHTNESS = NumberOption.<Float>number()
        .node("color-saturation", "color-saturation-brightness").type(TypeToken.get(Float.class))
        .defaultValue(5.0F).min(0.0F).max(10.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> COLOR_SATURATION_CONTRAST = NumberOption.<Float>number()
        .node("color-saturation", "color-saturation-contrast").type(TypeToken.get(Float.class))
        .defaultValue(5.0F).min(0.0F).max(10.0F)
        .build();

}
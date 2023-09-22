package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Allows you to configure or disable lighting effects, which can increase FPS.
 * 
 * @since %release_version%
 */
public final class ModLighting {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("lighting", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> FULL_BRIGHT = SimpleOption.<Boolean>builder()
        .node("lighting", "full-bright").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> BRIGHTNESS_BOOST = NumberOption.<Float>number()
        .node("lighting", "brightness-boost").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(1.0F).max(10.0F)
        .build();

}
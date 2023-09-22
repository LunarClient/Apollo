package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Customize the current weather.
 * 
 * @since %release_version%
 */
public final class ModWeatherChanger {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("weather-changer", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> RAIN_STRENGTH = NumberOption.<Float>number()
        .node("weather-changer", "rain-strength").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.0F).max(1.0F)
        .build();

    /**
     * Augment the rain/snow color
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Integer> RAIN_COLOR = NumberOption.<Integer>number()
        .comment("Augment the rain/snow color")
        .node("weather-changer", "rain-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .build();

}
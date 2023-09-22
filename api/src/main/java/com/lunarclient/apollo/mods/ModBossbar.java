package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Allows you to hide, move or customize the Minecraft boss bar.
 * 
 * @since %release_version%
 */
public final class ModBossbar {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("bossbar", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> SCALE = NumberOption.<Float>number()
        .node("bossbar", "scale").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.5F).max(1.5F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> RENDER_BAR = SimpleOption.<Boolean>builder()
        .node("bossbar", "render-bar").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> CUSTOM_BOSS_BAR = SimpleOption.<Boolean>builder()
        .node("bossbar", "custom-boss-bar").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> BAR_COLOR = NumberOption.<Integer>number()
        .node("bossbar", "bar-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFF249F2).min(0x80000000).max(0x7FFFFFFF)
        .build();

}
package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Resize items that are commonly needed in UHC to make spotting them easier.
 * 
 * @since %release_version%
 */
public final class ModUhcOverlay {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("uhc-overlay", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> GOLD_INGOT_SCALE = NumberOption.<Float>number()
        .node("uhc-overlay", "gold-ingot-scale").type(TypeToken.get(Float.class))
        .defaultValue(2.0F).min(1.0F).max(5.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> GOLD_NUGGET_SCALE = NumberOption.<Float>number()
        .node("uhc-overlay", "gold-nugget-scale").type(TypeToken.get(Float.class))
        .defaultValue(2.0F).min(1.0F).max(5.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> GOLD_ORE_SCALE = NumberOption.<Float>number()
        .node("uhc-overlay", "gold-ore-scale").type(TypeToken.get(Float.class))
        .defaultValue(2.0F).min(1.0F).max(5.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> GOLD_APPLE_SCALE = NumberOption.<Float>number()
        .node("uhc-overlay", "gold-apple-scale").type(TypeToken.get(Float.class))
        .defaultValue(2.0F).min(1.0F).max(5.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> SKULL_SCALE = NumberOption.<Float>number()
        .node("uhc-overlay", "skull-scale").type(TypeToken.get(Float.class))
        .defaultValue(2.0F).min(1.0F).max(5.0F)
        .build();

}
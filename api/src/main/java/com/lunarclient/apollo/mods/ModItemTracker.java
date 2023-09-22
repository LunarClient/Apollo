package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Shows you when and how much items you pick up or drop.
 * 
 * @since %release_version%
 */
public final class ModItemTracker {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("item-tracker", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> SCALE = NumberOption.<Float>number()
        .node("item-tracker", "scale").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.5F).max(1.5F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TEXT_SHADOW = SimpleOption.<Boolean>builder()
        .node("item-tracker", "text-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SKYBLOCK_ONLY = SimpleOption.<Boolean>builder()
        .node("item-tracker", "skyblock-only").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * How long the text should stay on screen (seconds)
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Float> POPUP_DURATION_SEC = NumberOption.<Float>number()
        .comment("How long the text should stay on screen (seconds)")
        .node("item-tracker", "popup-duration-sec").type(TypeToken.get(Float.class))
        .defaultValue(5.0F).min(0.1F).max(30.0F)
        .build();

}
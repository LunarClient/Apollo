package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * A mod that allows you to modify nametags.
 * 
 * @since %release_version%
 */
public final class ModNametag {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("nametag", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> NAMETAG_SHADOW = SimpleOption.<Boolean>builder()
        .node("nametag", "nametag-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * An option to see your own nametag in third person
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> NAMETAG = SimpleOption.<Boolean>builder()
        .comment("An option to see your own nametag in third person")
        .node("nametag", "nametag").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * An option to toggle LC logos in nametags
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> NAMETAG_LOGO = SimpleOption.<Boolean>builder()
        .comment("An option to toggle LC logos in nametags")
        .node("nametag", "nametag-logo").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * Determines if we should send the message when nametags are toggled.
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TOGGLE_NAMETAGS = SimpleOption.<Boolean>builder()
        .comment("Determines if we should send the message when nametags are toggled.")
        .node("nametag", "toggle-nametags").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> NAMETAG_BACKGROUND_OPACITY = NumberOption.<Float>number()
        .node("nametag", "nametag-background-opacity").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.0F).max(1.0F)
        .build();

}
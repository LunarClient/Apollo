package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Adjust the minecraft title and subtitle scale and location.
 * 
 * @since %release_version%
 */
public final class ModTitles {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("titles", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> SCALE = NumberOption.<Float>number()
        .node("titles", "scale").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.5F).max(1.5F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_IN_HUD_EDITOR = SimpleOption.<Boolean>builder()
        .node("titles", "show-in-hud-editor").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> TITLE_COLOR = NumberOption.<Integer>number()
        .node("titles", "title-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFF5555).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> SUBTITLE_COLOR = NumberOption.<Integer>number()
        .node("titles", "subtitle-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .build();

}
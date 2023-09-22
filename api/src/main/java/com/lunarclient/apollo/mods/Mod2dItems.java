package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * Render blocks and items in a 2D perspective or with sprites.
 * 
 * @since %release_version%
 */
public final class Mod2dItems {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("2d-items", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

}
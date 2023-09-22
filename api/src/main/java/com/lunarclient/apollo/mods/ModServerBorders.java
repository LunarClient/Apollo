package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * A mod class
 * 
 * @since %release_version%
 */
public final class ModServerBorders {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("server-borders", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

}
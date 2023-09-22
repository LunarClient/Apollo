package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * Skyblock Mod made by Moulberry
 * 
 * @since %release_version%
 */
public final class ModNeu {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("neu", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

}
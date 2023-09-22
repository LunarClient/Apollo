package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * Quickly join games on the Hypixel network
 * 
 * @since %release_version%
 */
public final class ModQuickplay {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("quickplay", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

}
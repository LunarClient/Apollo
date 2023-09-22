package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * Displays various PvP statistics
 * 
 * @since %release_version%
 */
public final class ModPvpInfo {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("pvp-info", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> RESET_ON_WORLD_CHANGE = SimpleOption.<Boolean>builder()
        .node("pvp-info", "reset-on-world-change").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

}
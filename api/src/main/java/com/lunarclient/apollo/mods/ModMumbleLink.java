package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * Uses mumble to create positional audio in the Minecraft world.
 * 
 * @since %release_version%
 */
public final class ModMumbleLink {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("mumble-link", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

}
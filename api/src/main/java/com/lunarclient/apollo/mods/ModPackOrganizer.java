package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * Allows you to organize your resource packs into folders
 * 
 * @since %release_version%
 */
public final class ModPackOrganizer {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("pack-organizer", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_INCOMPATIBLE = SimpleOption.<Boolean>builder()
        .node("pack-organizer", "show-incompatible").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

}
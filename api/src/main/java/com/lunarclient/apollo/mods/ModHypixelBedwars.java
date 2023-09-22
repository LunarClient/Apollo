package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * Various enhancements to assist you when playing Hypixel Bedwars
 * 
 * @since %release_version%
 */
public final class ModHypixelBedwars {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("hypixel-bedwars", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * Recolor the beds in-game according to the team's color
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> COLORED_BEDS = SimpleOption.<Boolean>builder()
        .comment("Recolor the beds in-game according to the team's color")
        .node("hypixel-bedwars", "colored-beds").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * Replaces normal hearts with hardcore hearts once your bed is lost
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BW_HARDCORE_HEARTS = SimpleOption.<Boolean>builder()
        .comment("Replaces normal hearts with hardcore hearts once your bed is lost")
        .node("hypixel-bedwars", "bw-hardcore-hearts").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

}
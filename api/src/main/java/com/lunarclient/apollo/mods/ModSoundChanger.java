package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * Modify the volume of any sound in the game
 * 
 * @since %release_version%
 */
public final class ModSoundChanger {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("sound-changer", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

}
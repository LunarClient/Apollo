package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * Allows you to set a key or key combination that will send a command or chat message when pressed.
 * 
 * @since %release_version%
 */
public final class ModAutoTextHotkey {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("auto-text-hotkey", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

}
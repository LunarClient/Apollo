package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * Shows you where your teammates are on the HUD.
 * 
 * @since %release_version%
 */
public final class ModTeamView {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("team-view", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

}
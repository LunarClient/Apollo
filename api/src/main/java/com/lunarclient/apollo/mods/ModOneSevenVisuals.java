package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * Revert certain visuals and animations to how they behaved on 1.7.
 * 
 * @since %release_version%
 */
public final class ModOneSevenVisuals {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("one-seven-visuals", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

}
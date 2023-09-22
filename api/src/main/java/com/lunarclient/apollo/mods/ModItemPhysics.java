package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Replaces ground items with ones that have physics.
 * 
 * @since %release_version%
 */
public final class ModItemPhysics {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("item-physics", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> ROTATION_SPEED = NumberOption.<Float>number()
        .node("item-physics", "rotation-speed").type(TypeToken.get(Float.class))
        .defaultValue(0.5F).min(0.1F).max(4.0F)
        .build();

}
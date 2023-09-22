package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * When activated, allows you to change your camera perspective while a button is pressed.
 * 
 * @since %release_version%
 */
public final class ModSnaplook {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("snaplook", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * When zoomed in the camera movement will move smoothly (cinematic camera)
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SMOOTH_CAMERA = SimpleOption.<Boolean>builder()
        .comment("When zoomed in the camera movement will move smoothly (cinematic camera)")
        .node("snaplook", "smooth-camera").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

}
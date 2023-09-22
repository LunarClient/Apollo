package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * When activated, allows you to change your camera perspective without rotating your player.
 * 
 * @since %release_version%
 */
public final class ModFreelook {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("freelook", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> INVERT_PITCH = SimpleOption.<Boolean>builder()
        .node("freelook", "invert-pitch").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> INVERT_YAW = SimpleOption.<Boolean>builder()
        .node("freelook", "invert-yaw").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * Quickly pressing and releasing the key will toggle freelook
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TOGGLE_KEY_FREELOOK = SimpleOption.<Boolean>builder()
        .comment("Quickly pressing and releasing the key will toggle freelook")
        .node("freelook", "toggle-key-freelook").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * When zoomed in the camera movement will move smoothly (cinematic camera)
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SMOOTH_CAMERA = SimpleOption.<Boolean>builder()
        .comment("When zoomed in the camera movement will move smoothly (cinematic camera)")
        .node("freelook", "smooth-camera").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

}
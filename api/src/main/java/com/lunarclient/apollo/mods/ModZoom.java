package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Allows you to smoothly zoom in and out.
 * 
 * @since %release_version%
 */
public final class ModZoom {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("zoom", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * Quickly pressing and releasing the key will toggle zoom
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TOGGLE_KEY_ZOOM = SimpleOption.<Boolean>builder()
        .comment("Quickly pressing and releasing the key will toggle zoom")
        .node("zoom", "toggle-key-zoom").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * When zoomed in the camera movement will move smoothly (cinematic camera)
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SMOOTH_CAMERA = SimpleOption.<Boolean>builder()
        .comment("When zoomed in the camera movement will move smoothly (cinematic camera)")
        .node("zoom", "smooth-camera").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * Add a transition when zooming in and out.
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SMOOTH_ZOOM = SimpleOption.<Boolean>builder()
        .comment("Add a transition when zooming in and out.")
        .node("zoom", "smooth-zoom").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * Adjust the zoom depth using Mouse Scroll Wheel
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> VARIABLE_ZOOM = SimpleOption.<Boolean>builder()
        .comment("Adjust the zoom depth using Mouse Scroll Wheel")
        .node("zoom", "variable-zoom").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * Change the initial zoom depth.
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Integer> ZOOM_DIVISOR = NumberOption.<Integer>number()
        .comment("Change the initial zoom depth.")
        .node("zoom", "zoom-divisor").type(TypeToken.get(Integer.class))
        .defaultValue(4).min(2).max(10)
        .build();

}
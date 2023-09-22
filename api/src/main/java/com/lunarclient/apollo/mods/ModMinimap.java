package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Display a miniature map of the world on your HUD.
 * 
 * @since %release_version%
 */
public final class ModMinimap {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("minimap", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> SCALE = NumberOption.<Float>number()
        .node("minimap", "scale").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.5F).max(1.5F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> MAP_WIDTH = NumberOption.<Integer>number()
        .node("minimap", "map-width").type(TypeToken.get(Integer.class))
        .defaultValue(8).min(1).max(16)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> MAP_HEIGHT = NumberOption.<Integer>number()
        .node("minimap", "map-height").type(TypeToken.get(Integer.class))
        .defaultValue(8).min(1).max(16)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> MAP_ZOOM = NumberOption.<Float>number()
        .node("minimap", "map-zoom").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(1.0F).max(2.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ROTATE_WITH_PLAYER = SimpleOption.<Boolean>builder()
        .node("minimap", "rotate-with-player").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BORDER = SimpleOption.<Boolean>builder()
        .node("minimap", "border").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> BORDER = NumberOption.<Integer>number()
        .node("minimap", "border").type(TypeToken.get(Integer.class))
        .defaultValue(0x9F000000).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> BORDER_THICKNESS = NumberOption.<Float>number()
        .node("minimap", "border-thickness").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(1.0F).max(5.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> COMPASS = SimpleOption.<Boolean>builder()
        .node("minimap", "compass").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> COMPASS_COLOR = NumberOption.<Integer>number()
        .node("minimap", "compass-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> COMPASS_SHADOW = SimpleOption.<Boolean>builder()
        .node("minimap", "compass-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> PLAYER_MARKER_COLOR = NumberOption.<Integer>number()
        .node("minimap", "player-marker-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFF0000FF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> PLAYER_MARKER_SIZE = NumberOption.<Float>number()
        .node("minimap", "player-marker-size").type(TypeToken.get(Float.class))
        .defaultValue(5.0F).min(1.0F).max(10.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> ENTITY_MARKER_OPACITY = NumberOption.<Float>number()
        .node("minimap", "entity-marker-opacity").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.0F).max(1.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> ENTITY_MARKER_SIZE = NumberOption.<Float>number()
        .node("minimap", "entity-marker-size").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(1.0F).max(10.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENTITY_MARKER_SHADOW = SimpleOption.<Boolean>builder()
        .node("minimap", "entity-marker-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_COORDINATES = SimpleOption.<Boolean>builder()
        .node("minimap", "show-coordinates").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

}
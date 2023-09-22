package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Create a waypoint anywhere in the world that will show how for away from a location you are as well as optionally render the name and a beam.
 * 
 * @since %release_version%
 */
public final class ModWaypoints {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("waypoints", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> DEATH_WAYPOINT = SimpleOption.<Boolean>builder()
        .node("waypoints", "death-waypoint").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * Adds a waypoint if coordinates are clicked in chat.
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ADD_FROM_CHAT = SimpleOption.<Boolean>builder()
        .comment("Adds a waypoint if coordinates are clicked in chat.")
        .node("waypoints", "add-from-chat").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> WAYPOINT_BEAMS = SimpleOption.<Boolean>builder()
        .node("waypoints", "waypoint-beams").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BOX_BORDER = SimpleOption.<Boolean>builder()
        .node("waypoints", "box-border").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TEXT_SHADOW = SimpleOption.<Boolean>builder()
        .node("waypoints", "text-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> BOX_PADDING = NumberOption.<Float>number()
        .node("waypoints", "box-padding").type(TypeToken.get(Float.class))
        .defaultValue(4.0F).min(1.0F).max(8.0F)
        .build();

    /**
     * Only show each waypoint when looking near then in the world
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ONLY_SHOW_WHEN_LOOKING_NEAR = SimpleOption.<Boolean>builder()
        .comment("Only show each waypoint when looking near then in the world")
        .node("waypoints", "only-show-when-looking-near").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * Show simpler text icons above each waypoint
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_ICONS = SimpleOption.<Boolean>builder()
        .comment("Show simpler text icons above each waypoint")
        .node("waypoints", "show-icons").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> LABEL_SCALE = NumberOption.<Float>number()
        .node("waypoints", "label-scale").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.1F).max(2.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> ICON_SCALE = NumberOption.<Float>number()
        .node("waypoints", "icon-scale").type(TypeToken.get(Float.class))
        .defaultValue(1.5F).min(0.1F).max(3.0F)
        .build();

}
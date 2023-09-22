package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Allows you to customize your field of view for different potion or speed states.
 * 
 * @since %release_version%
 */
public final class ModFov {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("fov", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * Determines if your FOV changes as you move.
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> STATIC_F_O_V = SimpleOption.<Boolean>builder()
        .comment("Determines if your FOV changes as you move.")
        .node("fov", "static-fov").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> DEFAULT_FOV = NumberOption.<Integer>number()
        .node("fov", "default-fov").type(TypeToken.get(Integer.class))
        .defaultValue(70).min(30).max(110)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> AIMING_MODIFIER = NumberOption.<Float>number()
        .node("fov", "aiming-modifier").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.0F).max(5.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> AIMING_MIN = NumberOption.<Float>number()
        .node("fov", "aiming-min").type(TypeToken.get(Float.class))
        .defaultValue(-10.0F).min(-200.0F).max(200.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> AIMING_MAX = NumberOption.<Float>number()
        .node("fov", "aiming-max").type(TypeToken.get(Float.class))
        .defaultValue(10.0F).min(-200.0F).max(200.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> SPEED_F_O_V = NumberOption.<Integer>number()
        .node("fov", "speed-fov").type(TypeToken.get(Integer.class))
        .defaultValue(70).min(30).max(110)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> SPEED_TWO_F_O_V = NumberOption.<Integer>number()
        .node("fov", "speed-two-fov").type(TypeToken.get(Integer.class))
        .defaultValue(70).min(30).max(110)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> SLOWNESS_F_O_V = NumberOption.<Integer>number()
        .node("fov", "slowness-fov").type(TypeToken.get(Integer.class))
        .defaultValue(70).min(30).max(110)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> MOVEMENT_MODIFIER = NumberOption.<Float>number()
        .node("fov", "movement-modifier").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.0F).max(5.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> MOVEMENT_MIN = NumberOption.<Float>number()
        .node("fov", "movement-min").type(TypeToken.get(Float.class))
        .defaultValue(-10.0F).min(-200.0F).max(200.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> MOVEMENT_MAX = NumberOption.<Float>number()
        .node("fov", "movement-max").type(TypeToken.get(Float.class))
        .defaultValue(10.0F).min(-200.0F).max(200.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> SPRINTING_F_O_V = NumberOption.<Integer>number()
        .node("fov", "sprinting-fov").type(TypeToken.get(Integer.class))
        .defaultValue(70).min(30).max(110)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> SPRINT_MODIFIER = NumberOption.<Float>number()
        .node("fov", "sprint-modifier").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.0F).max(5.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> SPRINT_MIN = NumberOption.<Float>number()
        .node("fov", "sprint-min").type(TypeToken.get(Float.class))
        .defaultValue(-10.0F).min(-200.0F).max(200.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> SPRINT_MAX = NumberOption.<Float>number()
        .node("fov", "sprint-max").type(TypeToken.get(Float.class))
        .defaultValue(10.0F).min(-200.0F).max(200.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> FLYING_FOV = NumberOption.<Integer>number()
        .node("fov", "flying-fov").type(TypeToken.get(Integer.class))
        .defaultValue(70).min(30).max(110)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> FLYING_MODIFIER = NumberOption.<Float>number()
        .node("fov", "flying-modifier").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.0F).max(5.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> FLYING_MIN = NumberOption.<Float>number()
        .node("fov", "flying-min").type(TypeToken.get(Float.class))
        .defaultValue(-10.0F).min(-200.0F).max(200.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> FLYING_MAX = NumberOption.<Float>number()
        .node("fov", "flying-max").type(TypeToken.get(Float.class))
        .defaultValue(10.0F).min(-200.0F).max(200.0F)
        .build();

}
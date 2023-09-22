package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Customize the hitbox displayed in F3+B
 * 
 * @since %release_version%
 */
public final class ModHitbox {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("hitbox", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> HITBOX_PLAYER_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-player-line-width").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(1.0F).max(5.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> HITBOX_PLAYER_LINE_COLOR = NumberOption.<Integer>number()
        .node("hitbox", "hitbox-player-line-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HITBOX_PLAYER_LOOK_VECTOR = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-player-look-vector").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HITBOX_PLAYER_SHOW = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-player-show").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> HITBOX_ITEM_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-item-line-width").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(1.0F).max(5.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> HITBOX_ITEM_LINE_COLOR = NumberOption.<Integer>number()
        .node("hitbox", "hitbox-item-line-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HITBOX_ITEM_LOOK_VECTOR = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-item-look-vector").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HITBOX_ITEM_SHOW = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-item-show").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> HITBOX_PROJECTILE_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-projectile-line-width").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(1.0F).max(5.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> HITBOX_PROJECTILE_LINE_COLOR = NumberOption.<Integer>number()
        .node("hitbox", "hitbox-projectile-line-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HITBOX_PROJECTILE_LOOK_VECTOR = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-projectile-look-vector").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HITBOX_PROJECTILE_SHOW = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-projectile-show").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> HITBOX_MOB_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-mob-line-width").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(1.0F).max(5.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> HITBOX_MOB_LINE_COLOR = NumberOption.<Integer>number()
        .node("hitbox", "hitbox-mob-line-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HITBOX_MOB_LOOK_VECTOR = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-mob-look-vector").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HITBOX_MOB_SHOW = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-mob-show").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> HITBOX_EXP_ORB_LINE_WIDTH = NumberOption.<Float>number()
        .node("hitbox", "hitbox-exp-orb-line-width").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(1.0F).max(5.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> HITBOX_EXP_ORB_LINE_COLOR = NumberOption.<Integer>number()
        .node("hitbox", "hitbox-exp-orb-line-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HITBOX_EXP_ORB_LOOK_VECTOR = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-exp-orb-look-vector").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HITBOX_EXP_ORB_SHOW = SimpleOption.<Boolean>builder()
        .node("hitbox", "hitbox-exp-orb-show").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

}
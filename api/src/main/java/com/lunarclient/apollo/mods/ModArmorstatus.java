package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Displays information about the armor you have equipped and the item you are holding on the HUD.
 * 
 * @since %release_version%
 */
public final class ModArmorstatus {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("armorstatus", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> SCALE = NumberOption.<Float>number()
        .node("armorstatus", "scale").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.5F).max(1.5F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> MOVE_ARMOR_INDIVIDUALLY = SimpleOption.<Boolean>builder()
        .node("armorstatus", "move-armor-individually").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ITEM_NAME = SimpleOption.<Boolean>builder()
        .node("armorstatus", "item-name").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ITEM_COUNT = SimpleOption.<Boolean>builder()
        .node("armorstatus", "item-count").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_WHILE_TYPING = SimpleOption.<Boolean>builder()
        .node("armorstatus", "show-while-typing").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TEXT_SHADOW = SimpleOption.<Boolean>builder()
        .node("armorstatus", "text-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> DAMAGE_OVERLAY = SimpleOption.<Boolean>builder()
        .node("armorstatus", "damage-overlay").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ITEM_DAMAGE = SimpleOption.<Boolean>builder()
        .node("armorstatus", "item-damage").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ARMOR_DAMAGE = SimpleOption.<Boolean>builder()
        .node("armorstatus", "armor-damage").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> MAX_DAMAGE = SimpleOption.<Boolean>builder()
        .node("armorstatus", "max-damage").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> STATIC_DAMAGE_COLORS = SimpleOption.<Boolean>builder()
        .node("armorstatus", "static-damage-colors").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> NAME_TEXT_COLOR = NumberOption.<Integer>number()
        .node("armorstatus", "name-text-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> HIGHEST_COLOR = NumberOption.<Integer>number()
        .node("armorstatus", "highest-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> HIGH_COLOR = NumberOption.<Integer>number()
        .node("armorstatus", "high-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFF55FF55).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> MEDIUM_COLOR = NumberOption.<Integer>number()
        .node("armorstatus", "medium-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFF55).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> MEDIUM_LOW_COLOR = NumberOption.<Integer>number()
        .node("armorstatus", "medium-low-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFAA00).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> LOW_COLOR = NumberOption.<Integer>number()
        .node("armorstatus", "low-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFF5555).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> LOWEST_COLOR = NumberOption.<Integer>number()
        .node("armorstatus", "lowest-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFAA0000).min(0x80000000).max(0x7FFFFFFF)
        .build();

}
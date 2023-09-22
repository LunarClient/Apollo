package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Display your currently active potion effects on the HUD.
 * 
 * @since %release_version%
 */
public final class ModPotionEffects {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("potion-effects", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> SCALE = NumberOption.<Float>number()
        .node("potion-effects", "scale").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.5F).max(1.5F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_IN_INVENTORY = SimpleOption.<Boolean>builder()
        .node("potion-effects", "show-in-inventory").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_WHILE_TYPING = SimpleOption.<Boolean>builder()
        .node("potion-effects", "show-while-typing").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> EFFECT_NAME = SimpleOption.<Boolean>builder()
        .node("potion-effects", "effect-name").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TEXT_SHADOW = SimpleOption.<Boolean>builder()
        .node("potion-effects", "text-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HIDE_MODERN_ICONS = SimpleOption.<Boolean>builder()
        .node("potion-effects", "hide-modern-icons").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> POTION_BLINK = SimpleOption.<Boolean>builder()
        .node("potion-effects", "potion-blink").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> BLINK_DURATION = NumberOption.<Integer>number()
        .node("potion-effects", "blink-duration").type(TypeToken.get(Integer.class))
        .defaultValue(10).min(2).max(20)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> COLOR_NAME_BASED_ON_EFFECT = SimpleOption.<Boolean>builder()
        .node("potion-effects", "color-name-based-on-effect").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> TEXT_COLOR = NumberOption.<Integer>number()
        .node("potion-effects", "text-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> DURATION_COLOR = NumberOption.<Integer>number()
        .node("potion-effects", "duration-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> EXCLUDE_PERM = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-perm").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> EXCLUDE_SPEED = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-speed").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> EXCLUDE_SLOWNESS = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-slowness").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> EXCLUDE_STRENGTH = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-strength").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> EXCLUDE_JUMP_BOOST = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-jump-boost").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> EXCLUDE_REGEN = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-regen").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> EXCLUDE_FIRE_RES = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-fire-res").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> EXCLUDE_WATER_BREATHING = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-water-breathing").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> EXCLUDE_NIGHT_VISION = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-night-vision").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> EXCLUDE_WEAKNESS = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-weakness").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> EXCLUDE_POISON = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-poison").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> EXCLUDE_HASTE = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-haste").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> EXCLUDE_INVIS = SimpleOption.<Boolean>builder()
        .node("potion-effects", "exclude-invis").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

}
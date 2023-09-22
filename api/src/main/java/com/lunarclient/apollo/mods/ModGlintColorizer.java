package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Customize the color of enchantment glints
 * 
 * @since %release_version%
 */
public final class ModGlintColorizer {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("glint-colorizer", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * Use a custom blending algorithm which supports darker colours
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> USE_LUNAR_EQUATION = SimpleOption.<Boolean>builder()
        .comment("Use a custom blending algorithm which supports darker colours")
        .node("glint-colorizer", "use-lunar-equation").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * Recolor the glints of items
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> OVERRIDE_ITEM_GLINT = SimpleOption.<Boolean>builder()
        .comment("Recolor the glints of items")
        .node("glint-colorizer", "override-item-glint").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> ITEM_GLINT_LUNAR_COLOR = NumberOption.<Integer>number()
        .node("glint-colorizer", "item-glint-lunar-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xCC6419FF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> ITEM_GLINT_VANILLA_COLOR = NumberOption.<Integer>number()
        .node("glint-colorizer", "item-glint-vanilla-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFF8040CC).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * Recolor the glints of worn armor
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> OVERRIDE_ARMOR_GLINT = SimpleOption.<Boolean>builder()
        .comment("Recolor the glints of worn armor")
        .node("glint-colorizer", "override-armor-glint").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> ARMOR_GLINT_LUNAR = NumberOption.<Integer>number()
        .node("glint-colorizer", "armor-glint-lunar").type(TypeToken.get(Integer.class))
        .defaultValue(0xCC6419FF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> ARMOR_GLINT_VANILLA = NumberOption.<Integer>number()
        .node("glint-colorizer", "armor-glint-vanilla").type(TypeToken.get(Integer.class))
        .defaultValue(0xFF8040CC).min(0x80000000).max(0x7FFFFFFF)
        .build();

}
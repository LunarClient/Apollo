package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Allows you to customize the outline or add an overlay to the block you are pointing at.
 * 
 * @since %release_version%
 */
public final class ModBlockOutline {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("block-outline", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> BLOCK_OUTLINE_COLOR = NumberOption.<Integer>number()
        .node("block-outline", "block-outline-color").type(TypeToken.get(Integer.class))
        .defaultValue(0x66000000).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> BLOCK_OVERLAY_COLOR = NumberOption.<Integer>number()
        .node("block-outline", "block-overlay-color").type(TypeToken.get(Integer.class))
        .defaultValue(0x66000000).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> BLOCK_OUTLINE_WIDTH = NumberOption.<Float>number()
        .node("block-outline", "block-outline-width").type(TypeToken.get(Float.class))
        .defaultValue(2.0F).min(1.0F).max(10.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BLOCK_OVERLAY = SimpleOption.<Boolean>builder()
        .node("block-outline", "block-overlay").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BLOCK_OUTLINE = SimpleOption.<Boolean>builder()
        .node("block-outline", "block-outline").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

}
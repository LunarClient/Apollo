package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Displays when your movement, mouse or space bar is pressed.
 * 
 * @since %release_version%
 */
public final class ModKeystrokes {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("keystrokes", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> SCALE = NumberOption.<Float>number()
        .node("keystrokes", "scale").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.5F).max(1.5F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> KEY_STROKES_CLICKS = SimpleOption.<Boolean>builder()
        .node("keystrokes", "key-strokes-clicks").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> LEFT_C_P_S = SimpleOption.<Boolean>builder()
        .node("keystrokes", "left-cps").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> RIGHT_C_P_S = SimpleOption.<Boolean>builder()
        .node("keystrokes", "right-cps").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> KEY_STROKES_MOVEMENT = SimpleOption.<Boolean>builder()
        .node("keystrokes", "key-strokes-movement").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> KEY_STROKES_SPACEBAR = SimpleOption.<Boolean>builder()
        .node("keystrokes", "key-strokes-spacebar").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> USE_ARROWS = SimpleOption.<Boolean>builder()
        .node("keystrokes", "use-arrows").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TEXT_SHADOW = SimpleOption.<Boolean>builder()
        .node("keystrokes", "text-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BORDER = SimpleOption.<Boolean>builder()
        .node("keystrokes", "border").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> INNER_BORDER = SimpleOption.<Boolean>builder()
        .node("keystrokes", "inner-border").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> BORDER_THICKNESS = NumberOption.<Float>number()
        .node("keystrokes", "border-thickness").type(TypeToken.get(Float.class))
        .defaultValue(0.5F).min(0.5F).max(3.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> BOX_SIZE = NumberOption.<Float>number()
        .node("keystrokes", "box-size").type(TypeToken.get(Float.class))
        .defaultValue(18.0F).min(10.0F).max(32.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> BORDER_COLOR = NumberOption.<Integer>number()
        .node("keystrokes", "border-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> TEXT_COLOR = NumberOption.<Integer>number()
        .node("keystrokes", "text-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> TEXT_PRESSED_COLOR = NumberOption.<Integer>number()
        .node("keystrokes", "text-pressed-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFF000000).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> BACKGROUND_COLOR = NumberOption.<Integer>number()
        .node("keystrokes", "background-color").type(TypeToken.get(Integer.class))
        .defaultValue(0x6F000000).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> BACKGROUND_PRESSED_COLOR = NumberOption.<Integer>number()
        .node("keystrokes", "background-pressed-color").type(TypeToken.get(Integer.class))
        .defaultValue(0x6FFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Long> KEY_FADE_DELAY = NumberOption.<Long>number()
        .node("keystrokes", "key-fade-delay").type(TypeToken.get(Long.class))
        .defaultValue(75L).min(0L).max(500L)
        .build();

}
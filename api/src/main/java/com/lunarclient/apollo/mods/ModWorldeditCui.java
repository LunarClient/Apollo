package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * A client-side user interface for the WorldEdit plugin allowing you to see your selected region in-game in real time.
 * 
 * @since %release_version%
 */
public final class ModWorldeditCui {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("worldedit-cui", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> POSITION_ONE_COLOR = NumberOption.<Integer>number()
        .node("worldedit-cui", "position-one-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFF0000FF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> POSITION_TWO_COLOR = NumberOption.<Integer>number()
        .node("worldedit-cui", "position-two-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFF0000).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> OUTLINE_COLOR = NumberOption.<Integer>number()
        .node("worldedit-cui", "outline-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFF00FF00).min(0x80000000).max(0x7FFFFFFF)
        .build();

}
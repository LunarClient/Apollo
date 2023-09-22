package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * View the border of the current chunk
 * 
 * @since %release_version%
 */
public final class ModChunkBorders {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("chunk-borders", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> GRID_COLOR = NumberOption.<Integer>number()
        .node("chunk-borders", "grid-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFF00).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> INNER_CHUNK_CORNER_COLOR = NumberOption.<Integer>number()
        .node("chunk-borders", "inner-chunk-corner-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFF0000FF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> OUTER_CHUNK_CORNER_COLOR = NumberOption.<Integer>number()
        .node("chunk-borders", "outer-chunk-corner-color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFF0000).min(0x80000000).max(0x7FFFFFFF)
        .build();

}
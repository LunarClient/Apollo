package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * Gives potions the full box shiny enchantment effect.
 * 
 * @since %release_version%
 */
public final class ModShinyPots {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("shiny-pots", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> COLORED_POTIONS = SimpleOption.<Boolean>builder()
        .node("shiny-pots", "colored-potions").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> RENDER_GLINT_BEHIND_POTION = SimpleOption.<Boolean>builder()
        .node("shiny-pots", "render-glint-behind-potion").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> RENDER_ENTIRE_SLOT = SimpleOption.<Boolean>builder()
        .node("shiny-pots", "render-entire-slot").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

}
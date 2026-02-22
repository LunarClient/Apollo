/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2023 Moonsworth
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.lunarclient.apollo.mods.impl;

import com.lunarclient.apollo.option.NumberOption;
import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;
import java.awt.Color;

/**
 * Configure various texture overlays, tweaks, and modifiers.
 *
 * @since %release_version%
 */
public final class ModOverlayMod {

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("overlay-mod", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * When View Bobbing is enabled, only bob your hand (requires View Bobbing to be enabled).
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> MINIMAL_VIEW_BOBBING = SimpleOption.<Boolean>builder()
        .comment("When View Bobbing is enabled, only bob your hand (requires View Bobbing to be enabled).")
        .node("overlay-mod", "minimal-view-bobbing").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> FIRE_HEIGHT = NumberOption.<Float>number()
        .node("overlay-mod", "fire-height").type(TypeToken.get(Float.class))
        .min(0.0F).max(2.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> SHIELD_HEIGHT = NumberOption.<Float>number()
        .node("overlay-mod", "shield-height").type(TypeToken.get(Float.class))
        .min(0.0F).max(2.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> TOTEM_SCALE = NumberOption.<Float>number()
        .node("overlay-mod", "totem-scale").type(TypeToken.get(Float.class))
        .min(0.25F).max(1.5F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> HELD_ITEM_SCALE = NumberOption.<Float>number()
        .node("overlay-mod", "held-item-scale").type(TypeToken.get(Float.class))
        .min(0.25F).max(1.5F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> PUMPKIN_OVERLAY = NumberOption.<Float>number()
        .node("overlay-mod", "pumpkin-overlay").type(TypeToken.get(Float.class))
        .min(0.0F).max(1.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> SPYGLASS_OVERLAY = NumberOption.<Float>number()
        .node("overlay-mod", "spyglass-overlay").type(TypeToken.get(Float.class))
        .min(0.0F).max(1.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> FROST_OVERLAY = NumberOption.<Float>number()
        .node("overlay-mod", "frost-overlay").type(TypeToken.get(Float.class))
        .min(0.0F).max(1.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * Hides grass and double grass blocks.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HIDE_FOLIAGE = SimpleOption.<Boolean>builder()
        .comment("Hides grass and double grass blocks")
        .node("overlay-mod", "hide-foliage").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Render shadows for entities.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENTITY_SHADOW = SimpleOption.<Boolean>builder()
        .comment("Render shadows for entities")
        .node("overlay-mod", "entity-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Choose whether or not to hide arrows that are stuck in the ground.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> GROUND_ARROWS = SimpleOption.<Boolean>builder()
        .comment("Choose whether or not to hide arrows that are stuck in the ground")
        .node("overlay-mod", "ground-arrows").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Choose whether or not to hide arrows that are stuck in players.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> STUCK_ARROWS = SimpleOption.<Boolean>builder()
        .comment("Choose whether or not to hide arrows that are stuck in players")
        .node("overlay-mod", "stuck-arrows").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HIDE_SKULLS = SimpleOption.<Boolean>builder()
        .node("overlay-mod", "hide-skulls").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> CLEAR_GLASS = SimpleOption.<Boolean>builder()
        .node("overlay-mod", "clear-glass").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> CLEAR_GLASS_TRANSPARENCY = NumberOption.<Float>number()
        .node("overlay-mod", "clear-glass-transparency").type(TypeToken.get(Float.class))
        .min(0.0F).max(1.0F)
        .defaultValue(0.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> CLEAR_COLORED_GLASS = SimpleOption.<Boolean>builder()
        .node("overlay-mod", "clear-colored-glass").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> CLEAR_GLASS_OUTLINE = SimpleOption.<Boolean>builder()
        .node("overlay-mod", "clear-glass-outline").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Integer> CLEAR_GLASS_OUTLINE_THICKNESS = NumberOption.<Integer>number()
        .node("overlay-mod", "clear-glass-outline-thickness").type(TypeToken.get(Integer.class))
        .min(1).max(3)
        .defaultValue(1)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> CLEAR_GLASS_OUTLINE_TRANSPARENCY = NumberOption.<Float>number()
        .node("overlay-mod", "clear-glass-outline-transparency").type(TypeToken.get(Float.class))
        .min(0.0F).max(1.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ORE_OUTLINES = SimpleOption.<Boolean>builder()
        .node("overlay-mod", "ore-outlines").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Use a heuristic to calculate the ore outline color from the actual ore texture, to make it match better. Turning this option off will use predefined vanilla ore outline colors.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SMART_OUTLINE_COLORS = SimpleOption.<Boolean>builder()
        .comment("Use a heuristic to calculate the ore outline color from the actual ore texture, to make it match better. Turning this option off will use predefined vanilla ore outline colors.")
        .node("overlay-mod", "smart-outline-colors").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Apply some noise to the ore outlines to make them appear shiny.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHINY_ORE_OUTLINES = SimpleOption.<Boolean>builder()
        .comment("Apply some noise to the ore outlines to make them appear shiny")
        .node("overlay-mod", "shiny-ore-outlines").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Integer> ORE_OUTLINE_THICKNESS = NumberOption.<Integer>number()
        .node("overlay-mod", "ore-outline-thickness").type(TypeToken.get(Integer.class))
        .min(1).max(3)
        .defaultValue(1)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> DIAMOND_ORE_OUTLINE = SimpleOption.<Boolean>builder()
        .node("overlay-mod", "diamond-ore-outline").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> GOLD_ORE_OUTLINE = SimpleOption.<Boolean>builder()
        .node("overlay-mod", "gold-ore-outline").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> IRON_ORE_OUTLINE = SimpleOption.<Boolean>builder()
        .node("overlay-mod", "iron-ore-outline").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> LAPIS_ORE_OUTLINE = SimpleOption.<Boolean>builder()
        .node("overlay-mod", "lapis-ore-outline").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> REDSTONE_ORE_OUTLINE = SimpleOption.<Boolean>builder()
        .node("overlay-mod", "redstone-ore-outline").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> EMERALD_ORE_OUTLINE = SimpleOption.<Boolean>builder()
        .node("overlay-mod", "emerald-ore-outline").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> COAL_ORE_OUTLINE = SimpleOption.<Boolean>builder()
        .node("overlay-mod", "coal-ore-outline").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> NETHER_QUARTZ_ORE_OUTLINE = SimpleOption.<Boolean>builder()
        .node("overlay-mod", "nether-quartz-ore-outline").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BARRIER_OUTLINES = SimpleOption.<Boolean>builder()
        .node("overlay-mod", "barrier-outlines").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Integer> BARRIER_OUTLINE_THICKNESS = NumberOption.<Integer>number()
        .node("overlay-mod", "barrier-outline-thickness").type(TypeToken.get(Integer.class))
        .min(1).max(3)
        .defaultValue(1)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> BARRIER_OUTLINE_COLOR = SimpleOption.<Color>builder()
        .node("overlay-mod", "barrier-outline-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 0, 0, 191))
        .notifyClient()
        .build();

    /**
     * Make strings/tripwires more visible by changing their color.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> COLORED_STRING = SimpleOption.<Boolean>builder()
        .comment("Make strings/tripwires more visible by changing their color")
        .node("overlay-mod", "colored-string").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Fill in the transparent spots on the string texture.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BOLD_STRING = SimpleOption.<Boolean>builder()
        .comment("Fill in the transparent spots on the string texture")
        .node("overlay-mod", "bold-string").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> STRING_COLOR = SimpleOption.<Color>builder()
        .node("overlay-mod", "string-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 0, 0))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> OVERRIDE_XP_ORB_COLOR = SimpleOption.<Boolean>builder()
        .node("overlay-mod", "override-xp-orb-color").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> XP_ORB_COLOR = SimpleOption.<Color>builder()
        .node("overlay-mod", "xp-orb-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255, 128))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> CUSTOM_FISHING_LINE = SimpleOption.<Boolean>builder()
        .node("overlay-mod", "custom-fishing-line").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> FISHING_LINE_THICKNESS = NumberOption.<Float>number()
        .node("overlay-mod", "fishing-line-thickness").type(TypeToken.get(Float.class))
        .min(0.5F).max(3.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> FISHING_LINE_COLOR = SimpleOption.<Color>builder()
        .node("overlay-mod", "fishing-line-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(0, 0, 0))
        .notifyClient()
        .build();

    /**
     * Improve visibility when riding a horse.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HEADLESS_HORSES = SimpleOption.<Boolean>builder()
        .comment("Improve visibility when riding a horse")
        .node("overlay-mod", "headless-horses").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> DISABLE_DEATH_ANIMATION = SimpleOption.<Boolean>builder()
        .node("overlay-mod", "disable-death-animation").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> DISABLE_DAMAGE_OVERLAY = SimpleOption.<Boolean>builder()
        .node("overlay-mod", "disable-damage-overlay").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Disable the rendering of the fire overlay when a mob or player is on fire.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> DISABLE_FIRE_OVERLAY = SimpleOption.<Boolean>builder()
        .comment("Disable the rendering of the fire overlay when a mob or player is on fire.")
        .node("overlay-mod", "disable-fire-overlay").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HIDE_HELMET = SimpleOption.<Boolean>builder()
        .node("overlay-mod", "hide-helmet").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Does not work on elytra.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HIDE_CHEST = SimpleOption.<Boolean>builder()
        .comment("Does not work on elytra.")
        .node("overlay-mod", "hide-chest").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HIDE_LEGGINGS = SimpleOption.<Boolean>builder()
        .node("overlay-mod", "hide-leggings").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HIDE_BOOTS = SimpleOption.<Boolean>builder()
        .node("overlay-mod", "hide-boots").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Disabling this allows you to hide armor slots for all entities.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SELF_ONLY = SimpleOption.<Boolean>builder()
        .comment("Disabling this allows you to hide armor slots for all entities.")
        .node("overlay-mod", "self-only").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    private ModOverlayMod() {
    }

}

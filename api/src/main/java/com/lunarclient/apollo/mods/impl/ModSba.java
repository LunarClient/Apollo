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
 * A mod created for Hypixel Skyblock with over 50 features that will enhance your skyblock experience (version: DEBUG).
 *
 * @since 1.0.0
 */
public final class ModSba {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("sba", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_HEALTH_BAR = SimpleOption.<Boolean>builder()
        .node("sba", "hide-health-bar").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_FOOD_BAR = SimpleOption.<Boolean>builder()
        .node("sba", "hide-food-bar").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_PET_HEALTH_BAR = SimpleOption.<Boolean>builder()
        .node("sba", "hide-pet-health-bar").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SKELETON_HELMET_BARS = SimpleOption.<Boolean>builder()
        .node("sba", "skeleton-helmet-bars").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> COOLDOWN_PREDICTION = SimpleOption.<Boolean>builder()
        .node("sba", "cooldown-prediction").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> LEG_MONKEY_LVL_100 = SimpleOption.<Boolean>builder()
        .node("sba", "leg-monkey-lvl100").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> COMPACT_TAB_LIST = SimpleOption.<Boolean>builder()
        .node("sba", "compact-tab-list").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_ITEM_COOLDOWNS = SimpleOption.<Boolean>builder()
        .node("sba", "show-item-cooldowns").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> USE_LUNAR_COOLDOWNS = SimpleOption.<Boolean>builder()
        .node("sba", "use-lunar-cooldowns").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_TRUE_DEFENSE = SimpleOption.<Boolean>builder()
        .node("sba", "hide-true-defense").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SPECIAL_ZEALOT_ALERT = SimpleOption.<Boolean>builder()
        .node("sba", "special-zealot-alert").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ZEALOT_COUNTER_EXPLOSIVE_BOW_SUPPORT = SimpleOption.<Boolean>builder()
        .node("sba", "zealot-counter-explosive-bow-support").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ZEALOT_COLOR_BOOLEAN = SimpleOption.<Boolean>builder()
        .node("sba", "zealot-color-boolean").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> ZEALOT_COLOR = SimpleOption.<Color>builder()
        .node("sba", "zealot-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(32, 32, 32))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> LOCK_SLOTS = SimpleOption.<Boolean>builder()
        .node("sba", "lock-slots").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> STOP_DROPPING_SELLING_RARE_ITEMS = SimpleOption.<Boolean>builder()
        .node("sba", "stop-dropping-selling-rare-items").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> AVOID_BLINKING_NIGHT_VISION = SimpleOption.<Boolean>builder()
        .node("sba", "avoid-blinking-night-vision").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> MAKE_BACKPACK_INVENTORIES_COLORED = SimpleOption.<Boolean>builder()
        .node("sba", "make-backpack-inventories-colored").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> FISHING_SOUND_INDICATOR = SimpleOption.<Boolean>builder()
        .node("sba", "fishing-sound-indicator").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> CHANGE_BAR_COLOR_FOR_POTIONS = SimpleOption.<Boolean>builder()
        .node("sba", "change-bar-color-for-potions").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> COLOR_ENDER_CHEST_IN_END = SimpleOption.<Boolean>builder()
        .node("sba", "color-ender-chest-in-end").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> END_ENDER_CHEST_COLOR = SimpleOption.<Color>builder()
        .node("sba", "end-ender-chest-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(32, 32, 32))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> OUTBID_ALERT_SOUND = SimpleOption.<Boolean>builder()
        .node("sba", "outbid-alert-sound").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> OUTBID_ALERT_SOUND_IN_OTHER_GAMES = SimpleOption.<Boolean>builder()
        .node("sba", "outbid-alert-sound-in-other-games").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_OTHER_PLAYER_PRESENTS = SimpleOption.<Boolean>builder()
        .node("sba", "hide-other-player-presents").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Requires Fast Render to be OFF.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENTITY_GLOW = SimpleOption.<Boolean>builder()
        .comment("Requires Fast Render to be OFF.")
        .node("sba", "entity-glow").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DROPPED_ITEMS_GLOW = SimpleOption.<Boolean>builder()
        .node("sba", "dropped-items-glow").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DROPPED_ITEMS_GLOW_ON_ISLAND = SimpleOption.<Boolean>builder()
        .node("sba", "dropped-items-glow-on-island").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_SVEN_PUP_NAMETAGS = SimpleOption.<Boolean>builder()
        .node("sba", "hide-sven-pup-nametags").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_PLAYERS_IN_LOBBY = SimpleOption.<Boolean>builder()
        .node("sba", "hide-players-in-lobby").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_SPAWN_POINT_PLAYERS = SimpleOption.<Boolean>builder()
        .node("sba", "hide-spawn-point-players").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_BONES = SimpleOption.<Boolean>builder()
        .node("sba", "hide-bones").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BIGGER_FISHING_WAKE = SimpleOption.<Boolean>builder()
        .node("sba", "bigger-fishing-wake").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> STOP_RAT_SOUNDS = SimpleOption.<Boolean>builder()
        .node("sba", "stop-rat-sounds").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> STOP_ONLY_RAT_SQUEAK = SimpleOption.<Boolean>builder()
        .node("sba", "stop-only-rat-squeak").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> FISHING_PARTICLE_OVERLAY = SimpleOption.<Boolean>builder()
        .node("sba", "fishing-particle-overlay").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Color> FISHING_PARTICLE_OVERLAY_COLOR = SimpleOption.<Color>builder()
        .node("sba", "fishing-particle-overlay-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DUNGEON_TEAMMATES_GLOW = SimpleOption.<Boolean>builder()
        .node("sba", "dungeon-teammates-glow").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> CRITICAL_HEALTH_DUNGEON_TEAMMATE_WARNING = SimpleOption.<Boolean>builder()
        .node("sba", "critical-health-dungeon-teammate-warning").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_DUNGEON_TEAMMATE_NAME_OVERLAY = SimpleOption.<Boolean>builder()
        .node("sba", "show-dungeon-teammate-name-overlay").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_HEALING_CIRCLE_WALL = SimpleOption.<Boolean>builder()
        .node("sba", "show-healing-circle-wall").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> HEALING_CIRCLE_OPACITY = NumberOption.<Float>number()
        .node("sba", "healing-circle-opacity").type(TypeToken.get(Float.class))
        .min(0.1F).max(1.0F)
        .defaultValue(0.4F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> STOP_BONZO_STAFF_SOUNDS = SimpleOption.<Boolean>builder()
        .node("sba", "stop-bonzo-staff-sounds").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_SALVAGE_ESSENCES_COUNTER = SimpleOption.<Boolean>builder()
        .node("sba", "show-salvage-essences-counter").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> RESET_SALVAGED_ESSENCES_AFTER_LEAVING_MENU = SimpleOption.<Boolean>builder()
        .node("sba", "reset-salvaged-essences-after-leaving-menu").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> CHANGE_DUNGEON_MAP_ZOOM_WITH_KEYBOARD = SimpleOption.<Boolean>builder()
        .node("sba", "change-dungeon-map-zoom-with-keyboard").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_ENCHANT_DESCRIPTION = SimpleOption.<Boolean>builder()
        .node("sba", "hide-enchant-description").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLE_MESSAGE_WHEN_BREAKING_PARK = SimpleOption.<Boolean>builder()
        .node("sba", "enable-message-when-breaking-park").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_BACKPACK_PREVIEW = SimpleOption.<Boolean>builder()
        .node("sba", "show-backpack-preview").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_BACKPACK_HOLDING_SHIFT = SimpleOption.<Boolean>builder()
        .node("sba", "show-backpack-holding-shift").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BACKPACK_PREVIEW_AH = SimpleOption.<Boolean>builder()
        .node("sba", "backpack-preview-ah").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> CAKE_BAG_PREVIEW = SimpleOption.<Boolean>builder()
        .node("sba", "cake-bag-preview").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BACKPACK_OPENING_SOUND = SimpleOption.<Boolean>builder()
        .node("sba", "backpack-opening-sound").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> FANCY_WARP_MENU = SimpleOption.<Boolean>builder()
        .node("sba", "fancy-warp-menu").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> WARP_ADVANCED_MODE = SimpleOption.<Boolean>builder()
        .node("sba", "warp-advanced-mode").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DISABLE_ENDERMAN_TELEPORTION_EFFECT = SimpleOption.<Boolean>builder()
        .node("sba", "disable-enderman-teleportion-effect").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIDE_GREY_ENCHANTS = SimpleOption.<Boolean>builder()
        .node("sba", "hide-grey-enchants").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_ITEM_ANVIL_USES = SimpleOption.<Boolean>builder()
        .node("sba", "show-item-anvil-uses").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> REPLACE_ROMAN_NUMERALS_WITH_NUMBERS = SimpleOption.<Boolean>builder()
        .node("sba", "replace-roman-numerals-with-numbers").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DONT_REPLACE_ROMAN_NUMERALS_IN_ITEM_NAME = SimpleOption.<Boolean>builder()
        .node("sba", "dont-replace-roman-numerals-in-item-name").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_ENCHANTMENTS_REFORGES = SimpleOption.<Boolean>builder()
        .node("sba", "show-enchantments-reforges").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_BROKEN_FRAGMENTS = SimpleOption.<Boolean>builder()
        .node("sba", "show-broken-fragments").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_BASE_STAT_BOOST_PERCENTAGE = SimpleOption.<Boolean>builder()
        .node("sba", "show-base-stat-boost-percentage").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> BASE_STAT_BOOST_COLOR_BY_RARITY = SimpleOption.<Boolean>builder()
        .node("sba", "base-stat-boost-color-by-rarity").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_ITEM_DUNGEON_FLOOR = SimpleOption.<Boolean>builder()
        .node("sba", "show-item-dungeon-floor").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_RARITY_UPGRADED = SimpleOption.<Boolean>builder()
        .node("sba", "show-rarity-upgraded").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_STACKING_ENCHANT_PROGRESS = SimpleOption.<Boolean>builder()
        .node("sba", "show-stacking-enchant-progress").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Shows the kills for swords that give stats reflecting the enemies killed with said sword (ex. Fels Sword).
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_SWORD_KILLS = SimpleOption.<Boolean>builder()
        .comment("Shows the kills for swords that give stats reflecting the enemies killed with said sword (ex. Fels Sword)")
        .node("sba", "show-sword-kills").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENCHANTMENT_LORE_PARSING = SimpleOption.<Boolean>builder()
        .node("sba", "enchantment-lore-parsing").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> HIGHLIGHT_SPECIAL_ENCHANTMENTS = SimpleOption.<Boolean>builder()
        .node("sba", "highlight-special-enchantments").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_EXPERIMENTATION_TABLE_TOOLTIPS = SimpleOption.<Boolean>builder()
        .node("sba", "show-experimentation-table-tooltips").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DEVELOPER_MODE = SimpleOption.<Boolean>builder()
        .node("sba", "developer-mode").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_SKY_BLOCK_ITEM_ID = SimpleOption.<Boolean>builder()
        .node("sba", "show-sky-block-item-i-d").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DONT_RESET_CURSOR_INVENTORY = SimpleOption.<Boolean>builder()
        .node("sba", "dont-reset-cursor-inventory").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_PERSONAL_COMPACTOR_PREVIEW = SimpleOption.<Boolean>builder()
        .node("sba", "show-personal-compactor-preview").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> REFORGE_FILTER = SimpleOption.<Boolean>builder()
        .node("sba", "reforge-filter").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_ENDER_CHEST_PREVIEW = SimpleOption.<Boolean>builder()
        .node("sba", "show-ender-chest-preview").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DISABLE_EMPTY_GLASS_PANES = SimpleOption.<Boolean>builder()
        .node("sba", "disable-empty-glass-panes").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DISABLE_TELEPORT_PAD_MESSAGES = SimpleOption.<Boolean>builder()
        .node("sba", "disable-teleport-pad-messages").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DISABLE_MAGICAL_SOUP_MESSAGES = SimpleOption.<Boolean>builder()
        .node("sba", "disable-magical-soup-messages").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DISABLE_MORT_MESSAGES = SimpleOption.<Boolean>builder()
        .node("sba", "disable-mort-messages").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DISABLE_BOSS_MESSAGES = SimpleOption.<Boolean>builder()
        .node("sba", "disable-boss-messages").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DISABLE_SPIRIT_SCEPTRE_MESSAGES = SimpleOption.<Boolean>builder()
        .node("sba", "disable-spirit-sceptre-messages").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> PLAYER_SYMBOLS_IN_CHAT = SimpleOption.<Boolean>builder()
        .node("sba", "player-symbols-in-chat").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_PROFILE_TYPE = SimpleOption.<Boolean>builder()
        .node("sba", "show-profile-type").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_NETHER_FACTION = SimpleOption.<Boolean>builder()
        .node("sba", "show-nether-faction").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> TREVOR_THE_TRAPPER_FEATURES = SimpleOption.<Boolean>builder()
        .node("sba", "trevor-the-trapper-features").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> TREVOR_HIGHLIGHT_TRACKED_ENTITY = SimpleOption.<Boolean>builder()
        .node("sba", "trevor-highlight-tracked-entity").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> TREVOR_SHOW_QUEST_COOLDOWN = SimpleOption.<Boolean>builder()
        .node("sba", "trevor-show-quest-cooldown").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    private ModSba() {
    }

}

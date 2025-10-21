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
 * Various features to help in Hypixel Skyblock.
 *
 * @since 1.0.9
 */
public final class ModSkyblock {

    /**
     * No documentation available.
     *
     * @since 1.0.9
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("skyblock", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.9
     */
    public static final NumberOption<Float> SKYBLOCK_ALERT_SCALE = NumberOption.<Float>number()
        .node("skyblock", "skyblock-alert-scale").type(TypeToken.get(Float.class))
        .min(0.2F).max(2.5F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * Automatically copies rare drops and pet drops to the clipboard.
     *
     * @since 1.0.9
     */
    public static final SimpleOption<Boolean> AUTO_COPY_RARE_DROPS = SimpleOption.<Boolean>builder()
        .comment("Automatically copies rare drops and pet drops to the clipboard.")
        .node("skyblock", "auto-copy-rare-drops").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Replaces left clicks in menus with a middle click. Hold control to override.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> SKY_BLOCK_MIDDLE_CLICK_ITEMS = SimpleOption.<Boolean>builder()
        .comment("Replaces left clicks in menus with a middle click. Hold control to override.")
        .node("skyblock", "sky-block-middle-click-items").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Shows the creation date of the item in the lore.
     *
     * @since 1.1.7
     */
    public static final SimpleOption<Boolean> SKY_BLOCK_CREATION_DATE = SimpleOption.<Boolean>builder()
        .comment("Shows the creation date of the item in the lore.")
        .node("skyblock", "sky-block-creation-date").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Shows the item's SkyBlock ID in the lore.
     *
     * @since 1.1.7
     */
    public static final SimpleOption<Boolean> SKY_BLOCK_ITEM_ID = SimpleOption.<Boolean>builder()
        .comment("Shows the item's SkyBlock ID in the lore.")
        .node("skyblock", "sky-block-item-id").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.1
     */
    public static final SimpleOption<Boolean> SKYBLOCK_BOW_REEQUIP = SimpleOption.<Boolean>builder()
        .node("skyblock", "skyblock-bow-reequip").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.9
     */
    public static final SimpleOption<Boolean> SKYBLOCK_HIDE_HUNGER = SimpleOption.<Boolean>builder()
        .node("skyblock", "skyblock-hide-hunger").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.9
     */
    public static final SimpleOption<Boolean> SKYBLOCK_HIDE_ARMOR = SimpleOption.<Boolean>builder()
        .node("skyblock", "skyblock-hide-armor").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.3
     */
    public static final SimpleOption<Boolean> SKYBLOCK_HIDE_HEARTS = SimpleOption.<Boolean>builder()
        .node("skyblock", "skyblock-hide-hearts").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.3
     */
    public static final SimpleOption<Boolean> SKYBLOCK_HIDE_ABSORB = SimpleOption.<Boolean>builder()
        .node("skyblock", "skyblock-hide-absorb").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Hides the hud previews in the edit hud layout menu if you're not currently on SkyBlock.
     *
     * @since 1.1.1
     */
    public static final SimpleOption<Boolean> ONLY_MOVERS_ON_SKYBLOCK = SimpleOption.<Boolean>builder()
        .comment("Hides the hud previews in the edit hud layout menu if you're not currently on SkyBlock.")
        .node("skyblock", "only-movers-on-skyblock").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.1
     */
    public static final SimpleOption<Color> SKYBLOCK_SUBJECT_COLOR = SimpleOption.<Color>builder()
        .node("skyblock", "skyblock-subject-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 170, 0))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.1
     */
    public static final SimpleOption<Color> SKYBLOCK_SEPARATOR_COLOR = SimpleOption.<Color>builder()
        .node("skyblock", "skyblock-separator-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.1
     */
    public static final SimpleOption<Color> SKYBLOCK_VALUE_COLOR = SimpleOption.<Color>builder()
        .node("skyblock", "skyblock-value-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(170, 170, 170))
        .notifyClient()
        .build();

    /**
     * Provides QOL for the Diana mayor that helps the user quickly locate burrows.
     *
     * @since 1.1.5
     */
    public static final SimpleOption<Boolean> GRIFFIN_BURROW_ESTIMATES = SimpleOption.<Boolean>builder()
        .comment("Provides QOL for the Diana mayor that helps the user quickly locate burrows.")
        .node("skyblock", "griffin-burrow-estimates").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Changes crop hitboxes with their 1.12+ variant. Only enabled on Hypixel or singleplayer.
     *
     * @since 1.0.9
     */
    public static final SimpleOption<Boolean> TALLER_CROPS = SimpleOption.<Boolean>builder()
        .comment("Changes crop hitboxes with their 1.12+ variant. Only enabled on Hypixel or singleplayer.")
        .node("skyblock", "taller-crops").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.8
     */
    public static final SimpleOption<Boolean> HIGHLIGHT_GLOWING_MUSHROOMS = SimpleOption.<Boolean>builder()
        .node("skyblock", "highlight-glowing-mushrooms").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.6
     */
    public static final SimpleOption<Boolean> SKY_BLOCK_WISHING_COMPASS = SimpleOption.<Boolean>builder()
        .node("skyblock", "sky-block-wishing-compass").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.6
     */
    public static final SimpleOption<Boolean> SKY_BLOCK_METAL_DETECTOR = SimpleOption.<Boolean>builder()
        .node("skyblock", "sky-block-metal-detector").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.6
     */
    public static final SimpleOption<Boolean> SKY_BLOCK_FINISHED_COMMISSIONS = SimpleOption.<Boolean>builder()
        .node("skyblock", "sky-block-finished-commissions").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Fixes your fishing bobber sinking in lava.
     *
     * @since 1.0.9
     */
    public static final SimpleOption<Boolean> FIX_LAVA_BOBBER = SimpleOption.<Boolean>builder()
        .comment("Fixes your fishing bobber sinking in lava.")
        .node("skyblock", "fix-lava-bobber").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Changes the texture of lava to water in the Crimson Isles and Kuudra.
     *
     * @since 1.0.9
     */
    public static final SimpleOption<Boolean> REPLACE_LAVA_WITH_WATER_CRIMSON = SimpleOption.<Boolean>builder()
        .comment("Changes the texture of lava to water in the Crimson Isles and Kuudra.")
        .node("skyblock", "replace-lava-with-water-crimson").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Changes the texture of lava to water everywhere in SkyBlock.
     *
     * @since 1.0.9
     */
    public static final SimpleOption<Boolean> REPLACE_LAVA_WITH_WATER_EVERYWHERE = SimpleOption.<Boolean>builder()
        .comment("Changes the texture of lava to water everywhere in SkyBlock.")
        .node("skyblock", "replace-lava-with-water-everywhere").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Hides players and fishing rods near your bobber while you are fishing.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> SKYBLOCK_FISHING_HIDE_PLAYERS = SimpleOption.<Boolean>builder()
        .comment("Hides players and fishing rods near your bobber while you are fishing.")
        .node("skyblock", "skyblock-fishing-hide-players").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Shows which hotspot the Hotspot Radar item is pointing to.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> SKYBLOCK_FISHING_HOTSPOT_LOCATOR = SimpleOption.<Boolean>builder()
        .comment("Shows which hotspot the Hotspot Radar item is pointing to.")
        .node("skyblock", "skyblock-fishing-hotspot-locator").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Provides a chat message on boss kill that tells you how long it took to kill!.
     *
     * @since 1.0.9
     */
    public static final SimpleOption<Boolean> SLAYER_BOSS_TIMER = SimpleOption.<Boolean>builder()
        .comment("Provides a chat message on boss kill that tells you how long it took to kill!")
        .node("skyblock", "slayer-boss-timer").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.9
     */
    public static final SimpleOption<Boolean> SLAYER_MINI_BOSS_ALERT = SimpleOption.<Boolean>builder()
        .node("skyblock", "slayer-mini-boss-alert").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Used in Stillgore of the Rift, this feature tells you where to go to refresh the duration of inactive Effigies.
     *
     * @since 1.0.9
     */
    public static final SimpleOption<Boolean> INACTIVE_EFFIGY_WAYPOINTS = SimpleOption.<Boolean>builder()
        .comment("Used in Stillgore of the Rift, this feature tells you where to go to refresh the duration of inactive Effigies.")
        .node("skyblock", "inactive-effigy-waypoints").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Highlights a boss when it can be 1 tapped using steak stake.
     *
     * @since 1.0.9
     */
    public static final SimpleOption<Boolean> VAMPIRE_STEAK_DISPLAY = SimpleOption.<Boolean>builder()
        .comment("Highlights a boss when it can be 1 tapped using steak stake.")
        .node("skyblock", "vampire-steak-display").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Highlights blood ichors present during T5 Bloodfiends.
     *
     * @since 1.0.9
     */
    public static final SimpleOption<Boolean> VAMPIRE_ICHOR_DISPLAY = SimpleOption.<Boolean>builder()
        .comment("Highlights blood ichors present during T5 Bloodfiends")
        .node("skyblock", "vampire-ichor-display").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Shows the positions where you can trap the gray blazes.
     *
     * @since 1.0.9
     */
    public static final SimpleOption<Boolean> GRAVITY_WELL_WAYPOINTS = SimpleOption.<Boolean>builder()
        .comment("Shows the positions where you can trap the gray blazes.")
        .node("skyblock", "gravity-well-waypoints").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.5
     */
    public static final SimpleOption<Boolean> SHOW_KUUDRA_HEALTH = SimpleOption.<Boolean>builder()
        .node("skyblock", "show-kuudra-health").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.8
     */
    public static final SimpleOption<Boolean> HIGHLIGHT_END_NODES = SimpleOption.<Boolean>builder()
        .node("skyblock", "highlight-end-nodes").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Makes Giant HP more visible by showing their HP at their feet.
     *
     * @since 1.1.5
     */
    public static final SimpleOption<Boolean> SHOW_GIANT_HPAT_FEET = SimpleOption.<Boolean>builder()
        .comment("Makes Giant HP more visible by showing their HP at their feet.")
        .node("skyblock", "show-giant-h-p-at-feet").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Makes Professor's Guardians HP more visible.
     *
     * @since 1.2.1
     */
    public static final SimpleOption<Boolean> SHOW_PROFESSOR_GUARDIAN_HP = SimpleOption.<Boolean>builder()
        .comment("Makes Professor's Guardians HP more visible.")
        .node("skyblock", "show-professor-guardian-h-p").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.1
     */
    public static final SimpleOption<Boolean> SKYBLOCK_DUNGEON_QUALITY = SimpleOption.<Boolean>builder()
        .node("skyblock", "skyblock-dungeon-quality").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> SKYBLOCK_SECRETS_COLLECTED = SimpleOption.<Boolean>builder()
        .node("skyblock", "skyblock-secrets-collected").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.1
     */
    public static final SimpleOption<Boolean> SKYBLOCK_HIDE_NON_STARRED = SimpleOption.<Boolean>builder()
        .node("skyblock", "skyblock-hide-non-starred").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Shows how long it takes to do each terminal stage in chat (and also Simon Says)!.
     *
     * @since 1.2.1
     */
    public static final SimpleOption<Boolean> SKYBLOCK_TERMINAL_SPLIT_TIMERS = SimpleOption.<Boolean>builder()
        .comment("Shows how long it takes to do each terminal stage in chat (and also Simon Says)!")
        .node("skyblock", "skyblock-terminal-split-timers").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Provides a general line thickness option that applies to most features with box/line rendering.
     *
     * @since 1.1.7
     */
    public static final NumberOption<Float> SKYBLOCK_LINE_THICKNESS = NumberOption.<Float>number()
        .comment("Provides a general line thickness option that applies to most features with box/line rendering.")
        .node("skyblock", "skyblock-line-thickness").type(TypeToken.get(Float.class))
        .min(1.0F).max(10.0F)
        .defaultValue(4.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.9
     */
    public static final SimpleOption<Boolean> HIDE_MIDAS_STAFF = SimpleOption.<Boolean>builder()
        .node("skyblock", "hide-midas-staff").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.9
     */
    public static final SimpleOption<Boolean> HIDE_FALLING_BLOCKS = SimpleOption.<Boolean>builder()
        .node("skyblock", "hide-falling-blocks").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> SKYBLOCK_USE_TICK_TIMERS = SimpleOption.<Boolean>builder()
        .node("skyblock", "skyblock-use-tick-timers").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Routes menu clicks to /sbmenu command in order to prevent sticky item.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> SB_MENU_CLICK_TO_COMMAND = SimpleOption.<Boolean>builder()
        .comment("Routes menu clicks to /sbmenu command in order to prevent sticky item.")
        .node("skyblock", "sb-menu-click-to-command").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.7
     */
    @Deprecated
    public static final SimpleOption<Boolean> DUNGEON_SECRETS_COLLECTED = SimpleOption.<Boolean>builder()
        .node("skyblock", "dungeon-secrets-collected").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    private ModSkyblock() {
    }

}

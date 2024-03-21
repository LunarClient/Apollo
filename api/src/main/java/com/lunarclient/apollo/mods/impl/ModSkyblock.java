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
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.9
     */
    public static final SimpleOption<Boolean> SKYBLOCK_HIDE_HUNGER = SimpleOption.<Boolean>builder()
        .node("skyblock", "skyblock-hide-hunger").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.9
     */
    public static final SimpleOption<Boolean> SKYBLOCK_HIDE_ARMOR = SimpleOption.<Boolean>builder()
        .node("skyblock", "skyblock-hide-armor").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.9
     */
    public static final SimpleOption<Boolean> HIDE_MIDAS_STAFF = SimpleOption.<Boolean>builder()
        .node("skyblock", "hide-midas-staff").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.9
     */
    public static final SimpleOption<Boolean> HIDE_FALLING_BLOCKS = SimpleOption.<Boolean>builder()
        .node("skyblock", "hide-falling-blocks").type(TypeToken.get(Boolean.class))
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
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.9
     */
    public static final SimpleOption<Boolean> SLAYER_MINI_BOSS_ALERT = SimpleOption.<Boolean>builder()
        .node("skyblock", "slayer-mini-boss-alert").type(TypeToken.get(Boolean.class))
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
        .notifyClient()
        .build();

    private ModSkyblock() {
    }

}

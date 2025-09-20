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
 * Changes the size of mobs.
 *
 * @since %release_version%
 */
public final class ModMobSize {

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("mob-size", "enabled").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * Only change player sizes when playing Hypixel SkyBlock.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> PLAYER_SIZE_SKYBLOCK_ONLY = SimpleOption.<Boolean>builder()
        .comment("Only change player sizes when playing Hypixel SkyBlock")
        .node("mob-size", "player-size-skyblock-only").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * The size of your player.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> PLAYER_SIZE = NumberOption.<Float>number()
        .comment("The size of your player")
        .node("mob-size", "player-size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * The size of other players.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> OTHER_PLAYER_SIZE = NumberOption.<Float>number()
        .comment("The size of other players")
        .node("mob-size", "other-player-size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * If the size of NPCs should be changed.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> CHANGE_NPC_SIZE = SimpleOption.<Boolean>builder()
        .comment("If the size of NPCs should be changed")
        .node("mob-size", "change-npc-size").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> BAT_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "bat -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> BLAZE_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "blaze -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> CAVE_ SPIDER_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "cave -spider -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> CHICKEN_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "chicken -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> COW_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "cow -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> CREEPER_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "creeper -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> ENDER_ DRAGON_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "ender -dragon -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> ENDERMAN_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "enderman -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> GHAST_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "ghast -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> GIANT_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "giant -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> HORSE_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "horse -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> IRON_ GOLEM_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "iron -golem -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> MAGMA_ CUBE_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "magma -cube -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> MONSTER_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "monster -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> MOOSHROOM_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "mooshroom -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> OCELOT_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "ocelot -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> PIG_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "pig -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> SHEEP_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "sheep -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> SILVERFISH_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "silverfish -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> SKELETON_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "skeleton -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> SLIME_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "slime -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> SNOW_ MAN_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "snow -man -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> SPIDER_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "spider -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> SQUID_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "squid -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> VILLAGER_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "villager -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> WITCH_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "witch -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> WITHER_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "wither -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> WOLF_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "wolf -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> ZOMBIE_ PIGMAN_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "zombie -pigman -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> ZOMBIE_ SIZE = NumberOption.<Float>number()
        .node("mob-size", "zombie -size").type(TypeToken.get(Float.class))
        .min(0.5F).max(1.0F)
        .notifyClient()
        .build();

    private ModMobSize() {
    }

}

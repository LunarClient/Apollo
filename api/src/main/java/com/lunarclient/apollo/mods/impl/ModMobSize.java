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
 * @since 1.2.0
 */
public final class ModMobSize {

    /**
     * No documentation available.
     *
     * @since 1.2.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("mob-size", "enabled").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * Only change player sizes when playing Hypixel SkyBlock.
     *
     * @since 1.2.0
     */
    public static final SimpleOption<Boolean> PLAYER_SIZE_SKYBLOCK_ONLY = SimpleOption.<Boolean>builder()
        .comment("Only change player sizes when playing Hypixel SkyBlock")
        .node("mob-size", "player-size-skyblock-only").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * The size of your player.
     *
     * @since 1.2.0
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
     * @since 1.2.0
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
     * @since 1.2.0
     */
    public static final SimpleOption<Boolean> CHANGE_NPC_SIZE = SimpleOption.<Boolean>builder()
        .comment("If the size of NPCs should be changed")
        .node("mob-size", "change-npc-size").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    private ModMobSize() {
    }

}

/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2026 Moonsworth
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
package com.lunarclient.apollo.module.cosmetic;

import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.module.cosmetic.options.BodyOptions;
import com.lunarclient.apollo.module.cosmetic.options.CloakOptions;
import com.lunarclient.apollo.module.cosmetic.options.CosmeticOptions;
import com.lunarclient.apollo.module.cosmetic.options.HatOptions;
import com.lunarclient.apollo.module.cosmetic.options.PetOptions;
import com.lunarclient.apollo.recipients.Recipients;
import java.util.List;
import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

/**
 * Represents the cosmetic module.
 *
 * @since 1.2.6
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "cosmetic", name = "Cosmetic")
public abstract class CosmeticModule extends ApolloModule {

    /**
     * Equips the provided cosmetics on an NPC for the given {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param npcUuid    the {@link UUID} of the NPC to equip the cosmetics on
     * @param cosmetics  the cosmetics to equip, including optional {@link CosmeticOptions} per entry
     *                   ({@link HatOptions}, {@link CloakOptions}, {@link PetOptions}, or {@link BodyOptions})
     * @since 1.2.6
     */
    public abstract void equipNpcCosmetics(Recipients recipients, UUID npcUuid, List<Cosmetic> cosmetics);

    /**
     * Unequips the provided cosmetics from an NPC for the given {@link Recipients}.
     *
     * @param recipients  the recipients that are receiving the packet
     * @param npcUuid     the {@link UUID} of the NPC to unequip the cosmetics from
     * @param cosmeticIds the list of cosmetic ids to unequip
     * @since 1.2.6
     */
    public abstract void unequipNpcCosmetics(Recipients recipients, UUID npcUuid, List<Integer> cosmeticIds);

    /**
     * Resets all cosmetics on an NPC for the given {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param npcUuid    the {@link UUID} of the NPC to reset the cosmetics on
     * @since 1.2.6
     */
    public abstract void resetNpcCosmetics(Recipients recipients, UUID npcUuid);

    /**
     * Displays a spray for the given {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param spray      the spray to display (durations under one second are raised to one second before sending)
     * @since 1.2.6
     */
    public abstract void displaySpray(Recipients recipients, Spray spray);

    /**
     * Removes every instance of a spray id for the given {@link Recipients}.
     *
     * <p>The spray id must be greater than 0.</p>
     *
     * @param recipients the recipients that are receiving the packet
     * @param sprayId    the spray cosmetic id
     * @since 1.2.6
     */
    public abstract void removeSpray(Recipients recipients, @Range(from = 1, to = Integer.MAX_VALUE) int sprayId);

    /**
     * Removes every instance of a spray id at a specific block for the given {@link Recipients}.
     *
     * <p>The spray id must be greater than 0. If {@code location} is {@code null}, every
     * instance of the spray id is removed regardless of position.</p>
     *
     * @param recipients the recipients that are receiving the packet
     * @param sprayId    the spray cosmetic id
     * @param location   the block location of the spray to remove, or {@code null} to remove all
     * @since 1.2.6
     */
    public abstract void removeSpray(Recipients recipients, @Range(from = 1, to = Integer.MAX_VALUE) int sprayId, @Nullable ApolloBlockLocation location);

    /**
     * Resets all server sprays for the given {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @since 1.2.6
     */
    public abstract void resetSprays(Recipients recipients);

}

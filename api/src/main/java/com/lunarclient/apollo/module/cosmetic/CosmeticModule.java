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

import com.lunarclient.apollo.common.ApolloEntity;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.recipients.Recipients;
import java.util.List;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the cosmetic module, responsible for applying cosmetics onto player NPCs.
 *
 * @since 1.2.6
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "cosmetic", name = "Cosmetic")
public abstract class CosmeticModule extends ApolloModule {

    /**
     * Equips the provided cosmetics on an NPC for the given {@link Recipients}.
     *
     * @param recipients  the recipients that are receiving the packet
     * @param entity      the {@link ApolloEntity} of the NPC to equip the cosmetics on
     * @param cosmeticIds the list of cosmetic ids to equip
     * @since 1.2.6
     */
    public abstract void equipNpcCosmetics(Recipients recipients, ApolloEntity entity, List<Integer> cosmeticIds);

    /**
     * Unequips the provided cosmetics from an NPC for the given {@link Recipients}.
     *
     * @param recipients  the recipients that are receiving the packet
     * @param entity      the {@link ApolloEntity} of the NPC to unequip the cosmetics from
     * @param cosmeticIds the list of cosmetic ids to unequip
     * @since 1.2.6
     */
    public abstract void unequipNpcCosmetics(Recipients recipients, ApolloEntity entity, List<Integer> cosmeticIds);

    /**
     * Resets all cosmetics on an NPC for the given {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param entity     the {@link ApolloEntity} of the NPC to reset the cosmetics on
     * @since 1.2.6
     */
    public abstract void resetNpcCosmetics(Recipients recipients, ApolloEntity entity);

}

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
package com.lunarclient.apollo.module.limb;

import com.lunarclient.apollo.audience.Audience;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import java.util.Collection;
import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the limb module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "limb", name = "Limb")
public abstract class LimbModule extends ApolloModule {

    /**
     * Hides the {@link ArmorPiece} for the {@link Audience}.
     *
     * @param audience    the audience that is receiving the packet
     * @param playerUuid  the player whose armor we will be manipulating
     * @param armorPieces the armor pieces to hide
     * @since 1.0.0
     */
    public abstract void hideArmorPieces(Audience audience, UUID playerUuid, Collection<ArmorPiece> armorPieces);

    /**
     * Resets the {@link ArmorPiece} for the {@link Audience}.
     *
     * @param audience    the audience that is receiving the packet
     * @param playerUuid  the player whose armor we will be manipulating
     * @param armorPieces the armor pieces to reset (show)
     * @since 1.0.0
     */
    public abstract void resetArmorPieces(Audience audience, UUID playerUuid, Collection<ArmorPiece> armorPieces);

    /**
     * Hides the {@link BodyPart} for the {@link Audience}.
     *
     * @param audience   the audience that is receiving the packet
     * @param playerUuid the player whose body we will be manipulating
     * @param bodyParts  the body parts to hide
     * @since 1.0.0
     */
    public abstract void hideBodyParts(Audience audience, UUID playerUuid, Collection<BodyPart> bodyParts);

    /**
     * Resets the {@link BodyPart} for the {@link Audience}.
     *
     * @param audience   the audience that is receiving the packet
     * @param playerUuid the player whose body we will be manipulating
     * @param bodyParts  the body parts to reset (show)
     * @since 1.0.0
     */
    public abstract void resetBodyParts(Audience audience, UUID playerUuid, Collection<BodyPart> bodyParts);

}

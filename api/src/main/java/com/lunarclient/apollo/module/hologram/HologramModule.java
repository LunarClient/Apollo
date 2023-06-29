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
package com.lunarclient.apollo.module.hologram;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Collection;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the hologram module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "hologram", name = "Hologram")
public abstract class HologramModule extends ApolloModule {

    /**
     * Adds or updates the {@link Hologram} to the {@link ApolloPlayer}s.
     *
     * @param hologram the hologram
     * @param viewers  the viewers
     * @since 1.0.0
     */
    public abstract void displayHologram(Collection<ApolloPlayer> viewers, Hologram hologram);

    /**
     * Removes the {@link Hologram} for the {@link ApolloPlayer}s.
     *
     * @param hologramId the hologram id
     * @param viewers    the viewers
     * @since 1.0.0
     */
    public abstract void removeHologram(Collection<ApolloPlayer> viewers, String hologramId);

    /**
     * Removes the {@link Hologram} for the {@link ApolloPlayer}s.
     *
     * @param viewers  the players who are receiving the packet
     * @param hologram the hologram
     * @since 1.0.0
     */
    public abstract void removeHologram(Collection<ApolloPlayer> viewers, Hologram hologram);

    /**
     * Resets all {@link Hologram}s for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @since 1.0.0r
     */
    public abstract void resetHolograms(ApolloPlayer viewer);

}

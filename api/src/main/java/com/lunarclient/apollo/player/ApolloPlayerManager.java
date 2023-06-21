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
package com.lunarclient.apollo.player;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import lombok.NonNull;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the player manager for Apollo.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public interface ApolloPlayerManager {

    /**
     * Returns {@code true} if the specified player {@link UUID} has Apollo
     * support, otherwise returns {@code false}.
     *
     * @param playerIdentifier the player identifier
     * @return true if the player supports apollo, otherwise false
     * @since 1.0.0
     */
    default boolean hasSupport(@NonNull UUID playerIdentifier) {
        return this.getPlayer(playerIdentifier).isPresent();
    }

    /**
     * Gets the {@link ApolloPlayer} for the specified {@link UUID} if it
     * exists, otherwise returns {@link Optional#empty()}.
     *
     * @param playerIdentifier the platform player
     * @return the apollo player, if present
     * @since 1.0.0
     */
    Optional<ApolloPlayer> getPlayer(UUID playerIdentifier);

    /**
     * Gets a collection of {@link ApolloPlayer}s that support Apollo.
     *
     * @return a list of apollo players
     * @since 1.0.0
     */
    Collection<ApolloPlayer> getPlayers();

}

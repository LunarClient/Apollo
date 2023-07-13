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
package com.lunarclient.apollo.world;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the world manager for Apollo.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public interface ApolloWorldManager {

    /**
     * Gets the {@link ApolloWorld} for the specified {@link UUID} if it
     * exists, otherwise returns {@link Optional#empty()}.
     *
     * @param worldIdentifier the platform world
     * @return the apollo world, if present
     * @since 1.0.0
     */
    Optional<ApolloWorld> getWorld(UUID worldIdentifier);

    /**
     * Gets an unmodifiable collection of {@link ApolloWorld}s that support
     * Apollo.
     *
     * @return an immutable collection of apollo worlds
     * @since 1.0.0
     */
    Collection<ApolloWorld> getWorlds();

}

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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Provides the implementation for the {@link ApolloWorldManager}.
 *
 * @since 1.0.0
 */
@NoArgsConstructor
public final class ApolloWorldManagerImpl implements ApolloWorldManager {

    private final Map<UUID, ApolloWorld> worlds = new HashMap<>();

    @Override
    public Optional<ApolloWorld> getWorld(@NonNull UUID worldIdentifier) {
        return Optional.ofNullable(this.worlds.get(worldIdentifier));
    }

    @Override
    public Collection<ApolloWorld> getWorlds() {
        return Collections.unmodifiableCollection(this.worlds.values());
    }

    /**
     * Adds a world to the world manager.
     *
     * @param world the world to add
     * @since 1.0.0
     */
    public void addWorld(@NonNull ApolloWorld world) {
        this.worlds.putIfAbsent(world.getUniqueId(), world);
    }

    /**
     * Removes a world from the world manager.
     *
     * @param world the world to remove
     * @since 1.0.0
     */
    public void removeWorld(@NonNull UUID world) {
        this.worlds.remove(world);
    }

}

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

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.player.ApolloRegisterPlayerEvent;
import com.lunarclient.apollo.event.player.ApolloUnregisterPlayerEvent;
import com.lunarclient.apollo.network.NetworkOptions;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Provides the implementation for the {@link ApolloPlayerManager}.
 *
 * @since 1.0.0
 */
@NoArgsConstructor
public final class ApolloPlayerManagerImpl implements ApolloPlayerManager {

    private final Map<UUID, ApolloPlayer> players = new HashMap<>();

    @Override
    public Optional<ApolloPlayer> getPlayer(@NonNull UUID playerIdentifier) {
        return Optional.ofNullable(this.players.get(playerIdentifier));
    }

    @Override
    public Collection<ApolloPlayer> getPlayers() {
        return Collections.unmodifiableCollection(this.players.values());
    }

    /**
     * Adds a player to the player manager.
     *
     * @param player the player to add
     * @since 1.0.0
     */
    public void addPlayer(@NonNull ApolloPlayer player) {
        if (this.players.putIfAbsent(player.getUniqueId(), player) == null) {
            NetworkOptions.sendOptions(
                Apollo.getModuleManager().getModules(),
                true,
                player
            );

            EventBus.EventResult<ApolloRegisterPlayerEvent> result = EventBus.getBus()
                .post(new ApolloRegisterPlayerEvent(player));
            for (Throwable throwable : result.getThrowing()) {
                throwable.printStackTrace();
            }
        }
    }

    /**
     * Removes a player from the player manager.
     *
     * @param player the player to remove
     * @since 1.0.0
     */
    public void removePlayer(@NonNull UUID player) {
        ApolloPlayer apolloPlayer = this.players.remove(player);
        if (apolloPlayer != null) {
            EventBus.EventResult<ApolloUnregisterPlayerEvent> result = EventBus.getBus()
                .post(new ApolloUnregisterPlayerEvent(apolloPlayer));
            for (Throwable throwable : result.getThrowing()) {
                throwable.printStackTrace();
            }
        }
    }

}

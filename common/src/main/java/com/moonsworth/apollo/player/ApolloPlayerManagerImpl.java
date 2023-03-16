package com.moonsworth.apollo.player;

import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayerManager;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Provides the implementation for the {@link ApolloPlayerManager}.
 *
 * @since 1.0.0
 */
public final class ApolloPlayerManagerImpl implements ApolloPlayerManager {

    private final Map<UUID, ApolloPlayer> players = new HashMap<>();

    public ApolloPlayerManagerImpl() {}

    @Override
    public Optional<ApolloPlayer> getPlayer(final UUID playerIdentifier) {
        return Optional.ofNullable(this.players.get(playerIdentifier));
    }

    @Override
    public Collection<ApolloPlayer> getPlayers() {
        return Collections.unmodifiableCollection(this.players.values());
    }

}

package com.moonsworth.apollo.player;

import com.moonsworth.apollo.Apollo;
import com.moonsworth.apollo.event.EventBus;
import com.moonsworth.apollo.event.player.ApolloRegisterPlayerEvent;
import com.moonsworth.apollo.event.player.ApolloUnregisterPlayerEvent;
import com.moonsworth.apollo.network.NetworkOptions;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

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

    public void addPlayer(final ApolloPlayer player) {
        requireNonNull(player, "player");
        if(this.players.putIfAbsent(player.getUniqueId(), player) == null) {
            final EventBus.EventResult<ApolloRegisterPlayerEvent> result = EventBus.getBus().post(new ApolloRegisterPlayerEvent(player));
            for(final Throwable throwable : result.getThrowing()) {
                throwable.printStackTrace();
            }

            NetworkOptions.sendOptions(
                    Apollo.getModuleManager().getModules(),
                    player
            );
        }
    }

    public void removePlayer(final UUID player) {
        requireNonNull(player, "player");
        final ApolloPlayer apolloPlayer = this.players.remove(player);
        if(apolloPlayer != null) {
            final EventBus.EventResult<ApolloUnregisterPlayerEvent> result = EventBus.getBus().post(new ApolloUnregisterPlayerEvent(apolloPlayer));
            for(final Throwable throwable : result.getThrowing()) {
                throwable.printStackTrace();
            }
        }
    }

}

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

import static java.util.Objects.requireNonNull;

/**
 * Provides the implementation for the {@link ApolloPlayerManager}.
 *
 * @since 1.0.0
 */
@NoArgsConstructor
public final class ApolloPlayerManagerImpl implements ApolloPlayerManager {

    private final Map<UUID, ApolloPlayer> players = new HashMap<>();

    @Override
    public Optional<ApolloPlayer> getPlayer(UUID playerIdentifier) {
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
    public void addPlayer(ApolloPlayer player) {
        requireNonNull(player, "player");

        if(this.players.putIfAbsent(player.getUniqueId(), player) == null) {
            EventBus.EventResult<ApolloRegisterPlayerEvent> result = EventBus.getBus().post(new ApolloRegisterPlayerEvent(player));
            for(Throwable throwable : result.getThrowing()) {
                throwable.printStackTrace();
            }

            NetworkOptions.sendOptions(
                    Apollo.getModuleManager().getModules(),
                    player
            );
        }
    }

    /**
     * Removes a player from the player manager.
     *
     * @param player the player to remove
     * @since 1.0.0
     */
    public void removePlayer(UUID player) {
        requireNonNull(player, "player");

        ApolloPlayer apolloPlayer = this.players.remove(player);
        if(apolloPlayer != null) {
            EventBus.EventResult<ApolloUnregisterPlayerEvent> result = EventBus.getBus().post(new ApolloUnregisterPlayerEvent(apolloPlayer));
            for(Throwable throwable : result.getThrowing()) {
                throwable.printStackTrace();
            }
        }
    }

}

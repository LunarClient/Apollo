package com.moonsworth.apollo.api.player;

import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.events.EventBus;
import com.moonsworth.apollo.api.events.Listener;
import com.moonsworth.apollo.api.events.impl.player.EventApolloPlayerRegister;
import com.moonsworth.apollo.api.events.impl.player.EventApolloPlayerUnregister;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public class ApolloPlayerManager {

    private final Map<UUID, ApolloPlayer> supportedPlayers = new HashMap<>();

    // TODO implement on Bungee and Velocity
    public ApolloPlayerManager() {

    }

    /**
     * Gets an Apollo representation of a player by their ID
     * @param id The Unique Id of an ApolloPlayer
     */
    public Optional<ApolloPlayer> getApolloPlayer(UUID id) {
        return Optional.ofNullable(supportedPlayers.get(id));
    }

    /**
     * Registers a player to Apollo.
     * @param player The player object on the specific platform to register.
     */
    public void registerPlayer(Object player) {
        ApolloPlayer apolloPlayer = Apollo.getPlatform().tryWrapPlayer(player);
        if (apolloPlayer == null) {
            return;
        }
        supportedPlayers.put(apolloPlayer.getUniqueId(), apolloPlayer);
        EventBus.getBus().post(new EventApolloPlayerRegister(apolloPlayer));
    }

    /**
     * Unregisters a player from Apollo.
     * @param player The player to unregister.
     */
    public void unRegisterPlayer(UUID player) {
        ApolloPlayer apolloPlayer = supportedPlayers.remove(player);
        if (apolloPlayer != null) {
            EventBus.getBus().post(new EventApolloPlayerUnregister(apolloPlayer));
        }
    }
}

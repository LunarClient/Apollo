package com.lunarclient.apollo.example;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.border.BorderModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Optional;

public class ApolloGeneralExample {

    public ApolloGeneralExample() {
        Player player = null;

        // Checking if the player is running Lunar Client
        boolean runningLunarClient = Apollo.getPlayerManager().hasSupport(player.getUniqueId());

        // Getting the ApolloPlayer object by player UUID
        Optional<ApolloPlayer> apolloPlayer = Apollo.getPlayerManager().getPlayer(player.getUniqueId());

        // Get all players running Lunar Client
        Collection<ApolloPlayer> playersRunningLunarClient = Apollo.getPlayerManager().getPlayers();

        // Get a specific module
        BorderModule borderModule = Apollo.getModuleManager().getModule(BorderModule.class);
    }
}

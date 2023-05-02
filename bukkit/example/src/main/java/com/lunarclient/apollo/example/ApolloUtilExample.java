package com.lunarclient.apollo.example;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;

import java.util.Collection;

public class ApolloUtilExample {

    public static boolean isRunningLunarClient(Player player) {
        return Apollo.getPlayerManager().hasSupport(player.getUniqueId());
    }

    public static Collection<ApolloPlayer> getLunarClientPlayers() {
        return Apollo.getPlayerManager().getPlayers();
    }
}

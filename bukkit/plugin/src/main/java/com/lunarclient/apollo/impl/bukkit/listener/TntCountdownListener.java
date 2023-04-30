package com.lunarclient.apollo.impl.bukkit.listener;

import com.lunarclient.apollo.module.tntcountdown.TntCountdownModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayerManager;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.Optional;

@AllArgsConstructor
public class TntCountdownListener implements Listener {

    private final TntCountdownModule tntCountdownModule;
    private final ApolloPlayerManager playerManager;

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onTntSpawn(EntitySpawnEvent event) {
        // We only care about TNT
        if (event.getEntityType() != EntityType.PRIMED_TNT) return;

        TNTPrimed primed = (TNTPrimed) event.getEntity();

        // We only care about TNT with a non-standard fuse as well
        if (primed.getFuseTicks() == tntCountdownModule.getOptions().get(TntCountdownModule.TNT_TICKS)) {
            return;
        }

        // If it does have a non-standard fuse, broadcast to all Apollo players nearby
        for (Player player : primed.getLocation().getNearbyPlayers(Bukkit.getSimulationDistance())) {
            Optional<ApolloPlayer> apolloPlayerOpt = playerManager.getPlayer(player.getUniqueId());
            apolloPlayerOpt.ifPresent(apolloPlayer -> {
                tntCountdownModule.setTntCountdown(apolloPlayer, primed.getUniqueId(), primed.getFuseTicks());
            });
        }
    }

}

package com.lunarclient.apollo.impl.bukkit.listener;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.type.TntCountdown;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

@RequiredArgsConstructor
public class TntCountdownListener implements Listener {
    private final TntCountdown tntCountdownModule;

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onTntSpawn(EntitySpawnEvent event) {
        if(!(event.getEntity() instanceof TNTPrimed primed)) return;
        int id = event.getEntity().getEntityId();
        int ticks = primed.getFuseTicks();

        if(ticks != tntCountdownModule.getOptions().get(TntCountdown.TNT_TICKS)) {
            for(Player bukkitPlayer : event.getLocation().getNearbyPlayers(Bukkit.getSimulationDistance())) {
                Optional<ApolloPlayer> player = Apollo.getPlayerManager().getPlayer(bukkitPlayer.getUniqueId());
                if(player.isEmpty()) continue;

                tntCountdownModule.setTntTicks(player.get(), id, ticks);
            }
        }
    }
}

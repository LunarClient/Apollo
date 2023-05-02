package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.tntcountdown.TntCountdownModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.Optional;
import java.util.UUID;

public class TntCountdownExample implements Listener {

    private final TntCountdownModule tntCountdownModule = Apollo.getModuleManager().getModule(TntCountdownModule.class);

    public void setTntCountdownExample(Player viewer, UUID tntUuid, int ticks) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.tntCountdownModule.setTntCountdown(apolloPlayer, tntUuid, ticks));
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onTntSpawn(EntitySpawnEvent event) {
        if (!(event.getEntity() instanceof TNTPrimed tnt)) return;

        if (tnt.hasMetadata("very-special-tnt") && tnt.getSource() instanceof Player player) {
            this.setTntCountdownExample(player, tnt.getUniqueId(), 5);
        }
    }

}

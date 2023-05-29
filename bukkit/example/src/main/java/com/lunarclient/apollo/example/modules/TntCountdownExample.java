package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.ApolloEntity;
import com.lunarclient.apollo.module.tntcountdown.TntCountdownModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.Listener;

import java.util.Optional;

public class TntCountdownExample implements Listener {

    private final TntCountdownModule tntCountdownModule = Apollo.getModuleManager().getModule(TntCountdownModule.class);

    public void setTntCountdownExample() {
        this.tntCountdownModule.getOptions().set(TntCountdownModule.TNT_TICKS, 160);
    }

    public void overrideTntCountdownExample(Player viewer) {
        Location location = viewer.getLocation();
        World world = viewer.getWorld();

        TNTPrimed entity = world.spawn(location, TNTPrimed.class);

        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.tntCountdownModule.setTntCountdown(
            new ApolloEntity(entity.getEntityId(), entity.getUniqueId()),
            200
        ));
    }

    public void clearTntCountdownOptionExample() {
        this.tntCountdownModule.getOptions().remove(TntCountdownModule.TNT_TICKS, 160);
    }

}

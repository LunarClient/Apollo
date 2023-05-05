package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.module.waypoint.Waypoint;
import com.lunarclient.apollo.module.waypoint.WaypointModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;

import java.awt.Color;
import java.util.Optional;

public class WaypointExample {

    private final WaypointModule waypointModule = Apollo.getModuleManager().getModule(WaypointModule.class);

    public void displayWaypointExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.waypointModule.displayWaypoint(apolloPlayer, Waypoint.builder()
                .name("KoTH")
                .location(ApolloBlockLocation.builder()
                    .world("world") // The world the waypoint should display in
                    .x(500)
                    .y(100)
                    .z(500)
                    .build()
                )
                .color(Color.ORANGE)
                .preventRemoval(false) // If the player can delete the waypoint
                .visible(true)
                .build()
            );
        });
    }

    public void removeWaypointExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.waypointModule.removeWaypoint(apolloPlayer, "KoTh"));
    }

    public void resetWaypointsExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.waypointModule::resetWaypoints);
    }

}

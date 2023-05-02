package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.module.beam.Beam;
import com.lunarclient.apollo.module.beam.BeamModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;

import java.awt.Color;
import java.util.Optional;

public class BeamExample {

    private final BeamModule beamModule = Apollo.getModuleManager().getModule(BeamModule.class);

    public void displayBeamExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.beamModule.displayBeam(apolloPlayer, Beam.builder()
                .id("spawn-beacon")
                .color(Color.CYAN)
                .location(ApolloBlockLocation.builder()
                    .world("world")
                    .x(0)
                    .y(60)
                    .z(0)
                    .build()
                )
                .build()
            );
        });
    }

    public void removeBeamExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.beamModule.removeBeam(apolloPlayer, "spawn-beacon"));
    }

    public void resetBeamsExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.beamModule::resetBeams);
    }

}

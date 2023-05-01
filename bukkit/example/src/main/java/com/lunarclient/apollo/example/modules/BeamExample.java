package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.module.beam.Beam;
import com.lunarclient.apollo.module.beam.BeamModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.awt.Color;
import java.util.Optional;

public class BeamExample {

    private BeamModule beamModule;

    public void displayBeamExample(Player player) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(player.getUniqueId());

        if (apolloPlayerOpt.isEmpty()) {
            player.sendMessage(Component.text("Join with Lunar Client to test this feature!"));
            return;
        }

        this.beamModule.displayBeam(apolloPlayerOpt.get(), Beam.builder()
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
    }

    public void removeBeamExample(Player player) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(player.getUniqueId());

        if (apolloPlayerOpt.isEmpty()) {
            player.sendMessage(Component.text("Join with Lunar Client to test this feature!"));
            return;
        }

        this.beamModule.removeBeam(apolloPlayerOpt.get(), "spawn-beacon");
    }

    public void resetBeamsExample(Player player) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(player.getUniqueId());

        if (apolloPlayerOpt.isEmpty()) {
            player.sendMessage(Component.text("Join with Lunar Client to test this feature!"));
            return;
        }

        this.beamModule.resetBeams(apolloPlayerOpt.get());
    }

}

package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.cuboid.Cuboid2D;
import com.lunarclient.apollo.module.border.Border;
import com.lunarclient.apollo.module.border.BorderModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;

import java.awt.Color;
import java.util.Optional;

public class BorderExample {

    private final BorderModule borderModule = Apollo.getModuleManager().getModule(BorderModule.class);

    public void displayBorderExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.borderModule.displayBorder(apolloPlayer, Border.builder()
                .id("pvp-tagged-spawn")
                .world("world")
                .cancelEntry(true)
                .cancelExit(false)
                .canShrinkOrExpand(false)
                .color(Color.RED)
                .bounds(Cuboid2D.builder()
                    .minX(-50)
                    .minZ(-50)
                    .maxX(50)
                    .maxZ(50)
                    .build()
                )
                .durationTicks(0)
                .build()
            );
        });
    }

    public void removeBorderExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.borderModule.removeBorder(apolloPlayer, "pvp-tagged-spawn"));
    }

    public void resetBordersExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.borderModule::resetBorders);
    }

}

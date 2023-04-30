package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.cuboid.Cuboid2D;
import com.lunarclient.apollo.module.border.Border;
import com.lunarclient.apollo.module.border.BorderModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.Optional;

public class BorderExample {

    private BorderModule borderModule;

    public void displayBorderExample(Player player) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(player.getUniqueId());

        if (apolloPlayerOpt.isEmpty()) {
            player.sendMessage(Component.text("Join with Lunar Client to test this feature!"));
            return;
        }

        borderModule.displayBorder(apolloPlayerOpt.get(), Border.builder()
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
    }

    public void removeBorderExample(Player player) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(player.getUniqueId());

        if (apolloPlayerOpt.isEmpty()) {
            player.sendMessage(Component.text("Join with Lunar Client to test this feature!"));
            return;
        }

        borderModule.removeBorder(apolloPlayerOpt.get(), "pvp-tagged-spawn");
    }

    public void resetBordersExample(Player player) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(player.getUniqueId());

        if (apolloPlayerOpt.isEmpty()) {
            player.sendMessage(Component.text("Join with Lunar Client to test this feature!"));
            return;
        }

        borderModule.resetBorders(apolloPlayerOpt.get());
    }

}

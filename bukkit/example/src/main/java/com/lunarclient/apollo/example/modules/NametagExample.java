package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.Component;
import com.lunarclient.apollo.module.nametag.Nametag;
import com.lunarclient.apollo.module.nametag.NametagModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;

import java.awt.Color;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class NametagExample {

    private final NametagModule nametagModule = Apollo.getModuleManager().getModule(NametagModule.class);

    public void overrideNametagExample(Player target) {
        Collection<ApolloPlayer> viewers = Apollo.getPlayerManager().getPlayers();

        this.nametagModule.overrideNametag(viewers, target.getUniqueId(), Nametag.builder()
                .lines(List.of(
                    Component.builder()
                        .content("[StaffMode]")
                        .color(Color.GRAY)
                        .build(),
                    Component.builder()
                        .content("Vanished")
                        .decorators(List.of(Component.TextDecorators.ITALIC))
                        .color(Color.GREEN)
                        .build()
                ))
                .build()
            );
    }

    public void resetNametagExample(Player target) {
        Collection<ApolloPlayer> viewers = Apollo.getPlayerManager().getPlayers();
        this.nametagModule.resetNametag(viewers, target.getUniqueId());
    }

    public void resetNametagsExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.nametagModule::resetNametags);
    }
}

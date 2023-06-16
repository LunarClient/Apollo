package com.lunarclient.apollo.example.modules;

import com.google.common.collect.Lists;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.Component;
import com.lunarclient.apollo.module.nametag.Nametag;
import com.lunarclient.apollo.module.nametag.NametagModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class NametagExample {

    private final NametagModule nametagModule = Apollo.getModuleManager().getModule(NametagModule.class);

    public void overrideNametagExample(Player target) {
        Collection<ApolloPlayer> viewers = Apollo.getPlayerManager().getPlayers();

        this.nametagModule.overrideNametag(viewers, target.getUniqueId(), Nametag.builder()
                .lines(Lists.newArrayList(
                    Component.builder()
                        .content("[StaffMode]")
                        .decorators(Collections.singletonList(Component.TextDecorators.ITALIC))
                        .color(Color.GRAY)
                        .build(),
                    Component.builder()
                        .content(target.getName())
                        .color(Color.RED)
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

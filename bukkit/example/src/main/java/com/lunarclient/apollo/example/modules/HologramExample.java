package com.lunarclient.apollo.example.modules;

import com.google.common.collect.Lists;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.Component;
import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.module.hologram.Hologram;
import com.lunarclient.apollo.module.hologram.HologramModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.Collection;
import java.util.Optional;

public class HologramExample {

    private final HologramModule hologramModule = Apollo.getModuleManager().getModule(HologramModule.class);

    public void displayHologramExample() {
        Collection<ApolloPlayer> viewers = Apollo.getPlayerManager().getPlayers();

        this.hologramModule.displayHologram(viewers, Hologram.builder()
            .id("welcome-hologram")
            .location(ApolloLocation.builder()
                .world("world")
                .z(5)
                .y(105)
                .z(0)
                .build())
            .lines(Lists.newArrayList(
                Component.builder()
                    .content("Welcome to my server!")
                    .color(Color.RED)
                    .decorators(Lists.newArrayList(Component.TextDecorators.BOLD, Component.TextDecorators.UNDERLINED))
                    .build(),
                Component.builder()
                    .content("Type /help to get started!")
                    .build()
            ))
            .showThroughWalls(true)
            .showShadow(false)
            .showBackground(true)
            .build()
        );
    }

    public void removeHologramExample() {
        Collection<ApolloPlayer> viewers = Apollo.getPlayerManager().getPlayers();

        /*
         * Removes the hologram with the ID "welcome-hologram" for all
         * players inside of the viewers collection.
         */
        this.hologramModule.removeHologram(viewers, "welcome-hologram");
    }

    public void resetHologramsExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        // Resetting every hologram
        apolloPlayerOpt.ifPresent(this.hologramModule::resetHolograms);
    }

}

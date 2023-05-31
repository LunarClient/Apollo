package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.ApolloEntity;
import com.lunarclient.apollo.module.misc.MiscModule;
import com.lunarclient.apollo.module.misc.Vignette;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class MiscExample {

    private final MiscModule miscModule = Apollo.getModuleManager().getModule(MiscModule.class);

    public void displayVignetteExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.miscModule.displayVignette(apolloPlayer, Vignette.builder()
                .resourceLocation("textures/misc/pumpkinblur.png")
                .opacity(0.75f)
                .build()
            );
        });
    }

    public void resetVignetteExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.miscModule::resetVignette);
    }

    public void overrideRainbowSheepExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            // Get all Sheep in players world
            List<ApolloEntity> sheepEntities = viewer.getWorld().getEntitiesByClass(Sheep.class)
                .stream().map(sheep -> new ApolloEntity(sheep.getEntityId(), sheep.getUniqueId()))
                .collect(Collectors.toList());

            this.miscModule.overrideRainbowSheep(apolloPlayer, sheepEntities);
        });
    }

    public void resetRainbowSheepExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            // Get all Sheep in players world
            List<ApolloEntity> sheepEntities = viewer.getWorld().getEntitiesByClass(Sheep.class)
                .stream().map(sheep -> new ApolloEntity(sheep.getEntityId(), sheep.getUniqueId()))
                .collect(Collectors.toList());

            this.miscModule.resetRainbowSheep(apolloPlayer, sheepEntities);
        });
    }

    public void flipEntityExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            // Get all cows within 10 blocks of the player
            List<ApolloEntity> entities = viewer.getWorld()
                .getNearbyEntitiesByType(Cow.class, viewer.getLocation(), 10)
                .stream().map(cow -> new ApolloEntity(cow.getEntityId(), cow.getUniqueId()))
                .collect(Collectors.toList());

            this.miscModule.flipEntity(apolloPlayer, entities);
        });
    }

    public void resetFlippedEntityExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            // Get all cows within 10 blocks of the player
            List<ApolloEntity> entities = viewer.getWorld()
                .getNearbyEntitiesByType(Cow.class, viewer.getLocation(), 10)
                .stream().map(cow -> new ApolloEntity(cow.getEntityId(), cow.getUniqueId()))
                .collect(Collectors.toList());

            this.miscModule.resetFlippedEntity(apolloPlayer, entities);
        });
    }

}

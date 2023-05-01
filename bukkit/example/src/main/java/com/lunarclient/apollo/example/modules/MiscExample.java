package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.misc.MiscModule;
import com.lunarclient.apollo.module.misc.Vignette;
import com.lunarclient.apollo.player.ApolloPlayer;
import net.kyori.adventure.text.Component;
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

        apolloPlayerOpt.ifPresentOrElse(apolloPlayer -> {
            this.miscModule.displayVignette(apolloPlayer, Vignette.builder()
                .resourceLocation("blocks/pumpkin.png")
                .opacity(0.75f)
                .build()
            );
        }, () -> viewer.sendMessage(Component.text("Join with Lunar Client to test this feature!")));
    }

    public void resetVignetteExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.miscModule::resetVignette);
    }

    public void overrideRainbowSheepExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresentOrElse(apolloPlayer -> {
            // Get all Sheep in players world
            List<UUID> sheepUuids = viewer.getWorld().getEntitiesByClass(Sheep.class)
                .stream().map(Sheep::getUniqueId).collect(Collectors.toList());

            this.miscModule.overrideRainbowSheep(apolloPlayer, sheepUuids);
        }, () -> viewer.sendMessage(Component.text("Join with Lunar Client to test this feature!")));
    }

    public void resetRainbowSheepExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            // Get all Sheep in players world
            List<UUID> sheepUuids = viewer.getWorld().getEntitiesByClass(Sheep.class)
                .stream().map(Sheep::getUniqueId).collect(Collectors.toList());

            this.miscModule.resetRainbowSheep(apolloPlayer, sheepUuids);
        });
    }

    public void flipEntityExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresentOrElse(apolloPlayer -> {
            // Get all cows within 10 blocks of the player
            List<UUID> sheepUuids = viewer.getWorld()
                .getNearbyEntitiesByType(Cow.class, viewer.getLocation(), 10)
                .stream().map(Cow::getUniqueId).collect(Collectors.toList());

            this.miscModule.flipEntity(apolloPlayer, sheepUuids);
        }, () -> viewer.sendMessage(Component.text("Join with Lunar Client to test this feature!")));
    }

    public void resetFlippedEntityExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            // Get all cows within 10 blocks of the player
            List<UUID> sheepUuids = viewer.getWorld()
                .getNearbyEntitiesByType(Cow.class, viewer.getLocation(), 10)
                .stream().map(Cow::getUniqueId).collect(Collectors.toList());

            this.miscModule.resetFlippedEntity(apolloPlayer, sheepUuids);
        });
    }
}

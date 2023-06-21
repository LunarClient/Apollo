/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2023 Moonsworth
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.ApolloEntity;
import com.lunarclient.apollo.module.misc.MiscModule;
import com.lunarclient.apollo.module.misc.Vignette;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

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

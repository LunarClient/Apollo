/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2026 Moonsworth
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
package com.lunarclient.apollo.example.api.module;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.icon.ItemStackIcon;
import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.example.module.impl.MarkerExample;
import com.lunarclient.apollo.module.marker.Marker;
import com.lunarclient.apollo.module.marker.MarkerModule;
import com.lunarclient.apollo.module.marker.MarkerStyle;
import com.lunarclient.apollo.module.marker.display.MarkerDisplayCondition;
import com.lunarclient.apollo.module.marker.display.MarkerFlag;
import com.lunarclient.apollo.module.marker.display.MarkerOwnerDisplay;
import com.lunarclient.apollo.module.marker.target.BlockMarkerTarget;
import com.lunarclient.apollo.module.marker.target.EntityMarkerTarget;
import com.lunarclient.apollo.module.marker.target.ItemMarkerTarget;
import com.lunarclient.apollo.module.marker.target.PlayerMarkerTarget;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.awt.Color;
import java.time.Duration;
import java.util.Optional;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MarkerApiExample extends MarkerExample {

    private final MarkerModule markerModule = Apollo.getModuleManager().getModule(MarkerModule.class);

    @Override
    public void displayBlockMarkerExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            Location location = viewer.getLocation();

            this.markerModule.displayMarker(apolloPlayer, Marker.builder()
                .id("loot-chest")
                .location(ApolloLocation.builder()
                    .world(location.getWorld().getName())
                    .x(location.getX())
                    .y(location.getY())
                    .z(location.getZ())
                    .build())
                .ownerId(viewer.getUniqueId())
                .ownerName("")
                .flag(MarkerFlag.INTEREST)
                .target(BlockMarkerTarget.builder()
                    .itemStack(ItemStackIcon
                        .builder()
                        .itemName("minecraft:chest")
                        .build())
                    .build())
                .duration(Duration.ofSeconds(60))
                .style(MarkerStyle.builder()
                    .showOwner(MarkerDisplayCondition.NEVER)
                    .showDescription(MarkerDisplayCondition.ALWAYS)
                    .build())
                .build()
            );
        });
    }

    @Override
    public void displayPlayerMarkerExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            Location location = viewer.getLocation();

            this.markerModule.displayMarker(apolloPlayer, Marker.builder()
                .id("bounty")
                .location(ApolloLocation.builder()
                    .world(location.getWorld().getName())
                    .x(location.getX())
                    .y(location.getY())
                    .z(location.getZ())
                    .build())
                .ownerId(viewer.getUniqueId())
                .ownerName(viewer.getName())
                .flag(MarkerFlag.DANGER)
                .color(Color.RED)
                .target(PlayerMarkerTarget.builder()
                    .playerId(UUID.fromString("f17627d8-1a97-487b-92ea-c04f413394bd"))
                    .playerName("ItsNature")
                    .build())
                .inGameNotification(true)
                .style(MarkerStyle.builder()
                    .showOwner(MarkerDisplayCondition.NEVER)
                    .showDescription(MarkerDisplayCondition.ALWAYS)
                    .build())
                .build()
            );
        });
    }

    @Override
    public void displayItemMarkerExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            Location location = viewer.getLocation();

            this.markerModule.displayMarker(apolloPlayer, Marker.builder()
                .id("wither-loot")
                .location(ApolloLocation.builder()
                    .world(location.getWorld().getName())
                    .x(location.getX())
                    .y(location.getY())
                    .z(location.getZ())
                    .build())
                .ownerId(viewer.getUniqueId())
                .ownerName(viewer.getName())
                .flag(MarkerFlag.INFO)
                .target(ItemMarkerTarget.builder()
                    .itemStack(ItemStackIcon
                        .builder()
                        .itemName("minecraft:nether_star")
                        .build())
                    .build())
                .style(MarkerStyle.builder()
                    .showOwner(MarkerDisplayCondition.NEVER)
                    .showDescription(MarkerDisplayCondition.ALWAYS)
                    .build())
                .build()
            );
        });
    }

    @Override
    public void displayEntityMarkerExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            Location location = viewer.getLocation();

            this.markerModule.displayMarker(apolloPlayer, Marker.builder()
                .id("tutorial-npc")
                .location(ApolloLocation.builder()
                    .world(location.getWorld().getName())
                    .x(location.getX())
                    .y(location.getY())
                    .z(location.getZ())
                    .build())
                .ownerId(UUID.randomUUID())
                .ownerName("Tutorial NPC")
                .flag(MarkerFlag.INTEREST)
                .target(EntityMarkerTarget.builder()
                    .entityType("minecraft:villager")
                    .build())
                .style(MarkerStyle.builder()
                    .scale(1.3F)
                    .ownerSuffix("")
                    .ownerDisplay(MarkerOwnerDisplay.NAME)
                    .showOwner(MarkerDisplayCondition.HOVER)
                    .showDistance(MarkerDisplayCondition.NEVER)
                    .build())
                .build()
            );
        });
    }

    @Override
    public void removeMarkersExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.markerModule.removeMarker(apolloPlayer, "loot-chest");
            this.markerModule.removeMarker(apolloPlayer, "bounty");
            this.markerModule.removeMarker(apolloPlayer, "wither-loot");
            this.markerModule.removeMarker(apolloPlayer, "tutorial-npc");
        });
    }

    @Override
    public void resetMarkersExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.markerModule::resetMarkers);
    }

}

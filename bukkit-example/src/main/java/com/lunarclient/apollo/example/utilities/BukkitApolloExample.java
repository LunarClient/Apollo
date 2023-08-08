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
package com.lunarclient.apollo.example.utilities;

import com.google.common.collect.Lists;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.BukkitApollo;
import com.lunarclient.apollo.common.Component;
import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.module.coloredfire.ColoredFireModule;
import com.lunarclient.apollo.module.hologram.Hologram;
import com.lunarclient.apollo.module.hologram.HologramModule;
import com.lunarclient.apollo.module.waypoint.Waypoint;
import com.lunarclient.apollo.module.waypoint.WaypointModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import java.awt.Color;
import java.util.Collection;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public final class BukkitApolloExample {

    private final ColoredFireModule coloredFireModule = Apollo.getModuleManager().getModule(ColoredFireModule.class);
    private final HologramModule hologramModule = Apollo.getModuleManager().getModule(HologramModule.class);
    private final WaypointModule waypointModule = Apollo.getModuleManager().getModule(WaypointModule.class);

    public void runPlayerExample(Player player) {
        BukkitApollo.runForPlayer(player, apolloPlayer -> {
            /* Operation goes here! */
        });
    }

    public void runRecipientsExample(Collection<Player> players, UUID burningPlayer) {
        Recipients recipients = BukkitApollo.getRecipientsFrom(players);
        this.coloredFireModule.overrideColoredFire(recipients,
            burningPlayer,
            Color.BLUE
        );
    }

    public void runLocationExample(Location location) {
        ApolloLocation apolloLocation = BukkitApollo.toApolloLocation(location);

        this.hologramModule.displayHologram(Recipients.ofEveryone(), Hologram.builder()
            .id("welcome-hologram")
            .location(apolloLocation)
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

    public void runBlockLocationExample(ApolloPlayer apolloPlayer, Location location) {
        ApolloBlockLocation apolloLocation = BukkitApollo.toApolloBlockLocation(location);

        this.waypointModule.displayWaypoint(apolloPlayer, Waypoint.builder()
            .name("KoTH")
            .location(apolloLocation)
            .color(Color.ORANGE)
            .preventRemoval(false) // If the player can delete the waypoint
            .hidden(false)
            .build()
        );
    }

    private BukkitApolloExample() {
    }

}

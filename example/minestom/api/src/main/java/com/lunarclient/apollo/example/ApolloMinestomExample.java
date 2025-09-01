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
package com.lunarclient.apollo.example;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.ApolloMinestomPlatform;
import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.player.ApolloRegisterPlayerEvent;
import com.lunarclient.apollo.module.waypoint.Waypoint;
import com.lunarclient.apollo.module.waypoint.WaypointModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.awt.Color;
import net.minestom.server.Auth;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.block.Block;

public final class ApolloMinestomExample {

    public static void main(String[] args) {
        MinecraftServer server = MinecraftServer.init(new Auth.Online());

        InstanceContainer instance = MinecraftServer.getInstanceManager().createInstanceContainer();
        instance.setGenerator(unit -> unit.modifier().fillHeight(-1, 0, Block.STONE));

        GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();
        eventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> event.setSpawningInstance(instance));
        eventHandler.addListener(PlayerSpawnEvent.class, event -> {
            Player player = event.getPlayer();
            player.setPermissionLevel(4);
            player.setGameMode(GameMode.CREATIVE);
        });

        // Initialize Apollo
        ApolloMinestomPlatform.init();

        // Display a Apollo Waypoint
        EventBus.getBus().register(ApolloRegisterPlayerEvent.class, event -> {
            ApolloPlayer player = event.getPlayer();

            Apollo.getModuleManager().getModule(WaypointModule.class).displayWaypoint(player, Waypoint.builder()
                .name("KoTH")
                .location(ApolloBlockLocation.builder()
                    .world(player.getWorld().get().getName())
                    .x(500)
                    .y(100)
                    .z(500)
                    .build())
                .color(Color.ORANGE)
                .preventRemoval(false)
                .hidden(false)
                .build()
            );
        });

        server.start("0.0.0.0", 25565);
    }

    private ApolloMinestomExample() {
    }

}

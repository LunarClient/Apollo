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
import com.lunarclient.apollo.BukkitApollo;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.ping.ApolloPlayerPingEvent;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.module.playerping.PlayerPing;
import com.lunarclient.apollo.module.playerping.PlayerPingModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import java.awt.Color;
import java.time.Duration;
import java.util.Optional;
import java.util.UUID;
import org.bukkit.entity.Player;

public class PlayerPingExample {

    private final PlayerPingModule playerPingModule = Apollo.getModuleManager().getModule(PlayerPingModule.class);

    public PlayerPingExample() {
        EventBus.getBus().register(ApolloPlayerPingEvent.class, this::onApolloPlayerPing);
    }

    private void onApolloPlayerPing(ApolloPlayerPingEvent event) {
        TeamExample teamExample = ApolloExamplePlugin.getPlugin().getTeamExample();
        UUID playerUuid = event.getPlayer().getUniqueId();

        // Forward the event to everyone on the team. You may want to limit by distance or other factors.
        teamExample.getByPlayerUuid(playerUuid).ifPresent(team -> {
            Recipients recipients = BukkitApollo.getRecipientsFrom(team.getMembers());

            team.getMembers().forEach(member -> {
                this.playerPingModule.displayPlayerPing(recipients, event.getPing());
            });
        });
    }

    public void displayPlayerPingExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.playerPingModule.displayPlayerPing(Recipients.ofEveryone(), PlayerPing.builder()
                .id("test-ping")
                .color(Color.BLUE)
                .location(BukkitApollo.toApolloLocation(viewer.getLocation()))
                .displayTime(Duration.ofSeconds(5))
                .build()
            );
        });
    }

    public void removePlayerPingExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.playerPingModule.removePlayerPing(apolloPlayer, "test-ping"));
    }

    public void resetPlayerPingsExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.playerPingModule::resetPlayerPings);
    }

}

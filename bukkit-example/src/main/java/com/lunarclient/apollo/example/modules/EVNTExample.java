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

import com.google.common.collect.Sets;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.evnt.CharacterType;
import com.lunarclient.apollo.module.evnt.EVNTModule;
import com.lunarclient.apollo.module.evnt.event.EventGame;
import com.lunarclient.apollo.module.evnt.event.EventPlayer;
import com.lunarclient.apollo.module.evnt.event.EventStatus;
import com.lunarclient.apollo.module.evnt.event.EventTeam;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import org.bukkit.entity.Player;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class EVNTExample {

    private final EVNTModule evntModule = Apollo.getModuleManager().getModule(EVNTModule.class);

    public void overrideCharacterCosmeticExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.evntModule.overrideCharacterCosmetic(Recipients.ofEveryone(), apolloPlayer.getUniqueId(), CharacterType.ANGEL);
        });
    }

    public void updateGameOverviewExample() {
        EventTeam teamOne = EventTeam.builder()
            .bottomCrystalHealth(1.0F)
            .build();

        EventTeam teamTwo = EventTeam.builder()
            .dragonHealth(13.0F)
            .build();

        EventGame eventGame = EventGame.builder()
            .teamOne(teamOne)
            .teamTwo(teamTwo)
            .tierThreeHealth(4.0F)
            .build();

        this.evntModule.updateGameOverview(Recipients.ofEveryone(), eventGame);
    }

    public void updateStatusOverviewExample() {
        Set<EventPlayer> teamOne = Sets.newHashSet(
            EventPlayer.builder()
                .playerUuid(UUID.randomUUID())
                .health(3.0F)
                .build(),

            EventPlayer.builder()
                .playerUuid(UUID.randomUUID())
                .health(10.0F)
                .build()
        );

        Set<EventPlayer> teamTwo = Sets.newHashSet(
            EventPlayer.builder()
                .playerUuid(UUID.randomUUID())
                .health(5.5F)
                .build()
        );

        EventStatus eventStatus = EventStatus.builder()
            .teamOne(teamOne)
            .teamTwo(teamTwo)
            .build();

        this.evntModule.updateStatusOverview(Recipients.ofEveryone(), eventStatus);
    }

}

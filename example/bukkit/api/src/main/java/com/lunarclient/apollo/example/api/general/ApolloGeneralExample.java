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
package com.lunarclient.apollo.example.api.general;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.border.BorderModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.bukkit.entity.Player;

public class ApolloGeneralExample {

    public ApolloGeneralExample() {
        Player player = null;

        // Checking if the player is running Lunar Client
        boolean runningLunarClient = Apollo.getPlayerManager().hasSupport(player.getUniqueId());

        // Getting the ApolloPlayer object by player UUID
        Optional<ApolloPlayer> apolloPlayer = Apollo.getPlayerManager().getPlayer(player.getUniqueId());

        // Get all players running Lunar Client
        Collection<ApolloPlayer> playersRunningLunarClient = Apollo.getPlayerManager().getPlayers();

        // Get a specific module
        BorderModule borderModule = Apollo.getModuleManager().getModule(BorderModule.class);

        // Creating recipients with a single Apollo Player
        apolloPlayer.ifPresent(apolloPlayerObject -> {
            Recipients recipients = apolloPlayerObject;
        });

        // Creating recipients with all players running Lunar Client
        Recipients allRecipients = Recipients.ofEveryone();

        // Creating recipients with all players running Lunar Client with names shorter than 6 characters
        Recipients filteredRecipients = Recipients.of(
            Apollo.getPlayerManager().getPlayers().stream()
                .filter(online -> online.getName().length() < 6)
                .collect(Collectors.toList())
        );
    }

}

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
package com.lunarclient.apollo.wrapper;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayerManager;
import com.lunarclient.apollo.recipients.ForwardingRecipients;
import com.lunarclient.apollo.recipients.Recipients;
import com.lunarclient.apollo.world.ApolloWorld;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;

/**
 * The Minestom implementation of {@link ApolloWorld}.
 *
 * @since 1.2.0
 */
@AllArgsConstructor
public final class MinestomApolloWorld implements ApolloWorld, ForwardingRecipients {

    private final Instance instance;

    @Override
    public String getName() {
        return this.instance.getUuid().toString();
    }

    @Override
    public Collection<ApolloPlayer> getPlayers() {
        ApolloPlayerManager playerManager = Apollo.getPlayerManager();
        Set<Player> players = this.instance.getPlayers();
        List<ApolloPlayer> apolloPlayers = new ArrayList<>(players.size());

        for (Player player : players) {
            playerManager.getPlayer(player.getUuid())
                .ifPresent(apolloPlayers::add);
        }

        return apolloPlayers;
    }

    @Override
    public Iterable<? extends Recipients> recipients() {
        return this.getPlayers();
    }

}

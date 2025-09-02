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
package com.lunarclient.apollo.wrapper;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.recipients.ForwardingRecipients;
import com.lunarclient.apollo.recipients.Recipients;
import com.lunarclient.apollo.world.ApolloWorld;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import net.minestom.server.instance.Instance;

/**
 * The Bukkit implementation of {@link ApolloWorld}.
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
        return this.instance.getPlayers().stream()
            .map(player -> Apollo.getPlayerManager().getPlayer(player.getUuid()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());
    }

    @Override
    public Iterable<? extends Recipients> recipients() {
        return this.getPlayers();
    }

}

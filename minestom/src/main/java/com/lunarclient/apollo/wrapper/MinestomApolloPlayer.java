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
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.world.ApolloWorld;
import java.util.Optional;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.audience.Audience;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;
import org.jetbrains.annotations.NotNull;

/**
 * The Minestom implementation of {@link ApolloPlayer}.
 *
 * @since 1.1.9
 */
@Getter
@RequiredArgsConstructor
public final class MinestomApolloPlayer extends AbstractApolloPlayer {

    private final Player player;

    @Override
    public UUID getUniqueId() {
        return this.player.getUuid();
    }

    @Override
    public String getName() {
        return this.player.getUsername();
    }

    @Override
    public Optional<ApolloWorld> getWorld() {
        Instance instance = this.player.getInstance();

        return Apollo.getWorldManager().getWorld(instance.getUuid().toString());
    }

    @Override
    public Optional<ApolloLocation> getLocation() {
        Pos position = this.player.getPosition();

        return Optional.of(ApolloLocation.builder()
            .world(this.player.getInstance().getUuid().toString())
            .x(position.x())
            .y(position.y())
            .z(position.z())
            .build());
    }

    @Override
    public boolean hasPermission(String permissionNode) {
        throw new UnsupportedOperationException("Permission checks are not supported in Minestom.");
    }

    @Override
    public void sendPacket(byte[] messages) {
        this.player.sendPluginMessage(ApolloManager.PLUGIN_MESSAGE_CHANNEL, messages);
    }

    @Override
    public @NotNull Audience audience() {
        return this.player;
    }
}

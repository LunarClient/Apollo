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
import com.lunarclient.apollo.ApolloBukkitPlatform;
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.world.ApolloWorld;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * The Bukkit implementation of {@link ApolloPlayer}.
 *
 * @since 1.0.0
 */
@AllArgsConstructor
public final class BukkitApolloPlayer extends AbstractApolloPlayer {

    private final Player player;

    @Override
    public UUID getUniqueId() {
        return this.player.getUniqueId();
    }

    @Override
    public String getName() {
        return this.player.getName();
    }

    @Override
    public Optional<ApolloWorld> getWorld() {
        World world = this.player.getWorld();

        return Apollo.getWorldManager().getWorld(world.getName());
    }

    @Override
    public Optional<ApolloLocation> getLocation() {
        Location location = this.player.getLocation();

        return Optional.of(ApolloLocation.builder()
            .world(location.getWorld().getName())
            .x(location.getX())
            .y(location.getY())
            .z(location.getZ())
            .build());
    }

    @Override
    public boolean hasPermission(String permissionNode) {
        return this.player.hasPermission(permissionNode);
    }

    @Override
    public void sendPacket(byte[] messages) {
        this.player.sendPluginMessage(ApolloBukkitPlatform.getInstance().getPlugin(), ApolloManager.PLUGIN_MESSAGE_CHANNEL, messages);
    }

}

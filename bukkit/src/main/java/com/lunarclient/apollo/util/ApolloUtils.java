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
package com.lunarclient.apollo.util;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayerManager;
import com.lunarclient.apollo.recipients.Recipients;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Utility class for converting objects to and from
 * their corresponding Bukkit representations with
 * additional helper methods for easier integration.
 *
 * @since 1.0.0
 */
public final class ApolloUtils {

    /**
     * Runs a specified operation for a {@link Player}.
     *
     * @param player         the player.
     * @param playerConsumer the operation to be performed.
     * @since 1.0.0
     */
    public static void runForPlayer(Player player, Consumer<ApolloPlayer> playerConsumer) {
        runForPlayer(player.getUniqueId(), playerConsumer);
    }

    /**
     * Runs a specified operation for a {@link ApolloPlayer} from the provided {@link UUID}.
     *
     * @param playerUuid     the player.
     * @param playerConsumer the operation to be performed.
     * @since 1.0.0
     */
    public static void runForPlayer(UUID playerUuid, Consumer<ApolloPlayer> playerConsumer) {
        Apollo.getPlayerManager().getPlayer(playerUuid).ifPresent(playerConsumer);
    }

    /**
     * Converts a {@link Collection} of {@link Player}s to an {@link Recipients}.
     *
     * @param players  the players.
     * @return the recipients object containing the converted ApolloPlayer objects.
     * @since 1.0.0
     */
    public static Recipients getRecipientsFrom(Collection<Player> players) {
        ApolloPlayerManager playerManager = Apollo.getPlayerManager();
        List<ApolloPlayer> apolloPlayers = players.stream()
            .map(player -> playerManager.getPlayer(player.getUniqueId()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());

        return Recipients.of(apolloPlayers);
    }

    /**
     * Converts a {@link Location} to an {@link ApolloLocation} object.
     *
     * @param location the location.
     * @return the converted apollo location object.
     * @since 1.0.0
     */
    public static ApolloLocation toApolloLocation(Location location) {
        return ApolloLocation.builder()
            .world(location.getWorld().getName())
            .x(location.getX())
            .y(location.getY())
            .z(location.getZ())
            .build();
    }

    /**
     * Converts a {@link Location} to an {@link ApolloBlockLocation} object.
     *
     * @param location the location.
     * @return the converted apollo block location object.
     * @since 1.0.0
     */
    public static ApolloBlockLocation toApolloBlockLocation(Location location) {
        return ApolloBlockLocation.builder()
            .world(location.getWorld().getName())
            .x(location.getBlockX())
            .y(location.getBlockY())
            .z(location.getBlockZ())
            .build();
    }

    /**
     * Converts a {@link ApolloLocation} to an {@link Location} object.
     *
     * @param location the apollo location.
     * @return the converted location object.
     * @since 1.0.0
     */
    public static Location toBukkitLocation(ApolloLocation location) {
        return new Location(
            Bukkit.getWorld(location.getWorld()),
            location.getX(),
            location.getY(),
            location.getZ()
        );
    }

    /**
     * Converts a {@link ApolloBlockLocation} to an {@link Location} object.
     *
     * @param location the apollo block location.
     * @return the converted location object.
     * @since 1.0.0
     */
    public static Location toBukkitLocation(ApolloBlockLocation location) {
        return new Location(
            Bukkit.getWorld(location.getWorld()),
            location.getX(),
            location.getY(),
            location.getZ()
        );
    }

    private ApolloUtils() {
    }

}

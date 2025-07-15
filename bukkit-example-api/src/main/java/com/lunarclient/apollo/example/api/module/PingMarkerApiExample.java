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
package com.lunarclient.apollo.example.api.module;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.BukkitApollo;
import com.lunarclient.apollo.common.icon.SimpleResourceLocationIcon;
import com.lunarclient.apollo.example.module.impl.PingMarkerExample;
import com.lunarclient.apollo.module.pingmarker.PingMarker;
import com.lunarclient.apollo.module.pingmarker.PingMarkerModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import java.awt.Color;
import java.time.Duration;
import java.util.Optional;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class PingMarkerApiExample extends PingMarkerExample {

    private final PingMarkerModule pingMarkerModule = Apollo.getModuleManager().getModule(PingMarkerModule.class);

    @Override
    public void displayMarkerResourceExample(Player player) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(player.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            Location location = this.findTargetLocation(player);

            if (location == null) {
                return;
            }

            this.pingMarkerModule.displayMarker(Recipients.ofWorld(apolloPlayer), PingMarker.builder()
                .id(UUID.randomUUID())
                .type(null) // TODO: add another example with a specific type
                .location(BukkitApollo.toApolloLocation(location))
                .color(Color.WHITE)
                .icon(SimpleResourceLocationIcon.builder()
                    .resourceLocation("lunar:icons/fluent-black-circle-icon.svg")
                    .size(12)
                    .build()
                )
                .duration(Duration.ofSeconds(5))
                .focus(true)
                .build());
        });

    }

    private Location findTargetLocation(Player player) {
        Block targetBlock = player.getTargetBlockExact(30);

        if (targetBlock == null) {
            return null;
        }

        return targetBlock.getLocation().add(0.5D, 0.5D, 0.5D);
    }

}

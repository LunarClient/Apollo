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
package com.lunarclient.apollo.example.api.mods;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.event.ApolloListener;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.Listen;
import com.lunarclient.apollo.event.mods.ApolloUpdateModOptionEvent;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.mods.ModStatus;
import com.lunarclient.apollo.mods.impl.ModFreelook;
import com.lunarclient.apollo.mods.impl.ModMinimap;
import com.lunarclient.apollo.mods.impl.ModWaypoints;
import java.util.Objects;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class ApolloModStatusExample implements ApolloListener, Listener {

    public ApolloModStatusExample(ApolloExamplePlugin plugin) {
        EventBus.getBus().register(this);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Listen
    private void onApolloUpdateModOption(ApolloUpdateModOptionEvent event) {
        event.getPlayer().sendMessage(Component.text(event.getOption().getKey())
            .append(Component.text(" was updated to "))
            .append(Component.text(Objects.toString(event.getValue()))));
    }

    @EventHandler(ignoreCancelled = true)
    private void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        Apollo.getPlayerManager().getPlayer(player.getUniqueId()).ifPresent(apolloPlayer -> {
            ModStatus status = apolloPlayer.getModStatus();

            apolloPlayer.sendMessage(Component.text("Waypoints Enabled: ")
                .append(Component.text(status.get(ModWaypoints.ENABLED))));

            apolloPlayer.sendMessage(Component.text("Freelook Invert Yaw: ")
                .append(Component.text(status.get(ModFreelook.INVERT_YAW))));

            apolloPlayer.sendMessage(Component.text("Freelook Invert Pitch: ")
                .append(Component.text(status.get(ModFreelook.INVERT_PITCH))));

            apolloPlayer.sendMessage(Component.text("Minimap Scale: ")
                .append(Component.text(status.get(ModMinimap.SCALE))));
        });
    }

}

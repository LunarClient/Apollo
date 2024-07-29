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
package com.lunarclient.lightweight.example;

import com.lunarclient.apollo.common.v1.BlockLocation;
import com.lunarclient.apollo.common.v1.Color;
import com.lunarclient.apollo.configurable.v1.ConfigurableSettings;
import com.lunarclient.apollo.configurable.v1.OverrideConfigurableSettingsMessage;
import com.lunarclient.apollo.waypoint.v1.DisplayWaypointMessage;
import com.lunarclient.apollo.waypoint.v1.ResetWaypointsMessage;
import com.lunarclient.lightweight.ProtobufUtils;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.event.player.PlayerUnregisterChannelEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class LightweightExamplePlugin extends JavaPlugin implements Listener {

    private static final String LIGHTWEIGHT_CHANNEL = "apollo:json"; // Used for sending feature packets
    private static final String REGISTER_CHANNEL = "lunar:apollo"; // Used for detecting whether the player supports Apollo

    private final Set<UUID> playersRunningApollo = new HashSet<>();

    @Override
    public void onEnable() {
        // Register the normal apollo channel for player detection
        Bukkit.getMessenger().registerIncomingPluginChannel(this, REGISTER_CHANNEL, (s, player, bytes) -> { });

        // Register the lightweight channel for apollo features
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, LIGHTWEIGHT_CHANNEL);

        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    private void onRegisterChannel(PlayerRegisterChannelEvent event) {
        // Note that this channel name is different from the lightweight one
        if (!event.getChannel().equalsIgnoreCase(REGISTER_CHANNEL)) {
            return;
        }

        Player player = event.getPlayer();
        this.playersRunningApollo.add(player.getUniqueId());

        this.displayWaypoint(player);
    }

    @EventHandler
    private void onUnregisterChannel(PlayerUnregisterChannelEvent event) {
        // Note that this channel name is different from the lightweight one
        if (!event.getChannel().equalsIgnoreCase(REGISTER_CHANNEL)) {
            return;
        }

        Player player = event.getPlayer();
        this.playersRunningApollo.remove(player.getUniqueId());

        this.resetWaypoints(player);
    }

    @EventHandler(ignoreCancelled = true)
    private void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if (!this.isRunningApollo(player.getUniqueId())) {
            return;
        }

        player.getInventory().addItem(new ItemStack(Material.COOKIE));
    }

    public boolean isRunningApollo(UUID uuid) {
        return this.playersRunningApollo.contains(uuid);
    }

    public void displayWaypoint(Player player) {
        // Create a message to enable a specific module
        OverrideConfigurableSettingsMessage enableModuleMessage = OverrideConfigurableSettingsMessage.newBuilder()
            .addConfigurableSettings(
                ConfigurableSettings.newBuilder()
                    .setApolloModule("waypoint")
                    .setEnable(true)
                    .build()
            ).build();

        // Create the display waypoint message
        DisplayWaypointMessage displayWaypointMessage = DisplayWaypointMessage.newBuilder()
            .setName("KoTH")
            .setLocation(
                BlockLocation.newBuilder()
                    .setWorld("world")
                    .setX(150)
                    .setY(100)
                    .setZ(-150)
                    .build())
            .setColor(
                Color.newBuilder()
                    .setColor(255)
                    .build())
            .setPreventRemoval(true)
            .build();

        player.sendPluginMessage(this, LIGHTWEIGHT_CHANNEL, ProtobufUtils.messageToString(enableModuleMessage).getBytes());
        player.sendPluginMessage(this, LIGHTWEIGHT_CHANNEL, ProtobufUtils.messageToString(displayWaypointMessage).getBytes());
    }

    public void resetWaypoints(Player player) {
        ResetWaypointsMessage resetWaypointsMessage = ResetWaypointsMessage.getDefaultInstance();
        player.sendPluginMessage(this, LIGHTWEIGHT_CHANNEL, ProtobufUtils.messageToString(resetWaypointsMessage).getBytes());
    }

}

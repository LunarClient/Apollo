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
package com.lunarclient.apollo.listener;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.event.ApolloListener;
import com.lunarclient.apollo.event.ApolloReceivePacketEvent;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.Listen;
import com.lunarclient.apollo.player.ApolloPlayerManagerImpl;
import com.lunarclient.apollo.player.v1.PlayerHandshakeMessage;
import com.lunarclient.apollo.version.ApolloVersionManager;
import com.lunarclient.apollo.wrapper.BukkitApolloPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.event.player.PlayerUnregisterChannelEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Handles registration and un-registration of Apollo players.
 *
 * @since 1.0.0
 */
public final class ApolloPlayerListener implements Listener, ApolloListener {

    /**
     * Constructs the {@link ApolloPlayerListener}.
     *
     * @param plugin the plugin
     * @since 1.0.6
     */
    public ApolloPlayerListener(JavaPlugin plugin) {
        EventBus.getBus().register(this);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onRegisterChannel(PlayerRegisterChannelEvent event) {
        if (!event.getChannel().equalsIgnoreCase(ApolloManager.PLUGIN_MESSAGE_CHANNEL)) {
            return;
        }

        ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).addPlayer(new BukkitApolloPlayer(event.getPlayer()));
    }

    @EventHandler
    private void onUnregisterChannel(PlayerUnregisterChannelEvent event) {
        if (!event.getChannel().equalsIgnoreCase(ApolloManager.PLUGIN_MESSAGE_CHANNEL)) {
            return;
        }

        ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).removePlayer(event.getPlayer().getUniqueId());
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).removePlayer(event.getPlayer().getUniqueId());
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        if (!ApolloVersionManager.NEEDS_UPDATE) {
            return;
        }

        Player player = event.getPlayer();
        if (player.isOp()) {
            player.sendMessage(ChatColor.YELLOW + ApolloVersionManager.UPDATE_MESSAGE);
        }
    }

    @Listen
    private void onApolloReceivePacket(ApolloReceivePacketEvent event) {
        event.unpack(PlayerHandshakeMessage.class).ifPresent(message -> {
            ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).handlePlayerHandshake(event.getPlayer(), message);
        });
    }

}

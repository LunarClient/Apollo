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
package com.lunarclient.apollo.example.api.debug.payload;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.event.ApolloListener;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.Listen;
import com.lunarclient.apollo.event.player.ApolloPlayerHandshakeEvent;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PayloadListener implements Listener, ApolloListener {

    private final Set<UUID> handshakeUuids;

    public PayloadListener() {
        this.handshakeUuids = new HashSet<>();

        EventBus.getBus().register(this);
        Bukkit.getPluginManager().registerEvents(this, ApolloExamplePlugin.getInstance());
    }

    private void checkReceivedPackets(Player player) {
        Bukkit.getScheduler().runTaskLater(ApolloExamplePlugin.getInstance(), () -> {
            UUID uuid = player.getUniqueId();

            boolean register = Apollo.getPlayerManager().hasSupport(uuid);
            boolean handshake = this.handshakeUuids.contains(uuid);

            if (register && !handshake) {
                player.sendMessage(ChatColor.RED + "Received Apollo register but not the handshake!");
            } else if (!register && !handshake) {
                player.sendMessage(ChatColor.RED + "Failed to receive Apollo register and handshake!");
            }
        }, 20 * 3);
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        this.checkReceivedPackets(event.getPlayer());
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        this.handshakeUuids.remove(event.getPlayer().getUniqueId());
    }

    @Listen
    private void onApolloPlayerHandshake(ApolloPlayerHandshakeEvent event) {
        this.handshakeUuids.add(event.getPlayer().getUniqueId());
    }

}

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
package com.lunarclient.apollo.example.proto.listeners;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.proto.ProtobufPacketUtil;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import com.lunarclient.apollo.player.v1.UpdatePlayerWorldMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.plugin.messaging.Messenger;

public class ApolloPlayerProtoListener implements Listener {

    private final ApolloExamplePlugin plugin;

    private final Set<UUID> playersRunningApollo = new HashSet<>();

    public ApolloPlayerProtoListener(ApolloExamplePlugin plugin) {
        this.plugin = plugin;

        Messenger messenger = Bukkit.getServer().getMessenger();
        messenger.registerOutgoingPluginChannel(plugin, "lunar:apollo");
        messenger.registerIncomingPluginChannel(plugin, "lunar:apollo", new ApolloRoundtripProtoListener());
        messenger.registerIncomingPluginChannel(plugin, "lunar:apollo", new ApolloPacketReceiveProtoListener());

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void disable() {
        this.playersRunningApollo.clear();

        Messenger messenger = Bukkit.getServer().getMessenger();
        messenger.unregisterOutgoingPluginChannel(this.plugin, "lunar:apollo");
        messenger.unregisterIncomingPluginChannel(this.plugin, "lunar:apollo");

        HandlerList.unregisterAll(this);
    }

    @EventHandler
    private void onRegisterChannel(PlayerRegisterChannelEvent event) {
        if (!event.getChannel().equalsIgnoreCase("lunar:apollo")) {
            return;
        }

        this.onApolloRegister(event.getPlayer());
    }

    @EventHandler
    private void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();

        // Sending the player's world name to the client is required for some modules
        ProtobufPacketUtil.sendPacket(player, this.createUpdatePlayerWorldMessage(player));
    }

    private UpdatePlayerWorldMessage createUpdatePlayerWorldMessage(Player player) {
        return UpdatePlayerWorldMessage.newBuilder()
            .setWorld(player.getWorld().getName())
            .build();
    }

    private boolean isPlayerRunningApollo(Player player) {
        return this.playersRunningApollo.contains(player.getUniqueId());
    }

    private void onApolloRegister(Player player) {
        ProtobufPacketUtil.enableModules(player);

        // Sending the player's world name to the client is required for some modules
        ProtobufPacketUtil.sendPacket(player, this.createUpdatePlayerWorldMessage(player));

        this.playersRunningApollo.add(player.getUniqueId());
        player.sendMessage("You are using LunarClient!");
    }

}

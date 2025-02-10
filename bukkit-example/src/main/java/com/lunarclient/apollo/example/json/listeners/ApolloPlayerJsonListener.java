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
package com.lunarclient.apollo.example.json.listeners;

import com.google.gson.JsonObject;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.json.JsonPacketUtil;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.plugin.messaging.Messenger;

public class ApolloPlayerJsonListener implements Listener {

    private final ApolloExamplePlugin plugin;

    private final Set<UUID> playersRunningApollo = new HashSet<>();

    public ApolloPlayerJsonListener(ApolloExamplePlugin plugin) {
        this.plugin = plugin;

        Messenger messenger = Bukkit.getServer().getMessenger();
        messenger.registerIncomingPluginChannel(plugin, "lunar:apollo", (s, player, bytes) -> { });
        messenger.registerIncomingPluginChannel(plugin, "apollo:json", (s, player, bytes) -> { });
        messenger.registerOutgoingPluginChannel(plugin, "apollo:json");

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void disable() {
        this.playersRunningApollo.clear();

        Messenger messenger = Bukkit.getServer().getMessenger();
        messenger.unregisterIncomingPluginChannel(this.plugin, "lunar:apollo");
        messenger.unregisterIncomingPluginChannel(this.plugin, "apollo:json");
        messenger.unregisterOutgoingPluginChannel(this.plugin, "apollo:json");

        HandlerList.unregisterAll(this);
    }

    @EventHandler
    private void onRegisterChannel(PlayerRegisterChannelEvent event) {
        if (!event.getChannel().equalsIgnoreCase("lunar:apollo")) {
            return;
        }

        Player player = event.getPlayer();
        JsonPacketUtil.enableModules(player);

        // Sending the player's world name to the client is required for some modules
        JsonPacketUtil.sendPacket(player, this.createUpdatePlayerWorldMessage(player));

        this.playersRunningApollo.add(player.getUniqueId());
        player.sendMessage("You are using LunarClient!");
    }

    @EventHandler
    private void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();

        // Sending the player's world name to the client is required for some modules
        JsonPacketUtil.sendPacket(player, this.createUpdatePlayerWorldMessage(player));
    }

    private JsonObject createUpdatePlayerWorldMessage(Player player) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.player.v1.UpdatePlayerWorldMessage");
        message.addProperty("world", player.getWorld().getName());
        return message;
    }

    private boolean isPlayerRunningApollo(Player player) {
        return this.playersRunningApollo.contains(player.getUniqueId());
    }

}

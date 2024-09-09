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
package com.lunarclient.apollo.example.listeners.json;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.utilities.JsonPacketUtil;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.plugin.messaging.Messenger;

public class ApolloPlayerJsonListener implements Listener {

    private static final List<String> APOLLO_MODULES = Arrays.asList("limb", "beam", "border", "chat", "colored_fire", "combat", "cooldown",
        "entity", "glow", "hologram", "mod_setting", "nametag", "nick_hider", "notification", "packet_enrichment", "rich_presence",
        "server_rule", "staff_mod", "stopwatch", "team", "title", "tnt_countdown", "transfer", "vignette", "waypoint"
    );

    private static final String REGISTER_CHANNEL = "lunar:apollo"; // Used for detecting whether the player supports Apollo
    private static final String LIGHTWEIGHT_CHANNEL = "apollo:json"; // Used for sending and receiving feature packets

    private final Set<UUID> playersRunningApollo = new HashSet<>();

    public ApolloPlayerJsonListener(ApolloExamplePlugin plugin) {
        Messenger messenger = Bukkit.getServer().getMessenger();
        messenger.registerIncomingPluginChannel(plugin, REGISTER_CHANNEL, (s, player, bytes) -> { });
        messenger.registerIncomingPluginChannel(plugin, LIGHTWEIGHT_CHANNEL, (s, player, bytes) -> { });
        messenger.registerOutgoingPluginChannel(plugin, LIGHTWEIGHT_CHANNEL);

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onRegisterChannel(PlayerRegisterChannelEvent event) {
        if (!event.getChannel().equalsIgnoreCase(REGISTER_CHANNEL)) {
            return;
        }

        this.onApolloRegister(event.getPlayer());
    }

    private boolean isPlayerRunningApollo(Player player) {
        return this.playersRunningApollo.contains(player.getUniqueId());
    }

    private void onApolloRegister(Player player) {
        JsonPacketUtil.enableModules(player, APOLLO_MODULES);

        this.playersRunningApollo.add(player.getUniqueId());
        player.sendMessage("You are using LunarClient!");
    }

}

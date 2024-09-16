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
package com.lunarclient.apollo.example.listeners.proto;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.protobuf.ListValue;
import com.google.protobuf.Value;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.utilities.ProtobufPacketUtil;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.plugin.messaging.Messenger;

public class ApolloPlayerProtoListener implements Listener {

    private static final List<String> APOLLO_MODULES = Arrays.asList("limb", "beam", "border", "chat", "colored_fire", "combat", "cooldown",
        "entity", "glow", "hologram", "mod_setting", "nametag", "nick_hider", "notification", "packet_enrichment", "rich_presence",
        "server_rule", "staff_mod", "stopwatch", "team", "title", "tnt_countdown", "transfer", "vignette", "waypoint"
    );

    // Module Id -> Option key -> Value
    private static final Table<String, String, Value> PROPERTIES = HashBasedTable.create();

    static {
        // Module Options that the client needs to notified about, these properties are sent with the enable module packet
        // While using the Apollo plugin this would be equivalent to modifying the config.yml
        PROPERTIES.put("combat", "disable-miss-penalty", Value.newBuilder().setBoolValue(false).build());
        PROPERTIES.put("server_rule", "competitive-game", Value.newBuilder().setBoolValue(false).build());
        PROPERTIES.put("server_rule", "competitive-commands", Value.newBuilder().setListValue(
            ListValue.newBuilder().addAllValues(Arrays.asList(
                Value.newBuilder().setStringValue("/server").build(),
                Value.newBuilder().setStringValue("/servers").build(),
                Value.newBuilder().setStringValue("/hub").build()))
                    .build()
        ).build());
        PROPERTIES.put("server_rule", "disable-shaders", Value.newBuilder().setBoolValue(false).build());
        PROPERTIES.put("server_rule", "disable-chunk-reloading", Value.newBuilder().setBoolValue(false).build());
        PROPERTIES.put("server_rule", "disable-broadcasting", Value.newBuilder().setBoolValue(false).build());
        PROPERTIES.put("server_rule", "anti-portal-traps", Value.newBuilder().setBoolValue(true).build());
        PROPERTIES.put("server_rule", "override-brightness", Value.newBuilder().setBoolValue(false).build());
        PROPERTIES.put("server_rule", "brightness", Value.newBuilder().setNumberValue(50).build());
        PROPERTIES.put("server_rule", "override-nametag-render-distance", Value.newBuilder().setBoolValue(false).build());
        PROPERTIES.put("server_rule", "nametag-render-distance", Value.newBuilder().setNumberValue(64).build());
        PROPERTIES.put("server_rule", "override-max-chat-length", Value.newBuilder().setBoolValue(false).build());
        PROPERTIES.put("server_rule", "max-chat-length", Value.newBuilder().setNumberValue(256).build());
        PROPERTIES.put("tnt_countdown", "tnt-ticks", Value.newBuilder().setNumberValue(80).build());
        PROPERTIES.put("waypoint", "server-handles-waypoints", Value.newBuilder().setBoolValue(false).build());
    }

    private static final String APOLLO_CHANNEL = "lunar:apollo"; // Used for detecting whether the player supports Apollo

    private final ApolloExamplePlugin plugin;

    private final Set<UUID> playersRunningApollo = new HashSet<>();

    public ApolloPlayerProtoListener(ApolloExamplePlugin plugin) {
        this.plugin = plugin;

        Messenger messenger = Bukkit.getServer().getMessenger();
        messenger.registerOutgoingPluginChannel(plugin, APOLLO_CHANNEL);
        messenger.registerIncomingPluginChannel(plugin, APOLLO_CHANNEL, new ApolloRoundtripProtoListener());
        messenger.registerIncomingPluginChannel(plugin, APOLLO_CHANNEL, new ApolloPacketReceiveProtoListener());

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void disable() {
        this.playersRunningApollo.clear();

        Messenger messenger = Bukkit.getServer().getMessenger();
        messenger.unregisterOutgoingPluginChannel(this.plugin, APOLLO_CHANNEL);
        messenger.unregisterIncomingPluginChannel(this.plugin, APOLLO_CHANNEL);

        HandlerList.unregisterAll(this);
    }

    @EventHandler
    private void onRegisterChannel(PlayerRegisterChannelEvent event) {
        if (!event.getChannel().equalsIgnoreCase(APOLLO_CHANNEL)) {
            return;
        }

        this.onApolloRegister(event.getPlayer());
    }

    private boolean isPlayerRunningApollo(Player player) {
        return this.playersRunningApollo.contains(player.getUniqueId());
    }

    private void onApolloRegister(Player player) {
        ProtobufPacketUtil.enableModules(player, APOLLO_MODULES, PROPERTIES);

        this.playersRunningApollo.add(player.getUniqueId());
        player.sendMessage("You are using LunarClient!");
    }

}

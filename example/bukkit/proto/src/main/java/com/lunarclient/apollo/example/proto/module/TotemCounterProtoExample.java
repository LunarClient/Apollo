/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2026 Moonsworth
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
package com.lunarclient.apollo.example.proto.module;

import com.google.protobuf.Value;
import com.lunarclient.apollo.configurable.v1.ConfigurableSettings;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.module.impl.TotemCounterExample;
import com.lunarclient.apollo.example.proto.util.ProtobufPacketUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufUtil;
import com.lunarclient.apollo.example.util.ServerUtil;
import com.lunarclient.apollo.totemcounter.v1.OverrideTotemCountersMessage;
import com.lunarclient.apollo.totemcounter.v1.ResetTotemCounterMessage;
import com.lunarclient.apollo.totemcounter.v1.ResetTotemCountersMessage;
import com.lunarclient.apollo.totemcounter.v1.TotemCounter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class TotemCounterProtoExample extends TotemCounterExample implements Listener {

    private final Map<UUID, Integer> totemPops = new ConcurrentHashMap<>();

    public TotemCounterProtoExample() {
        if (ServerUtil.hasEntityResurrectEvent()) {
            Bukkit.getPluginManager().registerEvents(this, ApolloExamplePlugin.getInstance());
        }
    }

    @Override
    public void overrideTotemCounterExample(Player viewer) {
        TotemCounter totemCounter = TotemCounter.newBuilder()
            .setPlayerUuid(ProtobufUtil.createUuidProto(viewer.getUniqueId()))
            .setPops(3)
            .build();

        OverrideTotemCountersMessage message = OverrideTotemCountersMessage.newBuilder()
            .addTotemCounters(totemCounter)
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void overrideTotemCountersExample(Player viewer) {
        List<TotemCounter> totemCounters = Bukkit.getOnlinePlayers()
            .stream().map(player -> TotemCounter.newBuilder()
                .setPlayerUuid(ProtobufUtil.createUuidProto(player.getUniqueId()))
                .setPops(2)
                .build())
            .collect(Collectors.toList());

        OverrideTotemCountersMessage message = OverrideTotemCountersMessage.newBuilder()
            .addAllTotemCounters(totemCounters)
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetTotemCounterExample(Player viewer) {
        ResetTotemCounterMessage message = ResetTotemCounterMessage.newBuilder()
            .addPlayerUuids(ProtobufUtil.createUuidProto(viewer.getUniqueId()))
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetTotemCountersExample(Player viewer) {
        ResetTotemCountersMessage message = ResetTotemCountersMessage.getDefaultInstance();
        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void setDisableLocalTracking(boolean value) {
        Map<String, Value> properties = new HashMap<>();
        properties.put("disable-local-tracking", Value.newBuilder().setBoolValue(value).build());

        ConfigurableSettings settings = ProtobufPacketUtil.createModuleMessage("totem_counter", properties);
        ProtobufPacketUtil.broadcastPacket(settings);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    private void onEntityResurrect(EntityResurrectEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();
        int pops = this.totemPops.merge(player.getUniqueId(), 1, Integer::sum);

        // Pops are absolute, send the new total instead of an increment
        TotemCounter totemCounter = TotemCounter.newBuilder()
            .setPlayerUuid(ProtobufUtil.createUuidProto(player.getUniqueId()))
            .setPops(pops)
            .build();

        OverrideTotemCountersMessage message = OverrideTotemCountersMessage.newBuilder()
            .addTotemCounters(totemCounter)
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    private void onPlayerDeath(PlayerDeathEvent event) {
        this.resetTotemPops(event.getEntity());
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        this.resetTotemPops(event.getPlayer());
    }

    private void resetTotemPops(Player player) {
        if (this.totemPops.remove(player.getUniqueId()) != null) {
            ResetTotemCounterMessage message = ResetTotemCounterMessage.newBuilder()
                .addPlayerUuids(ProtobufUtil.createUuidProto(player.getUniqueId()))
                .build();

            ProtobufPacketUtil.broadcastPacket(message);
        }
    }

}

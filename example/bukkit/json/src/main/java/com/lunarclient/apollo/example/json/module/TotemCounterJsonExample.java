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
package com.lunarclient.apollo.example.json.module;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.json.util.JsonPacketUtil;
import com.lunarclient.apollo.example.json.util.JsonUtil;
import com.lunarclient.apollo.example.module.impl.TotemCounterExample;
import com.lunarclient.apollo.example.util.ServerUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class TotemCounterJsonExample extends TotemCounterExample implements Listener {

    private final Map<UUID, Integer> totemPops = new ConcurrentHashMap<>();

    public TotemCounterJsonExample() {
        if (ServerUtil.hasEntityResurrectEvent()) {
            Bukkit.getPluginManager().registerEvents(this, ApolloExamplePlugin.getInstance());
        }
    }

    @Override
    public void overrideTotemCounterExample(Player viewer) {
        JsonObject totemCounter = new JsonObject();
        totemCounter.add("player_uuid", JsonUtil.createUuidObject(viewer.getUniqueId()));
        totemCounter.addProperty("pops", 3);

        JsonArray totemCounters = new JsonArray();
        totemCounters.add(totemCounter);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.totemcounter.v1.OverrideTotemCountersMessage");
        message.add("totem_counters", totemCounters);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void overrideTotemCountersExample(Player viewer) {
        JsonArray totemCounters = new JsonArray();

        for (Player player : Bukkit.getOnlinePlayers()) {
            JsonObject totemCounter = new JsonObject();
            totemCounter.add("player_uuid", JsonUtil.createUuidObject(player.getUniqueId()));
            totemCounter.addProperty("pops", 2);

            totemCounters.add(totemCounter);
        }

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.totemcounter.v1.OverrideTotemCountersMessage");
        message.add("totem_counters", totemCounters);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetTotemCounterExample(Player viewer) {
        JsonArray playerUuids = new JsonArray();
        playerUuids.add(JsonUtil.createUuidObject(viewer.getUniqueId()));

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.totemcounter.v1.ResetTotemCounterMessage");
        message.add("player_uuids", playerUuids);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetTotemCountersExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.totemcounter.v1.ResetTotemCountersMessage");

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void setDisableLocalTracking(boolean value) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("disable-local-tracking", value);

        JsonObject message = JsonUtil.createEnableModuleObjectWithType("totem_counter", properties);
        JsonPacketUtil.broadcastPacket(message);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    private void onEntityResurrect(EntityResurrectEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();
        int pops = this.totemPops.merge(player.getUniqueId(), 1, Integer::sum);

        // Pops are absolute, send the new total instead of an increment
        JsonObject totemCounter = new JsonObject();
        totemCounter.add("player_uuid", JsonUtil.createUuidObject(player.getUniqueId()));
        totemCounter.addProperty("pops", pops);

        JsonArray totemCounters = new JsonArray();
        totemCounters.add(totemCounter);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.totemcounter.v1.OverrideTotemCountersMessage");
        message.add("totem_counters", totemCounters);

        JsonPacketUtil.broadcastPacket(message);
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
            JsonArray playerUuids = new JsonArray();
            playerUuids.add(JsonUtil.createUuidObject(player.getUniqueId()));

            JsonObject message = new JsonObject();
            message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.totemcounter.v1.ResetTotemCounterMessage");
            message.add("player_uuids", playerUuids);

            JsonPacketUtil.broadcastPacket(message);
        }
    }

}

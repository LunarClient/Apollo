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
package com.lunarclient.apollo.example.api.module;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.module.impl.TotemCounterExample;
import com.lunarclient.apollo.example.util.ServerUtil;
import com.lunarclient.apollo.module.totemcounter.TotemCounter;
import com.lunarclient.apollo.module.totemcounter.TotemCounterModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

public class TotemCounterApiExample extends TotemCounterExample implements Listener {

    private final TotemCounterModule totemCounterModule = Apollo.getModuleManager().getModule(TotemCounterModule.class);

    private final Map<UUID, Integer> totemPops = new ConcurrentHashMap<>();

    public TotemCounterApiExample() {
        if (ServerUtil.hasEntityResurrectEvent()) {
            Bukkit.getPluginManager().registerEvents(this, ApolloExamplePlugin.getInstance());
        }
    }

    @Override
    public void overrideTotemCounterExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.totemCounterModule.overrideTotemCounter(apolloPlayer, TotemCounter.builder()
                .playerUuid(viewer.getUniqueId())
                .pops(3)
                .build()
            );
        });
    }

    @Override
    public void overrideTotemCountersExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            List<TotemCounter> totemCounters = Bukkit.getOnlinePlayers()
                .stream().map(player -> TotemCounter.builder()
                    .playerUuid(player.getUniqueId())
                    .pops(2)
                    .build())
                .collect(Collectors.toList());

            this.totemCounterModule.overrideTotemCounters(apolloPlayer, totemCounters);
        });
    }

    @Override
    public void resetTotemCounterExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.totemCounterModule.resetTotemCounter(apolloPlayer, viewer.getUniqueId()));
    }

    @Override
    public void resetTotemCountersExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.totemCounterModule::resetTotemCounters);
    }

    @Override
    public void setDisableLocalTracking(boolean value) {
        this.totemCounterModule.getOptions().set(TotemCounterModule.DISABLE_LOCAL_TRACKING, value);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    private void onEntityResurrect(EntityResurrectEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();
        int pops = this.totemPops.merge(player.getUniqueId(), 1, Integer::sum);

        // Pops are absolute, send the new total instead of an increment
        this.totemCounterModule.overrideTotemCounter(Recipients.ofEveryone(), TotemCounter.builder()
            .playerUuid(player.getUniqueId())
            .pops(pops)
            .build()
        );
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
            this.totemCounterModule.resetTotemCounter(Recipients.ofEveryone(), player.getUniqueId());
        }
    }

}

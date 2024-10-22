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
package com.lunarclient.apollo.example.debug;

import com.google.common.collect.Maps;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.network.NetworkOptions;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class SpamPacketDebug implements Listener {

    private final Map<UUID, SpamPacketTask> players = Maps.newConcurrentMap();

    public SpamPacketDebug() {
        Bukkit.getPluginManager().registerEvents(this, ApolloExamplePlugin.getPlugin());
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        this.stop(event.getPlayer());
    }

    public void start(Player player, int amount, long delay, Runnable callback) {
        UUID playerIdentifier = player.getUniqueId();
        SpamPacketTask task = new SpamPacketTask(playerIdentifier, amount, delay, callback);
        this.players.put(playerIdentifier, task);
        task.start();
    }

    public void stop(Player player) {
        UUID playerIdentifier = player.getUniqueId();
        SpamPacketTask task = this.players.remove(playerIdentifier);
        if (task != null) {
            task.stop();
        }
    }

    public void stopAll() {
        for (SpamPacketTask task : this.players.values()) {
            task.stop();
        }

        this.players.clear();
    }

    public static class SpamPacketTask implements Runnable {
        private final UUID player;
        private final int amount;
        private final long delay;
        private final Runnable onComplete;

        private volatile boolean running;
        private int sent;

        public SpamPacketTask(UUID player, int amount, long delay, Runnable onComplete) {
            this.player = player;
            this.amount = amount;
            this.delay = delay;
            this.onComplete = onComplete;
        }

        public void start() {
            this.running = true;

            Thread debugThread = new Thread(this);
            debugThread.setDaemon(true);
            debugThread.start();
        }

        public void stop() {
            this.running = false;
        }

        @Override
        public void run() {
            while (this.running) {
                if (this.sent > this.amount) {
                    this.onComplete.run();
                    this.running = false;
                    break;
                }

                Apollo.getPlayerManager().getPlayer(this.player)
                    .ifPresent(apolloPlayer -> NetworkOptions.sendOptions(Apollo.getModuleManager().getModules(), true, apolloPlayer));

                this.sent++;

                try {
                    Thread.sleep(this.delay);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
}

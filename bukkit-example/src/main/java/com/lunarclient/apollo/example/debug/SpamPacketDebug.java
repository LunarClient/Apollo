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

import com.google.common.collect.Sets;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.network.NetworkOptions;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class SpamPacketDebug implements Listener {

    private final Set<UUID> players = Sets.newConcurrentHashSet();

    private final SpamPacketTask debug;

    public SpamPacketDebug() {
        this.debug = new SpamPacketTask();

        Bukkit.getPluginManager().registerEvents(this, ApolloExamplePlugin.getPlugin());
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        this.pause(event.getPlayer());
    }

    public void start(Player player) {
        this.players.add(player.getUniqueId());

        if(!this.debug.running) {
            this.debug.start();
        }
    }

    public void pause(Player player) {
        this.players.remove(player.getUniqueId());
    }

    public void stop() {
        this.players.clear();
    }

    public class SpamPacketTask implements Runnable {
        private boolean running;

        public void start() {
            this.running = true;

            Thread debugThread = new Thread(this);
            debugThread.setDaemon(true);
            debugThread.start();
        }

        @Override
        public void run() {
            while (this.running) {
                Set<UUID> players = SpamPacketDebug.this.players;
                if (players.isEmpty()) {
                    this.running = false;
                }

                for (UUID uuid : players) {
                    Apollo.getPlayerManager().getPlayer(uuid)
                        .ifPresent(apolloPlayer -> NetworkOptions.sendOptions(Apollo.getModuleManager().getModules(), true, apolloPlayer));
                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
}

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
package com.lunarclient.apollo.listener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.metadata.BukkitMetadataManager;
import com.lunarclient.apollo.util.ByteBufUtil;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.PluginMessageListener;

/**
 * Handles Apollo metadata listeners.
 *
 * @since 1.1.9
 */
public final class ApolloMetadataListener implements Listener {

    private final JavaPlugin plugin;

    /**
     * Constructs the {@link ApolloMetadataListener}.
     *
     * @param plugin the plugin
     * @since 1.1.9
     */
    public ApolloMetadataListener(JavaPlugin plugin) {
        this.plugin = plugin;

        this.registerBrandListener();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onResourcePackStatus(PlayerResourcePackStatusEvent event) {
        String status = event.getStatus().name();
        BukkitMetadataManager manager = (BukkitMetadataManager) ApolloManager.getMetadataManager();
        Map<String, Integer> statuses = manager.getResourcePackStatuses();

        statuses.put(status, statuses.getOrDefault(status, 0) + 1);
    }

    private void registerBrandListener() {
        Messenger messenger = this.plugin.getServer().getMessenger();

        try {
            PluginMessageListener listener = (channel, player, bytes) -> {
                try (DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes))) {
                    this.collectBrand(in.readUTF());
                } catch (IOException ignored) {
                }
            };

            messenger.registerIncomingPluginChannel(this.plugin, "MC|Brand", listener);
        } catch (IllegalArgumentException ignored) {
        }

        messenger.registerIncomingPluginChannel(this.plugin, "minecraft:brand", (channel, player, bytes) -> {
            ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
            this.collectBrand(ByteBufUtil.readString(in));
        });
    }

    private void collectBrand(String brand) {
        BukkitMetadataManager manager = (BukkitMetadataManager) ApolloManager.getMetadataManager();
        manager.getClientBrands().add(brand);
    }

}

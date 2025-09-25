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
import com.lunarclient.apollo.metadata.VelocityMetadataManager;
import com.lunarclient.apollo.util.ByteBufUtil;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.event.connection.PreLoginEvent;
import com.velocitypowered.api.event.player.PlayerClientBrandEvent;
import com.velocitypowered.api.event.player.PlayerResourcePackStatusEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import java.net.InetSocketAddress;
import java.util.Map;

/**
 * Handles Apollo metadata listeners.
 *
 * @since 1.1.9
 */
public final class ApolloMetadataListener {

    public static final MinecraftChannelIdentifier FML_HANDSHAKE_CHANNEL;

    /**
     * Handles plugin messages for FML mod information.
     *
     * @param event the event
     * @since 1.1.9
     */
    @Subscribe
    public void onPluginMessage(PluginMessageEvent event) {
        if (!(event.getSource() instanceof Player)) {
            return;
        }

        if (!event.getIdentifier().equals(ApolloMetadataListener.FML_HANDSHAKE_CHANNEL)) {
            return;
        }

        this.handleFml(event.getData());
    }

    /**
     * Handles player client brands.
     *
     * @param event the event
     * @since 1.1.9
     */
    @Subscribe
    public void onPlayerClientBrand(PlayerClientBrandEvent event) {
        String brand = event.getBrand();
        VelocityMetadataManager manager = (VelocityMetadataManager) ApolloManager.getMetadataManager();
        Map<String, Integer> brands = manager.getClientBrands();

        brands.put(brand, brands.getOrDefault(brand, 0) + 1);
    }

    /**
     * Captures the server IP and port the player used to connect.
     *
     * @param event the event
     * @since 1.1.9
     */
    @Subscribe
    public void onPreLogin(PreLoginEvent event) {
        InetSocketAddress host = event.getConnection().getVirtualHost().orElse(null);

        if (host == null) {
            return;
        }

        VelocityMetadataManager manager = (VelocityMetadataManager) ApolloManager.getMetadataManager();
        manager.getServerAddress().add(host.getHostString() + ":" + host.getPort());
    }

    /**
     * Tracks the total number of resource pack status events received.
     *
     * @param event the event
     * @since 1.1.9
     */
    @Subscribe
    public void onResourcePackStatus(PlayerResourcePackStatusEvent event) {
        String status = event.getStatus().name();
        VelocityMetadataManager manager = (VelocityMetadataManager) ApolloManager.getMetadataManager();
        Map<String, Integer> statuses = manager.getResourcePackStatuses();

        statuses.put(status, statuses.getOrDefault(status, 0) + 1);
    }

    private void handleFml(byte[] data) {
        ByteArrayDataInput in = ByteStreams.newDataInput(data);

        try {
            byte discriminator = in.readByte();

            if (discriminator != 2) {
                return;
            }

            VelocityMetadataManager manager = (VelocityMetadataManager) ApolloManager.getMetadataManager();
            Map<String, Integer> mods = manager.getMods();

            int count = ByteBufUtil.readVarInt(in);
            for (int i = 0; i < count; i++) {
                String modId = ByteBufUtil.readString(in);
                String version = ByteBufUtil.readString(in);
                String key = modId + ":" + version;

                mods.put(key, mods.getOrDefault(key, 0) + 1);
            }
        } catch (Exception ignored) {
        }
    }

    static {
        FML_HANDSHAKE_CHANNEL = MinecraftChannelIdentifier.create("fml", "handshake");
    }

}

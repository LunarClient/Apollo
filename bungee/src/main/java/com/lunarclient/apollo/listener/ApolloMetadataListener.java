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
import com.lunarclient.apollo.ApolloBungeePlatform;
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.metadata.BungeeMetadataManager;
import com.lunarclient.apollo.util.ByteBufUtil;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.Connection;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

/**
 * Handles Apollo metadata listeners.
 *
 * @since 1.1.9
 */
public final class ApolloMetadataListener implements Listener {

    /**
     * Constructs the {@link ApolloMetadataListener}.
     *
     * @param plugin the plugin
     * @since 1.1.9
     */
    public ApolloMetadataListener(Plugin plugin) {
        ProxyServer server = plugin.getProxy();

        server.registerChannel("MC|Brand");
        server.registerChannel("minecraft:brand");

        server.registerChannel("FML|HS");
        server.registerChannel("fml:handshake");
    }

    /**
     * Handles plugin messages for client brand and FML mod information.
     *
     * @param event the event
     * @since 1.1.9
     */
    @EventHandler
    public void onPluginMessage(PluginMessageEvent event) {
        if (!(event.getSender() instanceof ProxiedPlayer)) {
            System.out.println("Not a player Brand");
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) event.getSender();
        String tag = event.getTag();

        if (tag.equals("minecraft:brand") || tag.equals("MC|Brand")) {
            this.handleBrand(event.getData(), player);
            return;
        }

        if (tag.equals("fml:handshake") || tag.equals("FML|HS")) {
            this.handleFml(event.getData(), player);
        }
    }

    /**
     * Captures the server IP and port the player used to connect.
     *
     * @param event the event
     * @since 1.1.9
     */
    @EventHandler
    public void onPreLogin(PreLoginEvent event) {
        PendingConnection connection = event.getConnection();
        InetSocketAddress host = connection.getVirtualHost();

        if (host == null) {
            return;
        }

        String hostString;

        if (host.getAddress() != null) {
            hostString = host.getAddress().getHostAddress();
        } else {
            hostString = host.getHostName();
        }

        BungeeMetadataManager manager = (BungeeMetadataManager) ApolloManager.getMetadataManager();
        manager.getServerAddress().add(hostString + ":" + host.getPort());
        ApolloBungeePlatform.getInstance().getPlatformLogger().log(Level.WARNING, hostString + ":" + host.getPort());
    }

    private void handleBrand(byte[] data, Connection sender) {
        String brand = new String(data, StandardCharsets.UTF_8);
        ApolloBungeePlatform.getInstance().getPlatformLogger().log(Level.WARNING, ((ProxiedPlayer) sender).getName() + " is using client brand: " + brand);

        BungeeMetadataManager manager = (BungeeMetadataManager) ApolloManager.getMetadataManager();
        manager.getClientBrands().add(brand);
    }

    private void handleFml(byte[] data, Connection sender) {
        ProxiedPlayer player = (ProxiedPlayer) sender;

        ByteArrayDataInput in = ByteStreams.newDataInput(data);
        byte discriminator = in.readByte();

        if (discriminator != 2) {
            return;
        }

        int count = ByteBufUtil.readVarInt(in);

        for (int i = 0; i < count; i++) {
            String modId = ByteBufUtil.readString(in);
            String version = ByteBufUtil.readString(in);

            ApolloBungeePlatform.getInstance().getPlatformLogger().log(Level.WARNING, player.getName() + " is using mod: " + modId + " with version: " + version);
            BungeeMetadataManager manager = (BungeeMetadataManager) ApolloManager.getMetadataManager();
            manager.getMods().put(modId, version);
        }
    }

}

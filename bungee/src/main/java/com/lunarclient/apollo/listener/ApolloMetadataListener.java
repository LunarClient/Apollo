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
import com.lunarclient.apollo.metadata.BungeeMetadataManager;
import com.lunarclient.apollo.util.ByteBufUtil;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import net.md_5.bungee.api.ProxyServer;
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
            return;
        }

        String tag = event.getTag();
        byte[] data = event.getData();

        switch (tag) {
            case "MC|Brand": {
                try (DataInputStream in = new DataInputStream(new ByteArrayInputStream(data))) {
                    this.collectBrand(in.readUTF());
                } catch (IOException ignored) {
                }

                break;
            }
            case "minecraft:brand": {
                ByteArrayDataInput in = ByteStreams.newDataInput(data);
                this.collectBrand(ByteBufUtil.readString(in));
                break;
            }
            case "fml:handshake":
            case "FML|HS": {
                this.handleFml(data);
                break;
            }
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
    }

    private void collectBrand(String brand) {
        BungeeMetadataManager manager = (BungeeMetadataManager) ApolloManager.getMetadataManager();
        manager.getClientBrands().add(brand);
    }

    private void handleFml(byte[] data) {
        ByteArrayDataInput in = ByteStreams.newDataInput(data);

        try {
            byte discriminator = in.readByte();

            if (discriminator != 2) {
                return;
            }

            int count = ByteBufUtil.readVarInt(in);

            for (int i = 0; i < count; i++) {
                String modId = ByteBufUtil.readString(in);
                String version = ByteBufUtil.readString(in);

                BungeeMetadataManager manager = (BungeeMetadataManager) ApolloManager.getMetadataManager();
                manager.getMods().put(modId, version);
            }
        } catch (Exception ignored) {
        }
    }

}

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

import com.google.common.base.Charsets;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.player.ApolloPlayerManagerImpl;
import com.lunarclient.apollo.wrapper.BungeeApolloPlayer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Handles registration and un-registration of Apollo players.
 *
 * @since 1.0.0
 */
public final class ApolloPlayerListener implements Listener {

    @EventHandler
    private void onPluginMessage(PluginMessageEvent event) {
        if (!(event.getReceiver() instanceof ProxyServer)) {
            return;
        }

        if (!(event.getSender() instanceof ProxiedPlayer)) {
            return;
        }

        if (!event.getTag().equals("REGISTER")) {
            return;
        }

        String channels = new String(event.getData(), Charsets.UTF_8);
        if (!channels.contains(ApolloManager.PLUGIN_MESSAGE_CHANNEL)) {
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) event.getSender();
        ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).addPlayer(new BungeeApolloPlayer(player));
    }

    @EventHandler
    private void onDisconnect(PlayerDisconnectEvent event) {
        ProxiedPlayer player = event.getPlayer();
        ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).removePlayer(player.getUniqueId());
    }

}

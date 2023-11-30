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

import com.google.protobuf.Any;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.ApolloVelocityPlatform;
import com.lunarclient.apollo.player.ApolloPlayerManagerImpl;
import com.lunarclient.apollo.wrapper.VelocityApolloPlayer;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.event.player.PlayerChannelRegisterEvent;
import com.velocitypowered.api.proxy.Player;

/**
 * Handles registration and un-registration of Apollo players.
 *
 * @since 1.0.0
 */
public final class ApolloPlayerListener {

    /**
     * Handles registering players that join with Lunar Client.
     *
     * @param event the event
     * @since 1.0.0
     */
    @Subscribe
    public void onPlayerRegisterChannel(PlayerChannelRegisterEvent event) {
        if(!event.getChannels().contains(ApolloVelocityPlatform.PLUGIN_CHANNEL)) {
            return;
        }

        Player player = event.getPlayer();
        ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).addPlayer(new VelocityApolloPlayer(player));
    }

    /**
     * Handles registering players that join with Lunar Client.
     *
     * @param event the event
     * @since 1.0.0
     */
    @Subscribe
    public void onPluginMessage(PluginMessageEvent event) {
        if(!event.getIdentifier().equals(ApolloVelocityPlatform.PLUGIN_CHANNEL)) {
            return;
        }
        if(!(event.getSource() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getSource();
        this.handlePacket(player, event.getData());
    }

    /**
     * Handles unregistering players from Apollo.
     *
     * @param event the event
     * @since 1.0.0
     */
    @Subscribe
    public void onDisconnect(DisconnectEvent event) {
        Player player = event.getPlayer();
        ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).removePlayer(player.getUniqueId());
    }

    private void handlePacket(Player player, byte[] bytes) {
        Apollo.getPlayerManager().getPlayer(player.getUniqueId()).ifPresent(apolloPlayer -> {
            try {
                ApolloManager.getNetworkManager().receivePacket(apolloPlayer, Any.parseFrom(bytes));
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        });
    }
}

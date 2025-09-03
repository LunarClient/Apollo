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

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.ApolloMinestomPlatform;
import com.lunarclient.apollo.event.ApolloListener;
import com.lunarclient.apollo.event.ApolloReceivePacketEvent;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.Listen;
import com.lunarclient.apollo.player.ApolloPlayerManagerImpl;
import com.lunarclient.apollo.player.v1.PlayerHandshakeMessage;
import com.lunarclient.apollo.wrapper.MinestomApolloPlayer;
import java.util.Arrays;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.entity.Player;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.player.PlayerDisconnectEvent;
import net.minestom.server.event.player.PlayerPluginMessageEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;

/**
 * Handles registration and un-registration of Apollo players.
 *
 * @since 1.2.0
 */
public final class ApolloPlayerListener implements ApolloListener {

    /**
     * Constructs the {@link ApolloPlayerListener}.
     *
     * @param node the node
     * @since 1.2.0
     */
    public ApolloPlayerListener(EventNode<Event> node) {
        EventBus.getBus().register(this);

        node.addListener(PlayerPluginMessageEvent.class, this::onPlayerPluginMessage);
        node.addListener(PlayerDisconnectEvent.class, this::onPlayerDisconnect);
        node.addListener(PlayerSpawnEvent.class, this::onPlayerSpawn);
    }

    private void onPlayerPluginMessage(PlayerPluginMessageEvent event) {
        Player player = event.getPlayer();
        String identifier = event.getIdentifier();

        switch (identifier) {
            case "minecraft:register": {
                if (this.getChannels(event.getMessageString()).contains(ApolloManager.PLUGIN_MESSAGE_CHANNEL)) {
                    ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).addPlayer(new MinestomApolloPlayer(player));
                }

                break;
            }

            case "minecraft:unregister": {
                if (this.getChannels(event.getMessageString()).contains(ApolloManager.PLUGIN_MESSAGE_CHANNEL)) {
                    ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).removePlayer(player.getUuid());
                }

                break;
            }

            case ApolloManager.PLUGIN_MESSAGE_CHANNEL: {
                ApolloManager.getNetworkManager().receivePacket(player.getUuid(), event.getMessage());
                break;
            }
        }
    }

    private void onPlayerDisconnect(PlayerDisconnectEvent event) {
        ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).removePlayer(event.getPlayer().getUuid());
    }

    private void onPlayerSpawn(PlayerSpawnEvent event) {
        Player player = event.getPlayer();

        if (ApolloMinestomPlatform.getInstance().getProperties().isSendRegisterPacket()) {
            player.sendPluginMessage("minecraft:register", ApolloManager.PLUGIN_MESSAGE_CHANNEL);
        }

        String version = ApolloManager.getVersionManager().getLatestVersion();
        if (version != null && player.getPermissionLevel() == 4) {
            Component message = Component.text("[Apollo] A new version of Apollo is available! Latest release: ", NamedTextColor.YELLOW)
                .append(Component.text(version, NamedTextColor.GOLD));

            player.sendMessage(message);
        }
    }

    @Listen
    private void onApolloReceivePacket(ApolloReceivePacketEvent event) {
        event.unpack(PlayerHandshakeMessage.class).ifPresent(message -> {
            ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).handlePlayerHandshake(event.getPlayer(), message);
        });
    }

    private List<String> getChannels(String channelsString) {
        return Arrays.asList(channelsString.split("\0"));
    }

}

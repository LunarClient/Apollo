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
import com.lunarclient.apollo.event.ApolloListener;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.Listen;
import com.lunarclient.apollo.event.player.ApolloRegisterPlayerEvent;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.v1.UpdatePlayerWorldMessage;
import com.lunarclient.apollo.world.ApolloWorldManagerImpl;
import com.lunarclient.apollo.wrapper.MinestomApolloWorld;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.instance.InstanceRegisterEvent;
import net.minestom.server.event.instance.InstanceUnregisterEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.instance.Instance;

/**
 * Handles registration and un-registration of Apollo worlds.
 *
 * @since 1.2.0
 */
public final class ApolloWorldListener implements ApolloListener {

    /**
     * Constructs the {@link ApolloWorldListener}.
     *
     * @param node the node
     * @since 1.2.0
     */
    public ApolloWorldListener(EventNode<Event> node) {
        EventBus.getBus().register(this);

        ApolloWorldManagerImpl worldManager = ((ApolloWorldManagerImpl) Apollo.getWorldManager());
        for (Instance world : MinecraftServer.getInstanceManager().getInstances()) {
            worldManager.addWorld(new MinestomApolloWorld(world));
        }

        node.addListener(InstanceRegisterEvent.class, this::onInstanceRegister);
        node.addListener(InstanceUnregisterEvent.class, this::onInstanceUnregister);
        node.addListener(PlayerSpawnEvent.class, this::onPlayerSpawn);
    }

    private void onInstanceRegister(InstanceRegisterEvent event) {
        ((ApolloWorldManagerImpl) Apollo.getWorldManager()).addWorld(new MinestomApolloWorld(event.getInstance()));
    }

    private void onInstanceUnregister(InstanceUnregisterEvent event) {
        ((ApolloWorldManagerImpl) Apollo.getWorldManager()).removeWorld(event.getInstance().getUuid().toString());
    }

    private void onPlayerSpawn(PlayerSpawnEvent event) {
        Player player = event.getPlayer();

        Apollo.getPlayerManager().getPlayer(player.getUuid()).ifPresent(apolloPlayer -> {
            UpdatePlayerWorldMessage message = UpdatePlayerWorldMessage.newBuilder()
                .setWorld(player.getInstance().getUuid().toString())
                .build();

            ((AbstractApolloPlayer) apolloPlayer).sendPacket(message);
        });
    }

    @Listen
    private void onApolloRegisterPlayer(ApolloRegisterPlayerEvent event) {
        ApolloPlayer apolloPlayer = event.getPlayer();

        apolloPlayer.getWorld().ifPresent(world -> {
            UpdatePlayerWorldMessage message = UpdatePlayerWorldMessage.newBuilder()
                .setWorld(world.getName())
                .build();

            ((AbstractApolloPlayer) apolloPlayer).sendPacket(message);
        });
    }

}

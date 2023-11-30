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
package com.lunarclient.apollo.network;

import com.google.protobuf.Any;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.event.ApolloReceivePacketEvent;
import com.lunarclient.apollo.event.ApolloSendPacketEvent;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.UUID;
import lombok.NoArgsConstructor;

/**
 * Provides the implementation for the {@link ApolloNetworkManager}.
 *
 * @since 1.0.0
 */
@NoArgsConstructor
public final class ApolloNetworkManager {

    /**
     * Sends an {@link Any} message packet to the provided player.
     *
     * @param player  the player to send the packet to
     * @param message the message to send
     * @since 1.0.0
     */
    public void sendPacket(ApolloPlayer player, Any message) {
        EventBus.EventResult<ApolloSendPacketEvent> result = EventBus.getBus()
            .post(new ApolloSendPacketEvent(player, message));

        if (!result.getEvent().isCancelled()) {
            ((AbstractApolloPlayer) player).sendPacket(message.toByteArray());
        }

        for (Throwable throwable : result.getThrowing()) {
            throwable.printStackTrace();
        }
    }

    /**
     * Receives an {@link Any} message packet from the provided player.
     *
     * @param player  the player to receive the packet from
     * @param message the message to receive
     * @since 1.0.0
     */
    public void receivePacket(ApolloPlayer player, Any message) {
        EventBus.EventResult<ApolloReceivePacketEvent> result = EventBus.getBus()
            .post(new ApolloReceivePacketEvent(player, message));

        for (Throwable throwable : result.getThrowing()) {
            throwable.printStackTrace();
        }
    }

    /**
     * Receives a {@code byte[]} message packet from the provided player.
     *
     * @param player the player to receive the packet from
     * @param bytes the message to receive
     * @since 1.0.0
     */
    public void receivePacket(UUID player, byte[] bytes) {
        Apollo.getPlayerManager().getPlayer(player).ifPresent(apolloPlayer -> {
            try {
                ApolloManager.getNetworkManager().receivePacket(apolloPlayer, Any.parseFrom(bytes));
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        });
    }

}

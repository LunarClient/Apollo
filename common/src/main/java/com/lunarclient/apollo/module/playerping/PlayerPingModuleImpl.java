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
package com.lunarclient.apollo.module.playerping;

import com.lunarclient.apollo.event.ApolloReceivePacketEvent;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.ping.ApolloPlayerPingEvent;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.playerping.v1.DisplayPlayerPingMessage;
import com.lunarclient.apollo.playerping.v1.RemovePlayerPingMessage;
import com.lunarclient.apollo.playerping.v1.ResetPlayerPingsMessage;
import com.lunarclient.apollo.recipients.Recipients;
import lombok.NonNull;

/**
 * Provides the player ping module.
 *
 * @since 1.1.2
 */
public final class PlayerPingModuleImpl extends PlayerPingModule {

    /**
     * Creates a new instance of {@link PlayerPingModuleImpl}.
     *
     * @since 1.1.2
     */
    public PlayerPingModuleImpl() {
        super();
        this.handle(ApolloReceivePacketEvent.class, this::onPingReceive);
    }

    @Override
    public void displayPlayerPing(@NonNull Recipients recipients, @NonNull PlayerPing playerPing) {
        DisplayPlayerPingMessage message = DisplayPlayerPingMessage.newBuilder()
            .setId(playerPing.getId())
            .setLocation(NetworkTypes.toProtobuf(playerPing.getLocation()))
            .setColor(NetworkTypes.toProtobuf(playerPing.getColor()))
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void removePlayerPing(@NonNull Recipients recipients, @NonNull String playerPingId) {
        RemovePlayerPingMessage message = RemovePlayerPingMessage.newBuilder()
            .setId(playerPingId)
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void removePlayerPing(@NonNull Recipients recipients, @NonNull PlayerPing playerPing) {
        this.removePlayerPing(recipients, playerPing.getId());
    }

    @Override
    public void resetPlayerPings(@NonNull Recipients recipients) {
        ResetPlayerPingsMessage message = ResetPlayerPingsMessage.getDefaultInstance();
        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    private void onPingReceive(ApolloReceivePacketEvent event) {
        event.unpack(DisplayPlayerPingMessage.class).ifPresent(packet -> {
            PlayerPing playerPing = PlayerPing.builder()
                .id(packet.getId())
                .color(NetworkTypes.fromProtobuf(packet.getColor()))
                .location(NetworkTypes.fromProtobuf(packet.getLocation()))
                .displayTime(NetworkTypes.fromProtobuf(packet.getDisplayTime()))
                .build();

            ApolloPlayerPingEvent playerPingEvent = new ApolloPlayerPingEvent(
                event.getPlayer(),
                playerPing
            );

            EventBus.EventResult<ApolloPlayerPingEvent> result = EventBus.getBus().post(playerPingEvent);

            for (Throwable throwable : result.getThrowing()) {
                throwable.printStackTrace();
            }
        });
    }

}

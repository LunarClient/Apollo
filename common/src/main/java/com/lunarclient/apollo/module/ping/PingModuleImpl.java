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
package com.lunarclient.apollo.module.ping;

import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.event.ApolloReceivePacketEvent;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.ping.ApolloPlayerPingEvent;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.ping.v1.PlayerPingMessage;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import java.util.UUID;

public class PingModuleImpl extends PingModule {
    public PingModuleImpl() {
        this.handle(ApolloReceivePacketEvent.class, this::onReceivePacket);
    }

    @Override
    public void pingTeamMembers(Recipients recipients, UUID playerUuid, ApolloLocation location, boolean isDoublePing) {
        PlayerPingMessage message = PlayerPingMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(playerUuid))
            .setLocation(NetworkTypes.toProtobuf(location))
            .setIsDoublePing(isDoublePing)
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }


    private void onReceivePacket(ApolloReceivePacketEvent event) {
        event.unpack(PlayerPingMessage.class).ifPresent(packet -> {
            ApolloPlayerPingEvent playerPingEvent = new ApolloPlayerPingEvent(
                event.getPlayer().getUniqueId(), // Ignore the player ID in the packet
                NetworkTypes.fromProtobuf(packet.getLocation()),
                packet.getIsDoublePing()
            );

            EventBus.EventResult<ApolloPlayerPingEvent> result = EventBus.getBus().post(playerPingEvent);

            for (Throwable throwable : result.getThrowing()) {
                throwable.printStackTrace();
            }
        });
    }
}

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
package com.lunarclient.apollo.module.packetenrichment;

import com.lunarclient.apollo.event.ApolloReceivePacketEvent;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.packetenrichment.chat.ApolloPlayerChatCloseEvent;
import com.lunarclient.apollo.event.packetenrichment.chat.ApolloPlayerChatOpenEvent;
import com.lunarclient.apollo.event.packetenrichment.melee.ApolloPlayerAttackEvent;
import com.lunarclient.apollo.event.packetenrichment.world.ApolloPlayerUseItemBucketEvent;
import com.lunarclient.apollo.event.packetenrichment.world.ApolloPlayerUseItemEvent;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.packetenrichment.v1.PlayerAttackMessage;
import com.lunarclient.apollo.packetenrichment.v1.PlayerChatCloseMessage;
import com.lunarclient.apollo.packetenrichment.v1.PlayerChatOpenMessage;
import com.lunarclient.apollo.packetenrichment.v1.PlayerUseItemBucketMessage;
import com.lunarclient.apollo.packetenrichment.v1.PlayerUseItemMessage;

/**
 * Provides the packet enrichment module.
 *
 * @since 1.0.7
 */
public final class PacketEnrichmentImpl extends PacketEnrichmentModule {

    /**
     * Creates a new instance of {@link PacketEnrichmentImpl}.
     *
     * @since 1.0.7
     */
    public PacketEnrichmentImpl() {
        super();
        this.handle(ApolloReceivePacketEvent.class, this::onReceivePacket);
    }

    private void onReceivePacket(ApolloReceivePacketEvent event) {
        event.unpack(PlayerAttackMessage.class).ifPresent(packet -> {
            ApolloPlayerAttackEvent playerAttackEvent = new ApolloPlayerAttackEvent(
                event.getPlayer(),
                NetworkTypes.fromProtobuf(packet.getPacketInfo().getInstantiationTime()),
                NetworkTypes.fromProtobuf(packet.getTargetInfo()),
                NetworkTypes.fromProtobuf(packet.getAttackerInfo()),
                packet.getDistance()
            );

            EventBus.EventResult<ApolloPlayerAttackEvent> result = EventBus.getBus().post(playerAttackEvent);

            for (Throwable throwable : result.getThrowing()) {
                throwable.printStackTrace();
            }
        });

        event.unpack(PlayerChatOpenMessage.class).ifPresent(packet -> {
            ApolloPlayerChatOpenEvent playerChatOpenEvent = new ApolloPlayerChatOpenEvent(
                event.getPlayer(),
                NetworkTypes.fromProtobuf(packet.getPacketInfo().getInstantiationTime()),
                NetworkTypes.fromProtobuf(packet.getPlayerInfo()));

            EventBus.EventResult<ApolloPlayerChatOpenEvent> result = EventBus.getBus().post(playerChatOpenEvent);

            for (Throwable throwable : result.getThrowing()) {
                throwable.printStackTrace();
            }
        });

        event.unpack(PlayerChatCloseMessage.class).ifPresent(packet -> {
            ApolloPlayerChatCloseEvent playerChatCloseEvent = new ApolloPlayerChatCloseEvent(
                event.getPlayer(),
                NetworkTypes.fromProtobuf(packet.getPacketInfo().getInstantiationTime()),
                NetworkTypes.fromProtobuf(packet.getPlayerInfo()));

            EventBus.EventResult<ApolloPlayerChatCloseEvent> result = EventBus.getBus().post(playerChatCloseEvent);

            for (Throwable throwable : result.getThrowing()) {
                throwable.printStackTrace();
            }
        });

        event.unpack(PlayerUseItemMessage.class).ifPresent(packet -> {
            ApolloPlayerUseItemEvent playerUseItemEvent = new ApolloPlayerUseItemEvent(
                event.getPlayer(),
                NetworkTypes.fromProtobuf(packet.getPacketInfo().getInstantiationTime()),
                NetworkTypes.fromProtobuf(packet.getPlayerInfo()),
                packet.getMainHand()
            );

            EventBus.EventResult<ApolloPlayerUseItemEvent> result = EventBus.getBus().post(playerUseItemEvent);

            for (Throwable throwable : result.getThrowing()) {
                throwable.printStackTrace();
            }
        });

        event.unpack(PlayerUseItemBucketMessage.class).ifPresent(packet -> {
            ApolloPlayerUseItemBucketEvent playerUseItemBucketEvent = new ApolloPlayerUseItemBucketEvent(
                event.getPlayer(),
                NetworkTypes.fromProtobuf(packet.getPacketInfo().getInstantiationTime()),
                NetworkTypes.fromProtobuf(packet.getPlayerInfo()),
                NetworkTypes.fromProtobuf(packet.getRayTraceResult())
            );

            EventBus.EventResult<ApolloPlayerUseItemBucketEvent> result = EventBus.getBus().post(playerUseItemBucketEvent);

            for (Throwable throwable : result.getThrowing()) {
                throwable.printStackTrace();
            }
        });
    }

}

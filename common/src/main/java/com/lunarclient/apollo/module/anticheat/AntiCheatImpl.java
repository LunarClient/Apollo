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
package com.lunarclient.apollo.module.anticheat;

import com.lunarclient.apollo.anticheat.v1.PlayerAttackMessage;
import com.lunarclient.apollo.anticheat.v1.PlayerAttackedMessage;
import com.lunarclient.apollo.event.ApolloReceivePacketEvent;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.anticheat.ApolloPlayerAttackEvent;
import com.lunarclient.apollo.network.NetworkTypes;

/**
 * Provides the anti cheat module.
 *
 * @since 1.0.0
 */
public final class AntiCheatImpl extends AntiCheatModule {

    /**
     * Creates a new instance of {@link AntiCheatImpl}.
     *
     * @since 1.0.0
     */
    public AntiCheatImpl() {
        super();
        this.handle(ApolloReceivePacketEvent.class, this::onReceivePacket);
    }

    private void onReceivePacket(ApolloReceivePacketEvent event) {
        event.unpack(PlayerAttackMessage.class).ifPresent(packet -> {
            ApolloPlayerAttackEvent playerAttackEvent = new ApolloPlayerAttackEvent(
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

        event.unpack(PlayerAttackedMessage.class).ifPresent(packet -> {

        });
    }
}

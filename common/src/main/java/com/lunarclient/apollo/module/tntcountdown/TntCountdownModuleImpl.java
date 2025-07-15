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
package com.lunarclient.apollo.module.tntcountdown;

import com.lunarclient.apollo.common.ApolloEntity;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import com.lunarclient.apollo.tntcountdown.v1.SetTntCountdownMessage;
import lombok.NonNull;

import static com.lunarclient.apollo.util.Ranges.checkPositive;

/**
 * Provides the tnt countdown module.
 *
 * @since 1.1.8
 */
public final class TntCountdownModuleImpl extends TntCountdownModule {

    @Override
    public void setTntCountdown(@NonNull ApolloEntity entity, int ticks) {
        this.setTntCountdown(Recipients.ofEveryone(), entity, ticks);
    }

    @Override
    public void setTntCountdown(@NonNull Recipients recipients, @NonNull ApolloEntity entity, int ticks) {
        SetTntCountdownMessage message = SetTntCountdownMessage.newBuilder()
            .setEntityId(NetworkTypes.toProtobuf(entity))
            .setDurationTicks(checkPositive(ticks, "TntCountdown#ticks"))
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

}

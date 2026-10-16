/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2026 Moonsworth
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
package com.lunarclient.apollo.module.totemcounter;

import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.recipients.Recipients;
import com.lunarclient.apollo.totemcounter.v1.OverrideTotemCountersMessage;
import com.lunarclient.apollo.totemcounter.v1.ResetTotemCounterMessage;
import com.lunarclient.apollo.totemcounter.v1.ResetTotemCountersMessage;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import lombok.NonNull;

import static com.lunarclient.apollo.util.Ranges.checkPositive;

/**
 * Provides the totem counter module.
 *
 * @since 1.3.0
 */
public final class TotemCounterModuleImpl extends TotemCounterModule {

    @Override
    public void overrideTotemCounters(@NonNull Recipients recipients, @NonNull Collection<TotemCounter> totemCounters) {
        OverrideTotemCountersMessage.Builder builder = OverrideTotemCountersMessage.newBuilder();

        for (TotemCounter totemCounter : totemCounters) {
            builder.addTotemCounters(this.toProtobuf(totemCounter));
        }

        ApolloManager.getNetworkManager().sendPacket(recipients, builder.build());
    }

    @Override
    public void overrideTotemCounter(@NonNull Recipients recipients, @NonNull TotemCounter totemCounter) {
        this.overrideTotemCounters(recipients, Collections.singleton(totemCounter));
    }

    @Override
    public void resetTotemCounter(@NonNull Recipients recipients, @NonNull UUID playerUuid) {
        this.resetTotemCounter(recipients, Collections.singleton(playerUuid));
    }

    @Override
    public void resetTotemCounter(@NonNull Recipients recipients, @NonNull TotemCounter totemCounter) {
        this.resetTotemCounter(recipients, Collections.singleton(totemCounter.getPlayerUuid()));
    }

    @Override
    public void resetTotemCounter(@NonNull Recipients recipients, @NonNull Collection<UUID> playerUuids) {
        ResetTotemCounterMessage.Builder builder = ResetTotemCounterMessage.newBuilder();

        for (UUID playerUuid : playerUuids) {
            builder.addPlayerUuids(NetworkTypes.toProtobuf(playerUuid));
        }

        ApolloManager.getNetworkManager().sendPacket(recipients, builder.build());
    }

    @Override
    public void resetTotemCounters(@NonNull Recipients recipients) {
        ResetTotemCountersMessage message = ResetTotemCountersMessage.getDefaultInstance();
        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    private com.lunarclient.apollo.totemcounter.v1.TotemCounter toProtobuf(TotemCounter totemCounter) {
        return com.lunarclient.apollo.totemcounter.v1.TotemCounter.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(totemCounter.getPlayerUuid()))
            .setPops(checkPositive(totemCounter.getPops(), "TotemCounter#pops"))
            .build();
    }

}

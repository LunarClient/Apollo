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
package com.lunarclient.apollo.module.hologram;

import com.lunarclient.apollo.common.ApolloComponent;
import com.lunarclient.apollo.hologram.v1.DisplayHologramMessage;
import com.lunarclient.apollo.hologram.v1.RemoveHologramMessage;
import com.lunarclient.apollo.hologram.v1.ResetHologramsMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import java.util.stream.Collectors;
import lombok.NonNull;

/**
 * Provides the hologram module.
 *
 * @since 1.0.0
 */
public final class HologramModuleImpl extends HologramModule {

    @Override
    public void displayHologram(@NonNull Recipients recipients, @NonNull Hologram hologram) {
        DisplayHologramMessage message = DisplayHologramMessage.newBuilder()
            .setId(hologram.getId())
            .setLocation(NetworkTypes.toProtobuf(hologram.getLocation()))
            .addAllAdventureJsonLines(hologram.getLines().stream()
                .map(ApolloComponent::toJson)
                .collect(Collectors.toList())
            )
            .setShowThroughWalls(hologram.isShowThroughWalls())
            .setShowShadow(hologram.isShowShadow())
            .setShowBackground(hologram.isShowBackground())
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void removeHologram(@NonNull Recipients recipients, @NonNull String hologramId) {
        RemoveHologramMessage message = RemoveHologramMessage.newBuilder()
            .setId(hologramId)
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void removeHologram(@NonNull Recipients recipients, @NonNull Hologram hologram) {
        this.removeHologram(recipients, hologram.getId());
    }

    @Override
    public void resetHolograms(@NonNull Recipients recipients) {
        ResetHologramsMessage message = ResetHologramsMessage.getDefaultInstance();
        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

}

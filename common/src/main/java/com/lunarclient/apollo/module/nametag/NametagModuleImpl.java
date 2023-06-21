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
package com.lunarclient.apollo.module.nametag;

import com.lunarclient.apollo.common.v1.Component;
import com.lunarclient.apollo.nametag.v1.OverrideNametagMessage;
import com.lunarclient.apollo.nametag.v1.ResetNametagMessage;
import com.lunarclient.apollo.nametag.v1.ResetNametagsMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.NonNull;

/**
 * Provides the nametag module.
 *
 * @since 1.0.0
 */
public final class NametagModuleImpl extends NametagModule {

    @Override
    public void overrideNametag(@NonNull Collection<ApolloPlayer> viewers, @NonNull UUID playerUuid, @NonNull Nametag nametag) {
        List<Component> lines = nametag.getLines().stream()
            .map(NetworkTypes::toProtobuf)
            .collect(Collectors.toList());

        OverrideNametagMessage message = OverrideNametagMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(playerUuid))
            .addAllLines(lines)
            .build();

        for (ApolloPlayer player : viewers) {
            ((AbstractApolloPlayer) player).sendPacket(message);
        }
    }

    @Override
    public void resetNametag(@NonNull Collection<ApolloPlayer> viewers, @NonNull UUID playerUuid) {
        ResetNametagMessage message = ResetNametagMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(playerUuid))
            .build();

        for (ApolloPlayer player : viewers) {
            ((AbstractApolloPlayer) player).sendPacket(message);
        }
    }

    @Override
    public void resetNametags(@NonNull ApolloPlayer player) {
        ((AbstractApolloPlayer) player).sendPacket(ResetNametagsMessage.getDefaultInstance());
    }

}

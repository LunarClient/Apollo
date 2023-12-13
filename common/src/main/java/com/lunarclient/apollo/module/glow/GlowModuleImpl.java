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
package com.lunarclient.apollo.module.glow;

import com.lunarclient.apollo.glow.v1.OverrideGlowEffectMessage;
import com.lunarclient.apollo.glow.v1.ResetGlowEffectMessage;
import com.lunarclient.apollo.glow.v1.ResetGlowEffectsMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import java.awt.Color;
import java.util.UUID;
import lombok.NonNull;

/**
 * Provides the glow module.
 *
 * @since 1.0.5
 */
public final class GlowModuleImpl extends GlowModule {

    @Override
    public void overrideGlow(@NonNull Recipients recipients, @NonNull UUID glowingPlayer, @NonNull Color color) {
        OverrideGlowEffectMessage message = OverrideGlowEffectMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(glowingPlayer))
            .setColor(NetworkTypes.toProtobuf(color))
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void resetGlow(@NonNull Recipients recipients, @NonNull UUID glowingPlayer) {
        ResetGlowEffectMessage message = ResetGlowEffectMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(glowingPlayer))
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void resetGlow(@NonNull Recipients recipients) {
        ResetGlowEffectsMessage message = ResetGlowEffectsMessage.getDefaultInstance();
        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

}

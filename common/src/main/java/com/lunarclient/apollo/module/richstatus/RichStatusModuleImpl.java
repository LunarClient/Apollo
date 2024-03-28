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
package com.lunarclient.apollo.module.richstatus;

import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import com.lunarclient.apollo.richstatus.v1.OverrideServerRichStatusMessage;
import com.lunarclient.apollo.richstatus.v1.ResetServerRichStatusMessage;
import lombok.NonNull;

/**
 * Provides the rich status module.
 *
 * @since 1.1.2
 */
public final class RichStatusModuleImpl extends RichStatusModule {

    @Override
    public void overrideRichStatus(@NonNull Recipients recipients, @NonNull ServerRichStatus richStatus) {
        // TODO: optional fields
        OverrideServerRichStatusMessage.Builder builder = OverrideServerRichStatusMessage.newBuilder()
            .setGameName(richStatus.getGameName())
            .setGameVariantName(richStatus.getGameVariantName())
            .setGameState(richStatus.getGameState())
            .setPlayerState(richStatus.getPlayerState())
            .setMapName(richStatus.getMapName())
            .setSubServer(richStatus.getSubServerName())
            .setTeamCurrentSize(richStatus.getTeamCurrentSize())
            .setTeamMaxSize(richStatus.getTeamMaxSize());

        OverrideServerRichStatusMessage message = builder.build();
        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void resetRichStatus(@NonNull Recipients recipients) {
        ResetServerRichStatusMessage message = ResetServerRichStatusMessage.getDefaultInstance();
        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

}

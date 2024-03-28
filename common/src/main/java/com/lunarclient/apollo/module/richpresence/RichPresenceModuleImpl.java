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
package com.lunarclient.apollo.module.richpresence;

import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import com.lunarclient.apollo.richpresence.v1.OverrideServerRichPresenceMessage;
import com.lunarclient.apollo.richpresence.v1.ResetServerRichPresenceMessage;
import lombok.NonNull;

/**
 * Provides the rich presence module.
 *
 * @since 1.1.2
 */
public final class RichPresenceModuleImpl extends RichPresenceModule {

    @Override
    public void overrideServerRichPresence(@NonNull Recipients recipients, @NonNull ServerRichPresence richPresence) {
        OverrideServerRichPresenceMessage.Builder builder = OverrideServerRichPresenceMessage.newBuilder()
            .setTeamCurrentSize(richPresence.getTeamCurrentSize())
            .setTeamMaxSize(richPresence.getTeamMaxSize());

        String gameName = richPresence.getGameName();
        if (gameName != null) {
            builder.setGameName(gameName);
        }

        String gameVariantName = richPresence.getGameVariantName();
        if (gameVariantName != null) {
            builder.setGameVariantName(gameVariantName);
        }

        String gameState = richPresence.getGameState();
        if (gameState != null) {
            builder.setGameState(gameState);
        }

        String playerState = richPresence.getPlayerState();
        if (playerState != null) {
            builder.setPlayerState(playerState);
        }

        String mapName = richPresence.getMapName();
        if (mapName != null) {
            builder.setMapName(mapName);
        }

        String subServerName = richPresence.getSubServerName();
        if (subServerName != null) {
            builder.setSubServer(subServerName);
        }

        OverrideServerRichPresenceMessage message = builder.build();
        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void resetServerRichPresence(@NonNull Recipients recipients) {
        ResetServerRichPresenceMessage message = ResetServerRichPresenceMessage.getDefaultInstance();
        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

}

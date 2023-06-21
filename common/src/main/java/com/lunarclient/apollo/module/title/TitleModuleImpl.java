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
package com.lunarclient.apollo.module.title;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.title.v1.DisplayTitleMessage;
import com.lunarclient.apollo.title.v1.ResetTitlesMessage;
import com.lunarclient.apollo.title.v1.TitleType;
import lombok.NonNull;

/**
 * Provides the title module.
 *
 * @since 1.0.0
 */
public final class TitleModuleImpl extends TitleModule {

    @Override
    public void displayTitle(@NonNull ApolloPlayer viewer, @NonNull Title title) {
        ((AbstractApolloPlayer) viewer).sendPacket(this.toProtobuf(title));
    }

    @Override
    public void broadcastTitle(@NonNull Title title) {
        DisplayTitleMessage message = this.toProtobuf(title);

        for (ApolloPlayer player : Apollo.getPlayerManager().getPlayers()) {
            ((AbstractApolloPlayer) player).sendPacket(message);
        }
    }

    @Override
    public void resetTitles(@NonNull ApolloPlayer viewer) {
        ((AbstractApolloPlayer) viewer).sendPacket(ResetTitlesMessage.getDefaultInstance());
    }

    private DisplayTitleMessage toProtobuf(Title title) {
        return DisplayTitleMessage.newBuilder()
            .setTitleType(TitleType.forNumber(title.getType().ordinal() + 1))
            .setMessage(NetworkTypes.toProtobuf(title.getMessage()))
            .setScale(title.getScale())
            .setFadeInTime(NetworkTypes.toProtobuf(title.getFadeInTime()))
            .setDisplayTime(NetworkTypes.toProtobuf(title.getDisplayTime()))
            .setFadeOutTime(NetworkTypes.toProtobuf(title.getFadeOutTime()))
            .build();
    }
}

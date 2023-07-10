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
package com.lunarclient.apollo.module.border;

import com.google.protobuf.ByteString;
import com.lunarclient.apollo.audience.Audience;
import com.lunarclient.apollo.border.v1.DisplayBorderMessage;
import com.lunarclient.apollo.border.v1.RemoveBorderMessage;
import com.lunarclient.apollo.border.v1.ResetBordersMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import lombok.NonNull;

/**
 * Provides the border module.
 *
 * @since 1.0.0
 */
public final class BorderModuleImpl extends BorderModule {

    @Override
    public void displayBorder(@NonNull Audience audience, @NonNull Border border) {
        DisplayBorderMessage message = DisplayBorderMessage.newBuilder()
            .setId(ByteString.copyFromUtf8(border.getId()))
            .setWorld(border.getWorld())
            .setCancelEntry(border.isCancelEntry())
            .setCancelExit(border.isCancelExit())
            .setCanShrinkOrExpand(border.isCanShrinkOrExpand())
            .setColor(NetworkTypes.toProtobuf(border.getColor()))
            .setBounds(NetworkTypes.toProtobuf(border.getBounds()))
            .setDurationTicks(border.getDurationTicks())
            .build();

        audience.forEachAudience(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void removeBorder(@NonNull Audience audience, @NonNull String borderId) {
        RemoveBorderMessage message = RemoveBorderMessage.newBuilder()
            .setId(ByteString.copyFromUtf8(borderId))
            .build();

        audience.forEachAudience(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void removeBorder(@NonNull Audience audience, @NonNull Border border) {
        this.removeBorder(audience, border.getId());
    }

    @Override
    public void resetBorders(@NonNull Audience audience) {
        ResetBordersMessage message = ResetBordersMessage.getDefaultInstance();
        audience.forEachAudience(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

}

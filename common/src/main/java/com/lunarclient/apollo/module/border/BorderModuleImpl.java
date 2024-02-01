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

import com.lunarclient.apollo.border.v1.DisplayBorderMessage;
import com.lunarclient.apollo.border.v1.RemoveBorderMessage;
import com.lunarclient.apollo.border.v1.ResetBordersMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import lombok.NonNull;

import static com.lunarclient.apollo.util.Ranges.checkPositive;

/**
 * Provides the border module.
 *
 * @since 1.0.0
 */
public final class BorderModuleImpl extends BorderModule {

    @Override
    public void displayBorder(@NonNull Recipients recipients, @NonNull Border border) {
        DisplayBorderMessage message = DisplayBorderMessage.newBuilder()
            .setId(border.getId())
            .setWorld(border.getWorld())
            .setCancelEntry(border.isCancelEntry())
            .setCancelExit(border.isCancelExit())
            .setCanShrinkOrExpand(border.isCanShrinkOrExpand())
            .setColor(NetworkTypes.toProtobuf(border.getColor()))
            .setBounds(NetworkTypes.toProtobuf(border.getBounds()))
            .setDurationTicks(checkPositive(border.getDurationTicks(), "Border#durationTicks"))
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void removeBorder(@NonNull Recipients recipients, @NonNull String borderId) {
        RemoveBorderMessage message = RemoveBorderMessage.newBuilder()
            .setId(borderId)
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void removeBorder(@NonNull Recipients recipients, @NonNull Border border) {
        this.removeBorder(recipients, border.getId());
    }

    @Override
    public void resetBorders(@NonNull Recipients recipients) {
        ResetBordersMessage message = ResetBordersMessage.getDefaultInstance();
        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

}

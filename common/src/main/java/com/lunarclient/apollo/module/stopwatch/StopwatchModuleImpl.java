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
package com.lunarclient.apollo.module.stopwatch;

import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.common.ApolloComponent;
import com.lunarclient.apollo.common.location.HudPosition;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.recipients.Recipients;
import com.lunarclient.apollo.stopwatch.v1.AddStopwatchMessage;
import com.lunarclient.apollo.stopwatch.v1.AddTimerMessage;
import com.lunarclient.apollo.stopwatch.v1.RemoveStopwatchMessage;
import com.lunarclient.apollo.stopwatch.v1.RemoveTimerMessage;
import com.lunarclient.apollo.stopwatch.v1.ResetStopwatchMessage;
import com.lunarclient.apollo.stopwatch.v1.ResetStopwatchesMessage;
import com.lunarclient.apollo.stopwatch.v1.ResetTimerMessage;
import com.lunarclient.apollo.stopwatch.v1.ResetTimersMessage;
import com.lunarclient.apollo.stopwatch.v1.StartStopwatchMessage;
import com.lunarclient.apollo.stopwatch.v1.StartTimerMessage;
import com.lunarclient.apollo.stopwatch.v1.StopStopwatchMessage;
import com.lunarclient.apollo.stopwatch.v1.StopTimerMessage;
import java.awt.Color;
import lombok.NonNull;
import net.kyori.adventure.text.Component;

/**
 * Provides the stopwatch module.
 *
 * @since 1.0.0
 */
public final class StopwatchModuleImpl extends StopwatchModule {

    @Override
    public void startStopwatch(@NonNull Recipients recipients) {
        StartStopwatchMessage message = StartStopwatchMessage.getDefaultInstance();
        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void stopStopwatch(@NonNull Recipients recipients) {
        StopStopwatchMessage message = StopStopwatchMessage.getDefaultInstance();
        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void resetStopwatch(@NonNull Recipients recipients) {
        ResetStopwatchMessage message = ResetStopwatchMessage.getDefaultInstance();
        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void addStopwatch(@NonNull Recipients recipients, @NonNull Stopwatch stopwatch) {
        AddStopwatchMessage.Builder builder = AddStopwatchMessage.newBuilder()
            .setId(stopwatch.getId())
            .setName(stopwatch.getName())
            .setResetOnStart(stopwatch.isResetOnStart())
            .setPreventModification(stopwatch.isPreventModification())
            .setHideWhenStopped(stopwatch.isHideWhenStopped());

        String displayFormat = stopwatch.getDisplayFormat();
        if (displayFormat != null) {
            builder.setDisplayFormat(displayFormat);
        }

        Color textColor = stopwatch.getTextColor();
        if (textColor != null) {
            builder.setTextColor(NetworkTypes.toProtobuf(textColor));
        }

        HudPosition hudPosition = stopwatch.getHudPosition();
        if (hudPosition != null) {
            builder.setHudPosition(NetworkTypes.toProtobuf(hudPosition));
        }

        AddStopwatchMessage message = builder.build();
        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void removeStopwatch(@NonNull Recipients recipients, @NonNull String id) {
        RemoveStopwatchMessage message = RemoveStopwatchMessage.newBuilder()
            .setId(id)
            .build();

        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void startStopwatch(@NonNull Recipients recipients, @NonNull String id) {
        StartStopwatchMessage message = StartStopwatchMessage.newBuilder()
            .setId(id)
            .build();

        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void stopStopwatch(@NonNull Recipients recipients, @NonNull String id) {
        StopStopwatchMessage message = StopStopwatchMessage.newBuilder()
            .setId(id)
            .build();

        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void resetStopwatch(@NonNull Recipients recipients, @NonNull String id) {
        ResetStopwatchMessage message = ResetStopwatchMessage.newBuilder()
            .setId(id)
            .build();

        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void resetStopwatches(@NonNull Recipients recipients) {
        ResetStopwatchesMessage message = ResetStopwatchesMessage.getDefaultInstance();
        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void addTimer(@NonNull Recipients recipients, @NonNull Timer timer) {
        AddTimerMessage.Builder builder = AddTimerMessage.newBuilder()
            .setId(timer.getId())
            .setName(timer.getName())
            .setDuration(NetworkTypes.toProtobuf(timer.getDuration()))
            .setLoop(timer.isLoop())
            .setPreventModification(timer.isPreventModification())
            .setHideWhenStopped(timer.isHideWhenStopped())
            .setInGameNotification(timer.isInGameNotification());

        String displayFormat = timer.getDisplayFormat();
        if (displayFormat != null) {
            builder.setDisplayFormat(displayFormat);
        }

        Component titleText = timer.getTitleText();
        if (titleText != null) {
            builder.setTitleTextAdventureJsonLines(ApolloComponent.toJson(titleText));
        }

        Color textColor = timer.getTextColor();
        if (textColor != null) {
            builder.setTextColor(NetworkTypes.toProtobuf(textColor));
        }

        HudPosition hudPosition = timer.getHudPosition();
        if (hudPosition != null) {
            builder.setHudPosition(NetworkTypes.toProtobuf(hudPosition));
        }

        AddTimerMessage message = builder.build();
        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void removeTimer(@NonNull Recipients recipients, @NonNull String id) {
        RemoveTimerMessage message = RemoveTimerMessage.newBuilder()
            .setId(id)
            .build();

        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void startTimer(@NonNull Recipients recipients, @NonNull String id) {
        StartTimerMessage message = StartTimerMessage.newBuilder()
            .setId(id)
            .build();

        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void stopTimer(@NonNull Recipients recipients, @NonNull String id) {
        StopTimerMessage message = StopTimerMessage.newBuilder()
            .setId(id)
            .build();

        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void resetTimer(@NonNull Recipients recipients, @NonNull String id) {
        ResetTimerMessage message = ResetTimerMessage.newBuilder()
            .setId(id)
            .build();

        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void resetTimers(@NonNull Recipients recipients) {
        ResetTimersMessage message = ResetTimersMessage.getDefaultInstance();
        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

}

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
package com.lunarclient.apollo.example.proto.module;

import com.lunarclient.apollo.example.module.impl.StopwatchExample;
import com.lunarclient.apollo.example.proto.util.AdventureUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufPacketUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufUtil;
import com.lunarclient.apollo.hud.v1.HudPosition;
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
import java.time.Duration;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class StopwatchProtoExample extends StopwatchExample {

    @Override
    public void addStopwatchExample(Player viewer) {
        AddStopwatchMessage message = AddStopwatchMessage.newBuilder()
            .setId("parkour-stopwatch")
            .setName("Parkour")
            .setResetOnStart(true)
            .setPreventModification(true)
            .setHideWhenStopped(false)
            .setDisplayFormat("mm:ss")
            .setTextColor(ProtobufUtil.createColorProto(new Color(0, 170, 170)))
            .setHudPosition(HudPosition.newBuilder()
                .setX(-30)
                .setY(30)
                .build()
            )
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void removeStopwatchExample(Player viewer) {
        RemoveStopwatchMessage message = RemoveStopwatchMessage.newBuilder()
            .setId("parkour-stopwatch")
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void startStopwatchExample(Player viewer) {
        StartStopwatchMessage message = StartStopwatchMessage.newBuilder()
            .setId("parkour-stopwatch")
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void stopStopwatchExample(Player viewer) {
        StopStopwatchMessage message = StopStopwatchMessage.newBuilder()
            .setId("parkour-stopwatch")
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetStopwatchExample(Player viewer) {
        ResetStopwatchMessage message = ResetStopwatchMessage.newBuilder()
            .setId("parkour-stopwatch")
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetStopwatchesExample(Player viewer) {
        ResetStopwatchesMessage message = ResetStopwatchesMessage.getDefaultInstance();
        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void addTimerExample(Player viewer) {
        AddTimerMessage message = AddTimerMessage.newBuilder()
            .setId("game-timer")
            .setName("Countdown")
            .setDuration(ProtobufUtil.createDurationProto(Duration.ofSeconds(45)))
            .setLoop(false)
            .setPreventModification(true)
            .setHideWhenStopped(false)
            .setDisplayFormat("mm:ss")
            .setTitleTextAdventureJsonLines(AdventureUtil.toJson(
                Component.text("Time's up!", NamedTextColor.RED)
            ))
            .setInGameNotification(true)
            .setTextColor(ProtobufUtil.createColorProto(new Color(255, 85, 255)))
            .setHudPosition(HudPosition.newBuilder()
                .setX(-10)
                .setY(30)
                .build()
            )
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void removeTimerExample(Player viewer) {
        RemoveTimerMessage message = RemoveTimerMessage.newBuilder()
            .setId("game-timer")
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void startTimerExample(Player viewer) {
        StartTimerMessage message = StartTimerMessage.newBuilder()
            .setId("game-timer")
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void stopTimerExample(Player viewer) {
        StopTimerMessage message = StopTimerMessage.newBuilder()
            .setId("game-timer")
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetTimerExample(Player viewer) {
        ResetTimerMessage message = ResetTimerMessage.newBuilder()
            .setId("game-timer")
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetTimersExample(Player viewer) {
        ResetTimersMessage message = ResetTimersMessage.getDefaultInstance();
        ProtobufPacketUtil.sendPacket(viewer, message);
    }

}

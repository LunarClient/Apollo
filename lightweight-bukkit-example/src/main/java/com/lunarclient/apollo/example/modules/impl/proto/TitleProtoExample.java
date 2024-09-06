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
package com.lunarclient.apollo.example.modules.impl.proto;

import com.lunarclient.apollo.example.modules.impl.json.TitleJsonExample;
import com.lunarclient.apollo.example.utilities.ProtobufPacketUtil;
import com.lunarclient.apollo.example.utilities.ProtobufUtil;
import com.lunarclient.apollo.title.v1.DisplayTitleMessage;
import com.lunarclient.apollo.title.v1.ResetTitlesMessage;
import com.lunarclient.apollo.title.v1.TitleType;
import org.bukkit.entity.Player;

import java.time.Duration;

public class TitleProtoExample extends TitleJsonExample {

    private final DisplayTitleMessage helloTitle = DisplayTitleMessage.newBuilder()
        .setTitleType(TitleType.TITLE_TYPE_TITLE)
        .setAdventureJsonMessage(null) // TODO
        .setScale(1.0f)
        .setFadeInTime(ProtobufUtil.toProtobuf(Duration.ofMillis(1500)))
        .setDisplayTime(ProtobufUtil.toProtobuf(Duration.ofMillis(250)))
        .setFadeOutTime(ProtobufUtil.toProtobuf(Duration.ofMillis(300)))
        .build();

    private final DisplayTitleMessage interpolatedTitle = DisplayTitleMessage.newBuilder()
        .setTitleType(TitleType.TITLE_TYPE_TITLE)
        .setAdventureJsonMessage(null) // TODO
        .setScale(0.1f)
        .setInterpolationScale(1.0f)
        .setInterpolationRate(0.01f)
        .setFadeInTime(ProtobufUtil.toProtobuf(Duration.ofMillis(5000)))
        .setDisplayTime(ProtobufUtil.toProtobuf(Duration.ofMillis(250)))
        .setFadeOutTime(ProtobufUtil.toProtobuf(Duration.ofMillis(300)))
        .build();

    @Override
    public void displayTitleExample(Player viewer) {
        ProtobufPacketUtil.sendPacket(viewer, this.helloTitle);
    }

    @Override
    public void resetTitlesExample(Player viewer) {
        ResetTitlesMessage message = ResetTitlesMessage.getDefaultInstance();
        ProtobufPacketUtil.sendPacket(viewer, message);
    }

}

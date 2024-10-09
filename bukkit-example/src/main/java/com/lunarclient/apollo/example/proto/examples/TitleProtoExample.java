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
package com.lunarclient.apollo.example.proto.examples;

import com.lunarclient.apollo.example.json.examples.TitleJsonExample;
import com.lunarclient.apollo.example.proto.AdventureUtil;
import com.lunarclient.apollo.example.proto.ProtobufPacketUtil;
import com.lunarclient.apollo.example.proto.ProtobufUtil;
import com.lunarclient.apollo.title.v1.DisplayTitleMessage;
import com.lunarclient.apollo.title.v1.ResetTitlesMessage;
import com.lunarclient.apollo.title.v1.TitleType;
import java.time.Duration;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

public class TitleProtoExample extends TitleJsonExample {

    @Override
    public void displayTitleExample(Player viewer) {
        DisplayTitleMessage message = DisplayTitleMessage.newBuilder()
            .setTitleType(TitleType.TITLE_TYPE_TITLE)
            .setAdventureJsonMessage(AdventureUtil.toJson(
                Component.text()
                    .content("Hello, player!")
                    .color(NamedTextColor.GREEN)
                    .decorate(TextDecoration.BOLD)
                    .build()
            ))
            .setScale(1.0f)
            .setFadeInTime(ProtobufUtil.createDurationProto(Duration.ofMillis(1500)))
            .setDisplayTime(ProtobufUtil.createDurationProto(Duration.ofMillis(250)))
            .setFadeOutTime(ProtobufUtil.createDurationProto(Duration.ofMillis(300)))
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void displayTitleInterpolatedExample(Player viewer) {
        DisplayTitleMessage message = DisplayTitleMessage.newBuilder()
            .setTitleType(TitleType.TITLE_TYPE_TITLE)
            .setAdventureJsonMessage(AdventureUtil.toJson(
                Component.text()
                    .content("This title expands!")
                    .color(NamedTextColor.GREEN)
                    .decorate(TextDecoration.BOLD)
                    .build()
            ))
            .setScale(0.1f)
            .setInterpolationScale(1.0f)
            .setInterpolationRate(0.01f)
            .setFadeInTime(ProtobufUtil.createDurationProto(Duration.ofMillis(5000)))
            .setDisplayTime(ProtobufUtil.createDurationProto(Duration.ofMillis(250)))
            .setFadeOutTime(ProtobufUtil.createDurationProto(Duration.ofMillis(300)))
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetTitlesExample(Player viewer) {
        ResetTitlesMessage message = ResetTitlesMessage.getDefaultInstance();
        ProtobufPacketUtil.sendPacket(viewer, message);
    }

}

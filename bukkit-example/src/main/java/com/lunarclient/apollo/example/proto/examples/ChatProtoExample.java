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

import com.lunarclient.apollo.chat.v1.DisplayLiveChatMessageMessage;
import com.lunarclient.apollo.chat.v1.RemoveLiveChatMessageMessage;
import com.lunarclient.apollo.example.common.modules.impl.ChatExample;
import com.lunarclient.apollo.example.proto.AdventureUtil;
import com.lunarclient.apollo.example.proto.ProtobufPacketUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class ChatProtoExample extends ChatExample {

    private int countdown = 5;

    @Override
    public void displayLiveChatMessageExample() {
        DisplayLiveChatMessageMessage message = DisplayLiveChatMessageMessage.newBuilder()
            .setAdventureJsonLines(AdventureUtil.toJson(
                Component.text("Game starting in ", NamedTextColor.GREEN)
                    .append(Component.text(this.countdown, NamedTextColor.BLUE)))
            )
            .setMessageId(13)
            .build();

        if (--this.countdown == 0) {
            this.countdown = 5;
        }

        ProtobufPacketUtil.broadcastPacket(message);
    }

    @Override
    public void removeLiveChatMessageExample() {
        RemoveLiveChatMessageMessage message = RemoveLiveChatMessageMessage.newBuilder()
            .setMessageId(13)
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

}

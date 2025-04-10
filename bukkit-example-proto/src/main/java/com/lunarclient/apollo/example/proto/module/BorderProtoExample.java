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
package com.lunarclient.apollo.example.proto.module;

import com.lunarclient.apollo.border.v1.DisplayBorderMessage;
import com.lunarclient.apollo.border.v1.RemoveBorderMessage;
import com.lunarclient.apollo.border.v1.ResetBordersMessage;
import com.lunarclient.apollo.example.module.impl.BorderExample;
import com.lunarclient.apollo.example.proto.util.ProtobufPacketUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufUtil;
import java.awt.Color;
import org.bukkit.entity.Player;

public class BorderProtoExample extends BorderExample {

    @Override
    public void displayBorderExample(Player viewer) {
        DisplayBorderMessage message = DisplayBorderMessage.newBuilder()
            .setId("pvp-tagged-spawn")
            .setWorld("world")
            .setCancelEntry(true)
            .setCancelExit(true)
            .setCanShrinkOrExpand(false)
            .setColor(ProtobufUtil.createColorProto(Color.RED))
            .setBounds(ProtobufUtil.createCuboid2DProto(-50, -50, 50, 50))
            .setDurationTicks(1000)
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void removeBorderExample(Player viewer) {
        RemoveBorderMessage message = RemoveBorderMessage.newBuilder()
            .setId("pvp-tagged-spawn")
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetBordersExample(Player viewer) {
        ResetBordersMessage message = ResetBordersMessage.getDefaultInstance();
        ProtobufPacketUtil.sendPacket(viewer, message);
    }

}

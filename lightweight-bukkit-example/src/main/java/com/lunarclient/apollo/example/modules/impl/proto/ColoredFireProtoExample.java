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

import com.lunarclient.apollo.coloredfire.v1.OverrideColoredFireMessage;
import com.lunarclient.apollo.coloredfire.v1.ResetColoredFireMessage;
import com.lunarclient.apollo.coloredfire.v1.ResetColoredFiresMessage;
import com.lunarclient.apollo.example.modules.impl.ColoredFireExample;
import com.lunarclient.apollo.example.utilities.ProtobufPacketUtil;
import com.lunarclient.apollo.example.utilities.ProtobufUtil;
import java.awt.Color;
import java.util.UUID;
import org.bukkit.entity.Player;

public class ColoredFireProtoExample extends ColoredFireExample {

    @Override
    public void overrideColoredFireExample(UUID burningPlayer) {
        OverrideColoredFireMessage message = OverrideColoredFireMessage.newBuilder()
            .setPlayerUuid(ProtobufUtil.createUuidProto(burningPlayer))
            .setColor(ProtobufUtil.createColorProto(Color.BLUE))
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

    @Override
    public void resetColoredFireExample(UUID burningPlayer) {
        ResetColoredFireMessage message = ResetColoredFireMessage.newBuilder()
            .setPlayerUuid(ProtobufUtil.createUuidProto(burningPlayer))
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

    @Override
    public void resetColoredFiresExample(Player viewer) {
        ResetColoredFiresMessage message = ResetColoredFiresMessage.getDefaultInstance();
        ProtobufPacketUtil.sendPacket(viewer, message);
    }

}

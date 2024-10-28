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

import com.lunarclient.apollo.cooldown.v1.DisplayCooldownMessage;
import com.lunarclient.apollo.cooldown.v1.RemoveCooldownMessage;
import com.lunarclient.apollo.cooldown.v1.ResetCooldownsMessage;
import com.lunarclient.apollo.example.common.modules.impl.CooldownExample;
import com.lunarclient.apollo.example.proto.ProtobufPacketUtil;
import com.lunarclient.apollo.example.proto.ProtobufUtil;
import java.time.Duration;
import org.bukkit.entity.Player;

public class CooldownProtoExample extends CooldownExample {

    @Override
    public void displayCooldownItemExample(Player viewer) {
        DisplayCooldownMessage message = DisplayCooldownMessage.newBuilder()
            .setName("enderpearl-cooldown")
            .setDuration(ProtobufUtil.createDurationProto(Duration.ofSeconds(15)))
            .setIcon(ProtobufUtil.createItemStackIconProto("ENDER_PEARL", 0, 0))
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void displayCooldownResourceExample(Player viewer) {
        DisplayCooldownMessage message = DisplayCooldownMessage.newBuilder()
            .setName("lunar-cooldown")
            .setDuration(ProtobufUtil.createDurationProto(Duration.ofSeconds(15)))
            .setIcon(ProtobufUtil.createSimpleResourceLocationIconProto("lunar:logo/logo-200x182.svg", 12))
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void removeCooldownExample(Player viewer) {
        RemoveCooldownMessage enderpearlMessage = RemoveCooldownMessage.newBuilder()
            .setName("enderpearl-cooldown")
            .build();

        ProtobufPacketUtil.sendPacket(viewer, enderpearlMessage);
    }

    @Override
    public void resetCooldownsExample(Player viewer) {
        ResetCooldownsMessage message = ResetCooldownsMessage.getDefaultInstance();
        ProtobufPacketUtil.sendPacket(viewer, message);
    }

}

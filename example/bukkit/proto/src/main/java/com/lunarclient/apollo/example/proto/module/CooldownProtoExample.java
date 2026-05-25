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

import com.lunarclient.apollo.common.v1.Icon;
import com.lunarclient.apollo.cooldown.v1.CooldownStyle;
import com.lunarclient.apollo.cooldown.v1.DisplayCooldownMessage;
import com.lunarclient.apollo.cooldown.v1.RemoveCooldownMessage;
import com.lunarclient.apollo.cooldown.v1.ResetCooldownsMessage;
import com.lunarclient.apollo.example.module.impl.CooldownExample;
import com.lunarclient.apollo.example.proto.util.ProtobufPacketUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufUtil;
import java.awt.Color;
import java.time.Duration;
import java.util.UUID;
import org.bukkit.entity.Player;

public class CooldownProtoExample extends CooldownExample {

    @Override
    public void displayCooldownItemExample(Player viewer) {
        DisplayCooldownMessage message = DisplayCooldownMessage.newBuilder()
            .setName("enderpearl-cooldown")
            .setDuration(ProtobufUtil.createDurationProto(Duration.ofSeconds(15)))
            .setIcon(Icon.newBuilder()
                .setItemStack(ProtobufUtil.createItemStackIconProto("ENDER_PEARL", 0))
                .build())
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void displayCooldownWithStyleExample(Player viewer) {
        DisplayCooldownMessage message = DisplayCooldownMessage.newBuilder()
            .setName("book-cooldown")
            .setDuration(ProtobufUtil.createDurationProto(Duration.ofSeconds(30)))
            .setIcon(Icon.newBuilder()
                .setItemStack(ProtobufUtil.createItemStackIconProto("BOOK", 0))
                .build())
            .setStyle(CooldownStyle.newBuilder()
                .setCircleStartColor(ProtobufUtil.createColorProto(new Color(255, 85, 85))) // ApolloColors.RED
                .setCircleEndColor(ProtobufUtil.createColorProto(new Color(85, 255, 85))) // ApolloColors.GREEN
                .setCircleEdgeColor(ProtobufUtil.createColorProto(new Color(85, 85, 85))) // ApolloColors.DAR_GRAY
                .setTextColor(ProtobufUtil.createColorProto(new Color(255, 85, 255))) // ApolloColors.LIGHT_PURPLE
                .build())
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void displayCooldownWithPlayerTextureExample(Player viewer) {
        DisplayCooldownMessage message = DisplayCooldownMessage.newBuilder()
            .setName("player-head-cooldown")
            .setDuration(ProtobufUtil.createDurationProto(Duration.ofSeconds(15)))
            .setIcon(Icon.newBuilder()
                .setItemStack(ProtobufUtil.createItemStackIconProto(
                    "PLAYER_HEAD", 0, null,
                    ProtobufUtil.createProfileProto(
                        UUID.fromString("f17627d8-1a97-487b-92ea-c04f413394bd"),
                        "e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWQ4MjUwNWJjZjNiYTU5YzJiZTdlMmQzNmY0ZTJiZGE4MzZmMmZkMTk0YjYyMTJhMmExYzRiNGEyYTQ3MWUifX19",
                        ""
                    )
                ))
                .build())
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void displayCooldownResourceExample(Player viewer) {
        DisplayCooldownMessage message = DisplayCooldownMessage.newBuilder()
            .setName("lunar-cooldown")
            .setDuration(ProtobufUtil.createDurationProto(Duration.ofSeconds(15)))
            .setIcon(Icon.newBuilder()
                .setSimpleResourceLocation(ProtobufUtil.createSimpleResourceLocationIconProto("lunar:logo/logo-64x64.png", 24))
                .build())
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

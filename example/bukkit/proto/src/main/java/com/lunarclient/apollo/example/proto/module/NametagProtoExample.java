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

import com.google.common.collect.Lists;
import com.lunarclient.apollo.example.module.impl.NametagExample;
import com.lunarclient.apollo.example.proto.util.AdventureUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufPacketUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufUtil;
import com.lunarclient.apollo.nametag.v1.OverrideNametagMessage;
import com.lunarclient.apollo.nametag.v1.ResetNametagMessage;
import com.lunarclient.apollo.nametag.v1.ResetNametagsMessage;
import java.util.List;
import java.util.stream.Collectors;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

public class NametagProtoExample extends NametagExample {

    @Override
    public void overrideNametagExample(Player target) {
        List<String> lines = Lists.newArrayList(
                Component.text()
                    .content("[StaffMode]")
                    .decorate(TextDecoration.ITALIC)
                    .color(NamedTextColor.GRAY)
                    .build(),
                Component.text()
                    .content(target.getName())
                    .color(NamedTextColor.RED)
                    .build()
                )
            .stream().map(AdventureUtil::toJson)
            .collect(Collectors.toList());

        OverrideNametagMessage message = OverrideNametagMessage.newBuilder()
            .setPlayerUuid(ProtobufUtil.createUuidProto(target.getUniqueId()))
            .addAllAdventureJsonLines(lines)
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

    @Override
    public void resetNametagExample(Player target) {
        ResetNametagMessage message = ResetNametagMessage.newBuilder()
            .setPlayerUuid(ProtobufUtil.createUuidProto(target.getUniqueId()))
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

    @Override
    public void resetNametagsExample(Player viewer) {
        ResetNametagsMessage message = ResetNametagsMessage.getDefaultInstance();
        ProtobufPacketUtil.sendPacket(viewer, message);
    }

}

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

import com.google.common.collect.Lists;
import com.lunarclient.apollo.example.modules.impl.HologramExample;
import com.lunarclient.apollo.example.utilities.AdventureUtil;
import com.lunarclient.apollo.example.utilities.ProtobufPacketUtil;
import com.lunarclient.apollo.example.utilities.ProtobufUtil;
import com.lunarclient.apollo.hologram.v1.DisplayHologramMessage;
import com.lunarclient.apollo.hologram.v1.RemoveHologramMessage;
import com.lunarclient.apollo.hologram.v1.ResetHologramsMessage;
import java.util.List;
import java.util.stream.Collectors;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class HologramProtoExample extends HologramExample {

    @Override
    public void displayHologramExample() {
        List<String> lines = Lists.newArrayList(
                Component.text()
                    .content("Welcome to my server!")
                    .color(NamedTextColor.RED)
                    .decorate(TextDecoration.BOLD, TextDecoration.UNDERLINED)
                    .build(),
                Component.text()
                    .content("Type /help to get started!")
                    .build()
            )
            .stream().map(AdventureUtil::toJson)
            .collect(Collectors.toList());

        DisplayHologramMessage message = DisplayHologramMessage.newBuilder()
            .setId("welcome-hologram")
            .setLocation(ProtobufUtil.createLocationProto(
                new Location(Bukkit.getWorld("world"), 5, 105, 0)
            ))
            .addAllAdventureJsonLines(lines)
            .setShowThroughWalls(true)
            .setShowShadow(false)
            .setShowBackground(true)
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

    @Override
    public void removeHologramExample() {
        RemoveHologramMessage message = RemoveHologramMessage.newBuilder()
            .setId("welcome-hologram")
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

    @Override
    public void resetHologramsExample(Player viewer) {
        ResetHologramsMessage message = ResetHologramsMessage.getDefaultInstance();
        ProtobufPacketUtil.sendPacket(viewer, message);
    }

}

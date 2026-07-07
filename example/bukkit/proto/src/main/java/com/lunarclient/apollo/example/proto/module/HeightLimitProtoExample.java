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

import com.lunarclient.apollo.example.module.impl.HeightLimitExample;
import com.lunarclient.apollo.example.proto.util.AdventureUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufPacketUtil;
import com.lunarclient.apollo.heightlimit.v1.OverrideHeightLimitMessage;
import com.lunarclient.apollo.heightlimit.v1.RemoveHeightLimitMessage;
import com.lunarclient.apollo.heightlimit.v1.ResetHeightLimitsMessage;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class HeightLimitProtoExample extends HeightLimitExample {

    @Override
    public void overrideHeightLimitExample(Player viewer) {
        OverrideHeightLimitMessage message = OverrideHeightLimitMessage.newBuilder()
            .setWorld(viewer.getWorld().getName())
            .setLimit(100)
            .setDisplayNameAdventureJsonLines(AdventureUtil.toJson(
                Component.text()
                    .content("Spawn")
                    .color(NamedTextColor.GOLD)
                    .build()
            ))
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void removeHeightLimitExample(Player viewer) {
        RemoveHeightLimitMessage message = RemoveHeightLimitMessage.newBuilder()
            .setWorld(viewer.getWorld().getName())
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetHeightLimitsExample(Player viewer) {
        ResetHeightLimitsMessage message = ResetHeightLimitsMessage.getDefaultInstance();
        ProtobufPacketUtil.sendPacket(viewer, message);
    }

}

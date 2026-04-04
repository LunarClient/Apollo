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

import com.google.common.collect.Lists;
import com.lunarclient.apollo.example.module.impl.ServerLinkExample;
import com.lunarclient.apollo.example.proto.util.AdventureUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufPacketUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufUtil;
import com.lunarclient.apollo.serverlink.v1.AddServerLinkMessage;
import com.lunarclient.apollo.serverlink.v1.OverrideServerLinkResourceMessage;
import com.lunarclient.apollo.serverlink.v1.RemoveServerLinkMessage;
import com.lunarclient.apollo.serverlink.v1.ResetServerLinkResourceMessage;
import com.lunarclient.apollo.serverlink.v1.ResetServerLinksMessage;
import com.lunarclient.apollo.serverlink.v1.ServerLink;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class ServerLinkProtoExample extends ServerLinkExample {

    @Override
    public void overrideServerLinkResourceExample(Player viewer) {
        OverrideServerLinkResourceMessage message = OverrideServerLinkResourceMessage.newBuilder()
            .setIcon(ProtobufUtil.createResourceLocationIconProto("lunar:logo/logo-100x100.png"))
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetServerLinkResourceExample(Player viewer) {
        ResetServerLinkResourceMessage message = ResetServerLinkResourceMessage.getDefaultInstance();
        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void addServerLinkExample(Player viewer) {
        List<ServerLink> serverLinks = Lists.newArrayList(
            ServerLink.newBuilder()
                .setId("website")
                .setDisplayNameAdventureJsonLines(AdventureUtil.toJson(
                    Component.text("Website", NamedTextColor.LIGHT_PURPLE)))
                .setUrl("https://www.lunarclient.com/")
                .build(),
            ServerLink.newBuilder()
                .setId("support")
                .setDisplayNameAdventureJsonLines(AdventureUtil.toJson(
                    Component.text("Support", NamedTextColor.AQUA)))
                .setUrl("https://support.lunarclient.com/")
                .build(),
            ServerLink.newBuilder()
                .setId("status")
                .setDisplayNameAdventureJsonLines(AdventureUtil.toJson(
                    Component.text("Status", NamedTextColor.RED)))
                .setUrl("https://status.lunarclient.com/")
                .build()
        );

        AddServerLinkMessage message = AddServerLinkMessage.newBuilder()
            .addAllServerLinks(serverLinks)
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void removeServerLinkExample(Player viewer) {
        List<String> serverLinkIds = Lists.newArrayList("website", "support", "status");

        RemoveServerLinkMessage message = RemoveServerLinkMessage.newBuilder()
            .addAllServerLinkIds(serverLinkIds)
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetServerLinksExample(Player viewer) {
        ResetServerLinksMessage message = ResetServerLinksMessage.getDefaultInstance();
        ProtobufPacketUtil.sendPacket(viewer, message);
    }

}

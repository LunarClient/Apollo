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

import com.lunarclient.apollo.button.v1.ButtonContent;
import com.lunarclient.apollo.button.v1.ButtonUpdate;
import com.lunarclient.apollo.chat.v1.DisplayLiveChatMessageMessage;
import com.lunarclient.apollo.chat.v1.RemoveChatButtonMessage;
import com.lunarclient.apollo.chat.v1.RemoveLiveChatMessageMessage;
import com.lunarclient.apollo.chat.v1.ResetChatButtonsMessage;
import com.lunarclient.apollo.chat.v1.UpdateChatButtonMessage;
import com.lunarclient.apollo.common.v1.Icon;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.module.impl.ChatExample;
import com.lunarclient.apollo.example.proto.module.chatbuttons.ChannelsLayout;
import com.lunarclient.apollo.example.proto.module.chatbuttons.ChatButtonParts;
import com.lunarclient.apollo.example.proto.module.chatbuttons.StaffChatLayout;
import com.lunarclient.apollo.example.proto.util.AdventureUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufPacketUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufUtil;
import com.lunarclient.apollo.example.util.ServerUtil;
import java.util.concurrent.atomic.AtomicInteger;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ChatProtoExample extends ChatExample {

    @Override
    public void displayLiveChatMessageExample() {
        if (ServerUtil.isFolia()) {
            this.runFoliaChatMessageTask();
        } else {
            this.runBukkitChatMessageTask();
        }
    }

    private void runBukkitChatMessageTask() {
        BukkitRunnable runnable = new BukkitRunnable() {

            private int countdown = 5;

            @Override
            public void run() {
                DisplayLiveChatMessageMessage.Builder builder = DisplayLiveChatMessageMessage.newBuilder()
                    .setMessageId(13);

                if (this.countdown > 0) {
                    builder.setAdventureJsonLines(AdventureUtil.toJson(
                        Component.text("Game starting in ", NamedTextColor.GREEN)
                            .append(Component.text(this.countdown, NamedTextColor.BLUE)))
                    );

                    ProtobufPacketUtil.broadcastPacket(builder.build());
                    this.countdown--;
                } else {
                    builder.setAdventureJsonLines(AdventureUtil.toJson(
                        Component.text("Game started! ", NamedTextColor.GREEN))
                    );

                    ProtobufPacketUtil.broadcastPacket(builder.build());
                    this.cancel();
                }
            }
        };

        runnable.runTaskTimer(ApolloExamplePlugin.getInstance(), 1L, 20L);
    }

    private void runFoliaChatMessageTask() {
        AtomicInteger countdown = new AtomicInteger(5);

        Bukkit.getGlobalRegionScheduler().runAtFixedRate(ApolloExamplePlugin.getInstance(), task -> {
            DisplayLiveChatMessageMessage.Builder builder = DisplayLiveChatMessageMessage.newBuilder()
                .setMessageId(13);

            int seconds = countdown.getAndDecrement();
            if (seconds > 0) {
                builder.setAdventureJsonLines(AdventureUtil.toJson(
                    Component.text("Game starting in ", NamedTextColor.GREEN)
                        .append(Component.text(seconds, NamedTextColor.BLUE)))
                );

                ProtobufPacketUtil.broadcastPacket(builder.build());
            } else {
                builder.setAdventureJsonLines(AdventureUtil.toJson(
                    Component.text("Game started! ", NamedTextColor.GREEN))
                );

                ProtobufPacketUtil.broadcastPacket(builder.build());
                task.cancel();
            }
        }, 1L, 20L);
    }

    @Override
    public void displayChannelsLayoutExample(Player viewer) {
        ChannelsLayout.display(viewer);
    }

    @Override
    public void displayStaffChatLayoutExample(Player viewer) {
        StaffChatLayout.display(viewer);
    }

    @Override
    public void resetChatButtonsExample(Player viewer) {
        ResetChatButtonsMessage message = ResetChatButtonsMessage.getDefaultInstance();
        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void updateChatButtonExample(Player viewer) {
        ButtonUpdate update = ButtonUpdate.newBuilder()
            .setContent(ButtonContent.newBuilder()
                .addParts(ChatButtonParts.iconPart(Icon.newBuilder()
                    .setItemStack(ProtobufUtil.createItemStackIconProto("PAPER", 0))
                    .build()))
                .addParts(ChatButtonParts.textPart(Component.text("Public Chat", NamedTextColor.AQUA)))
                .setScale(1.0F)
                .build())
            .build();

        UpdateChatButtonMessage message = UpdateChatButtonMessage.newBuilder()
            .setId("public-chat")
            .setUpdate(update)
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void removeChatButtonExample(Player viewer) {
        RemoveChatButtonMessage message = RemoveChatButtonMessage.newBuilder()
            .setId("party-chat")
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void removeLiveChatMessageExample() {
        RemoveLiveChatMessageMessage message = RemoveLiveChatMessageMessage.newBuilder()
            .setMessageId(13)
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

}

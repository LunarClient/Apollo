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

import com.lunarclient.apollo.button.v1.Button;
import com.lunarclient.apollo.button.v1.ButtonContent;
import com.lunarclient.apollo.button.v1.ButtonShape;
import com.lunarclient.apollo.button.v1.ButtonSize;
import com.lunarclient.apollo.button.v1.ButtonTooltip;
import com.lunarclient.apollo.button.v1.ButtonUpdate;
import com.lunarclient.apollo.button.v1.SelectChatTabAction;
import com.lunarclient.apollo.chat.v1.ChatButton;
import com.lunarclient.apollo.chat.v1.ChatPopup;
import com.lunarclient.apollo.chat.v1.ChatPopupOption;
import com.lunarclient.apollo.chat.v1.ChatTab;
import com.lunarclient.apollo.chat.v1.DisplayChatButtonsMessage;
import com.lunarclient.apollo.chat.v1.DisplayChatPopupMessage;
import com.lunarclient.apollo.chat.v1.DisplayChatTabsMessage;
import com.lunarclient.apollo.chat.v1.DisplayLiveChatMessageMessage;
import com.lunarclient.apollo.chat.v1.RemoveChatButtonMessage;
import com.lunarclient.apollo.chat.v1.RemoveChatPopupMessage;
import com.lunarclient.apollo.chat.v1.RemoveChatTabMessage;
import com.lunarclient.apollo.chat.v1.RemoveLiveChatMessageMessage;
import com.lunarclient.apollo.chat.v1.ResetChatButtonsMessage;
import com.lunarclient.apollo.chat.v1.ResetChatPopupsMessage;
import com.lunarclient.apollo.chat.v1.ResetChatTabsMessage;
import com.lunarclient.apollo.chat.v1.UpdateChatButtonMessage;
import com.lunarclient.apollo.chat.v1.UpdateChatTabMessage;
import com.lunarclient.apollo.common.v1.Icon;
import com.lunarclient.apollo.common.v1.TextPattern;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.module.impl.ChatExample;
import com.lunarclient.apollo.example.proto.module.chatbuttons.ChannelsLayout;
import com.lunarclient.apollo.example.proto.module.chatbuttons.ChatButtonParts;
import com.lunarclient.apollo.example.proto.module.chatbuttons.StaffChatLayout;
import com.lunarclient.apollo.example.proto.util.AdventureUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufPacketUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufUtil;
import com.lunarclient.apollo.example.util.ServerUtil;
import com.lunarclient.apollo.hud.v1.HudPosition;
import java.awt.Color;
import java.time.Duration;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ChatProtoExample extends ChatExample {

    private final Map<UUID, String> duelRequestIds = new ConcurrentHashMap<>();

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

    @Override
    public void displayChatTabsExample(Player viewer) {
        DisplayChatTabsMessage tabsMessage = DisplayChatTabsMessage.newBuilder()
            .addChatTabs(ChatTab.newBuilder()
                .setId("staff")
                .setDisplayName(ButtonContent.newBuilder()
                    .addParts(ChatButtonParts.textPart(Component.text("Staff", NamedTextColor.RED)))
                    .setScale(1.0F)
                    .build())
                .addPatterns(TextPattern.newBuilder()
                    .setStartsWith("[Staff] ")
                    .setCaseSensitive(true)
                    .build())
                .addPatterns(TextPattern.newBuilder()
                    .setRegex("^\\[(Admin|Mod)\\] ")
                    .build())
                .setInputPrefix("/staffchat ")
                .setHideFromAll(true)
                .build())
            .addChatTabs(ChatTab.newBuilder()
                .setId("events")
                .setDisplayName(ButtonContent.newBuilder()
                    .addParts(ChatButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("CLOCK", 0))
                        .build()))
                    .addParts(ChatButtonParts.textPart(Component.text("Events", NamedTextColor.GOLD)))
                    .setScale(1.0F)
                    .build())
                .setTooltip(ButtonTooltip.newBuilder()
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Server events", NamedTextColor.YELLOW)))
                    .build())
                .setBackgroundColor(ProtobufUtil.createColorProto(new Color(64, 32, 0, 160)))
                .build())
            .build();

        ProtobufPacketUtil.sendPacket(viewer, tabsMessage);

        DisplayChatButtonsMessage buttonsMessage = DisplayChatButtonsMessage.newBuilder()
            .addChatButtons(ChatButton.newBuilder()
                .setButton(Button.newBuilder()
                    .setId("back-to-all")
                    .setPosition(HudPosition.newBuilder().setX(264).setY(2).build())
                    .setSize(ButtonSize.newBuilder().setWidth(56).setHeight(16).build())
                    .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                    .setBackgroundColor(ProtobufUtil.createColorProto(ChatButtonParts.BACKGROUND))
                    .setBorderColor(ProtobufUtil.createColorProto(ChatButtonParts.BORDER))
                    .setContent(ButtonContent.newBuilder()
                        .addParts(ChatButtonParts.textPart(Component.text("Back to All")))
                        .setScale(1.0F)
                        .build())
                    .setSelectChatTab(SelectChatTabAction.newBuilder()
                        .setTabId("all")
                        .build())
                    .build())
                .build())
            .build();

        ProtobufPacketUtil.sendPacket(viewer, buttonsMessage);
    }

    @Override
    public void updateChatTabExample(Player viewer) {
        ButtonUpdate update = ButtonUpdate.newBuilder()
            .setContent(ButtonContent.newBuilder()
                .addParts(ChatButtonParts.textPart(Component.text("Events (1)", NamedTextColor.GOLD)))
                .setScale(1.0F)
                .build())
            .build();

        UpdateChatTabMessage message = UpdateChatTabMessage.newBuilder()
            .setId("events")
            .setUpdate(update)
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void displayChatTabMessageExample(Player viewer) {
        DisplayLiveChatMessageMessage message = DisplayLiveChatMessageMessage.newBuilder()
            .setMessageId(14)
            .setAdventureJsonLines(AdventureUtil.toJson(
                Component.text("A KOTH event starts in 5 minutes!", NamedTextColor.GOLD)
            ))
            .setTabId("events")
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void removeChatTabExample(Player viewer) {
        RemoveChatTabMessage message = RemoveChatTabMessage.newBuilder()
            .setId("staff")
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetChatTabsExample(Player viewer) {
        ResetChatTabsMessage message = ResetChatTabsMessage.getDefaultInstance();
        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void displayChatPopupExample(Player viewer) {
        String popupId = "duel-request-" + UUID.randomUUID();
        this.duelRequestIds.put(viewer.getUniqueId(), popupId);

        DisplayChatPopupMessage message = DisplayChatPopupMessage.newBuilder()
            .setChatPopup(ChatPopup.newBuilder()
                .setId(popupId)
                .setTitleAdventureJsonLines(AdventureUtil.toJson(Component.text("Duel Request")))
                .setDescriptionAdventureJsonLines(AdventureUtil.toJson(Component.text("Steve wants to duel you.")))
                .setDisplayTime(ProtobufUtil.createDurationProto(Duration.ofSeconds(60)))
                .addOptions(ChatPopupOption.newBuilder()
                    .setId("accept")
                    .setLabelAdventureJsonLines(AdventureUtil.toJson(Component.text("Accept", NamedTextColor.GREEN)))
                    .setRunCommand("/duel accept Steve")
                    .build())
                .addOptions(ChatPopupOption.newBuilder()
                    .setId("deny")
                    .setLabelAdventureJsonLines(AdventureUtil.toJson(Component.text("Deny", NamedTextColor.RED)))
                    .build())
                .addOptions(ChatPopupOption.newBuilder()
                    .setId("reply")
                    .setLabelAdventureJsonLines(AdventureUtil.toJson(Component.text("Reply", NamedTextColor.GRAY)))
                    .setSelectChatTab(SelectChatTabAction.newBuilder()
                        .setTabId("all")
                        .setOpenChat(true)
                        .build())
                    .build())
                .build())
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void removeChatPopupExample(Player viewer) {
        String popupId = this.duelRequestIds.remove(viewer.getUniqueId());
        if (popupId == null) {
            return;
        }

        RemoveChatPopupMessage message = RemoveChatPopupMessage.newBuilder()
            .setId(popupId)
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    public void handleChatPopupResponse(Player player, String popupId) {
        // the popup is closed now, so removeChatPopupExample has nothing left to remove
        this.duelRequestIds.remove(player.getUniqueId(), popupId);
    }

    @Override
    public void resetChatPopupsExample(Player viewer) {
        ResetChatPopupsMessage message = ResetChatPopupsMessage.getDefaultInstance();
        ProtobufPacketUtil.sendPacket(viewer, message);
    }

}

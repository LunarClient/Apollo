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
package com.lunarclient.apollo.example.proto.module.chatbuttons;

import com.lunarclient.apollo.button.v1.Button;
import com.lunarclient.apollo.button.v1.ButtonContent;
import com.lunarclient.apollo.button.v1.ButtonShape;
import com.lunarclient.apollo.button.v1.ButtonSize;
import com.lunarclient.apollo.button.v1.ButtonTooltip;
import com.lunarclient.apollo.chat.v1.ChatButton;
import com.lunarclient.apollo.chat.v1.DisplayChatButtonsMessage;
import com.lunarclient.apollo.common.v1.Icon;
import com.lunarclient.apollo.example.proto.util.AdventureUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufPacketUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufUtil;
import com.lunarclient.apollo.hud.v1.HudPosition;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public final class StaffChatLayout {

    public static void display(Player viewer) {
        ChatButton staffChat = ChatButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("staff-chat")
                .setPosition(HudPosition.newBuilder().setX(0).setY(2).build())
                .setSize(ButtonSize.newBuilder().setWidth(70).setHeight(16).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(ChatButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(ChatButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(ChatButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("COMMAND_BLOCK", 0))
                        .build()))
                    .addParts(ChatButtonParts.textPart(Component.text("Staff Chat", NamedTextColor.AQUA)))
                    .setScale(1.0F)
                    .build())
                .setTooltip(ButtonTooltip.newBuilder()
                    .addAdventureJsonLines(AdventureUtil.toJson(
                        Component.text("Click to switch!", NamedTextColor.YELLOW)))
                    .build())
                .setRunCommand("/channel staff")
                .build())
            .build();

        ChatButton publicChat = ChatButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("public-chat")
                .setPosition(HudPosition.newBuilder().setX(76).setY(2).build())
                .setSize(ButtonSize.newBuilder().setWidth(78).setHeight(16).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(ChatButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(ChatButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(ChatButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("OAK_SIGN", 0))
                        .build()))
                    .addParts(ChatButtonParts.textPart(Component.text("Public Chat")))
                    .setScale(1.0F)
                    .build())
                .setTooltip(ButtonTooltip.newBuilder()
                    .addAdventureJsonLines(AdventureUtil.toJson(
                        Component.text("Click to switch!", NamedTextColor.YELLOW)))
                    .build())
                .setRunCommand("/channel public")
                .build())
            .build();

        ChatButton clearChat = ChatButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("clear-chat")
                .setPosition(HudPosition.newBuilder().setX(160).setY(2).build())
                .setSize(ButtonSize.newBuilder().setWidth(44).setHeight(16).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(ChatButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(ChatButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(ChatButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("SPONGE", 0))
                        .build()))
                    .addParts(ChatButtonParts.textPart(Component.text("Clear", NamedTextColor.YELLOW)))
                    .setScale(1.0F)
                    .build())
                .setTooltip(ButtonTooltip.newBuilder()
                    .addAdventureJsonLines(AdventureUtil.toJson(
                        Component.text("Clears the public chat", NamedTextColor.GRAY)))
                    .build())
                .setRunCommand("/clearchat")
                .build())
            .build();

        ChatButton muteChat = ChatButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("mute-chat")
                .setPosition(HudPosition.newBuilder().setX(210).setY(2).build())
                .setSize(ButtonSize.newBuilder().setWidth(44).setHeight(16).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(ChatButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(ChatButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(ChatButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("BARRIER", 0))
                        .build()))
                    .addParts(ChatButtonParts.textPart(Component.text("Mute", NamedTextColor.RED)))
                    .setScale(1.0F)
                    .build())
                .setTooltip(ButtonTooltip.newBuilder()
                    .addAdventureJsonLines(AdventureUtil.toJson(
                        Component.text("Mutes the public chat", NamedTextColor.GRAY)))
                    .build())
                .setRunCommand("/mutechat")
                .build())
            .build();

        ChatButton unmuteChat = ChatButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("unmute-chat")
                .setPosition(HudPosition.newBuilder().setX(260).setY(2).build())
                .setSize(ButtonSize.newBuilder().setWidth(56).setHeight(16).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(ChatButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(ChatButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(ChatButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("BELL", 0))
                        .build()))
                    .addParts(ChatButtonParts.textPart(Component.text("Unmute", NamedTextColor.GREEN)))
                    .setScale(1.0F)
                    .build())
                .setTooltip(ButtonTooltip.newBuilder()
                    .addAdventureJsonLines(AdventureUtil.toJson(
                        Component.text("Unmutes the public chat", NamedTextColor.GRAY)))
                    .build())
                .setRunCommand("/unmutechat")
                .build())
            .build();

        DisplayChatButtonsMessage message = DisplayChatButtonsMessage.newBuilder()
            .addChatButtons(staffChat)
            .addChatButtons(publicChat)
            .addChatButtons(clearChat)
            .addChatButtons(muteChat)
            .addChatButtons(unmuteChat)
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    private StaffChatLayout() {
    }

}

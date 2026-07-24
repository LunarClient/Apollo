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
package com.lunarclient.apollo.example.json.module.chatbuttons;

import com.google.gson.JsonObject;
import com.lunarclient.apollo.example.json.util.JsonPacketUtil;
import com.lunarclient.apollo.example.json.util.JsonUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public final class StaffChatLayout {

    public static void display(Player viewer) {
        JsonObject staffChat = ChatButtonParts.createButtonObject("staff-chat", 0, 2, 70, 16,
            "BUTTON_SHAPE_ROUNDED_SQUARE", ChatButtonParts.BACKGROUND, ChatButtonParts.BORDER);
        JsonObject staffChatButton = ChatButtonParts.button(staffChat);
        staffChatButton.add("content", ChatButtonParts.createContentObject(1.0F,
            ChatButtonParts.iconPart(JsonUtil.createItemStackIconObject("COMMAND_BLOCK", 0)),
            ChatButtonParts.textPart(Component.text("Staff Chat", NamedTextColor.AQUA))));
        staffChatButton.add("tooltip", ChatButtonParts.createTooltipObject(
            Component.text("Click to switch!", NamedTextColor.YELLOW)));
        staffChatButton.addProperty("run_command", "/channel staff");

        JsonObject publicChat = ChatButtonParts.createButtonObject("public-chat", 76, 2, 78, 16,
            "BUTTON_SHAPE_ROUNDED_SQUARE", ChatButtonParts.BACKGROUND, ChatButtonParts.BORDER);
        JsonObject publicChatButton = ChatButtonParts.button(publicChat);
        publicChatButton.add("content", ChatButtonParts.createContentObject(1.0F,
            ChatButtonParts.iconPart(JsonUtil.createItemStackIconObject("OAK_SIGN", 0)),
            ChatButtonParts.textPart(Component.text("Public Chat"))));
        publicChatButton.add("tooltip", ChatButtonParts.createTooltipObject(
            Component.text("Click to switch!", NamedTextColor.YELLOW)));
        publicChatButton.addProperty("run_command", "/channel public");

        JsonObject clearChat = ChatButtonParts.createButtonObject("clear-chat", 160, 2, 44, 16,
            "BUTTON_SHAPE_ROUNDED_SQUARE", ChatButtonParts.BACKGROUND, ChatButtonParts.BORDER);
        JsonObject clearChatButton = ChatButtonParts.button(clearChat);
        clearChatButton.add("content", ChatButtonParts.createContentObject(1.0F,
            ChatButtonParts.iconPart(JsonUtil.createItemStackIconObject("SPONGE", 0)),
            ChatButtonParts.textPart(Component.text("Clear", NamedTextColor.YELLOW))));
        clearChatButton.add("tooltip", ChatButtonParts.createTooltipObject(
            Component.text("Clears the public chat", NamedTextColor.GRAY)));
        clearChatButton.addProperty("run_command", "/clearchat");

        JsonObject muteChat = ChatButtonParts.createButtonObject("mute-chat", 210, 2, 44, 16,
            "BUTTON_SHAPE_ROUNDED_SQUARE", ChatButtonParts.BACKGROUND, ChatButtonParts.BORDER);
        JsonObject muteChatButton = ChatButtonParts.button(muteChat);
        muteChatButton.add("content", ChatButtonParts.createContentObject(1.0F,
            ChatButtonParts.iconPart(JsonUtil.createItemStackIconObject("BARRIER", 0)),
            ChatButtonParts.textPart(Component.text("Mute", NamedTextColor.RED))));
        muteChatButton.add("tooltip", ChatButtonParts.createTooltipObject(
            Component.text("Mutes the public chat", NamedTextColor.GRAY)));
        muteChatButton.addProperty("run_command", "/mutechat");

        JsonObject unmuteChat = ChatButtonParts.createButtonObject("unmute-chat", 260, 2, 56, 16,
            "BUTTON_SHAPE_ROUNDED_SQUARE", ChatButtonParts.BACKGROUND, ChatButtonParts.BORDER);
        JsonObject unmuteChatButton = ChatButtonParts.button(unmuteChat);
        unmuteChatButton.add("content", ChatButtonParts.createContentObject(1.0F,
            ChatButtonParts.iconPart(JsonUtil.createItemStackIconObject("BELL", 0)),
            ChatButtonParts.textPart(Component.text("Unmute", NamedTextColor.GREEN))));
        unmuteChatButton.add("tooltip", ChatButtonParts.createTooltipObject(
            Component.text("Unmutes the public chat", NamedTextColor.GRAY)));
        unmuteChatButton.addProperty("run_command", "/unmutechat");

        JsonPacketUtil.sendPacket(viewer, ChatButtonParts.createDisplayMessage(
            staffChat, publicChat, clearChat, muteChat, unmuteChat));
    }

    private StaffChatLayout() {
    }

}

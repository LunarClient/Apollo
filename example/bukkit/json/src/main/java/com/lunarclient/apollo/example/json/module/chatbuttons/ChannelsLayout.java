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

public final class ChannelsLayout {

    public static void display(Player viewer) {
        JsonObject teamChat = ChatButtonParts.createButtonObject("team-chat", 0, 2, 70, 16,
            "BUTTON_SHAPE_ROUNDED_SQUARE", ChatButtonParts.BACKGROUND, ChatButtonParts.BORDER);
        JsonObject teamChatButton = ChatButtonParts.button(teamChat);
        teamChatButton.add("content", ChatButtonParts.createContentObject(1.0F,
            ChatButtonParts.iconPart(JsonUtil.createItemStackIconObject("SHIELD", 0)),
            ChatButtonParts.textPart(Component.text("Team Chat", NamedTextColor.GREEN))));
        teamChatButton.add("tooltip", ChatButtonParts.createTooltipObject(
            Component.text("Click to switch!", NamedTextColor.YELLOW)));
        teamChatButton.addProperty("run_command", "/channel team");

        JsonObject publicChat = ChatButtonParts.createButtonObject("public-chat", 76, 2, 78, 16,
            "BUTTON_SHAPE_ROUNDED_SQUARE", ChatButtonParts.BACKGROUND, ChatButtonParts.BORDER);
        JsonObject publicChatButton = ChatButtonParts.button(publicChat);
        publicChatButton.add("content", ChatButtonParts.createContentObject(1.0F,
            ChatButtonParts.iconPart(JsonUtil.createItemStackIconObject("OAK_SIGN", 0)),
            ChatButtonParts.textPart(Component.text("Public Chat"))));
        publicChatButton.add("tooltip", ChatButtonParts.createTooltipObject(
            Component.text("Click to switch!", NamedTextColor.YELLOW)));
        publicChatButton.addProperty("run_command", "/channel public");

        JsonObject partyChat = ChatButtonParts.createButtonObject("party-chat", 160, 2, 76, 16,
            "BUTTON_SHAPE_ROUNDED_SQUARE", ChatButtonParts.BACKGROUND, ChatButtonParts.BORDER);
        JsonObject partyChatButton = ChatButtonParts.button(partyChat);
        partyChatButton.add("content", ChatButtonParts.createContentObject(1.0F,
            ChatButtonParts.iconPart(JsonUtil.createItemStackIconObject("FIREWORK_ROCKET", 0)),
            ChatButtonParts.textPart(Component.text("Party Chat", NamedTextColor.LIGHT_PURPLE))));
        partyChatButton.add("tooltip", ChatButtonParts.createTooltipObject(
            Component.text("Click to switch!", NamedTextColor.YELLOW)));
        partyChatButton.addProperty("run_command", "/channel party");

        JsonPacketUtil.sendPacket(viewer, ChatButtonParts.createDisplayMessage(teamChat, publicChat, partyChat));
    }

    private ChannelsLayout() {
    }

}

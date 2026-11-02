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
package com.lunarclient.apollo.example.json.module;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.json.module.chatbuttons.ChannelsLayout;
import com.lunarclient.apollo.example.json.module.chatbuttons.ChatButtonParts;
import com.lunarclient.apollo.example.json.module.chatbuttons.StaffChatLayout;
import com.lunarclient.apollo.example.json.util.AdventureUtil;
import com.lunarclient.apollo.example.json.util.JsonPacketUtil;
import com.lunarclient.apollo.example.json.util.JsonUtil;
import com.lunarclient.apollo.example.module.impl.ChatExample;
import com.lunarclient.apollo.example.util.ServerUtil;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ChatJsonExample extends ChatExample {

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
                JsonObject message = new JsonObject();
                message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.chat.v1.DisplayLiveChatMessageMessage");
                message.addProperty("message_id", 13);

                if (this.countdown > 0) {
                    message.addProperty("adventure_json_lines", AdventureUtil.toJson(
                        Component.text("Game starting in ", NamedTextColor.GREEN)
                            .append(Component.text(this.countdown, NamedTextColor.BLUE))
                    ));

                    JsonPacketUtil.broadcastPacket(message);
                    this.countdown--;
                } else {
                    message.addProperty("adventure_json_lines", AdventureUtil.toJson(
                        Component.text("Game started! ", NamedTextColor.GREEN)
                    ));

                    JsonPacketUtil.broadcastPacket(message);
                    this.cancel();
                }
            }
        };

        runnable.runTaskTimer(ApolloExamplePlugin.getInstance(), 1L, 20L);
    }

    private void runFoliaChatMessageTask() {
        AtomicInteger countdown = new AtomicInteger(5);

        Bukkit.getGlobalRegionScheduler().runAtFixedRate(ApolloExamplePlugin.getInstance(), task -> {
            JsonObject message = new JsonObject();
            message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.chat.v1.DisplayLiveChatMessageMessage");
            message.addProperty("message_id", 13);

            int seconds = countdown.getAndDecrement();
            if (seconds > 0) {
                message.addProperty("adventure_json_lines", AdventureUtil.toJson(
                    Component.text("Game starting in ", NamedTextColor.GREEN)
                        .append(Component.text(seconds, NamedTextColor.BLUE))
                ));

                JsonPacketUtil.broadcastPacket(message);
            } else {
                message.addProperty("adventure_json_lines", AdventureUtil.toJson(
                    Component.text("Game started! ", NamedTextColor.GREEN)
                ));

                JsonPacketUtil.broadcastPacket(message);
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
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.chat.v1.ResetChatButtonsMessage");

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void updateChatButtonExample(Player viewer) {
        JsonObject update = new JsonObject();
        update.add("content", ChatButtonParts.createContentObject(1.0F,
            ChatButtonParts.iconPart(JsonUtil.createItemStackIconObject("PAPER", 0)),
            ChatButtonParts.textPart(Component.text("Public Chat", NamedTextColor.AQUA))));

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.chat.v1.UpdateChatButtonMessage");
        message.addProperty("id", "public-chat");
        message.add("update", update);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void removeChatButtonExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.chat.v1.RemoveChatButtonMessage");
        message.addProperty("id", "party-chat");

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void removeLiveChatMessageExample() {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.chat.v1.RemoveLiveChatMessageMessage");
        message.addProperty("message_id", 13);

        JsonPacketUtil.broadcastPacket(message);
    }

    @Override
    public void displayChatTabsExample(Player viewer) {
        JsonArray staffPatterns = new JsonArray();
        staffPatterns.add("^\\[Staff\\] ");

        JsonObject staffTab = new JsonObject();
        staffTab.addProperty("id", "staff");
        staffTab.addProperty("display_name_adventure_json", AdventureUtil.toJson(
            Component.text("Staff", NamedTextColor.RED)
        ));
        staffTab.add("patterns", staffPatterns);
        staffTab.addProperty("input_prefix", "/staffchat ");
        staffTab.addProperty("hide_from_all", true);

        JsonObject eventsTab = new JsonObject();
        eventsTab.addProperty("id", "events");
        eventsTab.addProperty("display_name_adventure_json", AdventureUtil.toJson(
            Component.text("Events", NamedTextColor.GOLD)
        ));

        JsonArray tabs = new JsonArray();
        tabs.add(staffTab);
        tabs.add(eventsTab);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.chat.v1.DisplayChatTabsMessage");
        message.add("chat_tabs", tabs);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void displayChatTabMessageExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.chat.v1.DisplayChatTabMessageMessage");
        message.addProperty("tab_id", "events");
        message.addProperty("message_id", 14);
        message.addProperty("adventure_json_lines", AdventureUtil.toJson(
            Component.text("A KOTH event starts in 5 minutes!", NamedTextColor.GOLD)
        ));

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void removeChatTabExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.chat.v1.RemoveChatTabMessage");
        message.addProperty("id", "staff");

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetChatTabsExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.chat.v1.ResetChatTabsMessage");

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void displayChatPopupExample(Player viewer) {
        JsonObject acceptOption = new JsonObject();
        acceptOption.addProperty("id", "accept");
        acceptOption.addProperty("label_adventure_json", AdventureUtil.toJson(
            Component.text("Accept", NamedTextColor.GREEN)
        ));

        JsonObject denyOption = new JsonObject();
        denyOption.addProperty("id", "deny");
        denyOption.addProperty("label_adventure_json", AdventureUtil.toJson(
            Component.text("Deny", NamedTextColor.RED)
        ));

        JsonArray options = new JsonArray();
        options.add(acceptOption);
        options.add(denyOption);

        JsonObject popup = new JsonObject();
        popup.addProperty("id", "duel-request");
        popup.addProperty("title_adventure_json", AdventureUtil.toJson(Component.text("Duel Request")));
        popup.addProperty("description_adventure_json", AdventureUtil.toJson(
            Component.text("Steve wants to duel you.")
        ));
        popup.addProperty("display_time", JsonUtil.createDurationObject(Duration.ofSeconds(60)));
        popup.add("options", options);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.chat.v1.DisplayChatPopupMessage");
        message.add("chat_popup", popup);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void removeChatPopupExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.chat.v1.RemoveChatPopupMessage");
        message.addProperty("id", "duel-request");

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetChatPopupsExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.chat.v1.ResetChatPopupsMessage");

        JsonPacketUtil.sendPacket(viewer, message);
    }

}

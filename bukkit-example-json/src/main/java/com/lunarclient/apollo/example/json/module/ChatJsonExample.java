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
package com.lunarclient.apollo.example.json.module;

import com.google.gson.JsonObject;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.json.util.AdventureUtil;
import com.lunarclient.apollo.example.json.util.JsonPacketUtil;
import com.lunarclient.apollo.example.module.impl.ChatExample;
import com.lunarclient.apollo.example.util.ServerUtil;
import java.util.concurrent.atomic.AtomicInteger;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
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
    public void removeLiveChatMessageExample() {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.chat.v1.RemoveLiveChatMessageMessage");
        message.addProperty("message_id", 13);

        JsonPacketUtil.broadcastPacket(message);
    }

}

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

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.apollo.example.json.listener.ApolloRoundtripJsonListener;
import com.lunarclient.apollo.example.module.impl.TransferExample;
import java.util.UUID;
import org.bukkit.entity.Player;

public class TransferJsonExample extends TransferExample {

    @Override
    public void transferExample(Player player) {
        UUID requestId = UUID.randomUUID();

        JsonObject transferRequestMessage = new JsonObject();
        transferRequestMessage.addProperty("server_ip", "mc.hypixel.net");

        ApolloRoundtripJsonListener.getInstance()
            .sendRequest(player, requestId, transferRequestMessage, "lunarclient.apollo.transfer.v1.TransferRequest")
            .thenAccept(response -> {
                String message = "";
                String status = response.get("status").getAsString();

                switch (status) {
                    case "STATUS_ACCEPTED": {
                        message = "Transfer accepted! Goodbye!";
                        break;
                    }

                    case "STATUS_REJECTED": {
                        message = "Transfer rejected by client!";
                        break;
                    }
                }

                player.sendMessage(message);
            }).exceptionally(throwable -> {
                player.sendMessage("Failed to receive a response in time.");
                return null;
            });
    }

    @Override
    public void pingExample(Player player) {
        UUID requestId = UUID.randomUUID();

        JsonObject pingRequestMessage = new JsonObject();
        JsonArray serverIps = Lists.newArrayList("mc.hypixel.net", "minehut.com")
            .stream().collect(JsonArray::new, JsonArray::add, JsonArray::addAll);
        pingRequestMessage.add("server_ips", serverIps);

        ApolloRoundtripJsonListener.getInstance()
            .sendRequest(player, requestId, pingRequestMessage, "lunarclient.apollo.transfer.v1.PingRequest")
            .thenAccept(response -> {
                JsonArray pingDataList = response.getAsJsonArray("ping_data");

                for (JsonElement element : pingDataList) {
                    JsonObject pingData = element.getAsJsonObject();
                    String message = "";

                    String status = pingData.get("status").getAsString();
                    switch (status) {
                        // Displays successful ping request to display the server IP and the players ping to that server.
                        case "STATUS_SUCCESS": {
                            message = String.format("Ping to %s is %d ms.", pingData.get("server_ip").getAsString(), pingData.get("ping").getAsInt());
                            break;
                        }

                        // If the ping request times-out
                        case "STATUS_TIMED_OUT": {
                            message = String.format("Failed to ping %s", pingData.get("server_ip").getAsString());
                            break;
                        }
                    }

                    player.sendMessage(message);
                }
            }).exceptionally(throwable -> {
                player.sendMessage("Failed to receive a response in time.");
                return null;
            });
    }

}

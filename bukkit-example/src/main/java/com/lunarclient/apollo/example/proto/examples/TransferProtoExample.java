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
package com.lunarclient.apollo.example.proto.examples;

import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;
import com.lunarclient.apollo.example.common.modules.impl.TransferExample;
import com.lunarclient.apollo.example.proto.listeners.ApolloRoundtripProtoListener;
import com.lunarclient.apollo.transfer.v1.PingData;
import com.lunarclient.apollo.transfer.v1.PingRequest;
import com.lunarclient.apollo.transfer.v1.PingResponse;
import com.lunarclient.apollo.transfer.v1.TransferRequest;
import com.lunarclient.apollo.transfer.v1.TransferResponse;
import java.util.UUID;
import org.bukkit.entity.Player;

public class TransferProtoExample extends TransferExample {

    @Override
    public void transferExample(Player player) {
        UUID requestId = UUID.randomUUID();

        TransferRequest transferRequestMessage = TransferRequest.newBuilder()
            .setRequestId(ByteString.copyFromUtf8(requestId.toString()))
            .setServerIp("mc.hypixel.net")
            .build();

        ApolloRoundtripProtoListener.getInstance().sendRequest(player, requestId, transferRequestMessage, TransferResponse.class, response -> {
            String message = "";

            switch (response.getStatus()) {
                case STATUS_ACCEPTED: {
                    message = "Transfer accepted! Goodbye!";
                    break;
                }

                case STATUS_REJECTED: {
                    message = "Transfer rejected by client!";
                    break;
                }
            }

            player.sendMessage(message);
        });
    }

    @Override
    public void pingExample(Player player) {
        UUID requestId = UUID.randomUUID();

        PingRequest pingRequestMessage = PingRequest.newBuilder()
            .setRequestId(ByteString.copyFromUtf8(requestId.toString()))
            .addAllServerIps(Lists.newArrayList("mc.hypixel.net", "minehut.com"))
            .build();

        ApolloRoundtripProtoListener.getInstance().sendRequest(player, requestId, pingRequestMessage, PingResponse.class, response -> {
            for (PingData pingData : response.getPingDataList()) {
                String message = "";

                switch (pingData.getStatus()) {
                    // Displays successful ping request to display the server IP and the players ping to that server.
                    case STATUS_SUCCESS: {
                        message = String.format("Ping to %s is %d ms.", pingData.getServerIp(), pingData.getPing());
                        break;
                    }

                    // If the ping request times-out
                    case STATUS_TIMED_OUT: {
                        message = String.format("Failed to ping %s", pingData.getServerIp());
                        break;
                    }
                }

                player.sendMessage(message);
            }
        });
    }

}

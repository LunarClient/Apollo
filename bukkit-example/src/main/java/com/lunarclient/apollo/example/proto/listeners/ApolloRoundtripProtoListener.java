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
package com.lunarclient.apollo.example.proto.listeners;

import com.google.protobuf.Any;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.lunarclient.apollo.example.proto.ProtobufPacketUtil;
import com.lunarclient.apollo.transfer.v1.PingResponse;
import com.lunarclient.apollo.transfer.v1.TransferResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class ApolloRoundtripProtoListener implements PluginMessageListener {

    @Getter
    private static ApolloRoundtripProtoListener instance;

    // Player UUID -> Packet ID -> Response Packet
    // Consider having a timeout for packets that expect a Response
    private final Map<UUID, Map<UUID, Consumer<GeneratedMessageV3>>> roundTripPacketConsumers = new HashMap<>();

    public ApolloRoundtripProtoListener() {
        instance = this;
    }

    @Override
    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {
        try {
            Any any = Any.parseFrom(bytes);

            if (any.is(PingResponse.class)) {
                PingResponse message = any.unpack(PingResponse.class);
                UUID requestId = UUID.fromString(message.getRequestId().toStringUtf8());
                this.handleResponse(player, requestId, message);
            } else if (any.is(TransferResponse.class)) {
                TransferResponse message = any.unpack(TransferResponse.class);
                UUID requestId = UUID.fromString(message.getRequestId().toStringUtf8());
                this.handleResponse(player, requestId, message);
            }

        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends GeneratedMessageV3> void sendRequest(Player player, UUID requestId, GeneratedMessageV3 request,
                                                            Class<T> response, Consumer<T> action) {
        ProtobufPacketUtil.sendPacket(player, request);

        this.roundTripPacketConsumers.computeIfAbsent(player.getUniqueId(), k -> new HashMap<>())
            .put(requestId, (Consumer<GeneratedMessageV3>) action);
    }

    private <T extends GeneratedMessageV3> void handleResponse(Player player, UUID requestId, T message) {
        Map<UUID, Consumer<GeneratedMessageV3>> consumers = this.roundTripPacketConsumers.get(player.getUniqueId());
        if (consumers == null) {
            return;
        }

        Consumer<GeneratedMessageV3> action = consumers.remove(requestId);

        if (action != null) {
            action.accept(message);
        }
    }

}

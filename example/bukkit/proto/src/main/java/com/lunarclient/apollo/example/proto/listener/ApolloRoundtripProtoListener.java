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
package com.lunarclient.apollo.example.proto.listener;

import com.google.protobuf.Any;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.proto.util.ProtobufPacketUtil;
import com.lunarclient.apollo.transfer.v1.PingResponse;
import com.lunarclient.apollo.transfer.v1.TransferResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class ApolloRoundtripProtoListener implements PluginMessageListener {

    @Getter
    private static ApolloRoundtripProtoListener instance;

    private final Map<UUID, Map<UUID, CompletableFuture<GeneratedMessageV3>>> roundTripPacketFutures = new ConcurrentHashMap<>();
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public ApolloRoundtripProtoListener(ApolloExamplePlugin plugin) {
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

    public <T extends GeneratedMessageV3> CompletableFuture<T> sendRequest(Player player, UUID requestId,
                                                                           GeneratedMessageV3 request,
                                                                           Class<T> responseType) {
        ProtobufPacketUtil.sendPacket(player, request);

        CompletableFuture<T> future = new CompletableFuture<>();

        this.roundTripPacketFutures
            .computeIfAbsent(player.getUniqueId(), k -> new ConcurrentHashMap<>())
            .put(requestId, (CompletableFuture<GeneratedMessageV3>) future);

        ScheduledFuture<?> timeoutTask = this.executorService.schedule(() ->
                future.completeExceptionally(new TimeoutException("Response timed out")),
            10, TimeUnit.SECONDS
        );

        future.whenComplete((result, throwable) -> timeoutTask.cancel(false));
        return future;
    }

    private <T extends GeneratedMessageV3> void handleResponse(Player player, UUID requestId, T message) {
        Map<UUID, CompletableFuture<GeneratedMessageV3>> futures = this.roundTripPacketFutures.get(player.getUniqueId());
        if (futures == null) {
            return;
        }

        CompletableFuture<GeneratedMessageV3> future = futures.remove(requestId);
        if (future != null) {
            future.complete(message);
        }
    }

}

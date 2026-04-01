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
package com.lunarclient.apollo.example.json.listener;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.json.util.JsonPacketUtil;
import java.nio.charset.StandardCharsets;
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
import org.jspecify.annotations.NonNull;

public class ApolloRoundtripJsonListener implements PluginMessageListener {

    private static final String TYPE_PREFIX = "type.googleapis.com/";
    private static final JsonParser JSON_PARSER = new JsonParser();

    @Getter
    private static ApolloRoundtripJsonListener instance;

    private final Map<UUID, Map<UUID, CompletableFuture<JsonObject>>> roundTripPacketFutures = new ConcurrentHashMap<>();
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public ApolloRoundtripJsonListener(ApolloExamplePlugin plugin) {
        instance = this;
    }

    @Override
    public void onPluginMessageReceived(@NonNull String channel, @NonNull Player player, byte[] bytes) {
        JsonObject payload;
        try {
            payload = JSON_PARSER.parse(new String(bytes, StandardCharsets.UTF_8)).getAsJsonObject();
        } catch (Exception e) {
            return;
        }

        if (!payload.has("@type")) {
            return;
        }

        String type = payload.get("@type").getAsString();
        if (type.startsWith(TYPE_PREFIX)) {
            type = type.substring(TYPE_PREFIX.length());
        }

        if ("lunarclient.apollo.transfer.v1.PingResponse".equals(type)
                || "lunarclient.apollo.transfer.v1.TransferResponse".equals(type)) {
            UUID requestId = UUID.fromString(payload.get("request_id").getAsString().replace("+", "-"));
            this.handleResponse(player, requestId, payload);
        }
    }

    public CompletableFuture<JsonObject> sendRequest(Player player, UUID requestId, JsonObject request, String requestType) {
        request.addProperty("@type", TYPE_PREFIX + requestType);
        request.addProperty("request_id", requestId.toString());
        JsonPacketUtil.sendPacket(player, request);

        CompletableFuture<JsonObject> future = new CompletableFuture<>();

        this.roundTripPacketFutures
            .computeIfAbsent(player.getUniqueId(), k -> new ConcurrentHashMap<>())
            .put(requestId, future);

        ScheduledFuture<?> timeoutTask = this.executorService.schedule(() ->
                future.completeExceptionally(new TimeoutException("Response timed out")),
            10, TimeUnit.SECONDS
        );

        future.whenComplete((result, throwable) -> timeoutTask.cancel(false));
        return future;
    }

    private void handleResponse(Player player, UUID requestId, JsonObject message) {
        Map<UUID, CompletableFuture<JsonObject>> futures = this.roundTripPacketFutures.get(player.getUniqueId());
        if (futures == null) {
            return;
        }

        CompletableFuture<JsonObject> future = futures.remove(requestId);
        if (future != null) {
            future.complete(message);
        }
    }

}

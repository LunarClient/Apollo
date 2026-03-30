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
package com.lunarclient.apollo.example.json.listener;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.json.util.JsonUtil;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jspecify.annotations.NonNull;

public class ApolloPacketReceiveJsonListener implements PluginMessageListener {

    private static final String TYPE_PREFIX = "type.googleapis.com/";
    private static final JsonParser JSON_PARSER = new JsonParser();

    public ApolloPacketReceiveJsonListener(ApolloExamplePlugin plugin) {
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

        if ("lunarclient.apollo.player.v1.PlayerHandshakeMessage".equals(type)) {
            this.onPlayerHandshake(payload);
        }

        // Packet Enrichment Module
        if ("lunarclient.apollo.packetenrichment.v1.PlayerAttackMessage".equals(type)) {
            this.onPlayerAttack(payload);
        } else if ("lunarclient.apollo.packetenrichment.v1.PlayerChatOpenMessage".equals(type)) {
            this.onPlayerChatOpen(payload);
        } else if ("lunarclient.apollo.packetenrichment.v1.PlayerChatCloseMessage".equals(type)) {
            this.onPlayerChatClose(payload);
        } else if ("lunarclient.apollo.packetenrichment.v1.PlayerUseItemMessage".equals(type)) {
            this.onPlayerUseItem(payload);
        } else if ("lunarclient.apollo.packetenrichment.v1.PlayerUseItemBucketMessage".equals(type)) {
            this.onPlayerUseItemBucket(payload);
        }
    }

    private void onPlayerHandshake(JsonObject message) {
        String checkoutSupport = message.get("embedded_checkout_support").getAsString();

        JsonObject minecraftVersion = message.getAsJsonObject("minecraft_version");
        String version = minecraftVersion.get("enum").getAsString();

        JsonObject lunarClientVersion = message.getAsJsonObject("lunar_client_version");
        String gitBranch = lunarClientVersion.get("git_branch").getAsString();
        String gitCommit = lunarClientVersion.get("git_commit").getAsString();
        String semVer = lunarClientVersion.get("semver").getAsString();

        for (JsonElement element : message.getAsJsonArray("installed_mods")) {
            JsonObject mod = element.getAsJsonObject();

            String modId = mod.get("id").getAsString();
            String displayName = mod.get("name").getAsString();
            String modVersion = mod.get("version").getAsString();
            String modType = mod.get("type").getAsString();
        }
    }

    private void onPlayerAttack(JsonObject message) {
        long instantiationTimeMs = JsonUtil.toJavaTimestamp(message);

        JsonObject targetInfo = message.getAsJsonObject("target_info");
        JsonObject attackerInfo = message.getAsJsonObject("attacker_info");

        this.onPlayerInfo(targetInfo);
        this.onPlayerInfo(attackerInfo);
    }

    private void onPlayerChatOpen(JsonObject message) {
        long instantiationTimeMs = JsonUtil.toJavaTimestamp(message);
        this.onPlayerInfo(message.getAsJsonObject("player_info"));
    }

    private void onPlayerChatClose(JsonObject message) {
        long instantiationTimeMs = JsonUtil.toJavaTimestamp(message);
        this.onPlayerInfo(message.getAsJsonObject("player_info"));
    }

    private void onPlayerUseItem(JsonObject message) {
        long instantiationTimeMs = JsonUtil.toJavaTimestamp(message);
        this.onPlayerInfo(message.getAsJsonObject("player_info"));

        boolean mainHand = message.get("main_hand").getAsBoolean();
    }

    private void onPlayerUseItemBucket(JsonObject message) {
        long instantiationTimeMs = JsonUtil.toJavaTimestamp(message);
        this.onPlayerInfo(message.getAsJsonObject("player_info"));

        JsonObject rayTraceResult = message.getAsJsonObject("ray_trace_result");

        if (rayTraceResult.has("block")) {
            JsonObject blockHit = rayTraceResult.getAsJsonObject("block");

            JsonObject hitLocation = blockHit.getAsJsonObject("hit_location");
            JsonObject blockLocation = blockHit.getAsJsonObject("block_location");
            String directionStr = blockHit.get("direction").getAsString();
        } else if (rayTraceResult.has("entity")) {
            JsonObject entityHit = rayTraceResult.getAsJsonObject("entity");

            JsonObject hitLocation = entityHit.getAsJsonObject("hit_location");
            String entityId = entityHit.get("entity_id").getAsString();
        } else {
            // MISS
        }
    }

    private void onPlayerInfo(JsonObject info) {
        UUID uuid = JsonUtil.toJavaUuid(info.getAsJsonObject("player_uuid"));
        Location location = JsonUtil.toBukkitPlayerLocation(info.getAsJsonObject("location"));
        boolean sneaking = info.get("sneaking").getAsBoolean();
        boolean sprinting = info.get("sprinting").getAsBoolean();
        boolean jumping = info.get("jumping").getAsBoolean();
        float forwardSpeed = info.get("forward_speed").getAsFloat();
        float strafeSpeed = info.get("strafe_speed").getAsFloat();
    }

}

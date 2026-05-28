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
import com.google.gson.JsonObject;
import com.lunarclient.apollo.example.json.util.JsonPacketUtil;
import com.lunarclient.apollo.example.json.util.JsonUtil;
import com.lunarclient.apollo.example.module.impl.CosmeticExample;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class CosmeticJsonExample extends CosmeticExample {

    @Override
    public void equipNpcCosmeticsExample(Player viewer, UUID npcUuid) {
        JsonArray cosmeticsArray = new JsonArray();

        JsonObject first = new JsonObject();
        first.addProperty("id", 434);
        cosmeticsArray.add(first);

        JsonObject second = new JsonObject();
        second.addProperty("id", 3654);
        cosmeticsArray.add(second);

        JsonObject third = new JsonObject();
        third.addProperty("id", 5095);
        JsonObject petOptions = new JsonObject();
        petOptions.addProperty("flip_shoulder", true);
        third.add("pet_options", petOptions);
        cosmeticsArray.add(third);

        JsonObject fourth = new JsonObject();
        fourth.addProperty("id", 3);
        JsonObject cloakOptions = new JsonObject();
        cloakOptions.addProperty("use_cloth_physics", true);
        fourth.add("cloak_options", cloakOptions);
        cosmeticsArray.add(fourth);

        JsonObject fifth = new JsonObject();
        fifth.addProperty("id", 3977);
        cosmeticsArray.add(fifth);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.cosmetic.v1.EquipNpcCosmeticsMessage");
        message.add("npc_uuid", JsonUtil.createUuidObject(npcUuid));
        message.add("cosmetics", cosmeticsArray);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void equipNpcCosmeticsCopyLocalExample(Player viewer, UUID npcUuid) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.cosmetic.v1.EquipNpcCosmeticsMessage");
        message.add("npc_uuid", JsonUtil.createUuidObject(npcUuid));
        message.addProperty("copy_local_cosmetics", true);

        JsonPacketUtil.broadcastPacket(message);
    }

    @Override
    public void equipNpcCosmeticsInternal(Player viewer, UUID npcUuid, List<Integer> cosmeticIds) {
        JsonArray cosmeticsArray = new JsonArray();
        for (int cosmeticId : cosmeticIds) {
            JsonObject cosmetic = new JsonObject();
            cosmetic.addProperty("id", cosmeticId);
            cosmeticsArray.add(cosmetic);
        }

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.cosmetic.v1.EquipNpcCosmeticsMessage");
        message.add("npc_uuid", JsonUtil.createUuidObject(npcUuid));
        message.add("cosmetics", cosmeticsArray);

        JsonPacketUtil.broadcastPacket(message);
    }

    @Override
    public void unequipNpcCosmeticsExample(Player viewer, UUID npcUuid) {
        List<Integer> cosmeticIds = Lists.newArrayList(434, 3654, 5095, 3, 3977);

        JsonArray cosmeticIdsArray = new JsonArray();
        cosmeticIds.forEach(cosmeticIdsArray::add);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.cosmetic.v1.UnequipNpcCosmeticsMessage");
        message.add("npc_uuid", JsonUtil.createUuidObject(npcUuid));
        message.add("cosmetic_ids", cosmeticIdsArray);

        JsonPacketUtil.broadcastPacket(message);
    }

    @Override
    public void unequipNpcCosmeticsInternal(Player viewer, UUID npcUuid, List<Integer> cosmeticIds) {
        JsonArray cosmeticIdsArray = new JsonArray();
        cosmeticIds.forEach(cosmeticIdsArray::add);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.cosmetic.v1.UnequipNpcCosmeticsMessage");
        message.add("npc_uuid", JsonUtil.createUuidObject(npcUuid));
        message.add("cosmetic_ids", cosmeticIdsArray);

        JsonPacketUtil.broadcastPacket(message);
    }

    @Override
    public void resetNpcCosmeticsExample(Player viewer, UUID npcUuid) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.cosmetic.v1.ResetNpcCosmeticsMessage");
        message.add("npc_uuid", JsonUtil.createUuidObject(npcUuid));

        JsonPacketUtil.broadcastPacket(message);
    }

    @Override
    public void startNpcEmoteExample(Player viewer, UUID npcUuid) {
        JsonObject emote = new JsonObject();
        emote.addProperty("id", 56);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.cosmetic.v1.StartNpcEmoteMessage");
        message.add("npc_uuid", JsonUtil.createUuidObject(npcUuid));
        message.add("emote", emote);

        JsonPacketUtil.broadcastPacket(message);
    }

    @Override
    public void startNpcEmoteInternal(Player viewer, UUID npcUuid, int emoteId, int metadata) {
        JsonObject emote = new JsonObject();
        emote.addProperty("id", emoteId);
        emote.addProperty("metadata", metadata);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.cosmetic.v1.StartNpcEmoteMessage");
        message.add("npc_uuid", JsonUtil.createUuidObject(npcUuid));
        message.add("emote", emote);

        JsonPacketUtil.broadcastPacket(message);
    }

    @Override
    public void stopNpcEmoteExample(Player viewer, UUID npcUuid) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.cosmetic.v1.StopNpcEmoteMessage");
        message.add("npc_uuid", JsonUtil.createUuidObject(npcUuid));

        JsonPacketUtil.broadcastPacket(message);
    }

    @Override
    public void resetNpcEmotesExample() {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.cosmetic.v1.ResetNpcEmotesMessage");

        JsonPacketUtil.broadcastPacket(message);
    }

    @Override
    public void displaySprayExample(Player viewer, int sprayId) {
        Block block = viewer.getTargetBlockExact(10);
        if (block == null) {
            return;
        }

        Material material = block.getType();
        if (material.isAir() || !material.isSolid()) {
            return;
        }

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.cosmetic.v1.DisplaySprayMessage");
        message.addProperty("spray_id", sprayId);
        message.add("location", JsonUtil.createBlockLocationObject(block.getLocation()));
        message.addProperty("facing", "UP");
        message.addProperty("rotation", 0f);
        message.addProperty("duration", JsonUtil.createDurationObject(Duration.ofSeconds(60)));

        JsonPacketUtil.broadcastPacket(message);
    }

    @Override
    public void removeSprayExample(int sprayId) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.cosmetic.v1.RemoveSprayMessage");
        message.addProperty("spray_id", sprayId);

        JsonPacketUtil.broadcastPacket(message);
    }

    @Override
    public void resetSpraysExample() {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.cosmetic.v1.ResetSpraysMessage");

        JsonPacketUtil.broadcastPacket(message);
    }

}

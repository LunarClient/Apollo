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
package com.lunarclient.apollo.example.proto.module;

import com.google.common.collect.Lists;
import com.lunarclient.apollo.cosmetic.v1.CloakOptions;
import com.lunarclient.apollo.cosmetic.v1.Cosmetic;
import com.lunarclient.apollo.cosmetic.v1.DisplaySprayMessage;
import com.lunarclient.apollo.cosmetic.v1.Emote;
import com.lunarclient.apollo.cosmetic.v1.EquipNpcCosmeticsMessage;
import com.lunarclient.apollo.cosmetic.v1.PetOptions;
import com.lunarclient.apollo.cosmetic.v1.RemoveSprayMessage;
import com.lunarclient.apollo.cosmetic.v1.ResetNpcCosmeticsMessage;
import com.lunarclient.apollo.cosmetic.v1.ResetNpcEmotesMessage;
import com.lunarclient.apollo.cosmetic.v1.ResetSpraysMessage;
import com.lunarclient.apollo.cosmetic.v1.StartNpcEmoteMessage;
import com.lunarclient.apollo.cosmetic.v1.StopNpcEmoteMessage;
import com.lunarclient.apollo.cosmetic.v1.UnequipNpcCosmeticsMessage;
import com.lunarclient.apollo.example.module.impl.CosmeticExample;
import com.lunarclient.apollo.example.proto.util.ProtobufPacketUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufUtil;
import com.lunarclient.apollo.packetenrichment.v1.Direction;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class CosmeticProtoExample extends CosmeticExample {

    @Override
    public void equipNpcCosmeticsExample(Player viewer, UUID npcUuid) {
        List<Cosmetic> cosmetics = Lists.newArrayList(
            Cosmetic.newBuilder()
                .setId(434)
                .build(),
            Cosmetic.newBuilder()
                .setId(3654)
                .build(),
            Cosmetic.newBuilder()
                .setId(5095)
                .setPetOptions(PetOptions.newBuilder()
                    .setFlipShoulder(true)
                    .build())
                .build(),
            Cosmetic.newBuilder()
                .setId(3)
                .setCloakOptions(CloakOptions.newBuilder()
                    .setUseClothPhysics(true)
                    .build())
                .build(),
            Cosmetic.newBuilder()
                .setId(3977)
                .build()
        );

        EquipNpcCosmeticsMessage message = EquipNpcCosmeticsMessage.newBuilder()
            .setNpcUuid(ProtobufUtil.createUuidProto(npcUuid))
            .addAllCosmetics(cosmetics)
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void equipNpcCosmeticsCopyLocalExample(Player viewer, UUID npcUuid) {
        EquipNpcCosmeticsMessage message = EquipNpcCosmeticsMessage.newBuilder()
            .setNpcUuid(ProtobufUtil.createUuidProto(npcUuid))
            .setCopyLocalCosmetics(true)
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

    @Override
    public void equipNpcCosmeticsInternal(Player viewer, UUID npcUuid, List<Integer> cosmeticIds) {
        List<Cosmetic> cosmetics = cosmeticIds.stream()
            .map(id -> Cosmetic.newBuilder().setId(id).build())
            .collect(Collectors.toList());

        EquipNpcCosmeticsMessage message = EquipNpcCosmeticsMessage.newBuilder()
            .setNpcUuid(ProtobufUtil.createUuidProto(npcUuid))
            .addAllCosmetics(cosmetics)
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

    @Override
    public void unequipNpcCosmeticsExample(Player viewer, UUID npcUuid) {
        List<Integer> cosmeticIds = Lists.newArrayList(434, 3654, 5095, 3, 3977);

        UnequipNpcCosmeticsMessage message = UnequipNpcCosmeticsMessage.newBuilder()
            .setNpcUuid(ProtobufUtil.createUuidProto(npcUuid))
            .addAllCosmeticIds(cosmeticIds)
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

    @Override
    public void unequipNpcCosmeticsInternal(Player viewer, UUID npcUuid, List<Integer> cosmeticIds) {
        UnequipNpcCosmeticsMessage message = UnequipNpcCosmeticsMessage.newBuilder()
            .setNpcUuid(ProtobufUtil.createUuidProto(npcUuid))
            .addAllCosmeticIds(cosmeticIds)
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

    @Override
    public void resetNpcCosmeticsExample(Player viewer, UUID npcUuid) {
        ResetNpcCosmeticsMessage message = ResetNpcCosmeticsMessage.newBuilder()
            .setNpcUuid(ProtobufUtil.createUuidProto(npcUuid))
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

    @Override
    public void startNpcEmoteExample(Player viewer, UUID npcUuid) {
        StartNpcEmoteMessage message = StartNpcEmoteMessage.newBuilder()
            .setNpcUuid(ProtobufUtil.createUuidProto(npcUuid))
            .setEmote(Emote.newBuilder()
                .setId(56)
                .build())
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

    @Override
    public void startNpcEmoteInternal(Player viewer, UUID npcUuid, int emoteId, int metadata) {
        StartNpcEmoteMessage message = StartNpcEmoteMessage.newBuilder()
            .setNpcUuid(ProtobufUtil.createUuidProto(npcUuid))
            .setEmote(Emote.newBuilder()
                .setId(emoteId)
                .setMetadata(metadata)
                .build())
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

    @Override
    public void stopNpcEmoteExample(Player viewer, UUID npcUuid) {
        StopNpcEmoteMessage message = StopNpcEmoteMessage.newBuilder()
            .setNpcUuid(ProtobufUtil.createUuidProto(npcUuid))
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

    @Override
    public void resetNpcEmotesExample() {
        ResetNpcEmotesMessage message = ResetNpcEmotesMessage.getDefaultInstance();
        ProtobufPacketUtil.broadcastPacket(message);
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

        DisplaySprayMessage message = DisplaySprayMessage.newBuilder()
            .setSprayId(sprayId)
            .setLocation(ProtobufUtil.createBlockLocationProto(block.getLocation()))
            .setFacing(Direction.DIRECTION_UP)
            .setRotation(0f)
            .setDuration(ProtobufUtil.createDurationProto(Duration.ofSeconds(60)))
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

    @Override
    public void removeSprayExample(int sprayId) {
        RemoveSprayMessage message = RemoveSprayMessage.newBuilder()
            .setSprayId(sprayId)
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

    @Override
    public void resetSpraysExample() {
        ProtobufPacketUtil.broadcastPacket(ResetSpraysMessage.getDefaultInstance());
    }

}

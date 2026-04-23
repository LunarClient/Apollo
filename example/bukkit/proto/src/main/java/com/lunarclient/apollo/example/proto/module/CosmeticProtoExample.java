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

import com.lunarclient.apollo.cosmetic.v1.EquipNpcCosmeticsMessage;
import com.lunarclient.apollo.cosmetic.v1.ResetNpcCosmeticsMessage;
import com.lunarclient.apollo.cosmetic.v1.UnequipNpcCosmeticsMessage;
import com.lunarclient.apollo.example.module.impl.CosmeticExample;
import com.lunarclient.apollo.example.proto.util.ProtobufPacketUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufUtil;
import java.util.List;
import java.util.UUID;

public class CosmeticProtoExample extends CosmeticExample {

    @Override
    public void equipNpcCosmeticsExample(int entityId, UUID npcUuid, List<Integer> cosmeticIds) {
        EquipNpcCosmeticsMessage message = EquipNpcCosmeticsMessage.newBuilder()
            .setEntityId(ProtobufUtil.createEntityIdProto(entityId, npcUuid))
            .addAllCosmeticIds(cosmeticIds)
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

    @Override
    public void unequipNpcCosmeticsExample(int entityId, UUID npcUuid, List<Integer> cosmeticIds) {
        UnequipNpcCosmeticsMessage message = UnequipNpcCosmeticsMessage.newBuilder()
            .setEntityId(ProtobufUtil.createEntityIdProto(entityId, npcUuid))
            .addAllCosmeticIds(cosmeticIds)
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

    @Override
    public void resetNpcCosmeticsExample(int entityId, UUID npcUuid) {
        ResetNpcCosmeticsMessage message = ResetNpcCosmeticsMessage.newBuilder()
            .setEntityId(ProtobufUtil.createEntityIdProto(entityId, npcUuid))
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

}

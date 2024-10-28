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

import com.lunarclient.apollo.example.common.modules.impl.LimbExample;
import com.lunarclient.apollo.example.proto.ProtobufPacketUtil;
import com.lunarclient.apollo.example.proto.ProtobufUtil;
import com.lunarclient.apollo.limb.v1.ArmorPiece;
import com.lunarclient.apollo.limb.v1.BodyPart;
import com.lunarclient.apollo.limb.v1.HideArmorPiecesMessage;
import com.lunarclient.apollo.limb.v1.HideBodyPartMessage;
import com.lunarclient.apollo.limb.v1.ResetArmorPiecesMessage;
import com.lunarclient.apollo.limb.v1.ResetBodyPartMessage;
import java.util.EnumSet;
import org.bukkit.entity.Player;

public class LimbProtoExample extends LimbExample {

    @Override
    public void hideArmorExample(Player viewer, Player target) {
        HideArmorPiecesMessage message = HideArmorPiecesMessage.newBuilder()
            .setPlayerUuid(ProtobufUtil.createUuidProto(target.getUniqueId()))
            .addAllArmorPieces(EnumSet.of(ArmorPiece.ARMOR_PIECE_HELMET, ArmorPiece.ARMOR_PIECE_LEGGINGS))
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetArmorExample(Player viewer, Player target) {
        ResetArmorPiecesMessage message = ResetArmorPiecesMessage.newBuilder()
            .setPlayerUuid(ProtobufUtil.createUuidProto(target.getUniqueId()))
            .addAllArmorPieces(EnumSet.of(ArmorPiece.ARMOR_PIECE_HELMET, ArmorPiece.ARMOR_PIECE_LEGGINGS))
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void hideBodyExample(Player viewer, Player target) {
        HideBodyPartMessage message = HideBodyPartMessage.newBuilder()
            .setPlayerUuid(ProtobufUtil.createUuidProto(target.getUniqueId()))
            .addAllBodyParts(EnumSet.of(BodyPart.BODY_PART_HEAD, BodyPart.BODY_PART_RIGHT_ARM))
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);;
    }

    @Override
    public void resetBodyExample(Player viewer, Player target) {
        ResetBodyPartMessage message = ResetBodyPartMessage.newBuilder()
            .setPlayerUuid(ProtobufUtil.createUuidProto(target.getUniqueId()))
            .addAllBodyParts(EnumSet.of(BodyPart.BODY_PART_HEAD, BodyPart.BODY_PART_RIGHT_ARM))
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);;
    }

}

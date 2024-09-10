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
package com.lunarclient.apollo.example.modules.impl.json;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lunarclient.apollo.example.modules.impl.LimbExample;
import com.lunarclient.apollo.example.utilities.JsonPacketUtil;
import com.lunarclient.apollo.example.utilities.JsonUtil;
import org.bukkit.entity.Player;

public class LimbJsonExample extends LimbExample {

    @Override
    public void hideArmorExample(Player viewer, Player target) {
        // 1 = helmet, 2 = chestplate, 3 = leggings, 4 = boots
        JsonArray armorPieces = Lists.newArrayList(1, 3).stream()
            .collect(JsonArray::new, JsonArray::add, JsonArray::addAll);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.limb.v1.HideArmorPiecesMessage");
        message.add("player_uuid", JsonUtil.createUuidObject(target.getUniqueId()));
        message.add("armor_pieces", armorPieces);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetArmorExample(Player viewer, Player target) {
        // 1 = helmet, 2 = chestplate, 3 = leggings, 4 = boots
        JsonArray armorPieces = Lists.newArrayList(1, 3).stream()
            .collect(JsonArray::new, JsonArray::add, JsonArray::addAll);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.limb.v1.ResetArmorPiecesMessage");
        message.add("player_uuid", JsonUtil.createUuidObject(target.getUniqueId()));
        message.add("armor_pieces", armorPieces);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void hideBodyExample(Player viewer, Player target) {
        // 1 = head, 2 = torso, 3 = left arm, 4 = right arm, 5 = left leg, 6 = right leg
        JsonArray bodyParts = Lists.newArrayList(1, 4).stream()
            .collect(JsonArray::new, JsonArray::add, JsonArray::addAll);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.limb.v1.HideBodyPartMessage");
        message.add("player_uuid", JsonUtil.createUuidObject(target.getUniqueId()));
        message.add("body_parts", bodyParts);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetBodyExample(Player viewer, Player target) {
        // 1 = head, 2 = torso, 3 = left arm, 4 = right arm, 5 = left leg, 6 = right leg
        JsonArray bodyParts = Lists.newArrayList(1, 4).stream()
            .collect(JsonArray::new, JsonArray::add, JsonArray::addAll);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.limb.v1.ResetBodyPartMessage");
        message.add("player_uuid", JsonUtil.createUuidObject(target.getUniqueId()));
        message.add("body_parts", bodyParts);

        JsonPacketUtil.sendPacket(viewer, message);
    }

}

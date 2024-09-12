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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lunarclient.apollo.example.modules.impl.EntityExample;
import com.lunarclient.apollo.example.utilities.JsonPacketUtil;
import com.lunarclient.apollo.example.utilities.JsonUtil;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

// DONE
public class EntityJsonExample extends EntityExample {

    @Override
    public void overrideRainbowSheepExample(Player viewer) {
        JsonArray entityIds = viewer.getWorld().getEntitiesByClass(Sheep.class).stream()
            .map(JsonUtil::createEntityIdObject)
            .collect(JsonArray::new, JsonArray::add, JsonArray::addAll);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.entity.v1.OverrideRainbowSheepMessage");
        message.add("entity_ids", entityIds);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetRainbowSheepExample(Player viewer) {
        JsonArray entityIds = viewer.getWorld().getEntitiesByClass(Sheep.class).stream()
            .map(JsonUtil::createEntityIdObject)
            .collect(JsonArray::new, JsonArray::add, JsonArray::addAll);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.entity.v1.ResetRainbowSheepMessage");
        message.add("entity_ids", entityIds);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void flipEntityExample(Player viewer) {
        JsonArray entityIds = viewer.getWorld()
            .getNearbyEntities(viewer.getLocation(), 10, 10, 10)
            .stream().filter(entity -> entity instanceof Cow)
            .map(JsonUtil::createEntityIdObject)
            .collect(JsonArray::new, JsonArray::add, JsonArray::addAll);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.entity.v1.FlipEntityMessage");
        message.add("entity_ids", entityIds);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetFlippedEntityExample(Player viewer) {
        JsonArray entityIds = viewer.getWorld()
            .getNearbyEntities(viewer.getLocation(), 10, 10, 10)
            .stream().filter(entity -> entity instanceof Cow)
            .map(JsonUtil::createEntityIdObject)
            .collect(JsonArray::new, JsonArray::add, JsonArray::addAll);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.entity.v1.ResetFlipedEntityMessage");
        message.add("entity_ids", entityIds);

        JsonPacketUtil.sendPacket(viewer, message);
    }

}

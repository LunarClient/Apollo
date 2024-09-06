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
import com.lunarclient.apollo.example.modules.impl.NametagExample;
import com.lunarclient.apollo.example.utilities.JsonPacketUtil;
import com.lunarclient.apollo.example.utilities.JsonUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

public class NametagJsonExample extends NametagExample {

    @Override
    public void overrideNametagExample(Player target) {
        JsonArray lines = Lists.newArrayList(
                Component.text()
                    .content("[StaffMode]")
                    .decorate(TextDecoration.ITALIC)
                    .color(NamedTextColor.GRAY)
                    .build(),
                Component.text()
                    .content(target.getName())
                    .color(NamedTextColor.RED)
                    .build()
            )
            .stream().map(JsonUtil::toJson)
            .collect(JsonArray::new, JsonArray::add, JsonArray::addAll);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.nametag.v1.OverrideNametagMessage");
        message.add("uuid", JsonUtil.createUuidObject(target.getUniqueId()));
        message.add("adventure_json_lines", lines);

        JsonPacketUtil.broadcastPacket(message);
    }

    @Override
    public void resetNametagExample(Player target) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.nametag.v1.ResetNametagMessage");
        message.add("uuid", JsonUtil.createUuidObject(target.getUniqueId()));

        JsonPacketUtil.broadcastPacket(message);
    }

    @Override
    public void resetNametagsExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.nametag.v1.ResetNametagsMessage");

        JsonPacketUtil.sendPacket(viewer, message);
    }

}

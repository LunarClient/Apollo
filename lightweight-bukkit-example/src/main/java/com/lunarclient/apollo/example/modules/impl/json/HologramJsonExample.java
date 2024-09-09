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
import com.lunarclient.apollo.example.modules.impl.HologramExample;
import com.lunarclient.apollo.example.utilities.JsonPacketUtil;
import com.lunarclient.apollo.example.utilities.JsonUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

// DONE
public class HologramJsonExample extends HologramExample {

    @Override
    public void displayHologramExample() {
        JsonArray lines = Lists.newArrayList(
            Component.text()
                .content("Welcome to my server!")
                .color(NamedTextColor.RED)
                .decorate(TextDecoration.BOLD, TextDecoration.UNDERLINED)
                .build(),
            Component.text()
                .content("Type /help to get started!")
                .build()
        ).stream().map(JsonUtil::toJson)
            .collect(JsonArray::new, JsonArray::add, JsonArray::addAll);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.hologram.v1.DisplayHologramMessage");
        message.addProperty("id", "welcome-hologram");
        message.add("location", JsonUtil.createLocationObject(
            new Location(Bukkit.getWorld("world"), 5, 105, 0)
        ));
        message.add("adventure_json_lines", lines);
        message.addProperty("show_through_walls", true);
        message.addProperty("show_shadow", false);
        message.addProperty("show_background", true);

        JsonPacketUtil.broadcastPacket(message);
    }

    @Override
    public void removeHologramExample() {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.hologram.v1.RemoveHologramMessage");
        message.addProperty("id", "welcome-hologram");

        JsonPacketUtil.broadcastPacket(message);
    }

    @Override
    public void resetHologramsExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.hologram.v1.ResetHologramsMessage");

        JsonPacketUtil.broadcastPacket(message);
    }

}

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
package com.lunarclient.apollo.example.json.module;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.lunarclient.apollo.example.json.util.JsonPacketUtil;
import com.lunarclient.apollo.example.module.impl.StaffModExample;
import java.util.stream.Stream;
import org.bukkit.entity.Player;

public class StaffModJsonExample extends StaffModExample {

    @Override
    public void enableStaffModsExample(Player viewer) {
        if (!viewer.hasPermission("apollo.staff")) {
            return;
        }

        // 1 = xray
        JsonArray staffMods = Stream.of(1)
            .map(JsonPrimitive::new)
            .collect(JsonArray::new, JsonArray::add, JsonArray::addAll);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.staffmod.v1.EnableStaffModsMessage");
        message.add("staff_mods", staffMods);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void disableStaffModsExample(Player viewer) {
        // 1 = xray
        JsonArray staffMods = Stream.of(1)
            .map(JsonPrimitive::new)
            .collect(JsonArray::new, JsonArray::add, JsonArray::addAll);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.staffmod.v1.DisableStaffModsMessage");
        message.add("staff_mods", staffMods);

        JsonPacketUtil.sendPacket(viewer, message);
    }

}

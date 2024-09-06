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

import com.google.gson.JsonObject;
import com.lunarclient.apollo.example.modules.impl.BorderExample;
import com.lunarclient.apollo.example.utilities.JsonPacketUtil;
import com.lunarclient.apollo.example.utilities.JsonUtil;
import com.lunarclient.apollo.example.utilities.objects.cuboid.Cuboid2D;
import org.bukkit.entity.Player;

import java.awt.*;

public class BorderJsonExample extends BorderExample {

    @Override
    public void displayBorderExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.border.v1.DisplayBorderMessage");
        message.addProperty("id", "pvp-tagged-spawn");
        message.addProperty("world", "world");
        message.addProperty("cancel_entry", true);
        message.addProperty("cancel_exit", true);
        message.addProperty("can_shrink_or_expand", false);
        message.add("color", JsonUtil.createColorObject(Color.RED));
        message.add("bounds", JsonUtil.createCuboid2DObject(
            Cuboid2D.builder().minX(-50).minZ(-50).maxX(50).maxZ(50).build()
        ));
        message.addProperty("duration_ticks", 1000);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void removeBorderExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.border.v1.RemoveBorderMessage");
        message.addProperty("id", "pvp-tagged-spawn");

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetBordersExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.border.v1.ResetBordersMessage");

        JsonPacketUtil.sendPacket(viewer, message);
    }

}

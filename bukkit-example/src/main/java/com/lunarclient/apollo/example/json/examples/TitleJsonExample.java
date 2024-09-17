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
package com.lunarclient.apollo.example.json.examples;

import com.google.gson.JsonObject;
import com.lunarclient.apollo.example.common.modules.impl.TitleExample;
import com.lunarclient.apollo.example.json.AdventureUtil;
import com.lunarclient.apollo.example.json.JsonPacketUtil;
import com.lunarclient.apollo.example.json.JsonUtil;
import java.time.Duration;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

public class TitleJsonExample extends TitleExample {

    @Override
    public void displayTitleExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.title.v1.DisplayTitleMessage");
        message.addProperty("title_type", 1); // 1 = title, 2 = subtitle
        message.addProperty("adventure_json_message", AdventureUtil.toJson(
            Component.text()
                .content("Hello, player!")
                .color(NamedTextColor.GREEN)
                .decorate(TextDecoration.BOLD)
                .build()
        ));
        message.addProperty("scale", 0.1f);
        message.addProperty("interpolation_scale", 1.0f);
        message.addProperty("interpolation_rate", 0.01f);
        message.addProperty("fade_in_time", JsonUtil.createDurationObject(Duration.ofMillis(5000)));
        message.addProperty("display_time", JsonUtil.createDurationObject(Duration.ofMillis(250)));
        message.addProperty("fade_out_time", JsonUtil.createDurationObject(Duration.ofMillis(300)));

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetTitlesExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.title.v1.ResetTitlesMessage");

        JsonPacketUtil.sendPacket(viewer, message);
    }

}

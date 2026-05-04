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
package com.lunarclient.apollo.example.json.module;

import com.google.gson.JsonObject;
import com.lunarclient.apollo.example.json.util.AdventureUtil;
import com.lunarclient.apollo.example.json.util.JsonPacketUtil;
import com.lunarclient.apollo.example.json.util.JsonUtil;
import com.lunarclient.apollo.example.module.impl.StopwatchExample;
import java.awt.Color;
import java.time.Duration;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class StopwatchJsonExample extends StopwatchExample {

    @Override
    public void addStopwatchExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.stopwatch.v1.AddStopwatchMessage");
        message.addProperty("id", "parkour-stopwatch");
        message.addProperty("name", "Parkour");
        message.addProperty("reset_on_start", true);
        message.addProperty("prevent_modification", true);
        message.addProperty("hide_when_stopped", false);
        message.addProperty("display_format", "mm:ss");
        message.add("text_color", JsonUtil.createColorObject(new Color(0, 170, 170)));

        JsonObject stopwatchPosition = new JsonObject();
        stopwatchPosition.addProperty("x", -30);
        stopwatchPosition.addProperty("y", 30);
        message.add("hud_position", stopwatchPosition);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void removeStopwatchExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.stopwatch.v1.RemoveStopwatchMessage");
        message.addProperty("id", "parkour-stopwatch");

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void startStopwatchExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.stopwatch.v1.StartStopwatchMessage");
        message.addProperty("id", "parkour-stopwatch");

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void stopStopwatchExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.stopwatch.v1.StopStopwatchMessage");
        message.addProperty("id", "parkour-stopwatch");

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetStopwatchExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.stopwatch.v1.ResetStopwatchMessage");
        message.addProperty("id", "parkour-stopwatch");

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetStopwatchesExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.stopwatch.v1.ResetStopwatchesMessage");

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void addTimerExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.stopwatch.v1.AddTimerMessage");
        message.addProperty("id", "game-timer");
        message.addProperty("name", "Countdown");
        message.addProperty("duration", JsonUtil.createDurationObject(Duration.ofSeconds(45)));
        message.addProperty("loop", false);
        message.addProperty("prevent_modification", true);
        message.addProperty("hide_when_stopped", false);
        message.addProperty("display_format", "mm:ss");
        message.addProperty("title_text_adventure_json_lines", AdventureUtil.toJson(
            Component.text("Time's up!", NamedTextColor.RED)
        ));
        message.addProperty("in_game_notification", true);
        message.add("text_color", JsonUtil.createColorObject(new Color(255, 85, 255)));

        JsonObject timerPosition = new JsonObject();
        timerPosition.addProperty("x", -10);
        timerPosition.addProperty("y", 30);
        message.add("hud_position", timerPosition);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void removeTimerExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.stopwatch.v1.RemoveTimerMessage");
        message.addProperty("id", "game-timer");

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void startTimerExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.stopwatch.v1.StartTimerMessage");
        message.addProperty("id", "game-timer");

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void stopTimerExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.stopwatch.v1.StopTimerMessage");
        message.addProperty("id", "game-timer");

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetTimerExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.stopwatch.v1.ResetTimerMessage");
        message.addProperty("id", "game-timer");

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetTimersExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.stopwatch.v1.ResetTimersMessage");

        JsonPacketUtil.sendPacket(viewer, message);
    }

}

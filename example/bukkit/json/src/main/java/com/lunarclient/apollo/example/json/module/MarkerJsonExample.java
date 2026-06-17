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
import com.lunarclient.apollo.example.json.util.JsonPacketUtil;
import com.lunarclient.apollo.example.json.util.JsonUtil;
import com.lunarclient.apollo.example.module.impl.MarkerExample;
import java.awt.Color;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.bukkit.entity.Player;

public class MarkerJsonExample extends MarkerExample {

    @Override
    public void displayBlockMarkerExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.marker.v1.DisplayMarkerMessage");
        message.addProperty("id", "loot-chest");
        message.add("location", JsonUtil.createLocationObject(viewer.getLocation()));
        message.add("owner_id", JsonUtil.createUuidObject(viewer.getUniqueId()));
        message.addProperty("owner_name", "");

        JsonObject flag = new JsonObject();
        flag.add("interest", new JsonObject());
        message.add("flag", flag);

        JsonObject itemStack = new JsonObject();
        itemStack.addProperty("item_name", "minecraft:chest");
        JsonObject block = new JsonObject();
        block.add("item_stack", itemStack);
        JsonObject target = new JsonObject();
        target.add("block", block);
        message.add("target", target);

        message.addProperty("duration", JsonUtil.createDurationObject(Duration.ofSeconds(60)));

        JsonObject style = new JsonObject();
        style.addProperty("scale", 1.0F);
        style.addProperty("animate_marker_on_hover", true);
        style.addProperty("compact_mode", false);
        style.addProperty("text_shadow", true);
        style.addProperty("owner_suffix", "'s Marker");
        style.addProperty("owner_display", "MARKER_OWNER_DISPLAY_HEAD");
        style.addProperty("show_owner", "MARKER_DISPLAY_CONDITION_NEVER");
        style.addProperty("show_coordinates", "MARKER_DISPLAY_CONDITION_NEVER");
        style.addProperty("show_distance", "MARKER_DISPLAY_CONDITION_HOVER");
        style.addProperty("show_description", "MARKER_DISPLAY_CONDITION_ALWAYS");
        style.addProperty("description_display", "MARKER_DESCRIPTION_DISPLAY_ICON");
        message.add("style", style);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void displayPlayerMarkerExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.marker.v1.DisplayMarkerMessage");
        message.addProperty("id", "bounty");
        message.add("location", JsonUtil.createLocationObject(viewer.getLocation()));
        message.add("owner_id", JsonUtil.createUuidObject(viewer.getUniqueId()));
        message.addProperty("owner_name", viewer.getName());

        JsonObject danger = new JsonObject();
        danger.add("color", JsonUtil.createColorObject(Color.RED));
        JsonObject flag = new JsonObject();
        flag.add("danger", danger);
        message.add("flag", flag);

        JsonObject player = new JsonObject();
        player.add("uuid", JsonUtil.createUuidObject(UUID.fromString("f17627d8-1a97-487b-92ea-c04f413394bd")));
        player.addProperty("name", "ItsNature");
        JsonObject target = new JsonObject();
        target.add("player", player);
        message.add("target", target);

        message.addProperty("in_game_notification", true);

        JsonObject style = new JsonObject();
        style.addProperty("scale", 1.0F);
        style.addProperty("animate_marker_on_hover", true);
        style.addProperty("compact_mode", false);
        style.addProperty("text_shadow", true);
        style.addProperty("owner_suffix", "'s Marker");
        style.addProperty("owner_display", "MARKER_OWNER_DISPLAY_HEAD");
        style.addProperty("show_owner", "MARKER_DISPLAY_CONDITION_NEVER");
        style.addProperty("show_coordinates", "MARKER_DISPLAY_CONDITION_NEVER");
        style.addProperty("show_distance", "MARKER_DISPLAY_CONDITION_HOVER");
        style.addProperty("show_description", "MARKER_DISPLAY_CONDITION_ALWAYS");
        style.addProperty("description_display", "MARKER_DESCRIPTION_DISPLAY_ICON");
        message.add("style", style);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void displayItemMarkerExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.marker.v1.DisplayMarkerMessage");
        message.addProperty("id", "wither-loot");
        message.add("location", JsonUtil.createLocationObject(viewer.getLocation()));
        message.add("owner_id", JsonUtil.createUuidObject(viewer.getUniqueId()));
        message.addProperty("owner_name", viewer.getName());

        JsonObject flag = new JsonObject();
        flag.add("info", new JsonObject());
        message.add("flag", flag);

        JsonObject itemStack = new JsonObject();
        itemStack.addProperty("item_name", "minecraft:nether_star");
        JsonObject item = new JsonObject();
        item.add("item_stack", itemStack);
        JsonObject target = new JsonObject();
        target.add("item", item);
        message.add("target", target);

        message.addProperty("chat_notify", true);

        JsonObject style = new JsonObject();
        style.addProperty("scale", 1.0F);
        style.addProperty("animate_marker_on_hover", true);
        style.addProperty("compact_mode", false);
        style.addProperty("text_shadow", true);
        style.addProperty("owner_suffix", "'s Marker");
        style.addProperty("owner_display", "MARKER_OWNER_DISPLAY_HEAD");
        style.addProperty("show_owner", "MARKER_DISPLAY_CONDITION_NEVER");
        style.addProperty("show_coordinates", "MARKER_DISPLAY_CONDITION_NEVER");
        style.addProperty("show_distance", "MARKER_DISPLAY_CONDITION_HOVER");
        style.addProperty("show_description", "MARKER_DISPLAY_CONDITION_ALWAYS");
        style.addProperty("description_display", "MARKER_DESCRIPTION_DISPLAY_ICON");
        message.add("style", style);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void displayEntityMarkerExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.marker.v1.DisplayMarkerMessage");
        message.addProperty("id", "tutorial-npc");
        message.add("location", JsonUtil.createLocationObject(viewer.getLocation()));
        message.add("owner_id", JsonUtil.createUuidObject(UUID.randomUUID()));
        message.addProperty("owner_name", "Tutorial NPC");

        JsonObject flag = new JsonObject();
        flag.add("interest", new JsonObject());
        message.add("flag", flag);

        JsonObject entity = new JsonObject();
        entity.addProperty("entity_type", "minecraft:villager");
        JsonObject target = new JsonObject();
        target.add("entity", entity);
        message.add("target", target);

        JsonObject style = new JsonObject();
        style.addProperty("scale", 1.3F);
        style.addProperty("animate_marker_on_hover", true);
        style.addProperty("compact_mode", false);
        style.addProperty("text_shadow", true);
        style.addProperty("owner_suffix", "");
        style.addProperty("owner_display", "MARKER_OWNER_DISPLAY_NAME");
        style.addProperty("show_owner", "MARKER_DISPLAY_CONDITION_HOVER");
        style.addProperty("show_coordinates", "MARKER_DISPLAY_CONDITION_NEVER");
        style.addProperty("show_distance", "MARKER_DISPLAY_CONDITION_NEVER");
        style.addProperty("show_description", "MARKER_DISPLAY_CONDITION_HOVER");
        style.addProperty("description_display", "MARKER_DESCRIPTION_DISPLAY_ICON");
        message.add("style", style);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void removeMarkersExample(Player viewer) {
        List<String> markerIds = Arrays.asList("loot-chest", "bounty", "wither-loot", "tutorial-npc");
        for (String id : markerIds) {
            JsonObject message = new JsonObject();
            message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.marker.v1.RemoveMarkerMessage");
            message.addProperty("id", id);

            JsonPacketUtil.sendPacket(viewer, message);
        }
    }

    @Override
    public void resetMarkersExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.marker.v1.ResetMarkersMessage");

        JsonPacketUtil.sendPacket(viewer, message);
    }

}

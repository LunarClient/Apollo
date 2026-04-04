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

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lunarclient.apollo.example.json.util.AdventureUtil;
import com.lunarclient.apollo.example.json.util.JsonPacketUtil;
import com.lunarclient.apollo.example.json.util.JsonUtil;
import com.lunarclient.apollo.example.module.impl.ServerLinkExample;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class ServerLinkJsonExample extends ServerLinkExample {

    @Override
    public void overrideServerLinkResourceExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.serverlink.v1.OverrideServerLinkResourceMessage");
        message.add("icon", JsonUtil.createResourceLocationIconObject("lunar:logo/logo-100x100.png"));

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetServerLinkResourceExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.serverlink.v1.ResetServerLinkResourceMessage");

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void addServerLinkExample(Player viewer) {
        JsonArray serverLinks = Lists.newArrayList(
            this.createServerLinkObject("website", Component.text("Website", NamedTextColor.LIGHT_PURPLE), "https://www.lunarclient.com/"),
            this.createServerLinkObject("support", Component.text("Support", NamedTextColor.AQUA), "https://support.lunarclient.com/"),
            this.createServerLinkObject("status", Component.text("Status", NamedTextColor.RED), "https://status.lunarclient.com/")
        ).stream().collect(JsonArray::new, JsonArray::add, JsonArray::addAll);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.serverlink.v1.AddServerLinkMessage");
        message.add("server_links", serverLinks);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    private JsonObject createServerLinkObject(String id, Component displayName, String url) {
        JsonObject serverLinkObject = new JsonObject();
        serverLinkObject.addProperty("id", id);
        serverLinkObject.addProperty("display_name_adventure_json_lines", AdventureUtil.toJson(displayName));
        serverLinkObject.addProperty("url", url);
        return serverLinkObject;
    }

    @Override
    public void removeServerLinkExample(Player viewer) {
        JsonArray serverLinkIds = Lists.newArrayList("website", "support", "status")
            .stream().collect(JsonArray::new, JsonArray::add, JsonArray::addAll);

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.serverlink.v1.RemoveServerLinkMessage");
        message.add("server_link_ids", serverLinkIds);

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetServerLinksExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.serverlink.v1.ResetServerLinksMessage");

        JsonPacketUtil.sendPacket(viewer, message);
    }

}

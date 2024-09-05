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
package com.lunarclient.apollo.example.modules.json;

import com.google.gson.JsonObject;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import org.bukkit.entity.Player;

public class RichPresenceJsonExample {

    public void overrideServerRichPresenceExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.richpresence.v1.OverrideServerRichPresenceMessage");
        message.addProperty("game_name", "BedWars");
        message.addProperty("game_variant_name", "Solo");
        message.addProperty("game_state", "In Game");
        message.addProperty("player_state", "Playing");
        message.addProperty("map_name", "Winter");
        message.addProperty("sub_server", "BW02");
        message.addProperty("team_current_size", 3);
        message.addProperty("team_max_size", 4);

        viewer.sendPluginMessage(ApolloExamplePlugin.getPlugin(), "apollo:json", message.toString().getBytes());
    }

    public void resetServerRichPresenceExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.richpresence.v1.ResetServerRichPresenceMessage");

        viewer.sendPluginMessage(ApolloExamplePlugin.getPlugin(), "apollo:json", message.toString().getBytes());
    }

}

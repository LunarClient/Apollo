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
package com.lunarclient.apollo.example.json.module.inventorybuttons;

import com.google.gson.JsonObject;
import com.lunarclient.apollo.example.json.util.JsonPacketUtil;
import com.lunarclient.apollo.example.json.util.JsonUtil;
import java.awt.Color;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

public final class MinigameLayout {

    public static void display(Player viewer) {
        JsonObject mapInfo = InventoryButtonParts.createButtonObject("map-info",
            "INVENTORY_BUTTON_BOX_LEFT", 6, 8, 80, 26,
            "BUTTON_SHAPE_ROUNDED_SQUARE", InventoryButtonParts.BACKGROUND, InventoryButtonParts.BORDER);
        JsonObject mapInfoButton = InventoryButtonParts.button(mapInfo);
        mapInfoButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.textPart(Component.text("Map: Apollo", NamedTextColor.AQUA))));
        mapInfoButton.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text("Built by the Lunar Client Team"),
            Component.text("Released in 2024", NamedTextColor.GRAY)));

        JsonObject kills = InventoryButtonParts.createButtonObject("kills",
            "INVENTORY_BUTTON_BOX_LEFT", 6, 40, 80, 26,
            "BUTTON_SHAPE_ROUNDED_SQUARE", InventoryButtonParts.BACKGROUND, InventoryButtonParts.BORDER);
        JsonObject killsButton = InventoryButtonParts.button(kills);
        killsButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.iconPart(JsonUtil.createItemStackIconObject("IRON_SWORD", 0)),
            InventoryButtonParts.textPart(killsContent(viewer))));

        JsonObject lobby = InventoryButtonParts.createButtonObject("lobby",
            "INVENTORY_BUTTON_BOX_RIGHT", 6, 136, 80, 26,
            "BUTTON_SHAPE_ROUNDED_SQUARE",
            new Color(224, 64, 64, 128), new Color(255, 200, 200, 140));
        JsonObject lobbyButton = InventoryButtonParts.button(lobby);
        lobbyButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.textPart(Component.text("Back to Lobby"))));
        lobbyButton.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text("Back to Lobby", NamedTextColor.RED),
            Component.text("Return to the main lobby", NamedTextColor.GRAY)));
        lobbyButton.addProperty("run_command", "/lobby");

        JsonObject profile = InventoryButtonParts.createButtonObject("profile",
            "INVENTORY_BUTTON_BOX_RIGHT", 4, 4, 40, 40,
            "BUTTON_SHAPE_CIRCLE", InventoryButtonParts.BACKGROUND, InventoryButtonParts.BORDER);
        JsonObject profileButton = InventoryButtonParts.button(profile);
        profileButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.iconPart(JsonUtil.createItemStackIconObject(
                "PLAYER_HEAD", 0, null, // use "skull" for legacy with customModelData set to 3
                JsonUtil.createProfileObject(
                    UUID.fromString("f17627d8-1a97-487b-92ea-c04f413394bd"),
                    "e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWQ4MjUwNWJjZjNiYTU5YzJiZTdlMmQzNmY0ZTJiZGE4MzZmMmZkMTk0YjYyMTJhMmExYzRiNGEyYTQ3MWUifX19",
                    ""
                )
            ))));
        profileButton.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text(viewer.getName(), NamedTextColor.GOLD),
            Component.text("View your stats", NamedTextColor.GRAY)));
        profileButton.addProperty("run_command", "/profile");

        JsonObject settings = InventoryButtonParts.createButtonObject("settings",
            "INVENTORY_BUTTON_BOX_RIGHT", 48, 4, 40, 40,
            "BUTTON_SHAPE_CIRCLE", new Color(222, 160, 60, 85), new Color(255, 218, 150, 140));
        JsonObject settingsButton = InventoryButtonParts.button(settings);
        settingsButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.iconPart(JsonUtil.createItemStackIconObject("COMPARATOR", 0))));
        settingsButton.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text("Settings", NamedTextColor.WHITE),
            Component.text("Server preferences", NamedTextColor.GRAY)));
        settingsButton.addProperty("run_command", "/settings");

        JsonObject showMap = InventoryButtonParts.createButtonObject("show-map",
            "INVENTORY_BUTTON_BOX_RIGHT", 6, 52, 80, 26,
            "BUTTON_SHAPE_ROUNDED_SQUARE", InventoryButtonParts.BACKGROUND, InventoryButtonParts.BORDER);
        JsonObject showMapButton = InventoryButtonParts.button(showMap);
        showMapButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.iconPart(JsonUtil.createItemStackIconObject("FILLED_MAP", 0)),
            InventoryButtonParts.textPart(Component.text("Show Map"))));
        showMapButton.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text("Show Map", NamedTextColor.AQUA),
            Component.text("Open the fullscreen minimap view", NamedTextColor.GRAY)));
        showMapButton.addProperty("client_action", "BUTTON_CLIENT_ACTION_OPEN_MINIMAP_VIEW");

        JsonObject waypoints = InventoryButtonParts.createButtonObject("waypoints",
            "INVENTORY_BUTTON_BOX_RIGHT", 6, 84, 80, 26,
            "BUTTON_SHAPE_ROUNDED_SQUARE", InventoryButtonParts.BACKGROUND, InventoryButtonParts.BORDER);
        JsonObject waypointsButton = InventoryButtonParts.button(waypoints);
        waypointsButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.iconPart(JsonUtil.createItemStackIconObject("LODESTONE", 0)),
            InventoryButtonParts.textPart(Component.text("Waypoints"))));
        waypointsButton.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text("Waypoints", NamedTextColor.GOLD),
            Component.text("Manage your waypoints", NamedTextColor.GRAY)));
        waypointsButton.addProperty("client_action", "BUTTON_CLIENT_ACTION_OPEN_WAYPOINTS_MENU");

        JsonPacketUtil.sendPacket(viewer, InventoryButtonParts.createDisplayMessage(mapInfo, kills,
            lobby, profile, settings, showMap, waypoints));
    }

    public static void sendKillsUpdate(Player viewer) {
        JsonPacketUtil.sendPacket(viewer, InventoryButtonParts.createUpdateMessage("kills",
            InventoryButtonParts.createContentObject(1.0F,
                InventoryButtonParts.iconPart(JsonUtil.createItemStackIconObject("IRON_SWORD", 0)),
                InventoryButtonParts.textPart(killsContent(viewer))),
            null));
    }

    private static Component killsContent(Player viewer) {
        return Component.text("Kills: ", NamedTextColor.GRAY)
            .append(Component.text(viewer.getStatistic(Statistic.PLAYER_KILLS), NamedTextColor.RED));
    }

    private MinigameLayout() {
    }

}

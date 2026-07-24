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
import org.bukkit.entity.Player;

public final class MenuLayout {

    public static void display(Player viewer) {
        JsonObject shop = InventoryButtonParts.createButtonObject("shop",
            "INVENTORY_BUTTON_BOX_LEFT", 4, 4, 40, 40,
            "BUTTON_SHAPE_ROUNDED_SQUARE", InventoryButtonParts.BACKGROUND, InventoryButtonParts.BORDER);
        shop.addProperty("inventory_type", "INVENTORY_TYPE_PLAYER");
        JsonObject shopButton = InventoryButtonParts.button(shop);
        shopButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.iconPart(JsonUtil.createItemStackIconObject("EMERALD", 0))));
        shopButton.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text("Shop", NamedTextColor.GREEN),
            Component.text("Browse categories and buy items", NamedTextColor.GRAY),
            Component.text("Click to open", NamedTextColor.YELLOW)));
        shopButton.addProperty("run_command", "/shop");

        JsonObject spawn = InventoryButtonParts.createButtonObject("spawn",
            "INVENTORY_BUTTON_BOX_LEFT", 48, 4, 40, 40,
            "BUTTON_SHAPE_ROUNDED_SQUARE", InventoryButtonParts.BACKGROUND, InventoryButtonParts.BORDER);
        JsonObject spawnButton = InventoryButtonParts.button(spawn);
        spawnButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.iconPart(JsonUtil.createItemStackIconObject("RED_BED", 0))));
        spawnButton.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text("Spawn", NamedTextColor.AQUA),
            Component.text("Teleport back to spawn", NamedTextColor.GRAY),
            Component.text("Click to teleport", NamedTextColor.YELLOW)));
        spawnButton.addProperty("run_command", "/spawn");

        JsonObject warps = InventoryButtonParts.createButtonObject("warps",
            "INVENTORY_BUTTON_BOX_LEFT", 4, 48, 40, 40,
            "BUTTON_SHAPE_ROUNDED_SQUARE", InventoryButtonParts.BACKGROUND, InventoryButtonParts.BORDER);
        JsonObject warpsButton = InventoryButtonParts.button(warps);
        warpsButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.iconPart(JsonUtil.createItemStackIconObject("COMPASS", 0))));
        warpsButton.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text("Warps", NamedTextColor.AQUA),
            Component.text("Browse public warps", NamedTextColor.GRAY),
            Component.text("Click to teleport", NamedTextColor.YELLOW)));
        warpsButton.addProperty("run_command", "/warps");

        JsonObject enderChest = InventoryButtonParts.createButtonObject("enderchest",
            "INVENTORY_BUTTON_BOX_LEFT", 48, 48, 40, 40,
            "BUTTON_SHAPE_ROUNDED_SQUARE", InventoryButtonParts.BACKGROUND, InventoryButtonParts.BORDER);
        JsonObject enderChestButton = InventoryButtonParts.button(enderChest);
        enderChestButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.iconPart(JsonUtil.createItemStackIconObject("ENDER_CHEST", 0))));
        enderChestButton.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text("Ender Chest", NamedTextColor.LIGHT_PURPLE),
            Component.text("Open your personal storage", NamedTextColor.GRAY),
            Component.text("Click to open", NamedTextColor.YELLOW)));
        enderChestButton.addProperty("run_command", "/enderchest");

        JsonObject balance = InventoryButtonParts.createButtonObject("balance",
            "INVENTORY_BUTTON_BOX_LEFT", 6, 92, 80, 26,
            "BUTTON_SHAPE_ROUNDED_SQUARE", InventoryButtonParts.BACKGROUND, InventoryButtonParts.BORDER);
        JsonObject balanceButton = InventoryButtonParts.button(balance);
        balanceButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.iconPart(JsonUtil.createItemStackIconObject("GOLD_INGOT", 0)),
            InventoryButtonParts.textPart(balanceContent(viewer))));
        balanceButton.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text("Your balance", NamedTextColor.GOLD)));

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

        JsonObject vote = InventoryButtonParts.createButtonObject("vote",
            "INVENTORY_BUTTON_BOX_RIGHT", 6, 52, 80, 26,
            "BUTTON_SHAPE_ROUNDED_SQUARE", new Color(34, 204, 68, 64), new Color(190, 255, 205, 110));
        JsonObject voteButton = InventoryButtonParts.button(vote);
        voteButton.add("hovered_background_color", JsonUtil.createColorObject(new Color(34, 204, 68, 130)));
        voteButton.add("hovered_border_color", JsonUtil.createColorObject(new Color(190, 255, 205, 210)));
        voteButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.textPart(Component.text("Vote"))));
        voteButton.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text("Vote", NamedTextColor.GREEN),
            Component.text("Vote daily for rewards", NamedTextColor.GRAY)));
        voteButton.addProperty("open_url", "https://example.com/vote");

        JsonObject discord = InventoryButtonParts.createButtonObject("discord",
            "INVENTORY_BUTTON_BOX_RIGHT", 6, 84, 80, 26,
            "BUTTON_SHAPE_ROUNDED_SQUARE", new Color(88, 101, 242, 90), new Color(150, 160, 250, 140));
        JsonObject discordButton = InventoryButtonParts.button(discord);
        discordButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.textPart(Component.text("Discord"))));
        discordButton.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text("Discord", NamedTextColor.BLUE),
            Component.text("Join our community", NamedTextColor.GRAY)));
        discordButton.addProperty("open_url", "https://lunarclient.dev/discord");

        JsonObject lobby = InventoryButtonParts.createButtonObject("lobby",
            "INVENTORY_BUTTON_BOX_RIGHT", 6, 136, 80, 26,
            "BUTTON_SHAPE_ROUNDED_SQUARE", new Color(224, 64, 64, 128), new Color(255, 200, 200, 140));
        JsonObject lobbyButton = InventoryButtonParts.button(lobby);
        lobbyButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.textPart(Component.text("Back to Lobby"))));
        lobbyButton.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text("Back to Lobby", NamedTextColor.RED),
            Component.text("Return to the main lobby", NamedTextColor.GRAY)));
        lobbyButton.addProperty("run_command", "/lobby");

        JsonPacketUtil.sendPacket(viewer, InventoryButtonParts.createDisplayMessage(
            shop, spawn, warps, enderChest, balance, profile, settings, vote, discord, lobby));
    }

    public static void sendBalanceUpdate(Player viewer) {
        JsonPacketUtil.sendPacket(viewer, InventoryButtonParts.createUpdateMessage("balance",
            InventoryButtonParts.createContentObject(1.0F,
                InventoryButtonParts.iconPart(JsonUtil.createItemStackIconObject("GOLD_INGOT", 0)),
                InventoryButtonParts.textPart(balanceContent(viewer))),
            InventoryButtonParts.createTooltipObject(
                Component.text("Your balance", NamedTextColor.GOLD))));
    }

    private static Component balanceContent(Player viewer) {
        return Component.text("$" + String.format("%,d", getBalance(viewer.getUniqueId())), NamedTextColor.GOLD);
    }

    // Demo economy: replace with your economy plugin lookup (e.g. Vault)
    private static long getBalance(UUID playerIdentifier) {
        long drift = (System.currentTimeMillis() / 10_000L) % 250L;
        return 1_000L + Math.abs(playerIdentifier.hashCode() % 4_000) + drift;
    }

    private MenuLayout() {
    }

}

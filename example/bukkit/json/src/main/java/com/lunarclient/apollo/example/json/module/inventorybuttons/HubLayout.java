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

public final class HubLayout {

    public static void display(Player viewer) {
        JsonObject practice = InventoryButtonParts.createButtonObject("practice",
            "INVENTORY_BUTTON_BOX_LEFT", 6, 8, 80, 26,
            "BUTTON_SHAPE_ROUNDED_SQUARE", InventoryButtonParts.BACKGROUND, InventoryButtonParts.BORDER);
        JsonObject practiceButton = InventoryButtonParts.button(practice);
        JsonObject practiceIcon = JsonUtil.createItemStackIconObject("SPLASH_POTION", 0);
        practiceIcon.getAsJsonObject("item_stack").addProperty("potion", "healing");
        practiceButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.iconPart(practiceIcon),
            InventoryButtonParts.textPart(Component.text("Practice", NamedTextColor.LIGHT_PURPLE))));
        practiceButton.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text("Click to join!", NamedTextColor.YELLOW)));
        practiceButton.addProperty("run_command", "/server practice");

        JsonObject factions = InventoryButtonParts.createButtonObject("factions",
            "INVENTORY_BUTTON_BOX_LEFT", 6, 40, 80, 26,
            "BUTTON_SHAPE_ROUNDED_SQUARE", InventoryButtonParts.BACKGROUND, InventoryButtonParts.BORDER);
        JsonObject factionsButton = InventoryButtonParts.button(factions);
        factionsButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.iconPart(JsonUtil.createItemStackIconObject("TNT", 0)),
            InventoryButtonParts.textPart(Component.text("Factions", NamedTextColor.RED))));
        factionsButton.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text("Click to join!", NamedTextColor.YELLOW)));
        factionsButton.addProperty("run_command", "/server factions");

        JsonObject bedWars = InventoryButtonParts.createButtonObject("bedwars",
            "INVENTORY_BUTTON_BOX_LEFT", 6, 72, 80, 26,
            "BUTTON_SHAPE_ROUNDED_SQUARE", InventoryButtonParts.BACKGROUND, InventoryButtonParts.BORDER);
        JsonObject bedWarsButton = InventoryButtonParts.button(bedWars);
        bedWarsButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.iconPart(JsonUtil.createItemStackIconObject("RED_BED", 0)),
            InventoryButtonParts.textPart(Component.text("BedWars", NamedTextColor.AQUA))));
        bedWarsButton.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text("Click to join!", NamedTextColor.YELLOW)));
        bedWarsButton.addProperty("run_command", "/server bedwars");

        JsonObject soupPvP = InventoryButtonParts.createButtonObject("souppvp",
            "INVENTORY_BUTTON_BOX_LEFT", 6, 104, 80, 26,
            "BUTTON_SHAPE_ROUNDED_SQUARE", InventoryButtonParts.BACKGROUND, InventoryButtonParts.BORDER);
        JsonObject soupPvPButton = InventoryButtonParts.button(soupPvP);
        soupPvPButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.iconPart(JsonUtil.createItemStackIconObject("MUSHROOM_STEW", 0)),
            InventoryButtonParts.textPart(Component.text("SoupPvP", NamedTextColor.GOLD))));
        soupPvPButton.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text("Click to join!", NamedTextColor.YELLOW)));
        soupPvPButton.addProperty("run_command", "/server souppvp");

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

        JsonObject changelog = InventoryButtonParts.createButtonObject("changelog",
            "INVENTORY_BUTTON_BOX_RIGHT", 6, 52, 80, 26,
            "BUTTON_SHAPE_ROUNDED_SQUARE", InventoryButtonParts.BACKGROUND, InventoryButtonParts.BORDER);
        JsonObject changelogButton = InventoryButtonParts.button(changelog);
        changelogButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.iconPart(JsonUtil.createItemStackIconObject("WRITABLE_BOOK", 0)),
            InventoryButtonParts.textPart(Component.text("Changelog"))));
        changelogButton.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text("🌙  Apollo - v1.2.8", NamedTextColor.GOLD),
            Component.empty(),
            Component.text("• Released Markers Module", NamedTextColor.GRAY),
            Component.text("• Added ALLOW_DIG_AND_USE & DISABLE_BLOCK_MISS_PENALTY", NamedTextColor.GRAY),
            Component.text("  options to Combat Module", NamedTextColor.GRAY),
            Component.text("• Added configurable Server Link Button placement", NamedTextColor.GRAY),
            Component.text("• Added API option to auto-enable Staff Mods", NamedTextColor.GRAY),
            Component.text("  when unlocked via the Staff Mod Module", NamedTextColor.GRAY),
            Component.text("• Improved performance with various optimizations", NamedTextColor.GRAY),
            Component.empty(),
            Component.text("Read the full changelog at", NamedTextColor.YELLOW),
            Component.text("https://github.com/LunarClient/Apollo/releases/tag/v1.2.8", NamedTextColor.YELLOW)));
        changelogButton.addProperty("open_url", "https://github.com/LunarClient/Apollo/releases/tag/v1.2.8");

        JsonPacketUtil.sendPacket(viewer, InventoryButtonParts.createDisplayMessage(practice, factions,
            bedWars, soupPvP, profile, settings, changelog));
    }

    private HubLayout() {
    }

}

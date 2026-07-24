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
import com.lunarclient.apollo.example.util.ServerStatsUtil;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class StaffLayout {

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void display(Player viewer) {
        JsonObject players = InventoryButtonParts.createButtonObject("players",
            "INVENTORY_BUTTON_BOX_LEFT", 6, 8, 80, 26,
            "BUTTON_SHAPE_ROUNDED_SQUARE", InventoryButtonParts.BACKGROUND, InventoryButtonParts.BORDER);
        JsonObject playersButton = InventoryButtonParts.button(players);
        playersButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.textPart(playersContent())));
        playersButton.add("tooltip", playersTooltipObject());

        JsonObject tps = InventoryButtonParts.createButtonObject("tps",
            "INVENTORY_BUTTON_BOX_LEFT", 6, 40, 80, 26,
            "BUTTON_SHAPE_ROUNDED_SQUARE", InventoryButtonParts.BACKGROUND, InventoryButtonParts.BORDER);
        JsonObject tpsButton = InventoryButtonParts.button(tps);
        tpsButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.textPart(tpsContent())));
        tpsButton.add("tooltip", tpsTooltipObject());

        JsonObject cpu = InventoryButtonParts.createButtonObject("cpu",
            "INVENTORY_BUTTON_BOX_LEFT", 6, 72, 80, 26,
            "BUTTON_SHAPE_ROUNDED_SQUARE", InventoryButtonParts.BACKGROUND, InventoryButtonParts.BORDER);
        JsonObject cpuButton = InventoryButtonParts.button(cpu);
        cpuButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.textPart(cpuContent())));
        cpuButton.add("tooltip", cpuTooltipObject());

        JsonObject ram = InventoryButtonParts.createButtonObject("ram",
            "INVENTORY_BUTTON_BOX_LEFT", 6, 104, 80, 26,
            "BUTTON_SHAPE_ROUNDED_SQUARE", InventoryButtonParts.BACKGROUND, InventoryButtonParts.BORDER);
        JsonObject ramButton = InventoryButtonParts.button(ram);
        ramButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.textPart(ramContent())));
        ramButton.add("tooltip", ramTooltipObject());

        JsonObject survival = InventoryButtonParts.createButtonObject("survival",
            "INVENTORY_BUTTON_BOX_RIGHT", 6, 8, 80, 26,
            "BUTTON_SHAPE_ROUNDED_SQUARE", InventoryButtonParts.BACKGROUND, InventoryButtonParts.BORDER);
        JsonObject survivalButton = InventoryButtonParts.button(survival);
        survivalButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.iconPart(JsonUtil.createItemStackIconObject("IRON_SWORD", 0)),
            InventoryButtonParts.textPart(Component.text("Survival"))));
        survivalButton.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text("Click to switch!", NamedTextColor.YELLOW)));
        survivalButton.addProperty("run_command", "/gamemode survival");

        JsonObject creative = InventoryButtonParts.createButtonObject("creative",
            "INVENTORY_BUTTON_BOX_RIGHT", 6, 40, 80, 26,
            "BUTTON_SHAPE_ROUNDED_SQUARE", InventoryButtonParts.BACKGROUND, InventoryButtonParts.BORDER);
        JsonObject creativeButton = InventoryButtonParts.button(creative);
        creativeButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.iconPart(JsonUtil.createItemStackIconObject("GRASS_BLOCK", 0)),
            InventoryButtonParts.textPart(Component.text("Creative"))));
        creativeButton.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text("Click to switch!", NamedTextColor.YELLOW)));
        creativeButton.addProperty("run_command", "/gamemode creative");

        JsonObject adventure = InventoryButtonParts.createButtonObject("adventure",
            "INVENTORY_BUTTON_BOX_RIGHT", 6, 72, 80, 26,
            "BUTTON_SHAPE_ROUNDED_SQUARE", InventoryButtonParts.BACKGROUND, InventoryButtonParts.BORDER);
        JsonObject adventureButton = InventoryButtonParts.button(adventure);
        adventureButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.iconPart(JsonUtil.createItemStackIconObject("FILLED_MAP", 0)),
            InventoryButtonParts.textPart(Component.text("Adventure"))));
        adventureButton.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text("Click to switch!", NamedTextColor.YELLOW)));
        adventureButton.addProperty("run_command", "/gamemode adventure");

        JsonObject spectator = InventoryButtonParts.createButtonObject("spectator",
            "INVENTORY_BUTTON_BOX_RIGHT", 6, 104, 80, 26,
            "BUTTON_SHAPE_ROUNDED_SQUARE", InventoryButtonParts.BACKGROUND, InventoryButtonParts.BORDER);
        JsonObject spectatorButton = InventoryButtonParts.button(spectator);
        spectatorButton.add("content", InventoryButtonParts.createContentObject(1.0F,
            InventoryButtonParts.iconPart(JsonUtil.createItemStackIconObject("ENDER_EYE", 0)),
            InventoryButtonParts.textPart(Component.text("Spectator"))));
        spectatorButton.add("tooltip", InventoryButtonParts.createTooltipObject(
            Component.text("Click to switch!", NamedTextColor.YELLOW)));
        spectatorButton.addProperty("run_command", "/gamemode spectator");

        JsonPacketUtil.sendPacket(viewer, InventoryButtonParts.createDisplayMessage(
            players, tps, cpu, ram, survival, creative, adventure, spectator));
    }

    public static void sendUpdates(Player viewer) {
        JsonPacketUtil.sendPacket(viewer, InventoryButtonParts.createUpdateMessage("players",
            InventoryButtonParts.createContentObject(1.0F, InventoryButtonParts.textPart(playersContent())),
            playersTooltipObject()));

        JsonPacketUtil.sendPacket(viewer, InventoryButtonParts.createUpdateMessage("tps",
            InventoryButtonParts.createContentObject(1.0F, InventoryButtonParts.textPart(tpsContent())),
            tpsTooltipObject()));

        JsonPacketUtil.sendPacket(viewer, InventoryButtonParts.createUpdateMessage("cpu",
            InventoryButtonParts.createContentObject(1.0F, InventoryButtonParts.textPart(cpuContent())),
            cpuTooltipObject()));

        JsonPacketUtil.sendPacket(viewer, InventoryButtonParts.createUpdateMessage("ram",
            InventoryButtonParts.createContentObject(1.0F, InventoryButtonParts.textPart(ramContent())),
            ramTooltipObject()));
    }

    private static Component playersContent() {
        return Component.text("Players: ", NamedTextColor.GRAY)
            .append(Component.text(Bukkit.getOnlinePlayers().size(), NamedTextColor.GREEN));
    }

    private static JsonObject playersTooltipObject() {
        return InventoryButtonParts.createTooltipObject(
            Component.text("Players currently online", NamedTextColor.GRAY),
            Component.empty(),
            refreshedLine());
    }

    private static JsonObject ramTooltipObject() {
        return InventoryButtonParts.createTooltipObject(
            Component.text("Used: ", NamedTextColor.GRAY)
                .append(Component.text(ServerStatsUtil.getUsedRamMb() + " MB", NamedTextColor.WHITE)),
            Component.text("Max: ", NamedTextColor.GRAY)
                .append(Component.text(ServerStatsUtil.getMaxRamMb() + " MB", NamedTextColor.WHITE)),
            Component.empty(),
            refreshedLine());
    }

    private static Component tpsContent() {
        double[] tps = ServerStatsUtil.getTps();
        if (tps == null || tps.length == 0) {
            return Component.text("TPS: N/A", NamedTextColor.GRAY);
        }

        double recent = Math.min(20.0D, tps[0]);
        NamedTextColor color = recent >= 18.0D ? NamedTextColor.GREEN
            : recent >= 15.0D ? NamedTextColor.YELLOW : NamedTextColor.RED;
        return Component.text("TPS: ", NamedTextColor.GRAY)
            .append(Component.text(String.format("%.1f", recent), color));
    }

    private static Component cpuContent() {
        double load = ServerStatsUtil.getSystemLoadAverage();
        if (load < 0.0D) {
            return Component.text("CPU: N/A", NamedTextColor.GRAY);
        }

        double perCore = load / ServerStatsUtil.getAvailableProcessors();
        NamedTextColor color = perCore < 0.5D ? NamedTextColor.GREEN
            : perCore < 1.0D ? NamedTextColor.YELLOW : NamedTextColor.RED;
        return Component.text("CPU: ", NamedTextColor.GRAY)
            .append(Component.text(String.format("%.2f", load), color));
    }

    private static Component ramContent() {
        long used = ServerStatsUtil.getUsedRamMb();
        long max = ServerStatsUtil.getMaxRamMb();
        long percent = max <= 0 ? 0 : used * 100 / max;

        NamedTextColor color = percent < 60 ? NamedTextColor.GREEN
            : percent < 85 ? NamedTextColor.YELLOW : NamedTextColor.RED;
        return Component.text("RAM: ", NamedTextColor.GRAY)
            .append(Component.text(percent + "%", color));
    }

    private static JsonObject tpsTooltipObject() {
        double[] tps = ServerStatsUtil.getTps();
        if (tps == null || tps.length < 3) {
            return InventoryButtonParts.createTooltipObject(
                Component.text("TPS averages require a Paper based server", NamedTextColor.GRAY),
                Component.empty(),
                refreshedLine());
        }

        return InventoryButtonParts.createTooltipObject(
            tpsAverageLine("1m", tps[0]),
            tpsAverageLine("5m", tps[1]),
            tpsAverageLine("15m", tps[2]),
            Component.empty(),
            refreshedLine());
    }

    private static Component tpsAverageLine(String window, double average) {
        double tps = Math.min(20.0D, average);
        NamedTextColor color = tps >= 18.0D ? NamedTextColor.GREEN
            : tps >= 15.0D ? NamedTextColor.YELLOW : NamedTextColor.RED;
        return Component.text(window + ": ", NamedTextColor.GRAY)
            .append(Component.text(String.format("%.2f", tps), color));
    }

    private static JsonObject cpuTooltipObject() {
        double load = ServerStatsUtil.getSystemLoadAverage();
        if (load < 0.0D) {
            return InventoryButtonParts.createTooltipObject(
                Component.text("The system load average is unavailable", NamedTextColor.GRAY),
                Component.empty(),
                refreshedLine());
        }

        int cores = ServerStatsUtil.getAvailableProcessors();
        double perCore = load * 100.0D / cores;
        NamedTextColor color = load / cores < 0.5D ? NamedTextColor.GREEN
            : load / cores < 1.0D ? NamedTextColor.YELLOW : NamedTextColor.RED;
        return InventoryButtonParts.createTooltipObject(
            Component.text("System load average (last minute)", NamedTextColor.GRAY),
            Component.text("Cores: ", NamedTextColor.GRAY)
                .append(Component.text(cores, NamedTextColor.WHITE)),
            Component.text("Per core: ", NamedTextColor.GRAY)
                .append(Component.text(String.format("%.0f%%", perCore), color)),
            Component.empty(),
            refreshedLine());
    }

    private static Component refreshedLine() {
        return Component.text("Updated at " + LocalTime.now().format(TIME_FORMAT), NamedTextColor.YELLOW, TextDecoration.ITALIC);
    }

    private StaffLayout() {
    }

}

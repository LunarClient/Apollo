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
package com.lunarclient.apollo.example.proto.module.inventorybuttons;

import com.lunarclient.apollo.button.v1.Button;
import com.lunarclient.apollo.button.v1.ButtonContent;
import com.lunarclient.apollo.button.v1.ButtonShape;
import com.lunarclient.apollo.button.v1.ButtonSize;
import com.lunarclient.apollo.button.v1.ButtonUpdate;
import com.lunarclient.apollo.common.v1.Icon;
import com.lunarclient.apollo.example.proto.util.ProtobufPacketUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufUtil;
import com.lunarclient.apollo.example.util.ServerStatsUtil;
import com.lunarclient.apollo.hud.v1.HudPosition;
import com.lunarclient.apollo.inventory.v1.DisplayInventoryButtonsMessage;
import com.lunarclient.apollo.inventory.v1.InventoryButton;
import com.lunarclient.apollo.inventory.v1.InventoryButtonBox;
import com.lunarclient.apollo.inventory.v1.UpdateInventoryButtonMessage;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class StaffLayout {

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void display(Player viewer) {
        InventoryButton players = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("players")
                .setPosition(HudPosition.newBuilder().setX(6).setY(8).build())
                .setSize(ButtonSize.newBuilder().setWidth(80).setHeight(26).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(InventoryButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(InventoryButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.textPart(playersContent()))
                    .setScale(1.0F)
                    .build())
                .setTooltip(InventoryButtonParts.tooltipMessage(playersTooltip()))
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_LEFT)
            .build();

        InventoryButton tps = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("tps")
                .setPosition(HudPosition.newBuilder().setX(6).setY(40).build())
                .setSize(ButtonSize.newBuilder().setWidth(80).setHeight(26).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(InventoryButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(InventoryButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.textPart(tpsContent()))
                    .setScale(1.0F)
                    .build())
                .setTooltip(InventoryButtonParts.tooltipMessage(tpsTooltip()))
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_LEFT)
            .build();

        InventoryButton cpu = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("cpu")
                .setPosition(HudPosition.newBuilder().setX(6).setY(72).build())
                .setSize(ButtonSize.newBuilder().setWidth(80).setHeight(26).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(InventoryButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(InventoryButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.textPart(cpuContent()))
                    .setScale(1.0F)
                    .build())
                .setTooltip(InventoryButtonParts.tooltipMessage(cpuTooltip()))
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_LEFT)
            .build();

        InventoryButton ram = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("ram")
                .setPosition(HudPosition.newBuilder().setX(6).setY(104).build())
                .setSize(ButtonSize.newBuilder().setWidth(80).setHeight(26).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(InventoryButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(InventoryButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.textPart(ramContent()))
                    .setScale(1.0F)
                    .build())
                .setTooltip(InventoryButtonParts.tooltipMessage(ramTooltip()))
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_LEFT)
            .build();

        InventoryButton survival = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("survival")
                .setPosition(HudPosition.newBuilder().setX(6).setY(8).build())
                .setSize(ButtonSize.newBuilder().setWidth(80).setHeight(26).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(InventoryButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(InventoryButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("IRON_SWORD", 0))
                        .build()))
                    .addParts(InventoryButtonParts.textPart(Component.text("Survival")))
                    .setScale(1.0F)
                    .build())
                .setTooltip(InventoryButtonParts.tooltipMessage(InventoryButtonParts.tooltipJson(
                    Component.text("Click to switch!", NamedTextColor.YELLOW))))
                .setRunCommand("/gamemode survival")
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_RIGHT)
            .build();

        InventoryButton creative = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("creative")
                .setPosition(HudPosition.newBuilder().setX(6).setY(40).build())
                .setSize(ButtonSize.newBuilder().setWidth(80).setHeight(26).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(InventoryButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(InventoryButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("GRASS_BLOCK", 0))
                        .build()))
                    .addParts(InventoryButtonParts.textPart(Component.text("Creative")))
                    .setScale(1.0F)
                    .build())
                .setTooltip(InventoryButtonParts.tooltipMessage(InventoryButtonParts.tooltipJson(
                    Component.text("Click to switch!", NamedTextColor.YELLOW))))
                .setRunCommand("/gamemode creative")
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_RIGHT)
            .build();

        InventoryButton adventure = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("adventure")
                .setPosition(HudPosition.newBuilder().setX(6).setY(72).build())
                .setSize(ButtonSize.newBuilder().setWidth(80).setHeight(26).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(InventoryButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(InventoryButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("FILLED_MAP", 0))
                        .build()))
                    .addParts(InventoryButtonParts.textPart(Component.text("Adventure")))
                    .setScale(1.0F)
                    .build())
                .setTooltip(InventoryButtonParts.tooltipMessage(InventoryButtonParts.tooltipJson(
                    Component.text("Click to switch!", NamedTextColor.YELLOW))))
                .setRunCommand("/gamemode adventure")
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_RIGHT)
            .build();

        InventoryButton spectator = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("spectator")
                .setPosition(HudPosition.newBuilder().setX(6).setY(104).build())
                .setSize(ButtonSize.newBuilder().setWidth(80).setHeight(26).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(InventoryButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(InventoryButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("ENDER_EYE", 0))
                        .build()))
                    .addParts(InventoryButtonParts.textPart(Component.text("Spectator")))
                    .setScale(1.0F)
                    .build())
                .setTooltip(InventoryButtonParts.tooltipMessage(InventoryButtonParts.tooltipJson(
                    Component.text("Click to switch!", NamedTextColor.YELLOW))))
                .setRunCommand("/gamemode spectator")
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_RIGHT)
            .build();

        DisplayInventoryButtonsMessage message = DisplayInventoryButtonsMessage.newBuilder()
            .addInventoryButtons(players)
            .addInventoryButtons(tps)
            .addInventoryButtons(cpu)
            .addInventoryButtons(ram)
            .addInventoryButtons(survival)
            .addInventoryButtons(creative)
            .addInventoryButtons(adventure)
            .addInventoryButtons(spectator)
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    public static void sendUpdates(Player viewer) {
        ProtobufPacketUtil.sendPacket(viewer, UpdateInventoryButtonMessage.newBuilder()
            .setId("players")
            .setUpdate(ButtonUpdate.newBuilder()
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.textPart(playersContent()))
                    .setScale(1.0F)
                    .build())
                .setTooltip(InventoryButtonParts.tooltipMessage(playersTooltip()))
                .build())
            .build());

        ProtobufPacketUtil.sendPacket(viewer, UpdateInventoryButtonMessage.newBuilder()
            .setId("tps")
            .setUpdate(ButtonUpdate.newBuilder()
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.textPart(tpsContent()))
                    .setScale(1.0F)
                    .build())
                .setTooltip(InventoryButtonParts.tooltipMessage(tpsTooltip()))
                .build())
            .build());

        ProtobufPacketUtil.sendPacket(viewer, UpdateInventoryButtonMessage.newBuilder()
            .setId("cpu")
            .setUpdate(ButtonUpdate.newBuilder()
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.textPart(cpuContent()))
                    .setScale(1.0F)
                    .build())
                .setTooltip(InventoryButtonParts.tooltipMessage(cpuTooltip()))
                .build())
            .build());

        ProtobufPacketUtil.sendPacket(viewer, UpdateInventoryButtonMessage.newBuilder()
            .setId("ram")
            .setUpdate(ButtonUpdate.newBuilder()
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.textPart(ramContent()))
                    .setScale(1.0F)
                    .build())
                .setTooltip(InventoryButtonParts.tooltipMessage(ramTooltip()))
                .build())
            .build());
    }

    private static Component playersContent() {
        return Component.text("Players: ", NamedTextColor.GRAY)
            .append(Component.text(Bukkit.getOnlinePlayers().size(), NamedTextColor.GREEN));
    }

    private static List<String> playersTooltip() {
        return InventoryButtonParts.tooltipJson(
            Component.text("Players currently online", NamedTextColor.GRAY),
            Component.empty(),
            refreshedLine());
    }

    private static Component tpsContent() {
        double[] tps = ServerStatsUtil.getTps();
        if (tps == null || tps.length == 0) {
            return Component.text("TPS: N/A", NamedTextColor.GRAY);
        }

        double recent = Math.min(20.0D, tps[0]);
        return Component.text("TPS: ", NamedTextColor.GRAY)
            .append(Component.text(String.format("%.1f", recent), tpsColor(recent)));
    }

    private static List<String> tpsTooltip() {
        double[] tps = ServerStatsUtil.getTps();
        if (tps == null || tps.length < 3) {
            return InventoryButtonParts.tooltipJson(
                Component.text("TPS averages require a Paper based server", NamedTextColor.GRAY),
                Component.empty(),
                refreshedLine());
        }

        return InventoryButtonParts.tooltipJson(
            tpsAverageLine("1m", tps[0]),
            tpsAverageLine("5m", tps[1]),
            tpsAverageLine("15m", tps[2]),
            Component.empty(),
            refreshedLine());
    }

    private static Component tpsAverageLine(String window, double average) {
        double tps = Math.min(20.0D, average);
        return Component.text(window + ": ", NamedTextColor.GRAY)
            .append(Component.text(String.format("%.2f", tps), tpsColor(tps)));
    }

    private static NamedTextColor tpsColor(double tps) {
        if (tps >= 18.0D) {
            return NamedTextColor.GREEN;
        }

        return tps >= 15.0D ? NamedTextColor.YELLOW : NamedTextColor.RED;
    }

    private static Component cpuContent() {
        double load = ServerStatsUtil.getSystemLoadAverage();
        if (load < 0.0D) {
            return Component.text("CPU: N/A", NamedTextColor.GRAY);
        }

        return Component.text("CPU: ", NamedTextColor.GRAY)
            .append(Component.text(String.format("%.2f", load), cpuColor(load)));
    }

    private static List<String> cpuTooltip() {
        double load = ServerStatsUtil.getSystemLoadAverage();
        if (load < 0.0D) {
            return InventoryButtonParts.tooltipJson(
                Component.text("The system load average is unavailable", NamedTextColor.GRAY),
                Component.empty(),
                refreshedLine());
        }

        int cores = ServerStatsUtil.getAvailableProcessors();
        double perCore = load * 100.0D / cores;
        return InventoryButtonParts.tooltipJson(
            Component.text("System load average (last minute)", NamedTextColor.GRAY),
            Component.text("Cores: ", NamedTextColor.GRAY)
                .append(Component.text(cores, NamedTextColor.WHITE)),
            Component.text("Per core: ", NamedTextColor.GRAY)
                .append(Component.text(String.format("%.0f%%", perCore), cpuColor(load))),
            Component.empty(),
            refreshedLine());
    }

    private static NamedTextColor cpuColor(double load) {
        double perCore = load / ServerStatsUtil.getAvailableProcessors();
        if (perCore < 0.5D) {
            return NamedTextColor.GREEN;
        }

        return perCore < 1.0D ? NamedTextColor.YELLOW : NamedTextColor.RED;
    }

    private static Component ramContent() {
        long used = ServerStatsUtil.getUsedRamMb();
        long max = ServerStatsUtil.getMaxRamMb();
        long percent = max <= 0 ? 0 : used * 100 / max;

        return Component.text("RAM: ", NamedTextColor.GRAY)
            .append(Component.text(percent + "%", ramColor(percent)));
    }

    private static List<String> ramTooltip() {
        return InventoryButtonParts.tooltipJson(
            Component.text("Used: ", NamedTextColor.GRAY)
                .append(Component.text(ServerStatsUtil.getUsedRamMb() + " MB", NamedTextColor.WHITE)),
            Component.text("Max: ", NamedTextColor.GRAY)
                .append(Component.text(ServerStatsUtil.getMaxRamMb() + " MB", NamedTextColor.WHITE)),
            Component.empty(),
            refreshedLine());
    }

    private static NamedTextColor ramColor(long percent) {
        if (percent < 60) {
            return NamedTextColor.GREEN;
        }

        return percent < 85 ? NamedTextColor.YELLOW : NamedTextColor.RED;
    }

    private static Component refreshedLine() {
        return Component.text("Updated at " + LocalTime.now().format(TIME_FORMAT), NamedTextColor.YELLOW, TextDecoration.ITALIC);
    }

    private StaffLayout() {
    }

}

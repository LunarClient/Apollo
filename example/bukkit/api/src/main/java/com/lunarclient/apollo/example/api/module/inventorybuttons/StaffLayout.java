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
package com.lunarclient.apollo.example.api.module.inventorybuttons;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.button.ApolloButtonShape;
import com.lunarclient.apollo.common.button.ApolloButtonTooltip;
import com.lunarclient.apollo.common.button.action.ApolloButtonAction;
import com.lunarclient.apollo.common.button.content.ApolloButtonContent;
import com.lunarclient.apollo.common.icon.ItemStackIcon;
import com.lunarclient.apollo.common.location.HudPosition;
import com.lunarclient.apollo.example.util.ServerStatsUtil;
import com.lunarclient.apollo.module.inventory.InventoryButton;
import com.lunarclient.apollo.module.inventory.InventoryButtonBox;
import com.lunarclient.apollo.module.inventory.InventoryModule;
import com.lunarclient.apollo.module.inventory.InventoryType;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class StaffLayout {

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void display(InventoryModule inventoryModule, Player viewer) {
        // Live parts refresh automatically only while the Apollo config enables
        // modules.inventory.live-buttons.broadcast (see the inventory module docs)
        Apollo.getPlayerManager().getPlayer(viewer.getUniqueId()).ifPresent(apolloPlayer -> {
            InventoryButton players = InventoryButton.builder()
                .id("players")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.LEFT)
                .position(HudPosition.of(6, 8))
                .size(InventoryButton.SIZE_WIDE)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(InventoryButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(InventoryButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(apolloViewer -> Component.text("Players: ", NamedTextColor.GRAY)
                            .append(Component.text(Bukkit.getOnlinePlayers().size(), NamedTextColor.GREEN)),
                        Duration.ofSeconds(1))
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.live(apolloViewer -> Arrays.asList(
                        Component.text("Players currently online", NamedTextColor.GRAY),
                        Component.empty(), refreshedLine()),
                    Duration.ofSeconds(1)))
                .build();

            InventoryButton tps = InventoryButton.builder()
                .id("tps")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.LEFT)
                .position(HudPosition.of(6, 40))
                .size(InventoryButton.SIZE_WIDE)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(InventoryButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(InventoryButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(apolloViewer -> tpsContent(), Duration.ofSeconds(1))
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.live(apolloViewer -> tpsTooltip(), Duration.ofSeconds(1)))
                .build();

            InventoryButton cpu = InventoryButton.builder()
                .id("cpu")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.LEFT)
                .position(HudPosition.of(6, 72))
                .size(InventoryButton.SIZE_WIDE)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(InventoryButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(InventoryButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(apolloViewer -> cpuContent(), Duration.ofSeconds(1))
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.live(apolloViewer -> cpuTooltip(), Duration.ofSeconds(1)))
                .build();

            InventoryButton ram = InventoryButton.builder()
                .id("ram")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.LEFT)
                .position(HudPosition.of(6, 104))
                .size(InventoryButton.SIZE_WIDE)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(InventoryButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(InventoryButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(apolloViewer -> ramContent(), Duration.ofSeconds(1))
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.live(apolloViewer -> ramTooltip(), Duration.ofSeconds(1)))
                .build();

            InventoryButton survival = InventoryButton.builder()
                .id("survival")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.RIGHT)
                .position(HudPosition.of(6, 8))
                .size(InventoryButton.SIZE_WIDE)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(InventoryButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(InventoryButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("IRON_SWORD")
                        .build())
                    .append(Component.text("Survival"))
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(Component.text("Click to switch!", NamedTextColor.YELLOW)))
                .onClick(ApolloButtonAction.runCommand("/gamemode survival"))
                .build();

            InventoryButton creative = InventoryButton.builder()
                .id("creative")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.RIGHT)
                .position(HudPosition.of(6, 40))
                .size(InventoryButton.SIZE_WIDE)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(InventoryButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(InventoryButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("GRASS_BLOCK")
                        .build())
                    .append(Component.text("Creative"))
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(Component.text("Click to switch!", NamedTextColor.YELLOW)))
                .onClick(ApolloButtonAction.runCommand("/gamemode creative"))
                .build();

            InventoryButton adventure = InventoryButton.builder()
                .id("adventure")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.RIGHT)
                .position(HudPosition.of(6, 72))
                .size(InventoryButton.SIZE_WIDE)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(InventoryButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(InventoryButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("FILLED_MAP")
                        .build())
                    .append(Component.text("Adventure"))
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(Component.text("Click to switch!", NamedTextColor.YELLOW)))
                .onClick(ApolloButtonAction.runCommand("/gamemode adventure"))
                .build();

            InventoryButton spectator = InventoryButton.builder()
                .id("spectator")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.RIGHT)
                .position(HudPosition.of(6, 104))
                .size(InventoryButton.SIZE_WIDE)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(InventoryButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(InventoryButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("ENDER_EYE")
                        .build())
                    .append(Component.text("Spectator"))
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(Component.text("Click to switch!", NamedTextColor.YELLOW)))
                .onClick(ApolloButtonAction.runCommand("/gamemode spectator"))
                .build();

            inventoryModule.displayInventoryButtons(apolloPlayer, Arrays.asList(players, tps, cpu, ram,
                survival, creative, adventure, spectator));
        });
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

    private static List<Component> tpsTooltip() {
        double[] tps = ServerStatsUtil.getTps();
        if (tps == null || tps.length < 3) {
            return Arrays.asList(
                Component.text("TPS averages require a Paper based server", NamedTextColor.GRAY),
                Component.empty(),
                refreshedLine());
        }

        return Arrays.asList(
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

    private static List<Component> cpuTooltip() {
        double load = ServerStatsUtil.getSystemLoadAverage();
        if (load < 0.0D) {
            return Arrays.asList(
                Component.text("The system load average is unavailable", NamedTextColor.GRAY),
                Component.empty(),
                refreshedLine());
        }

        int cores = ServerStatsUtil.getAvailableProcessors();
        double perCore = load * 100.0D / cores;
        return Arrays.asList(
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

    private static List<Component> ramTooltip() {
        return Arrays.asList(
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

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
import com.lunarclient.apollo.common.button.action.ApolloButtonClientAction;
import com.lunarclient.apollo.common.button.content.ApolloButtonContent;
import com.lunarclient.apollo.common.icon.ItemStackIcon;
import com.lunarclient.apollo.common.location.HudPosition;
import com.lunarclient.apollo.common.profile.Profile;
import com.lunarclient.apollo.mods.impl.ModMinimap;
import com.lunarclient.apollo.mods.impl.ModWaypoints;
import com.lunarclient.apollo.module.inventory.InventoryButton;
import com.lunarclient.apollo.module.inventory.InventoryButtonBox;
import com.lunarclient.apollo.module.inventory.InventoryModule;
import com.lunarclient.apollo.module.inventory.InventoryType;
import com.lunarclient.apollo.module.modsetting.ModSettingModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.awt.Color;
import java.time.Duration;
import java.util.Arrays;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

public final class MinigameLayout {

    public static void display(InventoryModule inventoryModule, Player viewer) {
        // Live parts refresh automatically only while the Apollo config enables
        // modules.inventory.buttons.live-broadcast (see the inventory module docs)
        Apollo.getPlayerManager().getPlayer(viewer.getUniqueId()).ifPresent(apolloPlayer -> {
            InventoryButton mapInfo = InventoryButton.builder()
                .id("map-info")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.LEFT)
                .position(HudPosition.of(6, 8))
                .size(InventoryButton.SIZE_WIDE)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(InventoryButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(InventoryButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(Component.text("Map: Apollo", NamedTextColor.AQUA))
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(
                    Component.text("Built by the Lunar Client Team"),
                    Component.text("Released in 2024", NamedTextColor.GRAY)))
                .build();

            InventoryButton kills = InventoryButton.builder()
                .id("kills")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.LEFT)
                .position(HudPosition.of(6, 40))
                .size(InventoryButton.SIZE_WIDE)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(InventoryButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(InventoryButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("IRON_SWORD")
                        .build())
                    .append(apolloViewer -> Component.text("Kills: ", NamedTextColor.GRAY)
                            .append(Component.text(getKills(apolloViewer), NamedTextColor.RED)),
                        Duration.ofMillis(2500L))
                    .scale(1.0F)
                    .build())
                .build();

            InventoryButton lobby = InventoryButton.builder()
                .id("lobby")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.RIGHT)
                .position(HudPosition.of(6, 136))
                .size(InventoryButton.SIZE_WIDE)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(new Color(224, 64, 64, 128))
                .borderColor(new Color(255, 200, 200, 140))
                .content(ApolloButtonContent.builder()
                    .append(Component.text("Back to Lobby"))
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(
                    Component.text("Back to Lobby", NamedTextColor.RED),
                    Component.text("Return to the main lobby", NamedTextColor.GRAY)))
                .onClick(ApolloButtonAction.runCommand("/lobby"))
                .build();

            InventoryButton profile = InventoryButton.builder()
                .id("profile")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.RIGHT)
                .position(HudPosition.of(4, 4))
                .size(InventoryButton.SIZE_MEDIUM)
                .shape(ApolloButtonShape.CIRCLE)
                .backgroundColor(InventoryButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(InventoryButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("PLAYER_HEAD") // use "skull" for legacy with customModelData set to 3
                        .profile(Profile.builder()
                            .id(UUID.fromString("f17627d8-1a97-487b-92ea-c04f413394bd"))
                            .texture("e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWQ4MjUwNWJjZjNiYTU5YzJiZTdlMmQzNmY0ZTJiZGE4MzZmMmZkMTk0YjYyMTJhMmExYzRiNGEyYTQ3MWUifX19")
                            .signature("")
                            .build())
                        .build())
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(
                    Component.text(viewer.getName(), NamedTextColor.GOLD),
                    Component.text("View your stats", NamedTextColor.GRAY)))
                .onClick(ApolloButtonAction.runCommand("/profile"))
                .build();

            InventoryButton settings = InventoryButton.builder()
                .id("settings")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.RIGHT)
                .position(HudPosition.of(48, 4))
                .size(InventoryButton.SIZE_MEDIUM)
                .shape(ApolloButtonShape.CIRCLE)
                .backgroundColor(new Color(222, 160, 60, 85))
                .borderColor(new Color(255, 218, 150, 140))
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("COMPARATOR")
                        .build())
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(
                    Component.text("Settings", NamedTextColor.WHITE),
                    Component.text("Server preferences", NamedTextColor.GRAY)))
                .onClick(ApolloButtonAction.runCommand("/settings"))
                .build();

            ModSettingModule modSettingModule = Apollo.getModuleManager().getModule(ModSettingModule.class);
            boolean minimapEnabled = modSettingModule.getStatus(apolloPlayer, ModMinimap.ENABLED);
            boolean waypointsEnabled = modSettingModule.getStatus(apolloPlayer, ModWaypoints.ENABLED);

            InventoryButton.InventoryButtonBuilder<?, ?> showMapBuilder = InventoryButton.builder()
                .id("show-map")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.RIGHT)
                .position(HudPosition.of(6, 52))
                .size(InventoryButton.SIZE_WIDE)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("FILLED_MAP")
                        .build())
                    .append(Component.text("Show Map"))
                    .scale(1.0F)
                    .build());

            if (minimapEnabled) {
                showMapBuilder
                    .backgroundColor(InventoryButton.DEFAULT_BACKGROUND_COLOR)
                    .borderColor(InventoryButton.DEFAULT_BORDER_COLOR)
                    .tooltip(ApolloButtonTooltip.of(
                        Component.text("Show Map", NamedTextColor.AQUA),
                        Component.text("Open the fullscreen minimap view", NamedTextColor.GRAY)))
                    .onClick(ApolloButtonAction.clientAction(ApolloButtonClientAction.OPEN_MINIMAP_VIEW));
            } else {
                showMapBuilder
                    .backgroundColor(new Color(224, 64, 64, 128))
                    .borderColor(new Color(255, 200, 200, 140))
                    .tooltip(ApolloButtonTooltip.of(Component.text("Minimap mod must be enabled", NamedTextColor.RED)));
            }

            InventoryButton showMap = showMapBuilder.build();

            InventoryButton.InventoryButtonBuilder<?, ?> waypointsBuilder = InventoryButton.builder()
                .id("waypoints")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.RIGHT)
                .position(HudPosition.of(6, 84))
                .size(InventoryButton.SIZE_WIDE)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("LODESTONE")
                        .build())
                    .append(Component.text("Waypoints"))
                    .scale(1.0F)
                    .build());

            if (waypointsEnabled) {
                waypointsBuilder
                    .backgroundColor(InventoryButton.DEFAULT_BACKGROUND_COLOR)
                    .borderColor(InventoryButton.DEFAULT_BORDER_COLOR)
                    .tooltip(ApolloButtonTooltip.of(
                        Component.text("Waypoints", NamedTextColor.GOLD),
                        Component.text("Manage your waypoints", NamedTextColor.GRAY)))
                    .onClick(ApolloButtonAction.clientAction(ApolloButtonClientAction.OPEN_WAYPOINTS_MENU));
            } else {
                waypointsBuilder
                    .backgroundColor(new Color(224, 64, 64, 128))
                    .borderColor(new Color(255, 200, 200, 140))
                    .tooltip(ApolloButtonTooltip.of(Component.text("Waypoints mod must be enabled", NamedTextColor.RED)));
            }

            InventoryButton waypoints = waypointsBuilder.build();

            inventoryModule.displayInventoryButtons(apolloPlayer, Arrays.asList(mapInfo, kills, lobby,
                profile, settings, showMap, waypoints));
        });
    }

    private static int getKills(ApolloPlayer apolloViewer) {
        Player player = Bukkit.getPlayer(apolloViewer.getUniqueId());
        return player != null ? player.getStatistic(Statistic.PLAYER_KILLS) : 0;
    }

    private MinigameLayout() {
    }

}

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
import com.lunarclient.apollo.common.button.content.ApolloButtonContentPart;
import com.lunarclient.apollo.common.icon.ItemStackIcon;
import com.lunarclient.apollo.common.location.HudPosition;
import com.lunarclient.apollo.common.profile.Profile;
import com.lunarclient.apollo.module.inventory.InventoryButton;
import com.lunarclient.apollo.module.inventory.InventoryButtonBox;
import com.lunarclient.apollo.module.inventory.InventoryModule;
import com.lunarclient.apollo.module.inventory.InventoryType;
import java.awt.Color;
import java.time.Duration;
import java.util.Arrays;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public final class MenuLayout {

    public static void display(InventoryModule inventoryModule, Player viewer) {
        // Live parts refresh automatically only while the Apollo config enables
        // modules.inventory.live-buttons.broadcast (see the inventory module docs)
        Apollo.getPlayerManager().getPlayer(viewer.getUniqueId()).ifPresent(apolloPlayer -> {
            InventoryButton shop = InventoryButton.builder()
                .id("shop")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.LEFT)
                .position(HudPosition.of(4, 4))
                .size(InventoryButton.SIZE_MEDIUM)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(InventoryButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(InventoryButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("EMERALD")
                        .build())
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(
                    Component.text("Shop", NamedTextColor.GREEN),
                    Component.text("Browse categories and buy items", NamedTextColor.GRAY),
                    Component.text("Click to open", NamedTextColor.YELLOW)))
                .onClick(ApolloButtonAction.runCommand("/shop"))
                .build();

            InventoryButton spawn = InventoryButton.builder()
                .id("spawn")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.LEFT)
                .position(HudPosition.of(48, 4))
                .size(InventoryButton.SIZE_MEDIUM)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(InventoryButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(InventoryButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("RED_BED")
                        .build())
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(
                    Component.text("Spawn", NamedTextColor.AQUA),
                    Component.text("Teleport back to spawn", NamedTextColor.GRAY),
                    Component.text("Click to teleport", NamedTextColor.YELLOW)))
                .onClick(ApolloButtonAction.runCommand("/spawn"))
                .build();

            InventoryButton warps = InventoryButton.builder()
                .id("warps")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.LEFT)
                .position(HudPosition.of(4, 48))
                .size(InventoryButton.SIZE_MEDIUM)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(InventoryButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(InventoryButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("COMPASS")
                        .build())
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(
                    Component.text("Warps", NamedTextColor.AQUA),
                    Component.text("Browse public warps", NamedTextColor.GRAY),
                    Component.text("Click to teleport", NamedTextColor.YELLOW)))
                .onClick(ApolloButtonAction.runCommand("/warps"))
                .build();

            InventoryButton enderChest = InventoryButton.builder()
                .id("enderchest")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.LEFT)
                .position(HudPosition.of(48, 48))
                .size(InventoryButton.SIZE_MEDIUM)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(InventoryButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(InventoryButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("ENDER_CHEST")
                        .build())
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(
                    Component.text("Ender Chest", NamedTextColor.LIGHT_PURPLE),
                    Component.text("Open your personal storage", NamedTextColor.GRAY),
                    Component.text("Click to open", NamedTextColor.YELLOW)))
                .onClick(ApolloButtonAction.runCommand("/enderchest"))
                .build();

            InventoryButton balance = InventoryButton.builder()
                .id("balance")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.LEFT)
                .position(HudPosition.of(6, 92))
                .size(InventoryButton.SIZE_WIDE)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(InventoryButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(InventoryButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("GOLD_INGOT")
                        .build())
                    .append(ApolloButtonContentPart.live(apolloViewer -> Component.text("$" +
                            String.format("%,d", getBalance(apolloViewer.getUniqueId())), NamedTextColor.GOLD),
                        Duration.ofMillis(2500L)))
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(Component.text("Your balance", NamedTextColor.GOLD)))
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

            InventoryButton vote = InventoryButton.builder()
                .id("vote")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.RIGHT)
                .position(HudPosition.of(6, 52))
                .size(InventoryButton.SIZE_WIDE)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(new Color(34, 204, 68, 64))
                .borderColor(new Color(190, 255, 205, 110))
                .hoveredBackgroundColor(new Color(34, 204, 68, 130))
                .hoveredBorderColor(new Color(190, 255, 205, 210))
                .content(ApolloButtonContent.builder()
                    .append(Component.text("Vote"))
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(
                    Component.text("Vote", NamedTextColor.GREEN),
                    Component.text("Vote daily for rewards", NamedTextColor.GRAY)))
                .onClick(ApolloButtonAction.openUrl("https://example.com/vote"))
                .build();

            InventoryButton discord = InventoryButton.builder()
                .id("discord")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.RIGHT)
                .position(HudPosition.of(6, 84))
                .size(InventoryButton.SIZE_WIDE)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(new Color(88, 101, 242, 90))
                .borderColor(new Color(150, 160, 250, 140))
                .content(ApolloButtonContent.builder()
                    .append(Component.text("Discord"))
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(
                    Component.text("Discord", NamedTextColor.BLUE),
                    Component.text("Join our community", NamedTextColor.GRAY)))
                .onClick(ApolloButtonAction.openUrl("https://lunarclient.dev/discord"))
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

            inventoryModule.displayInventoryButtons(apolloPlayer, Arrays.asList(shop, spawn, warps, enderChest, balance,
                profile, settings, vote, discord, lobby));
        });
    }

    // Demo economy: replace with your economy plugin lookup (e.g. Vault)
    private static long getBalance(UUID playerIdentifier) {
        long drift = (System.currentTimeMillis() / 10_000L) % 250L;
        return 1_000L + Math.abs(playerIdentifier.hashCode() % 4_000) + drift;
    }

    private MenuLayout() {
    }

}

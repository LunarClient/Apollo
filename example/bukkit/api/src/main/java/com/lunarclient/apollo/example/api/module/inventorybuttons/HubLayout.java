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
import com.lunarclient.apollo.common.profile.Profile;
import com.lunarclient.apollo.module.inventory.InventoryButton;
import com.lunarclient.apollo.module.inventory.InventoryButtonBox;
import com.lunarclient.apollo.module.inventory.InventoryModule;
import com.lunarclient.apollo.module.inventory.InventoryType;
import java.awt.Color;
import java.util.Arrays;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public final class HubLayout {

    public static void display(InventoryModule inventoryModule, Player viewer) {
        Apollo.getPlayerManager().getPlayer(viewer.getUniqueId()).ifPresent(apolloPlayer -> {
            InventoryButton practice = InventoryButton.builder()
                .id("practice")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.LEFT)
                .position(HudPosition.of(6, 8))
                .size(InventoryButton.SIZE_WIDE)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(InventoryButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(InventoryButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("SPLASH_POTION")
                        .potion("healing")
                        .build())
                    .append(Component.text("Practice", NamedTextColor.LIGHT_PURPLE))
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(Component.text("Click to join!", NamedTextColor.YELLOW)))
                .onClick(ApolloButtonAction.runCommand("/server practice"))
                .build();

            InventoryButton factions = InventoryButton.builder()
                .id("factions")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.LEFT)
                .position(HudPosition.of(6, 40))
                .size(InventoryButton.SIZE_WIDE)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(InventoryButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(InventoryButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("TNT")
                        .build())
                    .append(Component.text("Factions", NamedTextColor.RED))
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(Component.text("Click to join!", NamedTextColor.YELLOW)))
                .onClick(ApolloButtonAction.runCommand("/server factions"))
                .build();

            InventoryButton bedWars = InventoryButton.builder()
                .id("bedwars")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.LEFT)
                .position(HudPosition.of(6, 72))
                .size(InventoryButton.SIZE_WIDE)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(InventoryButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(InventoryButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("RED_BED")
                        .build())
                    .append(Component.text("BedWars", NamedTextColor.AQUA))
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(Component.text("Click to join!", NamedTextColor.YELLOW)))
                .onClick(ApolloButtonAction.runCommand("/server bedwars"))
                .build();

            InventoryButton soupPvP = InventoryButton.builder()
                .id("souppvp")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.LEFT)
                .position(HudPosition.of(6, 104))
                .size(InventoryButton.SIZE_WIDE)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(InventoryButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(InventoryButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("MUSHROOM_STEW")
                        .build())
                    .append(Component.text("SoupPvP", NamedTextColor.GOLD))
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(Component.text("Click to join!", NamedTextColor.YELLOW)))
                .onClick(ApolloButtonAction.runCommand("/server souppvp"))
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

            InventoryButton changelog = InventoryButton.builder()
                .id("changelog")
                .inventoryType(InventoryType.PLAYER)
                .box(InventoryButtonBox.RIGHT)
                .position(HudPosition.of(6, 52))
                .size(InventoryButton.SIZE_WIDE)
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(InventoryButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(InventoryButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("WRITABLE_BOOK")
                        .build())
                    .append(Component.text("Changelog"))
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(
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
                    Component.text("https://github.com/LunarClient/Apollo/releases/tag/v1.2.8", NamedTextColor.YELLOW)))
                .onClick(ApolloButtonAction.openUrl("https://github.com/LunarClient/Apollo/releases/tag/v1.2.8"))
                .build();

            inventoryModule.displayInventoryButtons(apolloPlayer, Arrays.asList(practice, factions, bedWars,
                soupPvP, profile, settings, changelog));
        });
    }

    private HubLayout() {
    }

}

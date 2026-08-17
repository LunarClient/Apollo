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
import com.lunarclient.apollo.button.v1.ButtonTooltip;
import com.lunarclient.apollo.common.v1.Icon;
import com.lunarclient.apollo.common.v1.ItemStackIcon;
import com.lunarclient.apollo.example.proto.util.AdventureUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufPacketUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufUtil;
import com.lunarclient.apollo.hud.v1.HudPosition;
import com.lunarclient.apollo.inventory.v1.DisplayInventoryButtonsMessage;
import com.lunarclient.apollo.inventory.v1.InventoryButton;
import com.lunarclient.apollo.inventory.v1.InventoryButtonBox;
import java.awt.Color;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public final class HubLayout {

    public static void display(Player viewer) {
        InventoryButton practice = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("practice")
                .setPosition(HudPosition.newBuilder().setX(6).setY(8).build())
                .setSize(ButtonSize.newBuilder().setWidth(80).setHeight(26).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(InventoryButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(InventoryButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ItemStackIcon.newBuilder()
                            .setItemName("SPLASH_POTION")
                            .setPotion("healing")
                            .build())
                        .build()))
                    .addParts(InventoryButtonParts.textPart(Component.text("Practice", NamedTextColor.LIGHT_PURPLE)))
                    .setScale(1.0F)
                    .build())
                .setTooltip(ButtonTooltip.newBuilder()
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Click to join!", NamedTextColor.YELLOW)))
                    .build())
                .setRunCommand("/server practice")
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_LEFT)
            .build();

        InventoryButton factions = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("factions")
                .setPosition(HudPosition.newBuilder().setX(6).setY(40).build())
                .setSize(ButtonSize.newBuilder().setWidth(80).setHeight(26).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(InventoryButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(InventoryButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("TNT", 0))
                        .build()))
                    .addParts(InventoryButtonParts.textPart(Component.text("Factions", NamedTextColor.RED)))
                    .setScale(1.0F)
                    .build())
                .setTooltip(ButtonTooltip.newBuilder()
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Click to join!", NamedTextColor.YELLOW)))
                    .build())
                .setRunCommand("/server factions")
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_LEFT)
            .build();

        InventoryButton bedWars = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("bedwars")
                .setPosition(HudPosition.newBuilder().setX(6).setY(72).build())
                .setSize(ButtonSize.newBuilder().setWidth(80).setHeight(26).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(InventoryButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(InventoryButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("RED_BED", 0))
                        .build()))
                    .addParts(InventoryButtonParts.textPart(Component.text("BedWars", NamedTextColor.AQUA)))
                    .setScale(1.0F)
                    .build())
                .setTooltip(ButtonTooltip.newBuilder()
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Click to join!", NamedTextColor.YELLOW)))
                    .build())
                .setRunCommand("/server bedwars")
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_LEFT)
            .build();

        InventoryButton soupPvP = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("souppvp")
                .setPosition(HudPosition.newBuilder().setX(6).setY(104).build())
                .setSize(ButtonSize.newBuilder().setWidth(80).setHeight(26).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(InventoryButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(InventoryButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("MUSHROOM_STEW", 0))
                        .build()))
                    .addParts(InventoryButtonParts.textPart(Component.text("SoupPvP", NamedTextColor.GOLD)))
                    .setScale(1.0F)
                    .build())
                .setTooltip(ButtonTooltip.newBuilder()
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Click to join!", NamedTextColor.YELLOW)))
                    .build())
                .setRunCommand("/server souppvp")
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_LEFT)
            .build();

        InventoryButton profile = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("profile")
                .setPosition(HudPosition.newBuilder().setX(4).setY(4).build())
                .setSize(ButtonSize.newBuilder().setWidth(40).setHeight(40).build())
                .setShape(ButtonShape.BUTTON_SHAPE_CIRCLE)
                .setBackgroundColor(ProtobufUtil.createColorProto(InventoryButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(InventoryButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto(
                            "PLAYER_HEAD", 0, null, // use "skull" for legacy with customModelData set to 3
                            ProtobufUtil.createProfileProto(
                                UUID.fromString("f17627d8-1a97-487b-92ea-c04f413394bd"),
                                "e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWQ4MjUwNWJjZjNiYTU5YzJiZTdlMmQzNmY0ZTJiZGE4MzZmMmZkMTk0YjYyMTJhMmExYzRiNGEyYTQ3MWUifX19",
                                ""
                            )
                        ))
                        .build()))
                    .setScale(1.0F)
                    .build())
                .setTooltip(ButtonTooltip.newBuilder()
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text(viewer.getName(), NamedTextColor.GOLD)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("View your stats", NamedTextColor.GRAY)))
                    .build())
                .setRunCommand("/profile")
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_RIGHT)
            .build();

        InventoryButton settings = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("settings")
                .setPosition(HudPosition.newBuilder().setX(48).setY(4).build())
                .setSize(ButtonSize.newBuilder().setWidth(40).setHeight(40).build())
                .setShape(ButtonShape.BUTTON_SHAPE_CIRCLE)
                .setBackgroundColor(ProtobufUtil.createColorProto(new Color(222, 160, 60, 85)))
                .setBorderColor(ProtobufUtil.createColorProto(new Color(255, 218, 150, 140)))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("COMPARATOR", 0))
                        .build()))
                    .setScale(1.0F)
                    .build())
                .setTooltip(ButtonTooltip.newBuilder()
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Settings", NamedTextColor.WHITE)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Server preferences", NamedTextColor.GRAY)))
                    .build())
                .setRunCommand("/settings")
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_RIGHT)
            .build();

        InventoryButton changelog = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("changelog")
                .setPosition(HudPosition.newBuilder().setX(6).setY(52).build())
                .setSize(ButtonSize.newBuilder().setWidth(80).setHeight(26).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(InventoryButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(InventoryButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("WRITABLE_BOOK", 0))
                        .build()))
                    .addParts(InventoryButtonParts.textPart(Component.text("Changelog")))
                    .setScale(1.0F)
                    .build())
                .setTooltip(ButtonTooltip.newBuilder()
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("🌙  Apollo - v1.2.8", NamedTextColor.GOLD)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.empty()))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("• Released Markers Module", NamedTextColor.GRAY)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("• Added ALLOW_DIG_AND_USE & DISABLE_BLOCK_MISS_PENALTY", NamedTextColor.GRAY)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("  options to Combat Module", NamedTextColor.GRAY)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("• Added configurable Server Link Button placement", NamedTextColor.GRAY)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("• Added API option to auto-enable Staff Mods", NamedTextColor.GRAY)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("  when unlocked via the Staff Mod Module", NamedTextColor.GRAY)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("• Improved performance with various optimizations", NamedTextColor.GRAY)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.empty()))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Read the full changelog at", NamedTextColor.YELLOW)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("https://github.com/LunarClient/Apollo/releases/tag/v1.2.8", NamedTextColor.YELLOW)))
                    .build())
                .setOpenUrl("https://github.com/LunarClient/Apollo/releases/tag/v1.2.8")
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_RIGHT)
            .build();

        DisplayInventoryButtonsMessage message = DisplayInventoryButtonsMessage.newBuilder()
            .addInventoryButtons(practice)
            .addInventoryButtons(factions)
            .addInventoryButtons(bedWars)
            .addInventoryButtons(soupPvP)
            .addInventoryButtons(profile)
            .addInventoryButtons(settings)
            .addInventoryButtons(changelog)
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    private HubLayout() {
    }

}

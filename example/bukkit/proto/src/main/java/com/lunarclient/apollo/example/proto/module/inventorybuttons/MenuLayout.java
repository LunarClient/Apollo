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
import com.lunarclient.apollo.button.v1.ButtonUpdate;
import com.lunarclient.apollo.common.v1.Icon;
import com.lunarclient.apollo.example.proto.util.AdventureUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufPacketUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufUtil;
import com.lunarclient.apollo.hud.v1.HudPosition;
import com.lunarclient.apollo.inventory.v1.DisplayInventoryButtonsMessage;
import com.lunarclient.apollo.inventory.v1.InventoryButton;
import com.lunarclient.apollo.inventory.v1.InventoryButtonBox;
import com.lunarclient.apollo.inventory.v1.InventoryType;
import com.lunarclient.apollo.inventory.v1.UpdateInventoryButtonMessage;
import java.awt.Color;
import java.util.List;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public final class MenuLayout {

    public static void display(Player viewer) {
        InventoryButton shop = InventoryButton.newBuilder()
            .setInventoryType(InventoryType.INVENTORY_TYPE_PLAYER)
            .setButton(Button.newBuilder()
                .setId("shop")
                .setPosition(HudPosition.newBuilder().setX(4).setY(4).build())
                .setSize(ButtonSize.newBuilder().setWidth(40).setHeight(40).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(InventoryButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(InventoryButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("EMERALD", 0))
                        .build()))
                    .setScale(1.0F)
                    .build())
                .setTooltip(ButtonTooltip.newBuilder()
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Shop", NamedTextColor.GREEN)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Browse categories and buy items", NamedTextColor.GRAY)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Click to open", NamedTextColor.YELLOW)))
                    .build())
                .setRunCommand("/shop")
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_LEFT)
            .build();

        InventoryButton spawn = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("spawn")
                .setPosition(HudPosition.newBuilder().setX(48).setY(4).build())
                .setSize(ButtonSize.newBuilder().setWidth(40).setHeight(40).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(InventoryButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(InventoryButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("RED_BED", 0))
                        .build()))
                    .setScale(1.0F)
                    .build())
                .setTooltip(ButtonTooltip.newBuilder()
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Spawn", NamedTextColor.AQUA)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Teleport back to spawn", NamedTextColor.GRAY)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Click to teleport", NamedTextColor.YELLOW)))
                    .build())
                .setRunCommand("/spawn")
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_LEFT)
            .build();

        InventoryButton warps = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("warps")
                .setPosition(HudPosition.newBuilder().setX(4).setY(48).build())
                .setSize(ButtonSize.newBuilder().setWidth(40).setHeight(40).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(InventoryButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(InventoryButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("COMPASS", 0))
                        .build()))
                    .setScale(1.0F)
                    .build())
                .setTooltip(ButtonTooltip.newBuilder()
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Warps", NamedTextColor.AQUA)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Browse public warps", NamedTextColor.GRAY)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Click to teleport", NamedTextColor.YELLOW)))
                    .build())
                .setRunCommand("/warps")
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_LEFT)
            .build();

        InventoryButton enderChest = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("enderchest")
                .setPosition(HudPosition.newBuilder().setX(48).setY(48).build())
                .setSize(ButtonSize.newBuilder().setWidth(40).setHeight(40).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(InventoryButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(InventoryButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("ENDER_CHEST", 0))
                        .build()))
                    .setScale(1.0F)
                    .build())
                .setTooltip(ButtonTooltip.newBuilder()
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Ender Chest", NamedTextColor.LIGHT_PURPLE)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Open your personal storage", NamedTextColor.GRAY)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Click to open", NamedTextColor.YELLOW)))
                    .build())
                .setRunCommand("/enderchest")
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_LEFT)
            .build();

        InventoryButton balance = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("balance")
                .setPosition(HudPosition.newBuilder().setX(6).setY(92).build())
                .setSize(ButtonSize.newBuilder().setWidth(80).setHeight(26).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(InventoryButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(InventoryButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("GOLD_INGOT", 0))
                        .build()))
                    .addParts(InventoryButtonParts.textPart(balanceContent(viewer)))
                    .setScale(1.0F)
                    .build())
                .setTooltip(InventoryButtonParts.tooltipMessage(balanceTooltip()))
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

        InventoryButton vote = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("vote")
                .setPosition(HudPosition.newBuilder().setX(6).setY(52).build())
                .setSize(ButtonSize.newBuilder().setWidth(80).setHeight(26).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(new Color(34, 204, 68, 64)))
                .setBorderColor(ProtobufUtil.createColorProto(new Color(190, 255, 205, 110)))
                .setHoveredBackgroundColor(ProtobufUtil.createColorProto(new Color(34, 204, 68, 130)))
                .setHoveredBorderColor(ProtobufUtil.createColorProto(new Color(190, 255, 205, 210)))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.textPart(Component.text("Vote")))
                    .setScale(1.0F)
                    .build())
                .setTooltip(ButtonTooltip.newBuilder()
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Vote", NamedTextColor.GREEN)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Vote daily for rewards", NamedTextColor.GRAY)))
                    .build())
                .setOpenUrl("https://example.com/vote")
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_RIGHT)
            .build();

        InventoryButton discord = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("discord")
                .setPosition(HudPosition.newBuilder().setX(6).setY(84).build())
                .setSize(ButtonSize.newBuilder().setWidth(80).setHeight(26).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(new Color(88, 101, 242, 90)))
                .setBorderColor(ProtobufUtil.createColorProto(new Color(150, 160, 250, 140)))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.textPart(Component.text("Discord")))
                    .setScale(1.0F)
                    .build())
                .setTooltip(ButtonTooltip.newBuilder()
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Discord", NamedTextColor.BLUE)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Join our community", NamedTextColor.GRAY)))
                    .build())
                .setOpenUrl("https://lunarclient.dev/discord")
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_RIGHT)
            .build();

        InventoryButton lobby = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("lobby")
                .setPosition(HudPosition.newBuilder().setX(6).setY(136).build())
                .setSize(ButtonSize.newBuilder().setWidth(80).setHeight(26).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(new Color(224, 64, 64, 128)))
                .setBorderColor(ProtobufUtil.createColorProto(new Color(255, 200, 200, 140)))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.textPart(Component.text("Back to Lobby")))
                    .setScale(1.0F)
                    .build())
                .setTooltip(ButtonTooltip.newBuilder()
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Back to Lobby", NamedTextColor.RED)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Return to the main lobby", NamedTextColor.GRAY)))
                    .build())
                .setRunCommand("/lobby")
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_RIGHT)
            .build();

        DisplayInventoryButtonsMessage message = DisplayInventoryButtonsMessage.newBuilder()
            .addInventoryButtons(shop)
            .addInventoryButtons(spawn)
            .addInventoryButtons(warps)
            .addInventoryButtons(enderChest)
            .addInventoryButtons(balance)
            .addInventoryButtons(profile)
            .addInventoryButtons(settings)
            .addInventoryButtons(vote)
            .addInventoryButtons(discord)
            .addInventoryButtons(lobby)
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    public static void sendBalanceUpdate(Player viewer) {
        UpdateInventoryButtonMessage message = UpdateInventoryButtonMessage.newBuilder()
            .setId("balance")
            .setUpdate(ButtonUpdate.newBuilder()
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("GOLD_INGOT", 0))
                        .build()))
                    .addParts(InventoryButtonParts.textPart(balanceContent(viewer)))
                    .setScale(1.0F)
                    .build())
                .setTooltip(InventoryButtonParts.tooltipMessage(balanceTooltip()))
                .build())
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    private static Component balanceContent(Player viewer) {
        return Component.text("$" + String.format("%,d", getBalance(viewer.getUniqueId())), NamedTextColor.GOLD);
    }

    private static List<String> balanceTooltip() {
        return InventoryButtonParts.tooltipJson(Component.text("Your balance", NamedTextColor.GOLD));
    }

    // Demo economy: replace with your economy plugin lookup (e.g. Vault)
    private static long getBalance(UUID playerIdentifier) {
        long drift = (System.currentTimeMillis() / 10_000L) % 250L;
        return 1_000L + Math.abs(playerIdentifier.hashCode() % 4_000) + drift;
    }

    private MenuLayout() {
    }

}

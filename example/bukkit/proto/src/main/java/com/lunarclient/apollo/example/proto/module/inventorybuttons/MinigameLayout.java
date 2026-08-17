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
import com.lunarclient.apollo.button.v1.ButtonClientAction;
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
import com.lunarclient.apollo.inventory.v1.UpdateInventoryButtonMessage;
import java.awt.Color;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

public final class MinigameLayout {

    public static void display(Player viewer) {
        InventoryButton mapInfo = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("map-info")
                .setPosition(HudPosition.newBuilder().setX(6).setY(8).build())
                .setSize(ButtonSize.newBuilder().setWidth(80).setHeight(26).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(InventoryButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(InventoryButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.textPart(Component.text("Map: Apollo", NamedTextColor.AQUA)))
                    .setScale(1.0F)
                    .build())
                .setTooltip(ButtonTooltip.newBuilder()
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Built by the Lunar Client Team")))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Released in 2024", NamedTextColor.GRAY)))
                    .build())
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_LEFT)
            .build();

        InventoryButton kills = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("kills")
                .setPosition(HudPosition.newBuilder().setX(6).setY(40).build())
                .setSize(ButtonSize.newBuilder().setWidth(80).setHeight(26).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(InventoryButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(InventoryButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("IRON_SWORD", 0))
                        .build()))
                    .addParts(InventoryButtonParts.textPart(killsContent(viewer)))
                    .setScale(1.0F)
                    .build())
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_LEFT)
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

        InventoryButton showMap = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("show-map")
                .setPosition(HudPosition.newBuilder().setX(6).setY(52).build())
                .setSize(ButtonSize.newBuilder().setWidth(80).setHeight(26).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(InventoryButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(InventoryButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("FILLED_MAP", 0))
                        .build()))
                    .addParts(InventoryButtonParts.textPart(Component.text("Show Map")))
                    .setScale(1.0F)
                    .build())
                .setTooltip(ButtonTooltip.newBuilder()
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Show Map", NamedTextColor.AQUA)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Open the fullscreen minimap view", NamedTextColor.GRAY)))
                    .build())
                .setClientAction(ButtonClientAction.BUTTON_CLIENT_ACTION_OPEN_MINIMAP_VIEW)
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_RIGHT)
            .build();

        InventoryButton waypoints = InventoryButton.newBuilder()
            .setButton(Button.newBuilder()
                .setId("waypoints")
                .setPosition(HudPosition.newBuilder().setX(6).setY(84).build())
                .setSize(ButtonSize.newBuilder().setWidth(80).setHeight(26).build())
                .setShape(ButtonShape.BUTTON_SHAPE_ROUNDED_SQUARE)
                .setBackgroundColor(ProtobufUtil.createColorProto(InventoryButtonParts.BACKGROUND))
                .setBorderColor(ProtobufUtil.createColorProto(InventoryButtonParts.BORDER))
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("LODESTONE", 0))
                        .build()))
                    .addParts(InventoryButtonParts.textPart(Component.text("Waypoints")))
                    .setScale(1.0F)
                    .build())
                .setTooltip(ButtonTooltip.newBuilder()
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Waypoints", NamedTextColor.GOLD)))
                    .addAdventureJsonLines(AdventureUtil.toJson(Component.text("Manage your waypoints", NamedTextColor.GRAY)))
                    .build())
                .setClientAction(ButtonClientAction.BUTTON_CLIENT_ACTION_OPEN_WAYPOINTS_MENU)
                .build())
            .setBox(InventoryButtonBox.INVENTORY_BUTTON_BOX_RIGHT)
            .build();

        DisplayInventoryButtonsMessage message = DisplayInventoryButtonsMessage.newBuilder()
            .addInventoryButtons(mapInfo)
            .addInventoryButtons(kills)
            .addInventoryButtons(lobby)
            .addInventoryButtons(profile)
            .addInventoryButtons(settings)
            .addInventoryButtons(showMap)
            .addInventoryButtons(waypoints)
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    public static void sendKillsUpdate(Player viewer) {
        UpdateInventoryButtonMessage message = UpdateInventoryButtonMessage.newBuilder()
            .setId("kills")
            .setUpdate(ButtonUpdate.newBuilder()
                .setContent(ButtonContent.newBuilder()
                    .addParts(InventoryButtonParts.iconPart(Icon.newBuilder()
                        .setItemStack(ProtobufUtil.createItemStackIconProto("IRON_SWORD", 0))
                        .build()))
                    .addParts(InventoryButtonParts.textPart(killsContent(viewer)))
                    .setScale(1.0F)
                    .build())
                .build())
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    private static Component killsContent(Player viewer) {
        return Component.text("Kills: ", NamedTextColor.GRAY)
            .append(Component.text(viewer.getStatistic(Statistic.PLAYER_KILLS), NamedTextColor.RED));
    }

    private MinigameLayout() {
    }

}

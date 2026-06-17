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
package com.lunarclient.apollo.example.proto.module;

import com.lunarclient.apollo.example.module.impl.MarkerExample;
import com.lunarclient.apollo.example.proto.util.ProtobufPacketUtil;
import com.lunarclient.apollo.example.proto.util.ProtobufUtil;
import com.lunarclient.apollo.marker.v1.BlockTarget;
import com.lunarclient.apollo.marker.v1.DangerMarker;
import com.lunarclient.apollo.marker.v1.DisplayMarkerMessage;
import com.lunarclient.apollo.marker.v1.EntityTarget;
import com.lunarclient.apollo.marker.v1.InfoMarker;
import com.lunarclient.apollo.marker.v1.InterestMarker;
import com.lunarclient.apollo.marker.v1.ItemTarget;
import com.lunarclient.apollo.marker.v1.MarkerDescriptionDisplay;
import com.lunarclient.apollo.marker.v1.MarkerDisplayCondition;
import com.lunarclient.apollo.marker.v1.MarkerFlag;
import com.lunarclient.apollo.marker.v1.MarkerOwnerDisplay;
import com.lunarclient.apollo.marker.v1.MarkerStyle;
import com.lunarclient.apollo.marker.v1.MarkerTarget;
import com.lunarclient.apollo.marker.v1.PlayerTarget;
import com.lunarclient.apollo.marker.v1.RemoveMarkerMessage;
import com.lunarclient.apollo.marker.v1.ResetMarkersMessage;
import java.awt.Color;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.bukkit.entity.Player;

public class MarkerProtoExample extends MarkerExample {

    @Override
    public void displayBlockMarkerExample(Player viewer) {
        DisplayMarkerMessage message = DisplayMarkerMessage.newBuilder()
            .setId("loot-chest")
            .setLocation(ProtobufUtil.createLocationProto(viewer.getLocation()))
            .setOwnerId(ProtobufUtil.createUuidProto(viewer.getUniqueId()))
            .setOwnerName("")
            .setFlag(MarkerFlag.newBuilder()
                .setInterest(InterestMarker.getDefaultInstance())
                .build())
            .setTarget(MarkerTarget.newBuilder()
                .setBlock(BlockTarget.newBuilder()
                    .setItemStack(ProtobufUtil.createItemStackIconProto("minecraft:chest", 0))
                    .build())
                .build())
            .setDuration(ProtobufUtil.createDurationProto(Duration.ofSeconds(60)))
            .setStyle(MarkerStyle.newBuilder()
                .setScale(1.0F)
                .setAnimateMarkerOnHover(true)
                .setCompactMode(false)
                .setTextShadow(true)
                .setOwnerSuffix("'s Marker")
                .setOwnerDisplay(MarkerOwnerDisplay.MARKER_OWNER_DISPLAY_HEAD)
                .setShowOwner(MarkerDisplayCondition.MARKER_DISPLAY_CONDITION_NEVER)
                .setShowCoordinates(MarkerDisplayCondition.MARKER_DISPLAY_CONDITION_NEVER)
                .setShowDistance(MarkerDisplayCondition.MARKER_DISPLAY_CONDITION_HOVER)
                .setShowDescription(MarkerDisplayCondition.MARKER_DISPLAY_CONDITION_ALWAYS)
                .setDescriptionDisplay(MarkerDescriptionDisplay.MARKER_DESCRIPTION_DISPLAY_ICON)
                .build())
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void displayPlayerMarkerExample(Player viewer) {
        DisplayMarkerMessage message = DisplayMarkerMessage.newBuilder()
            .setId("bounty")
            .setLocation(ProtobufUtil.createLocationProto(viewer.getLocation()))
            .setOwnerId(ProtobufUtil.createUuidProto(viewer.getUniqueId()))
            .setOwnerName(viewer.getName())
            .setFlag(MarkerFlag.newBuilder()
                .setDanger(DangerMarker.newBuilder()
                    .setColor(ProtobufUtil.createColorProto(Color.RED))
                    .build())
                .build())
            .setTarget(MarkerTarget.newBuilder()
                .setPlayer(PlayerTarget.newBuilder()
                    .setUuid(ProtobufUtil.createUuidProto(UUID.fromString("f17627d8-1a97-487b-92ea-c04f413394bd")))
                    .setName("ItsNature")
                    .build())
                .build())
            .setInGameNotification(true)
            .setStyle(MarkerStyle.newBuilder()
                .setScale(1.0F)
                .setAnimateMarkerOnHover(true)
                .setCompactMode(false)
                .setTextShadow(true)
                .setOwnerSuffix("'s Marker")
                .setOwnerDisplay(MarkerOwnerDisplay.MARKER_OWNER_DISPLAY_HEAD)
                .setShowOwner(MarkerDisplayCondition.MARKER_DISPLAY_CONDITION_NEVER)
                .setShowCoordinates(MarkerDisplayCondition.MARKER_DISPLAY_CONDITION_NEVER)
                .setShowDistance(MarkerDisplayCondition.MARKER_DISPLAY_CONDITION_HOVER)
                .setShowDescription(MarkerDisplayCondition.MARKER_DISPLAY_CONDITION_ALWAYS)
                .setDescriptionDisplay(MarkerDescriptionDisplay.MARKER_DESCRIPTION_DISPLAY_ICON)
                .build())
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void displayItemMarkerExample(Player viewer) {
        DisplayMarkerMessage message = DisplayMarkerMessage.newBuilder()
            .setId("wither-loot")
            .setLocation(ProtobufUtil.createLocationProto(viewer.getLocation()))
            .setOwnerId(ProtobufUtil.createUuidProto(viewer.getUniqueId()))
            .setOwnerName(viewer.getName())
            .setFlag(MarkerFlag.newBuilder()
                .setInfo(InfoMarker.getDefaultInstance())
                .build())
            .setTarget(MarkerTarget.newBuilder()
                .setItem(ItemTarget.newBuilder()
                    .setItemStack(ProtobufUtil.createItemStackIconProto("minecraft:nether_star", 0))
                    .build())
                .build())
            .setChatNotify(true)
            .setStyle(MarkerStyle.newBuilder()
                .setScale(1.0F)
                .setAnimateMarkerOnHover(true)
                .setCompactMode(false)
                .setTextShadow(true)
                .setOwnerSuffix("'s Marker")
                .setOwnerDisplay(MarkerOwnerDisplay.MARKER_OWNER_DISPLAY_HEAD)
                .setShowOwner(MarkerDisplayCondition.MARKER_DISPLAY_CONDITION_NEVER)
                .setShowCoordinates(MarkerDisplayCondition.MARKER_DISPLAY_CONDITION_NEVER)
                .setShowDistance(MarkerDisplayCondition.MARKER_DISPLAY_CONDITION_HOVER)
                .setShowDescription(MarkerDisplayCondition.MARKER_DISPLAY_CONDITION_ALWAYS)
                .setDescriptionDisplay(MarkerDescriptionDisplay.MARKER_DESCRIPTION_DISPLAY_ICON)
                .build())
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void displayEntityMarkerExample(Player viewer) {
        DisplayMarkerMessage message = DisplayMarkerMessage.newBuilder()
            .setId("tutorial-npc")
            .setLocation(ProtobufUtil.createLocationProto(viewer.getLocation()))
            .setOwnerId(ProtobufUtil.createUuidProto(UUID.randomUUID()))
            .setOwnerName("Tutorial NPC")
            .setFlag(MarkerFlag.newBuilder()
                .setInterest(InterestMarker.getDefaultInstance())
                .build())
            .setTarget(MarkerTarget.newBuilder()
                .setEntity(EntityTarget.newBuilder()
                    .setEntityType("minecraft:villager")
                    .build())
                .build())
            .setStyle(MarkerStyle.newBuilder()
                .setScale(1.3F)
                .setAnimateMarkerOnHover(true)
                .setCompactMode(false)
                .setTextShadow(true)
                .setOwnerSuffix("")
                .setOwnerDisplay(MarkerOwnerDisplay.MARKER_OWNER_DISPLAY_NAME)
                .setShowOwner(MarkerDisplayCondition.MARKER_DISPLAY_CONDITION_HOVER)
                .setShowCoordinates(MarkerDisplayCondition.MARKER_DISPLAY_CONDITION_NEVER)
                .setShowDistance(MarkerDisplayCondition.MARKER_DISPLAY_CONDITION_NEVER)
                .setShowDescription(MarkerDisplayCondition.MARKER_DISPLAY_CONDITION_HOVER)
                .setDescriptionDisplay(MarkerDescriptionDisplay.MARKER_DESCRIPTION_DISPLAY_ICON)
                .build())
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void removeMarkersExample(Player viewer) {
        List<String> markerIds = Arrays.asList("loot-chest", "bounty", "wither-loot", "tutorial-npc");
        for (String id : markerIds) {
            RemoveMarkerMessage message = RemoveMarkerMessage.newBuilder()
                .setId(id)
                .build();

            ProtobufPacketUtil.sendPacket(viewer, message);
        }
    }

    @Override
    public void resetMarkersExample(Player viewer) {
        ResetMarkersMessage message = ResetMarkersMessage.getDefaultInstance();
        ProtobufPacketUtil.sendPacket(viewer, message);
    }

}

/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2023 Moonsworth
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
package com.lunarclient.apollo.example.utilities;

import com.google.protobuf.Timestamp;
import com.lunarclient.apollo.common.cuboid.Cuboid2D;
import com.lunarclient.apollo.common.cuboid.Cuboid3D;
import com.lunarclient.apollo.common.icon.AdvancedResourceLocationIcon;
import com.lunarclient.apollo.common.icon.Icon;
import com.lunarclient.apollo.common.icon.ItemStackIcon;
import com.lunarclient.apollo.common.icon.SimpleResourceLocationIcon;
import com.lunarclient.apollo.common.location.ApolloPlayerLocation;
import com.lunarclient.apollo.common.v1.EntityId;
import com.lunarclient.apollo.common.v1.Uuid;
import com.lunarclient.apollo.module.packetenrichment.PlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.awt.*;
import java.time.Duration;
import java.util.UUID;

import static com.lunarclient.apollo.util.Ranges.checkPositive;
import static com.lunarclient.apollo.util.Ranges.checkRange;

/**
 * Utility class for converting objects to and from their
 * corresponding Protocol Buffers representations.
 *
 * @since 1.0.0
 */
public final class ProtobufUtil {

    public static EntityId toProtobuf(int id, UUID uuid) {
        return EntityId.newBuilder()
            .setEntityId(id)
            .setEntityUuid(ProtobufUtil.toProtobuf(uuid))
            .build();
    }

    public static Uuid toProtobuf(UUID object) {
        return Uuid.newBuilder()
            .setHigh64(object.getMostSignificantBits())
            .setLow64(object.getLeastSignificantBits())
            .build();
    }

    public static UUID fromProtobuf(Uuid message) {
        return new UUID(message.getHigh64(), message.getLow64());
    }

    public static com.lunarclient.apollo.common.v1.Color toProtobuf(Color object) {
        return com.lunarclient.apollo.common.v1.Color.newBuilder()
            .setColor(object.getRGB())
            .build();
    }

    public static Color fromProtobuf(com.lunarclient.apollo.common.v1.Color message) {
        return new Color(message.getColor());
    }

    public static com.google.protobuf.Duration toProtobuf(Duration object) {
        return com.google.protobuf.Duration.newBuilder()
            .setSeconds(object.getSeconds())
            .setNanos(object.getNano())
            .build();
    }

    public static Duration fromProtobuf(com.google.protobuf.Duration message) {
        return Duration.ofSeconds(message.getSeconds()).withNanos(message.getNanos());
    }

    public static Timestamp toProtobuf(long millis) {
        return Timestamp.newBuilder()
            .setSeconds(millis / 1000)
            .setNanos((int) ((millis % 1000) * 1000000))
            .build();
    }

    public static long fromProtobuf(Timestamp message) {
        return message.getSeconds() * 1000 + message.getNanos() / 1000000;
    }

    /**
     * Converts an {@link PlayerInfo} object to an
     * {@link com.lunarclient.apollo.packetenrichment.v1.PlayerInfo} proto message.
     *
     * @param object the player info
     * @return the proto player info message
     * @since 1.0.7
     */
    public static com.lunarclient.apollo.packetenrichment.v1.PlayerInfo toProtobuf(PlayerInfo object) {
        return com.lunarclient.apollo.packetenrichment.v1.PlayerInfo.newBuilder()
            .setPlayerUuid(ProtobufUtil.toProtobuf(object.getPlayerUuid()))
            .setLocation(ProtobufUtil.toProtobuf(object.getLocation()))
            .setSprinting(object.isSprinting())
            .setSneaking(object.isSneaking())
            .setJumping(object.isJumping())
            .setForwardSpeed(object.getForwardSpeed())
            .setStrafeSpeed(object.getStrafeSpeed())
            .build();
    }

    /**
     * Converts an {@link com.lunarclient.apollo.packetenrichment.v1.PlayerInfo}
     * proto message to an {@link PlayerInfo} object.
     *
     * @param message the player info message
     * @return the apollo player info object
     * @since 1.0.7
     */
    public static PlayerInfo fromProtobuf(com.lunarclient.apollo.packetenrichment.v1.PlayerInfo message) {
        return PlayerInfo.builder()
            .playerUuid(ProtobufUtil.fromProtobuf(message.getPlayerUuid()))
            .location(ProtobufUtil.fromProtobuf(message.getLocation()))
            .sneaking(message.getSneaking())
            .sprinting(message.getSprinting())
            .jumping(message.getJumping())
            .forwardSpeed(message.getForwardSpeed())
            .strafeSpeed(message.getStrafeSpeed())
            .build();
    }

    public static com.lunarclient.apollo.common.v1.Location toLocationProtobuf(Location location) {
        return com.lunarclient.apollo.common.v1.Location.newBuilder()
            .setWorld(location.getWorld().getName())
            .setX(location.getX())
            .setY(location.getY())
            .setZ(location.getZ())
            .build();
    }

    public static Location fromProtobuf(com.lunarclient.apollo.common.v1.Location message) {
        return new Location(Bukkit.getWorld(message.getWorld()), message.getX(), message.getY(), message.getZ());
    }

    public static com.lunarclient.apollo.common.v1.BlockLocation toBlockLocationProtobuf(Location location) {
        return com.lunarclient.apollo.common.v1.BlockLocation.newBuilder()
            .setWorld(location.getWorld().getName())
            .setX(location.getBlockX())
            .setY(location.getBlockY())
            .setZ(location.getBlockZ())
            .build();
    }

    public static Location fromProtobuf(com.lunarclient.apollo.common.v1.BlockLocation message) {
        return new Location(Bukkit.getWorld(message.getWorld()), message.getX(), message.getY(), message.getZ());
    }

    public static com.lunarclient.apollo.common.v1.PlayerLocation toProtobuf(Location location) {
        return com.lunarclient.apollo.common.v1.PlayerLocation.newBuilder()
            .setLocation(ProtobufUtil.toLocationProtobuf(location))
            .setYaw(location.getYaw())
            .setPitch(location.getPitch())
            .build();
    }

    public static Location fromProtobuf(com.lunarclient.apollo.common.v1.PlayerLocation message) {
        Location location = ProtobufUtil.fromProtobuf(message.getLocation());
        location.setYaw(message.getYaw());
        location.setPitch(message.getPitch());
        return location;
    }

    public static com.lunarclient.apollo.common.v1.Cuboid2D toProtobuf(Cuboid2D object) {
        return com.lunarclient.apollo.common.v1.Cuboid2D.newBuilder()
            .setMinX(object.getMinX())
            .setMinZ(object.getMinZ())
            .setMaxX(object.getMaxX())
            .setMaxZ(object.getMaxZ())
            .build();
    }

    /**
     * Converts an {@link com.lunarclient.apollo.common.v1.Cuboid2D}
     * proto message to an {@link Cuboid2D} object.
     *
     * @param message the cuboid 2D message
     * @return the cuboid 2D object
     * @since 1.0.0
     */
    public static Cuboid2D fromProtobuf(com.lunarclient.apollo.common.v1.Cuboid2D message) {
        return Cuboid2D.builder()
            .minX(message.getMinX())
            .minZ(message.getMinZ())
            .maxX(message.getMaxX())
            .maxZ(message.getMaxZ())
            .build();
    }

    /**
     * Converts an {@link Cuboid3D} object to an
     * {@link com.lunarclient.apollo.common.v1.Cuboid3D} proto message.
     *
     * @param object the cuboid 3D
     * @return the proto cuboid 3D message
     * @since 1.0.0
     */
    public static com.lunarclient.apollo.common.v1.Cuboid3D toProtobuf(Cuboid3D object) {
        return com.lunarclient.apollo.common.v1.Cuboid3D.newBuilder()
            .setMinX(object.getMinX())
            .setMinY(object.getMinY())
            .setMinZ(object.getMinZ())
            .setMaxX(object.getMaxX())
            .setMaxY(object.getMaxY())
            .setMaxZ(object.getMaxZ())
            .build();
    }

    /**
     * Converts an {@link com.lunarclient.apollo.common.v1.Cuboid3D}
     * proto message to an {@link Cuboid3D} object.
     *
     * @param message the cuboid 3D message
     * @return the cuboid 3D object
     * @since 1.0.0
     */
    public static Cuboid3D fromProtobuf(com.lunarclient.apollo.common.v1.Cuboid3D message) {
        return Cuboid3D.builder()
            .minX(message.getMinX())
            .minY(message.getMinY())
            .minZ(message.getMinZ())
            .maxX(message.getMaxX())
            .maxY(message.getMaxY())
            .maxZ(message.getMaxZ())
            .build();
    }

    /**
     * Converts an {@link Icon} object to an
     * {@link com.lunarclient.apollo.common.v1.Icon} proto message.
     *
     * @param icon the icon
     * @return the proto icon message
     * @since 1.0.0
     */
    public static com.lunarclient.apollo.common.v1.Icon toProtobuf(Icon icon) {
        com.lunarclient.apollo.common.v1.Icon.Builder builder = com.lunarclient.apollo.common.v1.Icon.newBuilder();

        if (icon instanceof ItemStackIcon) {
            ItemStackIcon item = (ItemStackIcon) icon;
            String itemName = item.getItemName();

            com.lunarclient.apollo.common.v1.ItemStackIcon.Builder itemBuilder = com.lunarclient.apollo.common.v1.ItemStackIcon.newBuilder()
                .setItemId(item.getItemId())
                .setCustomModelData(item.getCustomModelData());

            if (itemName != null) {
                itemBuilder.setItemName(itemName);
            }

            builder.setItemStack(itemBuilder.build());
        } else if (icon instanceof SimpleResourceLocationIcon) {
            SimpleResourceLocationIcon simple = (SimpleResourceLocationIcon) icon;

            builder.setSimpleResourceLocation(com.lunarclient.apollo.common.v1.SimpleResourceLocationIcon.newBuilder()
                .setResourceLocation(simple.getResourceLocation())
                .setSize(checkPositive(simple.getSize(), "SimpleResourceLocationIcon#size"))
                .build());
        } else if (icon instanceof AdvancedResourceLocationIcon) {
            AdvancedResourceLocationIcon advanced = (AdvancedResourceLocationIcon) icon;

            builder.setAdvancedResourceLocation(com.lunarclient.apollo.common.v1.AdvancedResourceLocationIcon.newBuilder()
                .setResourceLocation(advanced.getResourceLocation())
                .setWidth(checkPositive(advanced.getWidth(), "AdvancedResourceLocationIcon#width"))
                .setHeight(checkPositive(advanced.getHeight(), "AdvancedResourceLocationIcon#height"))
                .setMinU(checkRange(advanced.getMinU(), 0, 1, "AdvancedResourceLocationIcon#minU"))
                .setMaxU(checkRange(advanced.getMaxU(), 0, 1, "AdvancedResourceLocationIcon#maxU"))
                .setMinV(checkRange(advanced.getMinV(), 0, 1, "AdvancedResourceLocationIcon#minV"))
                .setMaxV(checkRange(advanced.getMaxV(), 0, 1, "AdvancedResourceLocationIcon#maxV"))
                .build());
        }

        return builder.build();
    }

    /**
     * Converts an {@link com.lunarclient.apollo.common.v1.Icon}
     * proto message to an {@link Icon} object.
     *
     * @param icon the icon message
     * @return the icon object
     * @since 1.0.0
     */
    public static Icon fromProtobuf(com.lunarclient.apollo.common.v1.Icon icon) {
        if (icon.hasItemStack()) {
            com.lunarclient.apollo.common.v1.ItemStackIcon item = icon.getItemStack();

            return ItemStackIcon.builder()
                .itemName(item.getItemName())
                .itemId(item.getItemId())
                .customModelData(item.getCustomModelData())
                .build();
        } else if (icon.hasSimpleResourceLocation()) {
            com.lunarclient.apollo.common.v1.SimpleResourceLocationIcon simple = icon.getSimpleResourceLocation();

            return SimpleResourceLocationIcon.builder()
                .resourceLocation(simple.getResourceLocation())
                .size(simple.getSize())
                .build();
        } else if (icon.hasAdvancedResourceLocation()) {
            com.lunarclient.apollo.common.v1.AdvancedResourceLocationIcon advanced = icon.getAdvancedResourceLocation();

            return AdvancedResourceLocationIcon.builder()
                .resourceLocation(advanced.getResourceLocation())
                .width(advanced.getWidth())
                .height(advanced.getHeight())
                .minU(advanced.getMinU())
                .maxU(advanced.getMaxU())
                .minV(advanced.getMinV())
                .maxV(advanced.getMaxV())
                .build();
        }

        return null;
    }

    private ProtobufUtil() {
    }

}

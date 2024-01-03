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
package com.lunarclient.apollo.network;

import com.google.protobuf.Timestamp;
import com.lunarclient.apollo.common.ApolloEntity;
import com.lunarclient.apollo.common.packetenrichment.PlayerInfo;
import com.lunarclient.apollo.common.cuboid.Cuboid2D;
import com.lunarclient.apollo.common.cuboid.Cuboid3D;
import com.lunarclient.apollo.common.icon.AdvancedResourceLocationIcon;
import com.lunarclient.apollo.common.icon.Icon;
import com.lunarclient.apollo.common.icon.ItemStackIcon;
import com.lunarclient.apollo.common.icon.SimpleResourceLocationIcon;
import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.common.location.ApolloPlayerLocation;
import com.lunarclient.apollo.common.v1.EntityId;
import com.lunarclient.apollo.common.v1.Uuid;
import java.awt.Color;
import java.time.Duration;
import java.util.UUID;

/**
 * Utility class for converting objects to and from their
 * corresponding Protocol Buffers representations.
 *
 * @since 1.0.0
 */
public final class NetworkTypes {

    /**
     * Converts an {@link ApolloEntity} object to
     * an {@link EntityId} proto message.
     *
     * @param object the apollo entity
     * @return the proto entity id message
     * @since 1.0.0
     */
    public static EntityId toProtobuf(ApolloEntity object) {
        return EntityId.newBuilder()
            .setEntityId(object.getEntityId())
            .setEntityUuid(NetworkTypes.toProtobuf(object.getEntityUuid()))
            .build();
    }

    /**
     * Converts an {@link EntityId} proto message to
     * an {@link ApolloEntity} object.
     *
     * @param message the entity id message
     * @return the apollo entity object
     * @since 1.0.0
     */
    public static ApolloEntity fromProtobuf(EntityId message) {
        return new ApolloEntity(message.getEntityId(), NetworkTypes.fromProtobuf(message.getEntityUuid()));
    }

    /**
     * Converts an {@link UUID} object to
     * an {@link Uuid} proto message.
     *
     * @param object the uuid
     * @return the proto uuid message
     * @since 1.0.0
     */
    public static Uuid toProtobuf(UUID object) {
        return Uuid.newBuilder()
            .setHigh64(object.getMostSignificantBits())
            .setLow64(object.getLeastSignificantBits())
            .build();
    }

    /**
     * Converts an {@link Uuid} proto message to
     * an {@link UUID} object.
     *
     * @param message the uuid message
     * @return the uuid object
     * @since 1.0.0
     */
    public static UUID fromProtobuf(Uuid message) {
        return new UUID(message.getHigh64(), message.getLow64());
    }

    /**
     * Converts an {@link Color} object to an
     * {@link com.lunarclient.apollo.common.v1.Color} proto message.
     *
     * @param object the color
     * @return the proto color message
     * @since 1.0.0
     */
    public static com.lunarclient.apollo.common.v1.Color toProtobuf(Color object) {
        return com.lunarclient.apollo.common.v1.Color.newBuilder()
            .setColor(object.getRGB())
            .build();
    }

    /**
     * Converts an {@link com.lunarclient.apollo.common.v1.Color}
     * proto message to an {@link Color} object.
     *
     * @param message the color message
     * @return the color object
     * @since 1.0.0
     */
    public static Color fromProtobuf(com.lunarclient.apollo.common.v1.Color message) {
        return new Color(message.getColor());
    }

    /**
     * Converts an {@link Duration} object to an
     * {@link com.google.protobuf.Duration} proto message.
     *
     * @param object the duration
     * @return the proto duration message
     * @since 1.0.0
     */
    public static com.google.protobuf.Duration toProtobuf(Duration object) {
        return com.google.protobuf.Duration.newBuilder()
            .setSeconds(object.getSeconds())
            .setNanos(object.getNano())
            .build();
    }

    /**
     * Converts an {@link com.google.protobuf.Duration}
     * proto message to an {@link Duration} object.
     *
     * @param message the duration message
     * @return the duration object
     * @since 1.0.0
     */
    public static Duration fromProtobuf(com.google.protobuf.Duration message) {
        return Duration.ofSeconds(message.getSeconds()).withNanos(message.getNanos());
    }

    /**
     * Converts an unix timestamp in milliseconds to an
     * {@link com.google.protobuf.Timestamp} proto message.
     *
     * @param millis the millis
     * @return the proto timestamp message
     * @since 1.0.7
     */
    public static Timestamp toProtobuf(long millis) {
        return Timestamp.newBuilder()
            .setSeconds(millis / 1000)
            .setNanos((int) ((millis % 1000) * 1000000))
            .build();
    }

    /**
     * Converts an {@link com.google.protobuf.Timestamp}
     * proto message to a unix timestamp in milliseconds.
     *
     * @param message the duration message
     * @return the unix timestamp in milliseconds
     * @since 1.0.7
     */
    public static long fromProtobuf(Timestamp message) {
        return message.getSeconds() * 1000 + message.getNanos() / 1000000;
    }

    /**
     * Converts an {@link PlayerInfo} object to an
     * {@link com.lunarclient.apollo.anticheat.v1.PlayerInfo} proto message.
     *
     * @param object the player info
     * @return the proto player info message
     * @since 1.0.7
     */
    public static com.lunarclient.apollo.anticheat.v1.PlayerInfo toProtobuf(PlayerInfo object) {
        return com.lunarclient.apollo.anticheat.v1.PlayerInfo.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(object.getPlayerUuid()))
            .setLocation(NetworkTypes.toProtobuf(object.getLocation()))
            .setSprinting(object.isSprinting())
            .setSneaking(object.isSneaking())
            .setPingMs((int) object.getPing()) // TODO
            .build();
    }

    /**
     * Converts an {@link com.lunarclient.apollo.anticheat.v1.PlayerInfo}
     * proto message to an {@link PlayerInfo} object.
     *
     * @param message the player info message
     * @return the apollo player info object
     * @since 1.0.7
     */
    public static PlayerInfo fromProtobuf(com.lunarclient.apollo.anticheat.v1.PlayerInfo message) {
        return PlayerInfo.builder()
            .playerUuid(NetworkTypes.fromProtobuf(message.getPlayerUuid()))
            .location(NetworkTypes.fromProtobuf(message.getLocation()))
            .sneaking(message.getSneaking())
            .sprinting(message.getSprinting())
            .ping(message.getPingMs())
            .build();
    }

    /**
     * Converts an {@link ApolloLocation} object to an
     * {@link com.lunarclient.apollo.common.v1.Location} proto message.
     *
     * @param object the location
     * @return the proto location message
     * @since 1.0.0
     */
    public static com.lunarclient.apollo.common.v1.Location toProtobuf(ApolloLocation object) {
        return com.lunarclient.apollo.common.v1.Location.newBuilder()
            .setWorld(object.getWorld())
            .setX(object.getX())
            .setY(object.getY())
            .setZ(object.getZ())
            .build();
    }

    /**
     * Converts an {@link com.lunarclient.apollo.common.v1.Location}
     * proto message to an {@link ApolloLocation} object.
     *
     * @param message the location message
     * @return the apollo location object
     * @since 1.0.0
     */
    public static ApolloLocation fromProtobuf(com.lunarclient.apollo.common.v1.Location message) {
        return ApolloLocation.builder()
            .world(message.getWorld())
            .x(message.getX())
            .y(message.getY())
            .z(message.getZ())
            .build();
    }

    /**
     * Converts an {@link ApolloBlockLocation} object to an
     * {@link com.lunarclient.apollo.common.v1.BlockLocation} proto message.
     *
     * @param object the block location
     * @return the proto block location message
     * @since 1.0.0
     */
    public static com.lunarclient.apollo.common.v1.BlockLocation toProtobuf(ApolloBlockLocation object) {
        return com.lunarclient.apollo.common.v1.BlockLocation.newBuilder()
            .setWorld(object.getWorld())
            .setX(object.getX())
            .setY(object.getY())
            .setZ(object.getZ())
            .build();
    }

    /**
     * Converts an {@link com.lunarclient.apollo.common.v1.BlockLocation}
     * proto message to an {@link ApolloBlockLocation} object.
     *
     * @param message the block location message
     * @return the apollo block location object
     * @since 1.0.0
     */
    public static ApolloBlockLocation fromProtobuf(com.lunarclient.apollo.common.v1.BlockLocation message) {
        return ApolloBlockLocation.builder()
            .world(message.getWorld())
            .x(message.getX())
            .y(message.getY())
            .z(message.getZ())
            .build();
    }

    /**
     * Converts an {@link ApolloPlayerLocation} object to an
     * {@link com.lunarclient.apollo.common.v1.PlayerLocation} proto message.
     *
     * @param object the player location
     * @return the proto player location message
     * @since 1.0.7
     */
    public static com.lunarclient.apollo.common.v1.PlayerLocation toProtobuf(ApolloPlayerLocation object) {
        return com.lunarclient.apollo.common.v1.PlayerLocation.newBuilder()
            .setLocation(NetworkTypes.toProtobuf(object.getLocation()))
            .setYaw(object.getYaw())
            .setPitch(object.getPitch())
            .build();
    }

    /**
     * Converts an {@link com.lunarclient.apollo.common.v1.PlayerLocation}
     * proto message to an {@link ApolloPlayerLocation} object.
     *
     * @param message the player location message
     * @return the apollo player location object
     * @since 1.0.7
     */
    public static ApolloPlayerLocation fromProtobuf(com.lunarclient.apollo.common.v1.PlayerLocation message) {
        return ApolloPlayerLocation.builder()
            .location(NetworkTypes.fromProtobuf(message.getLocation()))
            .yaw(message.getYaw())
            .pitch(message.getPitch())
            .build();
    }

    /**
     * Converts an {@link Cuboid2D} object to an
     * {@link com.lunarclient.apollo.common.v1.Cuboid2D} proto message.
     *
     * @param object the cuboid 2D
     * @return the proto cuboid 2D message
     * @since 1.0.0
     */
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
                .setItemId(item.getItemId());

            if (itemName != null) {
                itemBuilder.setItemName(itemName);
            }

            builder.setItemStack(itemBuilder.build());
        } else if (icon instanceof SimpleResourceLocationIcon) {
            SimpleResourceLocationIcon simple = (SimpleResourceLocationIcon) icon;

            builder.setSimpleResourceLocation(com.lunarclient.apollo.common.v1.SimpleResourceLocationIcon.newBuilder()
                .setResourceLocation(simple.getResourceLocation())
                .setSize(simple.getSize())
                .build());
        } else if (icon instanceof AdvancedResourceLocationIcon) {
            AdvancedResourceLocationIcon advanced = (AdvancedResourceLocationIcon) icon;

            builder.setAdvancedResourceLocation(com.lunarclient.apollo.common.v1.AdvancedResourceLocationIcon.newBuilder()
                .setResourceLocation(advanced.getResourceLocation())
                .setWidth(advanced.getWidth())
                .setHeight(advanced.getHeight())
                .setMinU(advanced.getMinU())
                .setMaxU(advanced.getMaxU())
                .setMinV(advanced.getMinV())
                .setMaxV(advanced.getMaxV())
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

    private NetworkTypes() {
    }

}

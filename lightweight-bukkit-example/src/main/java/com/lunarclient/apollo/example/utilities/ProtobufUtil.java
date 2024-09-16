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
import com.lunarclient.apollo.common.v1.AdvancedResourceLocationIcon;
import com.lunarclient.apollo.common.v1.BlockLocation;
import com.lunarclient.apollo.common.v1.Cuboid2D;
import com.lunarclient.apollo.common.v1.EntityId;
import com.lunarclient.apollo.common.v1.Icon;
import com.lunarclient.apollo.common.v1.ItemStackIcon;
import com.lunarclient.apollo.common.v1.SimpleResourceLocationIcon;
import com.lunarclient.apollo.common.v1.Uuid;
import java.awt.Color;
import java.time.Duration;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

public final class ProtobufUtil {

    public static UUID toJavaUuid(Uuid message) {
        return new UUID(message.getHigh64(), message.getLow64());
    }

    public static long toJavaTimestamp(Timestamp message) {
        return message.getSeconds() * 1000 + message.getNanos() / 1000000;
    }

    public static Location toBukkitLocation(com.lunarclient.apollo.common.v1.Location message) {
        return new Location(Bukkit.getWorld(message.getWorld()), message.getX(), message.getY(), message.getZ());
    }

    public static Location toBukkitLocation(com.lunarclient.apollo.common.v1.PlayerLocation message) {
        Location location = ProtobufUtil.toBukkitLocation(message.getLocation());
        location.setYaw(message.getYaw());
        location.setPitch(message.getPitch());
        return location;
    }

    public static EntityId createEntityIdProto(int id, UUID uuid) {
        return EntityId.newBuilder()
            .setEntityId(id)
            .setEntityUuid(ProtobufUtil.createUuidProto(uuid))
            .build();
    }

    public static Uuid createUuidProto(UUID object) {
        return Uuid.newBuilder()
            .setHigh64(object.getMostSignificantBits())
            .setLow64(object.getLeastSignificantBits())
            .build();
    }

    public static com.lunarclient.apollo.common.v1.Color createColorProto(Color object) {
        return com.lunarclient.apollo.common.v1.Color.newBuilder()
            .setColor(object.getRGB())
            .build();
    }

    public static com.google.protobuf.Duration createDurationProto(Duration object) {
        return com.google.protobuf.Duration.newBuilder()
            .setSeconds(object.getSeconds())
            .setNanos(object.getNano())
            .build();
    }

    public static com.lunarclient.apollo.common.v1.Location createLocationProto(Location location) {
        return com.lunarclient.apollo.common.v1.Location.newBuilder()
            .setWorld(location.getWorld().getName())
            .setX(location.getX())
            .setY(location.getY())
            .setZ(location.getZ())
            .build();
    }

    public static BlockLocation createBlockLocationProto(Location location) {
        return BlockLocation.newBuilder()
            .setWorld(location.getWorld().getName())
            .setX(location.getBlockX())
            .setY(location.getBlockY())
            .setZ(location.getBlockZ())
            .build();
    }

    public static Cuboid2D createCuboid2DProto(double minX, double minZ, double maxX, double maxZ) {
        return Cuboid2D.newBuilder()
            .setMinX(minX)
            .setMinZ(minZ)
            .setMaxX(maxX)
            .setMaxZ(maxZ)
            .build();
    }

    public static Icon createItemStackIconProto(@Nullable String itemName, int itemId, int customModelData) {
        ItemStackIcon.Builder iconBuilder = ItemStackIcon.newBuilder()
            .setItemId(itemId)
            .setCustomModelData(customModelData);

        if (itemName != null) {
            iconBuilder.setItemName(itemName);
        }

        return Icon.newBuilder().setItemStack(iconBuilder.build()).build();
    }

    public static Icon createSimpleResourceLocationIconProto(String resourceLocation, int size) {
        SimpleResourceLocationIcon icon = SimpleResourceLocationIcon.newBuilder()
            .setResourceLocation(resourceLocation)
            .setSize(size)
            .build();

        return Icon.newBuilder().setSimpleResourceLocation(icon).build();
    }

    public static Icon createAdvancedResourceLocationIconProto(String resourceLocation, float width, float height,
                                                               float minU, float maxU, float minV, float maxV) {
        AdvancedResourceLocationIcon icon = AdvancedResourceLocationIcon.newBuilder()
            .setResourceLocation(resourceLocation)
            .setWidth(width)
            .setHeight(height)
            .setMinU(minU)
            .setMaxU(maxU)
            .setMinV(minV)
            .setMaxV(maxV)
            .build();

        return Icon.newBuilder().setAdvancedResourceLocation(icon).build();
    }

    private ProtobufUtil() {
    }

}

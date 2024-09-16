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
import com.lunarclient.apollo.common.v1.EntityId;
import com.lunarclient.apollo.common.v1.Uuid;
import com.lunarclient.apollo.example.utilities.objects.cuboid.Cuboid2D;
import com.lunarclient.apollo.example.utilities.objects.icon.AdvancedResourceLocationIcon;
import com.lunarclient.apollo.example.utilities.objects.icon.Icon;
import com.lunarclient.apollo.example.utilities.objects.icon.ItemStackIcon;
import com.lunarclient.apollo.example.utilities.objects.icon.SimpleResourceLocationIcon;
import java.awt.Color;
import java.time.Duration;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;

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

    public static com.lunarclient.apollo.common.v1.BlockLocation createBlockLocationProto(Location location) {
        return com.lunarclient.apollo.common.v1.BlockLocation.newBuilder()
            .setWorld(location.getWorld().getName())
            .setX(location.getBlockX())
            .setY(location.getBlockY())
            .setZ(location.getBlockZ())
            .build();
    }

    public static com.lunarclient.apollo.common.v1.Cuboid2D createCuboid2DProto(Cuboid2D object) {
        return com.lunarclient.apollo.common.v1.Cuboid2D.newBuilder()
            .setMinX(object.getMinX())
            .setMinZ(object.getMinZ())
            .setMaxX(object.getMaxX())
            .setMaxZ(object.getMaxZ())
            .build();
    }

    public static com.lunarclient.apollo.common.v1.Icon createIconProto(Icon icon) {
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

    private ProtobufUtil() {
    }

}

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
package com.lunarclient.apollo.example.json.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class JsonUtil {

    public static UUID toJavaUuid(JsonObject obj) {
        long high = Long.parseUnsignedLong(obj.get("high64").getAsString());
        long low = Long.parseUnsignedLong(obj.get("low64").getAsString());
        return new UUID(high, low);
    }

    public static long toJavaTimestamp(JsonObject timestampObject) {
        JsonObject packetInfo = timestampObject.getAsJsonObject("packet_info");
        String iso = packetInfo.get("instantiation_time").getAsString();
        return Instant.parse(iso).toEpochMilli();
    }

    public static JsonObject createEnableModuleObjectWithType(@NotNull String module, Map<String, Object> properties) {
        JsonObject enableModuleObject = JsonPacketUtil.createEnableModuleObject(module, properties);
        enableModuleObject.addProperty("@type", "type.googleapis.com/lunarclient.apollo.configurable.v1.ConfigurableSettings");
        return enableModuleObject;
    }

    public static JsonObject createUuidObject(@NotNull UUID uuid) {
        JsonObject uuidObject = new JsonObject();
        uuidObject.addProperty("high64", Long.toUnsignedString(uuid.getMostSignificantBits()));
        uuidObject.addProperty("low64", Long.toUnsignedString(uuid.getLeastSignificantBits()));
        return uuidObject;
    }

    public static JsonObject createColorObject(@NotNull Color color) {
        JsonObject colorObject = new JsonObject();
        colorObject.addProperty("color", color.getRGB());
        return colorObject;
    }

    public static String createDurationObject(@NotNull Duration duration) {
        long seconds = duration.getSeconds();
        int nanos = duration.getNano();

        // Is there a better way to do this?
        String durationString;
        if (nanos == 0) {
            durationString = seconds + "s";
        } else {
            durationString = String.format("%d.%09ds", seconds, nanos)
                .replaceAll("0+$", "")
                .replaceAll("\\.$", "");
        }

        return durationString;
    }

    public static JsonObject createCuboid2DObject(double minX, double minZ, double maxX, double maxZ) {
        JsonObject cuboid2DObject = new JsonObject();
        cuboid2DObject.addProperty("min_x", minX);
        cuboid2DObject.addProperty("min_z", minZ);
        cuboid2DObject.addProperty("max_x", maxX);
        cuboid2DObject.addProperty("max_z", maxZ);
        return cuboid2DObject;
    }

    public static JsonObject createEntityIdObject(@NotNull Entity entity) {
        return JsonUtil.createEntityIdObject(entity.getEntityId(), entity.getUniqueId());
    }

    public static JsonObject createEntityIdObject(int entityId, @NotNull UUID uuid) {
        JsonObject entityIdObject = new JsonObject();
        entityIdObject.addProperty("entity_id", entityId);
        entityIdObject.add("entity_uuid", JsonUtil.createUuidObject(uuid));
        return entityIdObject;
    }

    public static JsonObject createLocationObject(@NotNull Location location) {
        JsonObject locationObject = new JsonObject();
        locationObject.addProperty("world", location.getWorld().getName());
        locationObject.addProperty("x", location.getX());
        locationObject.addProperty("y", location.getY());
        locationObject.addProperty("z", location.getZ());
        return locationObject;
    }

    public static JsonObject createBlockLocationObject(@NotNull Location location) {
        JsonObject locationObject = new JsonObject();
        locationObject.addProperty("world", location.getWorld().getName());
        locationObject.addProperty("x", location.getBlockX());
        locationObject.addProperty("y", location.getBlockY());
        locationObject.addProperty("z", location.getBlockZ());
        return locationObject;
    }

    public static Location toBukkitLocation(JsonObject message) {
        return new Location(
            Bukkit.getWorld(message.get("world").getAsString()),
            message.get("x").getAsDouble(),
            message.get("y").getAsDouble(),
            message.get("z").getAsDouble()
        );
    }

    public static Location toBukkitPlayerLocation(JsonObject message) {
        Location location = JsonUtil.toBukkitLocation(message.getAsJsonObject("location"));
        location.setYaw(message.get("yaw").getAsFloat());
        location.setPitch(message.get("pitch").getAsFloat());
        return location;
    }

    public static JsonObject createItemStackIconObject(@Nullable String itemName, int itemId) {
        return JsonUtil.createItemStackIconObject(itemName, itemId, null, null);
    }

    public static JsonObject createItemStackIconObject(@Nullable String itemName, int itemId, @Nullable JsonObject customModelData, @Nullable JsonObject profile) {
        JsonObject itemIconObject = new JsonObject();
        if (itemName != null) {
            itemIconObject.addProperty("item_name", itemName);
        } else {
            itemIconObject.addProperty("item_id", itemId);
        }

        if (customModelData != null) {
            itemIconObject.add("custom_model_data_object", customModelData);
        }

        if (profile != null) {
            itemIconObject.add("profile", profile);
        }

        JsonObject iconObject = new JsonObject();
        iconObject.add("item_stack", itemIconObject);
        return iconObject;
    }

    public static JsonObject createCustomModelDataObject(List<Float> floats, List<Boolean> flags, List<String> strings, List<Integer> colors) {
        JsonObject customModelDataObject = new JsonObject();

        JsonArray floatsArray = new JsonArray();
        floats.forEach(floatsArray::add);
        customModelDataObject.add("floats", floatsArray);

        JsonArray flagsArray = new JsonArray();
        flags.forEach(flagsArray::add);
        customModelDataObject.add("flags", flagsArray);

        JsonArray stringsArray = new JsonArray();
        strings.forEach(stringsArray::add);
        customModelDataObject.add("strings", stringsArray);

        JsonArray colorsArray = new JsonArray();
        colors.forEach(colorsArray::add);
        customModelDataObject.add("colors", colorsArray);

        return customModelDataObject;
    }

    public static JsonObject createProfileObject(@Nullable UUID id, @NotNull String texture, @NotNull String signature) {
        JsonObject profileObject = new JsonObject();
        if (id != null) {
            profileObject.add("id", JsonUtil.createUuidObject(id));
        }
        profileObject.addProperty("texture", texture);
        profileObject.addProperty("signature", signature);
        return profileObject;
    }

    public static JsonObject createResourceLocationIconObject(@NotNull String resourceLocation) {
        JsonObject resourceIconObject = new JsonObject();
        resourceIconObject.addProperty("resource_location", resourceLocation);

        JsonObject iconObject = new JsonObject();
        iconObject.add("resource_location", resourceIconObject);

        return iconObject;
    }

    public static JsonObject createSimpleResourceLocationIconObject(@NotNull String resourceLocation, int size) {
        JsonObject simpleIconObject = new JsonObject();
        simpleIconObject.addProperty("resource_location", resourceLocation);
        simpleIconObject.addProperty("size", size);

        JsonObject iconObject = new JsonObject();
        iconObject.add("simple_resource_location", simpleIconObject);

        return iconObject;
    }

    public static JsonObject createAdvancedResourceLocationIconObject(@NotNull String resourceLocation, float width, float height,
                                                               float minU, float maxU, float minV, float maxV) {
        JsonObject advancedIcon = new JsonObject();
        advancedIcon.addProperty("resource_location", resourceLocation);
        advancedIcon.addProperty("width", width);
        advancedIcon.addProperty("height", height);
        advancedIcon.addProperty("min_u", minU);
        advancedIcon.addProperty("max_u", maxU);
        advancedIcon.addProperty("min_v", minV);
        advancedIcon.addProperty("max_v", maxV);

        JsonObject iconObject = new JsonObject();
        iconObject.add("advanced_resource_location", advancedIcon);

        return iconObject;
    }

    private JsonUtil() {
    }

}

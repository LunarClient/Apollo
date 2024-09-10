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

import com.google.gson.JsonObject;
import com.lunarclient.apollo.example.utilities.objects.cuboid.Cuboid2D;
import com.lunarclient.apollo.example.utilities.objects.icon.AdvancedResourceLocationIcon;
import com.lunarclient.apollo.example.utilities.objects.icon.Icon;
import com.lunarclient.apollo.example.utilities.objects.icon.ItemStackIcon;
import com.lunarclient.apollo.example.utilities.objects.icon.SimpleResourceLocationIcon;
import java.awt.Color;
import java.time.Duration;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public final class JsonUtil {

    // TODO: broken
    public static JsonObject createDurationObject(@NotNull Duration duration) {
        JsonObject durationObject = new JsonObject();
        durationObject.addProperty("seconds", duration.getSeconds());
        durationObject.addProperty("nanos", duration.getNano());
        return durationObject;
    }

    public static JsonObject createColorObject(@NotNull Color color) {
        JsonObject colorObject = new JsonObject();
        colorObject.addProperty("color", color.getRGB());
        return colorObject;
    }

    // TODO: broken
    public static JsonObject createUuidObject(@NotNull UUID uuid) {
        JsonObject uuidObject = new JsonObject();
        uuidObject.addProperty("high64", uuid.getMostSignificantBits());
        uuidObject.addProperty("low64", uuid.getLeastSignificantBits());
        return uuidObject;
    }

    public static JsonObject createEnableModuleObject(@NotNull String module) {
        JsonObject enableModuleObject = new JsonObject();
        enableModuleObject.addProperty("apollo_module", module);
        enableModuleObject.addProperty("enable", true);
        return enableModuleObject;
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

    public static JsonObject createEntityIdObject(@NotNull Entity entity) {
        JsonObject entityIdObject = new JsonObject();
        entityIdObject.addProperty("entity_id", entity.getEntityId());
        entityIdObject.add("entity_uuid", JsonUtil.createUuidObject(entity.getUniqueId()));
        return entityIdObject;
    }

    public static JsonObject createCuboid2DObject(@NotNull Cuboid2D cuboid2D) {
        JsonObject cuboid2DObject = new JsonObject();
        cuboid2DObject.addProperty("min_x", cuboid2D.getMinX());
        cuboid2DObject.addProperty("min_z", cuboid2D.getMinZ());
        cuboid2DObject.addProperty("max_x", cuboid2D.getMaxX());
        cuboid2DObject.addProperty("max_z", cuboid2D.getMaxZ());
        return cuboid2DObject;
    }

    public static JsonObject createIconObject(@NotNull Icon icon) {
        JsonObject iconObject = new JsonObject();

        if (icon instanceof ItemStackIcon) {
            ItemStackIcon item = (ItemStackIcon) icon;
            String itemName = item.getItemName();

            iconObject.addProperty("@type", "type.googleapis.com/lunarclient.apollo.common.v1.ItemStackIcon");
            iconObject.addProperty("item_id", item.getItemId());
            iconObject.addProperty("custom_model_data", item.getCustomModelData());

            if (itemName != null) {
                iconObject.addProperty("item_name", itemName);
            }
        } else if (icon instanceof SimpleResourceLocationIcon) {
            SimpleResourceLocationIcon simple = (SimpleResourceLocationIcon) icon;

            iconObject.addProperty("@type", "type.googleapis.com/lunarclient.apollo.common.v1.SimpleResourceLocationIcon");
            iconObject.addProperty("resource_location", simple.getResourceLocation());
            iconObject.addProperty("size", simple.getSize());
        } else if (icon instanceof AdvancedResourceLocationIcon) {
            AdvancedResourceLocationIcon advanced = (AdvancedResourceLocationIcon) icon;

            iconObject.addProperty("@type", "type.googleapis.com/lunarclient.apollo.common.v1.AdvancedResourceLocationIcon");
            iconObject.addProperty("resource_location", advanced.getResourceLocation());
            iconObject.addProperty("width", advanced.getWidth());
            iconObject.addProperty("height", advanced.getHeight());
            iconObject.addProperty("min_u", advanced.getMinU());
            iconObject.addProperty("max_u", advanced.getMaxU());
            iconObject.addProperty("min_v", advanced.getMinV());
            iconObject.addProperty("max_v", advanced.getMaxV());
        }

        return iconObject;
    }

    private JsonUtil() {
    }

}

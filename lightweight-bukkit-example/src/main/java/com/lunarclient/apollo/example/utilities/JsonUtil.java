package com.lunarclient.apollo.example.utilities;

import com.google.gson.JsonObject;
import com.lunarclient.apollo.example.utilities.objects.cuboid.Cuboid2D;
import com.lunarclient.apollo.example.utilities.objects.cuboid.Cuboid3D;
import com.lunarclient.apollo.example.utilities.objects.icon.AdvancedResourceLocationIcon;
import com.lunarclient.apollo.example.utilities.objects.icon.Icon;
import com.lunarclient.apollo.example.utilities.objects.icon.ItemStackIcon;
import com.lunarclient.apollo.example.utilities.objects.icon.SimpleResourceLocationIcon;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.awt.*;
import java.time.Duration;
import java.util.UUID;

public final class JsonUtil {

    public static String component(Object object) {
        return null; // TODO
    }

    public static JsonObject createDurationObject(Duration duration) {
        return null; // TODO
    }

    public static JsonObject createColorObject(Color color) {
        JsonObject colorObject = new JsonObject();
        colorObject.addProperty("@type", "type.googleapis.com/lunarclient.apollo.common.v1.Color");
        colorObject.addProperty("color", color.getRGB());
        return colorObject;
    }

    public static JsonObject createUuidObject(UUID uuid) {
        JsonObject uuidObject = new JsonObject();
        uuidObject.addProperty("@type", "type.googleapis.com/lunarclient.apollo.common.v1.Uuid");
        uuidObject.addProperty("high64", uuid.getMostSignificantBits());
        uuidObject.addProperty("low64", uuid.getLeastSignificantBits());
        return uuidObject;
    }

    public static JsonObject createLocationObject(Location location) {
        JsonObject locationObject = new JsonObject();
        locationObject.addProperty("@type", "type.googleapis.com/lunarclient.apollo.common.v1.Location");
        locationObject.addProperty("world", location.getWorld().getName());
        locationObject.addProperty("x", location.getX());
        locationObject.addProperty("y", location.getY());
        locationObject.addProperty("z", location.getZ());
        return locationObject;
    }

    public static JsonObject createBlockLocationObject(Location location) {
        JsonObject locationObject = new JsonObject();
        locationObject.addProperty("@type", "type.googleapis.com/lunarclient.apollo.common.v1.Location");
        locationObject.addProperty("world", location.getWorld().getName());
        locationObject.addProperty("x", location.getBlockX());
        locationObject.addProperty("y", location.getBlockY());
        locationObject.addProperty("z", location.getBlockZ());
        return locationObject;
    }

    public static JsonObject createEntityIdObject(Entity entity) {
        JsonObject entityIdObject = new JsonObject();
        entityIdObject.addProperty("@type", "type.googleapis.com/lunarclient.apollo.common.v1.EntityId");
        entityIdObject.addProperty("entity_id", entity.getEntityId());
        entityIdObject.add("uuid", JsonUtil.createUuidObject(entity.getUniqueId()));
        return entityIdObject;
    }

    public static JsonObject createCuboid2DObject(Cuboid2D cuboid2D) {
        JsonObject cuboid2DObject = new JsonObject();
        cuboid2DObject.addProperty("min_x", cuboid2D.getMinX());
        cuboid2DObject.addProperty("min_z", cuboid2D.getMinZ());
        cuboid2DObject.addProperty("max_x", cuboid2D.getMaxX());
        cuboid2DObject.addProperty("max_z", cuboid2D.getMaxZ());
        return cuboid2DObject;
    }

    public static JsonObject createCuboid3DObject(Cuboid3D cuboid3D) {
        JsonObject cuboid3DObject = new JsonObject();
        cuboid3DObject.addProperty("min_x", cuboid3D.getMinX());
        cuboid3DObject.addProperty("min_y", cuboid3D.getMinY());
        cuboid3DObject.addProperty("min_z", cuboid3D.getMinZ());
        cuboid3DObject.addProperty("max_x", cuboid3D.getMaxX());
        cuboid3DObject.addProperty("max_y", cuboid3D.getMaxY());
        cuboid3DObject.addProperty("max_z", cuboid3D.getMaxZ());
        return cuboid3DObject;
    }

    public static JsonObject createIconObject(Icon icon) {
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

}

package com.lunarclient.apollo.example.utilities;

import com.google.gson.JsonObject;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.awt.*;
import java.util.UUID;

public final class JsonUtil {

    public static JsonObject createColorObject(Color color) {
        JsonObject colorObject = new JsonObject();
        colorObject.addProperty("color", color.getRGB());
        return colorObject;
    }

    public static JsonObject createUuidObject(UUID uuid) {
        JsonObject uuidObject = new JsonObject();
        uuidObject.addProperty("high64", uuid.getMostSignificantBits());
        uuidObject.addProperty("low64", uuid.getLeastSignificantBits());
        return uuidObject;
    }

    public static JsonObject createLocationObject(Location location) {
        JsonObject locationObject = new JsonObject();
        locationObject.addProperty("world", location.getWorld().getName());
        locationObject.addProperty("x", location.getX());
        locationObject.addProperty("y", location.getY());
        locationObject.addProperty("z", location.getZ());
        return locationObject;
    }

    public static JsonObject createBlockLocationObject(Location location) {
        JsonObject locationObject = new JsonObject();
        locationObject.addProperty("world", location.getWorld().getName());
        locationObject.addProperty("x", location.getBlockX());
        locationObject.addProperty("y", location.getBlockY());
        locationObject.addProperty("z", location.getBlockZ());
        return locationObject;
    }

    public static JsonObject createEntityIdObject(Entity entity) {
        JsonObject entityIdObject = new JsonObject();
        entityIdObject.addProperty("entity_id", entity.getEntityId());
        entityIdObject.add("uuid", JsonUtil.createUuidObject(entity.getUniqueId()));
        return entityIdObject;
    }

}

package com.moonsworth.apollo.impl.bukkit.wrapper;

import com.moonsworth.apollo.api.bridge.ApolloLocation;
import org.bukkit.Location;

public record BukkitLocation(Location location) implements ApolloLocation {
    @Override
    public int getX() {
        return location.getBlockX();
    }

    @Override
    public int getY() {
        return location.getBlockY();
    }

    @Override
    public int getZ() {
        return location.getBlockZ();
    }

    @Override
    public String getWorld() {
        if (location.getWorld() != null) {
            return location.getWorld().getName();
        }
        return null;
    }
}

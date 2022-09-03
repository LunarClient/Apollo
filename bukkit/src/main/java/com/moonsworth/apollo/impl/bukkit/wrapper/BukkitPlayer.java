package com.moonsworth.apollo.impl.bukkit.wrapper;

import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.bridge.ApolloLocation;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.impl.bukkit.ApolloBukkitPlatform;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Wrapper for Bukkit Players to implement the ApolloPlayer interface.
 *
 * @param player Player to wrap
 */
public record BukkitPlayer(Player player) implements ApolloPlayer {

    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    @Override
    public void sendPacket(byte[] bytes) {
        player.sendPluginMessage(ApolloBukkitPlatform.getInstance(), Apollo.PLUGIN_MESSAGE_CHANNEL, bytes);
    }

    @Override
    public boolean hasPermission(String permissionNode) {
        return player.hasPermission(permissionNode);
    }

    @Override
    public ApolloLocation getWorldLocation() {
        return new BukkitLocation(player.getLocation());
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(message);
    }
}

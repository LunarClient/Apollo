package com.moonsworth.apollo.impl.bukkit.wrapper;

import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.impl.bukkit.ApolloBukkitPlatform;
import org.bukkit.entity.Player;

/**
 * Wrapper for Bukkit Players to implement the ApolloPlayer interface.
 *
 * @param player Player to wrap
 */
public record BukkitPlayer(Player player) implements ApolloPlayer {

    @Override
    public void sendPacket(byte[] bytes) {
        player.sendPluginMessage(ApolloBukkitPlatform.getInstance(), Apollo.PLUGIN_MESSAGE_CHANNEL, bytes);
    }
}

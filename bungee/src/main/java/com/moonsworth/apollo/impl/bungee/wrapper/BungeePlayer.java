package com.moonsworth.apollo.impl.bungee.wrapper;

import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.bridge.ApolloLocation;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

public record BungeePlayer(ProxiedPlayer player) implements ApolloPlayer {

    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    @Override
    public void sendPacket(byte[] bytes) {
        player.sendData(Apollo.PLUGIN_MESSAGE_CHANNEL, bytes);
    }

    @Override
    public boolean hasPermission(String permissionNode) {
        return player.hasPermission(permissionNode);
    }

    @Override
    public ApolloLocation getWorldLocation() {
        throw new UnsupportedOperationException("This method is not allowed on the proxy!");
    }
}

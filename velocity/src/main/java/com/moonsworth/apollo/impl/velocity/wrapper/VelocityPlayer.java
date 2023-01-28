package com.moonsworth.apollo.impl.velocity.wrapper;

import com.moonsworth.apollo.api.bridge.ApolloLocation;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.impl.velocity.ApolloVelocityPlatform;
import com.velocitypowered.api.proxy.Player;
import net.kyori.adventure.text.Component;

import java.util.UUID;

public record VelocityPlayer(Player player) implements ApolloPlayer {

    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    @Override
    public void sendPacket(byte[] bytes) {
        player.sendPluginMessage(ApolloVelocityPlatform.PLUGIN_CHANNEL, bytes);
    }

    @Override
    public boolean hasPermission(String permissionNode) {
        return player.hasPermission(permissionNode);
    }

    @Override
    public ApolloLocation getWorldLocation() {
        throw new UnsupportedOperationException("This method is not allowed on the proxy!");
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(Component.text(message));
    }
}

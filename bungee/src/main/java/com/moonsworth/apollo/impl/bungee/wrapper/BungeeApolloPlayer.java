package com.moonsworth.apollo.impl.bungee.wrapper;

import com.moonsworth.apollo.ApolloManager;
import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayerVersion;
import com.moonsworth.apollo.world.ApolloLocation;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public final class BungeeApolloPlayer extends AbstractApolloPlayer {

    private final ProxiedPlayer player;

    @Override
    public ApolloPlayerVersion getVersion() {
        return ApolloPlayerVersion.v1_19_4;
    }

    @Override
    public UUID getUniqueId() {
        return this.player.getUniqueId();
    }

    @Override
    public Optional<ApolloLocation> getLocation() {
        return Optional.empty();
    }

    @Override
    public boolean hasPermission(final String permissionNode) {
        return this.player.hasPermission(permissionNode);
    }

    @Override
    public void sendPacket(final byte[] messages) {
        this.player.sendData(ApolloManager.PLUGIN_MESSAGE_CHANNEL, messages);
    }

}

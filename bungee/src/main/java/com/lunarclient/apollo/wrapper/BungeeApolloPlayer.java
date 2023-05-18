package com.lunarclient.apollo.wrapper;

import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayerVersion;
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
    public boolean hasPermission(String permissionNode) {
        return this.player.hasPermission(permissionNode);
    }

    @Override
    public void sendPacket(byte[] messages) {
        this.player.sendData(ApolloManager.PLUGIN_MESSAGE_CHANNEL, messages);
    }

}

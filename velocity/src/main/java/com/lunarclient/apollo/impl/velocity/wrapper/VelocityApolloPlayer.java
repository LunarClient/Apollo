package com.lunarclient.apollo.impl.velocity.wrapper;

import com.lunarclient.apollo.impl.velocity.ApolloVelocityPlatform;
import com.lunarclient.apollo.player.ApolloPlayerVersion;
import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.velocitypowered.api.proxy.Player;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public final class VelocityApolloPlayer extends AbstractApolloPlayer {

    private final Player player;


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
        this.player.sendPluginMessage(ApolloVelocityPlatform.PLUGIN_CHANNEL, messages);
    }

}

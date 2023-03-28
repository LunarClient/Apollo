package com.moonsworth.apollo.impl.velocity.wrapper;

import com.moonsworth.apollo.impl.velocity.ApolloVelocityPlatform;
import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayerVersion;
import com.moonsworth.apollo.world.ApolloLocation;
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
    public boolean hasPermission(final String permissionNode) {
        return this.player.hasPermission(permissionNode);
    }

    @Override
    public void sendPacket(final byte[] messages) {
        this.player.sendPluginMessage(ApolloVelocityPlatform.PLUGIN_CHANNEL, messages);
    }

}

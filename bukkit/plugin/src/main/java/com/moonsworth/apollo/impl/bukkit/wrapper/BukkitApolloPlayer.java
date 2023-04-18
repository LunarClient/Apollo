package com.moonsworth.apollo.impl.bukkit.wrapper;

import com.moonsworth.apollo.ApolloManager;
import com.moonsworth.apollo.impl.bukkit.ApolloBukkitPlatform;
import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayerVersion;
import com.moonsworth.apollo.world.ApolloLocation;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public final class BukkitApolloPlayer extends AbstractApolloPlayer {

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
        final Location location = this.player.getLocation();
        return Optional.of(ApolloLocation.of(
                location.getWorld().getName(),
                location.getX(),
                location.getY(),
                location.getZ()
        ));
    }

    @Override
    public boolean hasPermission(final String permissionNode) {
        return this.player.hasPermission(permissionNode);
    }

    @Override
    public void sendPacket(final byte[] messages) {
        this.player.sendPluginMessage(ApolloBukkitPlatform.getInstance(), ApolloManager.PLUGIN_MESSAGE_CHANNEL, messages);
    }

}

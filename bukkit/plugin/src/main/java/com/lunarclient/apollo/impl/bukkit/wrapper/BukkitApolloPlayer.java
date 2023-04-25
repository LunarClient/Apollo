package com.lunarclient.apollo.impl.bukkit.wrapper;

import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.impl.bukkit.ApolloBukkitPlatform;
import com.lunarclient.apollo.player.ApolloPlayerVersion;
import com.lunarclient.apollo.world.ApolloLocation;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
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
        Location location = this.player.getLocation();
        return Optional.of(ApolloLocation.of(
                location.getWorld().getName(),
                location.getX(),
                location.getY(),
                location.getZ()
        ));
    }

    @Override
    public boolean hasPermission(String permissionNode) {
        return this.player.hasPermission(permissionNode);
    }

    @Override
    public void sendPacket(byte[] messages) {
        this.player.sendPluginMessage(ApolloBukkitPlatform.getInstance(), ApolloManager.PLUGIN_MESSAGE_CHANNEL, messages);
    }

}

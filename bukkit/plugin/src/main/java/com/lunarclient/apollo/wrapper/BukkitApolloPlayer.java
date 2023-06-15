package com.lunarclient.apollo.wrapper;

import com.lunarclient.apollo.ApolloBukkitPlatform;
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import lombok.AllArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public final class BukkitApolloPlayer extends AbstractApolloPlayer {

    private final Player player;

    @Override
    public UUID getUniqueId() {
        return this.player.getUniqueId();
    }

    @Override
    public Optional<ApolloLocation> getLocation() {
        Location location = this.player.getLocation();
        return Optional.of(ApolloLocation.builder()
            .world(location.getWorld().getName())
            .x(location.getX())
            .y(location.getY())
            .z(location.getZ())
            .build());
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

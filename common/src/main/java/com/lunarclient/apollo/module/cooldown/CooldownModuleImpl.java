package com.lunarclient.apollo.module.cooldown;

import com.lunarclient.apollo.cooldown.v1.DisplayCooldownMessage;
import com.lunarclient.apollo.cooldown.v1.RemoveCooldownMessage;
import com.lunarclient.apollo.cooldown.v1.ResetCooldownsMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import lombok.NonNull;

/**
 * Provides the cooldown module.
 *
 * @since 1.0.0
 */
public final class CooldownModuleImpl extends CooldownModule {

    @Override
    public void displayCooldown(@NonNull ApolloPlayer viewer, @NonNull Cooldown cooldown) {
        ((AbstractApolloPlayer) viewer).sendPacket(DisplayCooldownMessage.newBuilder()
            .setName(cooldown.getName())
            .setDuration(NetworkTypes.toProtobuf(cooldown.getDuration()))
            .setIcon(NetworkTypes.toProtobuf(cooldown.getIcon()))
            .build());
    }

    @Override
    public void removeCooldown(@NonNull ApolloPlayer viewer, @NonNull String cooldownName) {
        ((AbstractApolloPlayer) viewer).sendPacket(RemoveCooldownMessage.newBuilder()
            .setName(cooldownName)
            .build());
    }

    @Override
    public void removeCooldown(@NonNull ApolloPlayer viewer, @NonNull Cooldown cooldown) {
        this.removeCooldown(viewer, cooldown.getName());
    }

    @Override
    public void resetCooldowns(@NonNull ApolloPlayer viewer) {
        ((AbstractApolloPlayer) viewer).sendPacket(ResetCooldownsMessage.getDefaultInstance());
    }

}

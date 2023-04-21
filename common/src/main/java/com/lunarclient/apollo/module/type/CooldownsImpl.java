package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.cooldown.v1.DisplayCooldownMessage;
import com.lunarclient.apollo.cooldown.v1.RemoveCooldownMessage;
import com.lunarclient.apollo.cooldown.v1.ResetCooldownsMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.Cooldown;

import static java.util.Objects.requireNonNull;

/**
 * Provides the cooldown module.
 *
 * @since 1.0.0
 */
public final class CooldownsImpl extends Cooldowns {

    public CooldownsImpl() {
        super();
    }

    @Override
    public void displayCooldown(ApolloPlayer player, Cooldown cooldown) {
        requireNonNull(player, "player");
        requireNonNull(cooldown, "cooldown");

        ((AbstractApolloPlayer) player).sendPacket(DisplayCooldownMessage.newBuilder()
            .setName(cooldown.getName())
            .setDuration(NetworkTypes.toProtobuf(cooldown.getDuration()))
            .setIcon(NetworkTypes.toProtobuf(cooldown.getIcon()))
            .build());
    }

    @Override
    public void removeCooldown(ApolloPlayer player, String cooldownName) {
        requireNonNull(cooldownName, "cooldownName");

        ((AbstractApolloPlayer) player).sendPacket(RemoveCooldownMessage.newBuilder()
            .setName(cooldownName)
            .build());
    }

    @Override
    public void removeCooldown(ApolloPlayer player, Cooldown cooldown) {
        requireNonNull(cooldown, "cooldown");

        this.removeCooldown(player, cooldown.getName());
    }

    @Override
    public void resetCooldowns(ApolloPlayer player) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(ResetCooldownsMessage.getDefaultInstance());
    }

}

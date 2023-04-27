package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.coloredfire.v1.OverrideColoredFireMessage;
import com.lunarclient.apollo.coloredfire.v1.ResetColoredFireMessage;
import com.lunarclient.apollo.coloredfire.v1.ResetColoredFiresMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.ColoredFire;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

/**
 * Provides the colored fire module.
 *
 * @since 1.0.0
 */
public class ColoredFiresImpl extends ColoredFires {

    public ColoredFiresImpl() {
        super();
    }

    @Override
    public void overrideFireColor(ColoredFire fire, ApolloPlayer... viewers) {
        requireNonNull(fire, "fire");
        requireNonNull(viewers, "viewers");

        OverrideColoredFireMessage message = OverrideColoredFireMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(fire.getPlayer()))
            .setColor(NetworkTypes.toProtobuf(fire.getColor()))
            .build();

        for (ApolloPlayer player : viewers) {
            ((AbstractApolloPlayer) player).sendPacket(message);
        }
    }

    @Override
    public void resetFireColor(UUID playerUuid, ApolloPlayer... viewers) {
        requireNonNull(playerUuid, "playerUuid");
        requireNonNull(viewers, "viewers");

        ResetColoredFireMessage message = ResetColoredFireMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(playerUuid))
            .build();

        for (ApolloPlayer player : viewers) {
            ((AbstractApolloPlayer) player).sendPacket(message);
        }
    }

    @Override
    public void resetFireColor(ColoredFire fire, ApolloPlayer... viewers) {
        requireNonNull(fire, "fire");

        this.resetFireColor(fire.getPlayer(), viewers);
    }

    @Override
    public void resetFireColor(ApolloPlayer player, ApolloPlayer... viewers) {
        requireNonNull(player, "player");

        this.resetFireColor(player.getUniqueId(), viewers);
    }

    @Override
    public void resetFires(ApolloPlayer player) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(ResetColoredFiresMessage.getDefaultInstance());
    }

}

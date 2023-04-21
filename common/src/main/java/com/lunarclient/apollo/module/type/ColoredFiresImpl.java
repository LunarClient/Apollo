package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.coloredfire.v1.OverrideColoredFireMessage;
import com.lunarclient.apollo.coloredfire.v1.ResetColoredFireMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.ColoredFire;

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
    public void resetFireColor(ApolloPlayer... viewers) {
        requireNonNull(viewers, "viewers");

        for (ApolloPlayer player : viewers) {
            ((AbstractApolloPlayer) player).sendPacket(ResetColoredFireMessage.getDefaultInstance());
        }
    }

}

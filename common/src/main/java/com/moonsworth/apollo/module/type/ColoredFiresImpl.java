package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.network.NetworkTypes;
import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.ColoredFire;
import lunarclient.apollo.common.OptionOperation;
import lunarclient.apollo.modules.ColoredFireMessage;

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

        for(ApolloPlayer player : viewers) {
            ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.ADD, this.to(fire));
        }
    }

    @Override
    public void resetFireColor(ApolloPlayer... viewers) {
        requireNonNull(viewers, "viewers");

        for(ApolloPlayer player : viewers) {
            ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.CLEAR);
        }
    }

    private ColoredFireMessage to(ColoredFire fire) {
        return ColoredFireMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toUuid(fire.getPlayer()))
            .setColor(NetworkTypes.toColor(fire.getColor()))
            .build();
    }

    private ColoredFire from(ColoredFireMessage message) {
        return ColoredFire.of(
            NetworkTypes.fromUuid(message.getPlayerUuid()),
            NetworkTypes.fromColor(message.getColor())
        );
    }
}

package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.misc.Entity;
import com.lunarclient.apollo.tntcountdown.v1.SetTntCountdownMessage;

import static java.util.Objects.requireNonNull;

/**
 * Provides the tnt countdown module.
 *
 * @since 1.0.0
 */
public final class TntCountdownImpl extends TntCountdown {

    public TntCountdownImpl() {
        super();
    }

    @Override
    public void setTntTicks(ApolloPlayer player, Entity entity, int ticks) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(SetTntCountdownMessage.newBuilder()
            .setEntityUuid(NetworkTypes.toProtobuf(entity.getId()))
            .setDurationTicks(ticks)
            .build());
    }

}

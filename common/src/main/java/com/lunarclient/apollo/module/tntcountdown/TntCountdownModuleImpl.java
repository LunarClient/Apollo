package com.lunarclient.apollo.module.tntcountdown;

import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.tntcountdown.v1.SetTntCountdownMessage;
import java.util.UUID;
import lombok.NonNull;

/**
 * Provides the tnt countdown module.
 *
 * @since 1.0.0
 */
public final class TntCountdownModuleImpl extends TntCountdownModule {

    @Override
    public void setTntCountdown(@NonNull ApolloPlayer viewer, UUID tntUuid, int ticks) {
        ((AbstractApolloPlayer) viewer).sendPacket(SetTntCountdownMessage.newBuilder()
            .setEntityUuid(NetworkTypes.toProtobuf(tntUuid))
            .setDurationTicks(ticks)
            .build());
    }

}

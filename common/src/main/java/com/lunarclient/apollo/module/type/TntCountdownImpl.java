package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import lunarclient.apollo.common.OptionOperation;
import lunarclient.apollo.modules.TntCountdownMessage;

import static java.util.Objects.requireNonNull;

public final class TntCountdownImpl extends TntCountdown {

    public TntCountdownImpl() {
        super();
    }

    @Override
    public void setTntTicks(ApolloPlayer player, int entityId, int ticks) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.SET, this.to(entityId, ticks));
    }

    private TntCountdownMessage to(int entityId, int ticks) {
        return TntCountdownMessage.newBuilder()
                .setEntityId(entityId)
                .setTicks(ticks)
                .build();
    }

}

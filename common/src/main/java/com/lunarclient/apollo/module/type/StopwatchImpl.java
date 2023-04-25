package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import lunarclient.apollo.common.OptionOperation;
import lunarclient.apollo.modules.StopwatchActionMessage;

import static java.util.Objects.requireNonNull;

/**
 * Provides the stopwatch module.
 *
 * @since 1.0.0
 */
public final class StopwatchImpl extends Stopwatch {

    public StopwatchImpl() {
        super();
    }

    @Override
    public void start(ApolloPlayer player) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.SET, this.to(StopwatchActionMessage.Action.START));
    }

    @Override
    public void stop(ApolloPlayer player) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.SET, this.to(StopwatchActionMessage.Action.STOP));
    }

    @Override
    public void reset(ApolloPlayer player) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.SET, this.to(StopwatchActionMessage.Action.RESET));
    }

    private StopwatchActionMessage to(StopwatchActionMessage.Action action) {
        return StopwatchActionMessage.newBuilder()
                .setAction(action)
                .build();
    }

}

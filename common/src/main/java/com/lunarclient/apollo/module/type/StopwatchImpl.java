package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.stopwatch.v1.ResetStopwatchMessage;
import com.lunarclient.apollo.stopwatch.v1.StartStopwatchMessage;
import com.lunarclient.apollo.stopwatch.v1.StopStopwatchMessage;

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

        ((AbstractApolloPlayer) player).sendPacket(StartStopwatchMessage.getDefaultInstance());
    }

    @Override
    public void stop(ApolloPlayer player) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(StopStopwatchMessage.getDefaultInstance());
    }

    @Override
    public void reset(ApolloPlayer player) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(ResetStopwatchMessage.getDefaultInstance());
    }

}

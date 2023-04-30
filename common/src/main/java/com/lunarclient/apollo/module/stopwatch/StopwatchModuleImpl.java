package com.lunarclient.apollo.module.stopwatch;

import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.stopwatch.v1.ResetStopwatchMessage;
import com.lunarclient.apollo.stopwatch.v1.StartStopwatchMessage;
import com.lunarclient.apollo.stopwatch.v1.StopStopwatchMessage;
import lombok.NonNull;

/**
 * Provides the stopwatch module.
 *
 * @since 1.0.0
 */
public final class StopwatchModuleImpl extends StopwatchModule {

    @Override
    public void startStopwatch(@NonNull ApolloPlayer viewer) {
        ((AbstractApolloPlayer) viewer).sendPacket(StartStopwatchMessage.getDefaultInstance());
    }

    @Override
    public void stopStopwatch(@NonNull ApolloPlayer viewer) {
        ((AbstractApolloPlayer) viewer).sendPacket(StopStopwatchMessage.getDefaultInstance());
    }

    @Override
    public void resetStopwatch(@NonNull ApolloPlayer viewer) {
        ((AbstractApolloPlayer) viewer).sendPacket(ResetStopwatchMessage.getDefaultInstance());
    }

}

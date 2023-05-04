package com.lunarclient.apollo.module.stopwatch;

import com.lunarclient.apollo.ApolloPlatform;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Arrays;
import java.util.Collection;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the stopwatch module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "stopwatch", name = "Stopwatch")
public abstract class StopwatchModule extends ApolloModule {

    @Override
    public Collection<ApolloPlatform.Kind> getSupportedPlatforms() {
        return Arrays.asList(ApolloPlatform.Kind.SERVER, ApolloPlatform.Kind.PROXY);
    }

    /**
     * Starts the stopwatch for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @since 1.0.0
     */
    public abstract void startStopwatch(ApolloPlayer viewer);

    /**
     * Stops the stopwatch for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @since 1.0.0
     */
    public abstract void stopStopwatch(ApolloPlayer viewer);

    /**
     * Resets the stopwatch for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @since 1.0.0
     */
    public abstract void resetStopwatch(ApolloPlayer viewer);

}

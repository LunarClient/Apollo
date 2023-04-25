package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.ApolloPlatform;
import com.lunarclient.apollo.module.ApolloModule;
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
public abstract class Stopwatch extends ApolloModule {

    Stopwatch() {
        super("Stopwatch");
    }

    @Override
    public Collection<ApolloPlatform.Kind> getSupport() {
        return Arrays.asList(ApolloPlatform.Kind.SERVER, ApolloPlatform.Kind.PROXY);
    }

    /**
     * Starts the stopwatch for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void start(ApolloPlayer player);

    /**
     * Stops the stopwatch for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void stop(ApolloPlayer player);

    /**
     * Resets the stopwatch for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void reset(ApolloPlayer player);

}

package com.moonsworth.apollo;

import com.moonsworth.apollo.module.ApolloModuleManager;
import com.moonsworth.apollo.player.ApolloPlayerManager;
import com.moonsworth.apollo.roundtrip.ApolloRoundtripManager;
import org.jetbrains.annotations.Nullable;

import static java.util.Objects.requireNonNull;

/**
 * Provides static accessors to Apollo.
 *
 * @since 1.0.0
 */
public final class Apollo {

    private static ApolloPlatform platform = null;
    private static ApolloModuleManager moduleManager = null;
    private static ApolloPlayerManager playerManager = null;
    private static ApolloRoundtripManager roundtripManager = null;

    /**
     * Returns the {@link ApolloPlatform}.
     *
     * @return the platform
     * @since 1.0.0
     */
    public static ApolloPlatform getPlatform() {
        return Apollo.checkEnabled(Apollo.platform);
    }

    /**
     * Returns the {@link ApolloModuleManager}.
     *
     * @return the module manager
     * @since 1.0.0
     */
    public static ApolloModuleManager getModuleManager() {
        return Apollo.checkEnabled(Apollo.moduleManager);
    }

    /**
     * Returns the {@link ApolloPlayerManager}.
     *
     * @return the player manager
     * @since 1.0.0
     */
    public static ApolloPlayerManager getPlayerManager() {
        return Apollo.checkEnabled(Apollo.playerManager);
    }

    /**
     * Return the {@link ApolloRoundtripManager}.
     *
     * @return the round trip manager
     * @since 1.0.0
     */
    public static ApolloRoundtripManager getRoundtripManager() {
        return Apollo.checkEnabled(Apollo.roundtripManager);
    }

    private static <T> T checkEnabled(@Nullable T object) {
        if (object == null) throw new UnsupportedOperationException("Apollo has not started yet!");
        return object;
    }

    static void initialize(ApolloPlatform platform, ApolloModuleManager moduleManager,
                           ApolloPlayerManager playerManager, ApolloRoundtripManager roundtripManager) {
        Apollo.platform = requireNonNull(platform, "platform");
        Apollo.moduleManager = requireNonNull(moduleManager, "moduleManager");
        Apollo.playerManager = playerManager;
        Apollo.roundtripManager = requireNonNull(roundtripManager, "roundtripManager");
    }

}

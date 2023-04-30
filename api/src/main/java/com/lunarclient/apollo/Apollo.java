package com.lunarclient.apollo;

import com.lunarclient.apollo.module.ApolloModuleManager;
import com.lunarclient.apollo.player.ApolloPlayerManager;
import com.lunarclient.apollo.roundtrip.ApolloRoundtripManager;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

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

    static void initialize(@NonNull ApolloPlatform platform, @NonNull ApolloModuleManager moduleManager, @NonNull ApolloPlayerManager playerManager, @NonNull ApolloRoundtripManager roundtripManager) {
        Apollo.platform = platform;
        Apollo.moduleManager = moduleManager;
        Apollo.playerManager = playerManager;
        Apollo.roundtripManager = roundtripManager;
    }

    private static <T> T checkEnabled(@Nullable T object) {
        if (object == null) throw new UnsupportedOperationException("Apollo has not started yet!");
        return object;
    }

    private Apollo() {

    }

}

package com.moonsworth.apollo.api;

import com.moonsworth.apollo.api.module.ApolloModuleManager;
import com.moonsworth.apollo.api.player.ApolloPlayerManager;
import org.jetbrains.annotations.Nullable;

/**
 * Provides static accessors to Apollo.
 *
 * @since 1.0.0
 */
public final class Apollo {

    private static final ApolloPlatform platform = null;
    private static final ApolloModuleManager moduleManager = null;
    private static final ApolloPlayerManager playerManager = null;

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

    private static <T> T checkEnabled(final @Nullable T object) {
        if (object == null) throw new UnsupportedOperationException("Apollo has not started yet!");
        return object;
    }

}

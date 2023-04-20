package com.lunarclient.apollo;

import com.lunarclient.apollo.module.ApolloModuleManagerImpl;
import com.lunarclient.apollo.network.ApolloNetworkManager;
import com.lunarclient.apollo.player.ApolloPlayerManagerImpl;
import com.lunarclient.apollo.roundtrip.ApolloRoundtripManager;
import lombok.Getter;

/**
 * Provides the instances for {@link Apollo}.
 *
 * @since 1.0.0
 */
public final class ApolloManager {

    /**
     * The plugin message channel that Lunar Client and Apollo talk over.
     */
    public static final String PLUGIN_MESSAGE_CHANNEL = "lunarclient:apollo";

    @Getter private static ApolloNetworkManager networkManager;
    private static boolean bootstrapped = false;

    /**
     * Bootstraps Apollo with the given {@link ApolloPlatform}.
     *
     * @param platform the platform
     * @since 1.0.0
     */
    public static void bootstrap(ApolloPlatform platform) {
        if(ApolloManager.bootstrapped) throw new IllegalStateException("Cannot bootstrap Apollo more than once!");
        try {
            Apollo.initialize(
                    platform,
                    new ApolloModuleManagerImpl(),
                    new ApolloPlayerManagerImpl(),
                    new ApolloRoundtripManager()
            );

            ApolloManager.networkManager = new ApolloNetworkManager();
        } catch(Throwable throwable) {
            throw new RuntimeException("Unable to bootstrap Apollo!", throwable);
        }
        ApolloManager.bootstrapped = true;
    }

    private ApolloManager() {
    }

}

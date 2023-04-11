package com.moonsworth.apollo;

import com.moonsworth.apollo.module.ApolloModuleManagerImpl;
import com.moonsworth.apollo.network.ApolloNetworkManager;
import com.moonsworth.apollo.player.ApolloPlayerManagerImpl;
import com.moonsworth.apollo.roundtrip.ApolloRoundtripManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Provides the instances for {@link Apollo}.
 *
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApolloManager {

    /**
     * The plugin message channel that Lunar Client and Apollo talk over.
     */
    public static final String PLUGIN_MESSAGE_CHANNEL = "lunarclient:apollo";

    @Getter private static ApolloNetworkManager networkManager;
    private static boolean bootstrapped = false;

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

}

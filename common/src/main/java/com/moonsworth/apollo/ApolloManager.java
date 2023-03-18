package com.moonsworth.apollo;

import com.moonsworth.apollo.module.ApolloModuleManagerImpl;
import com.moonsworth.apollo.network.ApolloNetworkManager;
import com.moonsworth.apollo.player.ApolloPlayerManagerImpl;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;

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

    public static void bootstrap(final ApolloPlatform platform) {
        if(ApolloManager.bootstrapped) throw new IllegalStateException("Cannot bootstrap Apollo more than once!");
        try {
            ApolloManager.set("platform", platform);
            ApolloManager.set("moduleManager", new ApolloModuleManagerImpl());
            ApolloManager.set("playerManager", new ApolloPlayerManagerImpl());

            ApolloManager.networkManager = new ApolloNetworkManager();
        } catch(final Throwable throwable) {
            throw new RuntimeException("Unable to bootstrap Apollo!", throwable);
        }
        ApolloManager.bootstrapped = true;
    }

    private static void set(final String name, final Object instance) throws NoSuchFieldException, IllegalAccessException {
        final Field field = Apollo.class.getDeclaredField(name);
        field.setAccessible(true);
        field.set(null, instance);
        field.setAccessible(false);
    }

}

package com.lunarclient.apollo;

import com.lunarclient.apollo.module.ApolloModuleManagerImpl;
import com.lunarclient.apollo.network.ApolloNetworkManager;
import com.lunarclient.apollo.option.config.Serializers;
import com.lunarclient.apollo.player.ApolloPlayerManagerImpl;
import com.lunarclient.apollo.roundtrip.ApolloRoundtripManager;
import java.nio.file.Path;
import lombok.Getter;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

/**
 * Provides the instances for {@link Apollo}.
 *
 * @since 1.0.0
 */
public final class ApolloManager {

    /**
     * The plugin message channel that Lunar Client and Apollo talk over.
     */
    public static final String PLUGIN_MESSAGE_CHANNEL = "lunar:apollo";

    @Getter private static ApolloNetworkManager networkManager;
    @Getter private static CommentedConfigurationNode configurationNode;

    private static YamlConfigurationLoader configurationLoader;
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

    /**
     * Loads the configuration from the given {@link Path}.
     *
     * @param path the path
     * @since 1.0.0
     */
    public static void loadConfiguration(Path path) {
        try {
            if(ApolloManager.configurationLoader == null) {
                ApolloManager.configurationLoader = YamlConfigurationLoader.builder()
                    .nodeStyle(NodeStyle.BLOCK)
                    .path(path.resolve("settings.yml"))
                    .defaultOptions(options -> options.serializers(builder -> builder.registerAll(Serializers.serializers())))
                    .build();
            }

            ApolloManager.configurationNode = ApolloManager.configurationLoader.load();
        } catch(Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * Saves the configuration.
     *
     * @since 1.0.0
     */
    public static void saveConfiguration() {
        try {
            if(ApolloManager.configurationNode == null) return;

            CommentedConfigurationNode modules = ApolloManager.configurationNode.node("modules");

            ((ApolloModuleManagerImpl) Apollo.getModuleManager()).saveConfiguration(modules);

            ApolloManager.configurationLoader.save(ApolloManager.configurationNode);
        } catch(Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private ApolloManager() {
    }

}

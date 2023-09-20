/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2023 Moonsworth
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.lunarclient.apollo;

import com.lunarclient.apollo.module.ApolloModuleManagerImpl;
import com.lunarclient.apollo.network.ApolloNetworkManager;
import com.lunarclient.apollo.option.ConfigOptions;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.config.Serializers;
import com.lunarclient.apollo.player.ApolloPlayerManagerImpl;
import com.lunarclient.apollo.roundtrip.ApolloRoundtripManager;
import com.lunarclient.apollo.world.ApolloWorldManagerImpl;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
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

    /**
     * The plugin root module identifier for Apollos general options.
     */
    public static final String PLUGIN_ROOT_MODULE = "apollo";

    private static List<Option<?, ?, ?>> optionKeys = new LinkedList<>();

    @Getter private static ApolloPlatform platform;
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
        if (ApolloManager.bootstrapped) {
            throw new IllegalStateException("Cannot bootstrap Apollo more than once!");
        }

        try {
            Apollo.initialize(
                platform,
                new ApolloModuleManagerImpl(),
                new ApolloWorldManagerImpl(),
                new ApolloPlayerManagerImpl(),
                new ApolloRoundtripManager()
            );

            ApolloManager.networkManager = new ApolloNetworkManager();

            ApolloManager.platform = platform;
        } catch (Throwable throwable) {
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
            if (ApolloManager.configurationLoader == null) {
                ApolloManager.configurationLoader = YamlConfigurationLoader.builder()
                    .nodeStyle(NodeStyle.BLOCK)
                    .path(path.resolve("settings.yml"))
                    .defaultOptions(options -> options.serializers(builder -> builder.registerAll(Serializers.serializers())))
                    .build();
            }

            ApolloManager.configurationNode = ApolloManager.configurationLoader.load();

            ConfigOptions.loadOptions(ApolloManager.platform.getOptions(), ApolloManager.configurationNode, ApolloManager.optionKeys);
        } catch (Throwable throwable) {
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
            if (ApolloManager.configurationNode == null) {
                return;
            }

            ConfigOptions.saveOptions(ApolloManager.platform.getOptions(), ApolloManager.configurationNode);

            CommentedConfigurationNode modules = ApolloManager.configurationNode.node("modules");

            ((ApolloModuleManagerImpl) Apollo.getModuleManager()).saveConfiguration(modules);

            ApolloManager.configurationLoader.save(ApolloManager.configurationNode);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private ApolloManager() {
    }

}

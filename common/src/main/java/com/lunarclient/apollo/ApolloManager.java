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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lunarclient.apollo.api.ApolloHttpManager;
import com.lunarclient.apollo.module.ApolloModuleManagerImpl;
import com.lunarclient.apollo.network.ApolloNetworkManager;
import com.lunarclient.apollo.option.ConfigOptions;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.config.CommonSerializers;
import com.lunarclient.apollo.player.ApolloPlayerManagerImpl;
import com.lunarclient.apollo.roundtrip.ApolloRoundtripManager;
import com.lunarclient.apollo.stats.ApolloStatsManager;
import com.lunarclient.apollo.stats.metadata.ApolloMetadataManager;
import com.lunarclient.apollo.util.ConfigTarget;
import com.lunarclient.apollo.version.ApolloVersionManager;
import com.lunarclient.apollo.world.ApolloWorldManagerImpl;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

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

    /**
     * The plugins GSON used for http.
     */
    public static final Gson GSON = new GsonBuilder().create();

    private static final List<Option<?, ?, ?>> optionKeys = new LinkedList<>();

    private static ApolloPlatform platform;

    @Getter private static ApolloRoundtripManager roundtripManager;
    @Getter private static ApolloHttpManager httpManager;
    @Getter private static ApolloNetworkManager networkManager;
    @Getter private static ApolloVersionManager versionManager;
    @Getter private static ApolloStatsManager statsManager;
    @Getter @Setter private static ApolloMetadataManager metadataManager;

    @Getter private static Path configPath;

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
                new ApolloPlayerManagerImpl()
            );

            ApolloManager.roundtripManager = new ApolloRoundtripManager();
            ApolloManager.httpManager = new ApolloHttpManager();
            ApolloManager.networkManager = new ApolloNetworkManager();
            ApolloManager.versionManager = new ApolloVersionManager();
            ApolloManager.statsManager = new ApolloStatsManager();

            new CommonSerializers();

            ApolloManager.platform = platform;
        } catch (Throwable throwable) {
            throw new RuntimeException("Unable to bootstrap Apollo!", throwable);
        }

        ApolloManager.bootstrapped = true;
    }

    /**
     * Registers {@link Option}s for Apollo.
     *
     * @param options the option keys
     * @since 1.0.0
     */
    public static void registerOptions(Option<?, ?, ?>... options) {
        ApolloManager.optionKeys.addAll(Arrays.asList(options));
    }

    /**
     * Sets the config data path.
     *
     * @param path the config data path
     * @since 1.0.5
     */
    public static void setConfigPath(Path path) {
        ApolloManager.configPath = path;
    }

    /**
     * Loads the configuration.
     *
     * @since 1.0.0
     */
    public static void loadConfiguration() throws Throwable {
        for (ApolloConfig config : ApolloConfig.configs()) {
            config.reset();
        }

        ApolloConfig generalSettings = ApolloConfig.compute(ApolloManager.configPath, ConfigTarget.GENERAL_SETTINGS);
        ConfigOptions.loadOptions(ApolloManager.platform.getOptions(), generalSettings.node(), ApolloManager.optionKeys);
    }

    /**
     * Saves the configuration.
     *
     * @since 1.0.0
     */
    public static void saveConfiguration() throws Throwable {
        ApolloConfig generalSettings = ApolloConfig.compute(ApolloManager.configPath, ConfigTarget.GENERAL_SETTINGS);
        ConfigOptions.saveOptions(ApolloManager.platform.getOptions(), generalSettings.node(), ApolloManager.optionKeys);

        ((ApolloModuleManagerImpl) Apollo.getModuleManager()).saveConfiguration();

        for (ApolloConfig config : ApolloConfig.configs()) {
            config.save();
        }
    }

    private ApolloManager() {
    }

}

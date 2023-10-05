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

import com.google.inject.Inject;
import com.lunarclient.apollo.listener.ApolloPlayerListener;
import com.lunarclient.apollo.module.ApolloModuleManagerImpl;
import com.lunarclient.apollo.option.Options;
import com.lunarclient.apollo.option.OptionsImpl;
import com.lunarclient.apollo.stats.ApolloStats;
import com.lunarclient.apollo.stats.ApolloStatsManager;
import com.lunarclient.apollo.version.ApolloVersionManager;
import com.lunarclient.apollo.wrapper.VelocityApolloStats;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.PluginContainer;
import com.velocitypowered.api.plugin.PluginDescription;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import java.nio.file.Path;
import java.util.logging.Logger;
import lombok.Getter;

/**
 * The Velocity implementation plugin.
 *
 * @since 1.0.0
 */
@Plugin(
    id = "apollo",
    name = "Apollo-Velocity",
    version = "0.1.0-SNAPSHOT",
    url = "https://moonsworth.com",
    description = "Implementation of Apollo for Velocity",
    authors = {"Moonsworth"}
)
@Getter
public final class ApolloVelocityPlatform implements ApolloPlatform {

    public static MinecraftChannelIdentifier PLUGIN_CHANNEL;

    @Getter private static ApolloVelocityPlatform instance;

    private final Options options = new OptionsImpl(null);
    private ApolloStats stats;

    private final ProxyServer server;
    private final Logger logger;
    private final Path dataDirectory;

    @Inject
    private ApolloVelocityPlatform(ProxyServer server,
                                   Logger logger,
                                   @DataDirectory Path dataDirectory) {
        this.server = server;
        this.logger = logger;
        this.dataDirectory = dataDirectory;
    }

    @Override
    public Kind getKind() {
        return Kind.PROXY;
    }

    @Override
    public String getApolloVersion() {
        return this.server.getPluginManager().fromInstance(this)
            .map(PluginContainer::getDescription)
            .flatMap(PluginDescription::getVersion)
            .orElse(null);
    }

    @Override
    public Logger getPlatformLogger() {
        return this.logger;
    }

    @Override
    public ApolloStats getStats() {
        return this.stats;
    }

    /**
     * Handles initialization of the proxy.
     *
     * @param event the event
     * @since 1.0.0
     */
    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        ApolloVelocityPlatform.instance = this;
        this.stats = new VelocityApolloStats();
        ApolloManager.bootstrap(this);

        ApolloStatsManager statsManager = new ApolloStatsManager();
        ApolloVersionManager versionManager = new ApolloVersionManager();

        ApolloManager.loadConfiguration(this.dataDirectory);
        ((ApolloModuleManagerImpl) Apollo.getModuleManager()).enableModules();

        ApolloManager.saveConfiguration();

        this.server.getEventManager().register(this, new ApolloPlayerListener());
        this.server.getChannelRegistrar().register(ApolloVelocityPlatform.PLUGIN_CHANNEL);

        statsManager.enable();
        versionManager.checkForUpdates();
    }

    /**
     * Handles the shutdown of the proxy.
     *
     * @param event the event
     * @since 1.0.0
     */
    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        ((ApolloModuleManagerImpl) Apollo.getModuleManager()).disableModules();

        ApolloManager.saveConfiguration();
    }

    static {
        try {
            PLUGIN_CHANNEL = MinecraftChannelIdentifier.from(ApolloManager.PLUGIN_MESSAGE_CHANNEL);
        } catch (NoSuchMethodError e) {
            String[] messageChannel = ApolloManager.PLUGIN_MESSAGE_CHANNEL.split(":");
            PLUGIN_CHANNEL = MinecraftChannelIdentifier.create(messageChannel[0], messageChannel[1]);
        }
    }

}

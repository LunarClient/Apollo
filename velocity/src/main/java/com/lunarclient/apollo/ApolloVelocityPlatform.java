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

import com.google.common.base.Charsets;
import com.google.inject.Inject;
import com.lunarclient.apollo.module.ApolloModuleManagerImpl;
import com.lunarclient.apollo.player.ApolloPlayerManagerImpl;
import com.lunarclient.apollo.wrapper.VelocityApolloPlayer;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import java.nio.file.Path;
import lombok.Getter;

@Plugin(
    id = "apollo",
    name = "Apollo-Velocity",
    version = "0.1.0-SNAPSHOT",
    url = "https://moonsworth.com",
    description = "Implementation of Apollo for Velocity",
    authors = {"Moonsworth"}
)
public final class ApolloVelocityPlatform implements ApolloPlatform {

    public static MinecraftChannelIdentifier PLUGIN_CHANNEL = MinecraftChannelIdentifier.from(ApolloManager.PLUGIN_MESSAGE_CHANNEL);

    @Getter private static ApolloVelocityPlatform instance;

    private final ProxyServer server;
    private final Path dataDirectory;

    @Inject
    public ApolloVelocityPlatform(ProxyServer server,
                                  @DataDirectory Path dataDirectory) {
        this.server = server;
        this.dataDirectory = dataDirectory;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        ApolloVelocityPlatform.instance = this;

        ApolloManager.bootstrap(this);
        ApolloManager.loadConfiguration(this.dataDirectory);

        ((ApolloModuleManagerImpl) Apollo.getModuleManager()).enableModules();

        this.server.getChannelRegistrar().register(ApolloVelocityPlatform.PLUGIN_CHANNEL);

        ApolloManager.saveConfiguration();
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        ((ApolloModuleManagerImpl) Apollo.getModuleManager()).disableModules();

        ApolloManager.saveConfiguration();
    }

    @Override
    public Kind getKind() {
        return Kind.PROXY;
    }

    @Subscribe
    public void onPluginMessage(PluginMessageEvent event) {
        if (!event.getIdentifier().getId().equals("REGISTER")) {
            return;
        }

        if (!(event.getSource() instanceof Player)) {
            return;
        }

        String channels = new String(event.getData(), Charsets.UTF_8);
        if (!channels.contains(ApolloManager.PLUGIN_MESSAGE_CHANNEL)) {
            return;
        }

        Player player = (Player) event.getSource();
        ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).addPlayer(new VelocityApolloPlayer(player));
    }

    @Subscribe
    public void onDisconnect(DisconnectEvent event) {
        Player player = event.getPlayer();
        ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).removePlayer(player.getUniqueId());
    }

}

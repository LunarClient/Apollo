package com.moonsworth.apollo.impl.velocity;

import com.google.common.base.Charsets;
import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.google.inject.Inject;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.impl.velocity.wrapper.VelocityPlayer;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Plugin(
        id = "apollo",
        name = "Apollo-Velocity",
        version = "0.1.0-SNAPSHOT",
        url = "https://moonsworth.com",
        description = "Implementation of Apollo for Velocity",
        authors = {"Moonsworth"}
)
public class ApolloVelocityPlatform implements ApolloPlatform {
    public static MinecraftChannelIdentifier PLUGIN_CHANNEL = MinecraftChannelIdentifier.from(Apollo.PLUGIN_MESSAGE_CHANNEL);
    private final ProxyServer server;
    private final Logger logger;

    @Inject
    public ApolloVelocityPlatform(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
        Apollo.setPlatform(this);
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        server.getChannelRegistrar().register(PLUGIN_CHANNEL);
    }

    @Subscribe
    public void onDisconnect(DisconnectEvent event) {
        Apollo.getApolloPlayerManager().unRegisterPlayer(event.getPlayer().getUniqueId());
    }

    @Override
    public Kind getKind() {
        return Kind.PROXY;
    }

    @Nullable
    @Override
    public ApolloPlayer tryWrapPlayer(Object o) {
        if (o instanceof Player player) {
            return Apollo.getApolloPlayerManager().getApolloPlayer(player.getUniqueId()).orElse(new VelocityPlayer(player));
        }
        return null;
    }

    @Subscribe
    public void onRegister(PluginMessageEvent event) {
        if (event.getSource() instanceof Player player) {
            if (event.getIdentifier().getId().equals("REGISTER")) {
                String channels = new String(event.getData(), Charsets.UTF_8);
                if (channels.contains(Apollo.PLUGIN_MESSAGE_CHANNEL)) {
                    Apollo.getApolloPlayerManager().registerPlayer(player);
                }
            }
        }
    }
}

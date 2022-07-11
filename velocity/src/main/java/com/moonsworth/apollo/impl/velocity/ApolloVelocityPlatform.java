package com.moonsworth.apollo.impl.velocity;

import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.google.inject.Inject;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.network.PacketRegistry;
import com.moonsworth.apollo.impl.velocity.network.VelocityPacketHandler;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

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
    private final VelocityPacketHandler packetHandler = new VelocityPacketHandler();

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
    public void onPluginMessageEvent(PluginMessageEvent event) {
        if (event.getIdentifier().equals(PLUGIN_CHANNEL)) {
            PacketRegistry.parse(event.getData()).ifPresent(packet -> {
                packet.processServer(packetHandler);
            });
        }
    }

    @Override
    public Kind getKind() {
        return Kind.PROXY;
    }

    @Nullable
    @Override
    public ApolloPlayer tryWrapPlayer(Object o) {

        return null;
    }
}

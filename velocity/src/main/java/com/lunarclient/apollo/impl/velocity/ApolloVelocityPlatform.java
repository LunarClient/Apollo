package com.lunarclient.apollo.impl.velocity;

import com.google.common.base.Charsets;
import com.google.inject.Inject;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.ApolloPlatform;
import com.lunarclient.apollo.impl.velocity.wrapper.VelocityApolloPlayer;
import com.lunarclient.apollo.module.ApolloModuleManagerImpl;
import com.lunarclient.apollo.player.ApolloPlayerManagerImpl;
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
import lombok.Getter;
import org.slf4j.Logger;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;

import java.nio.file.Path;

@Plugin(
        id = "apollo",
        name = "Apollo-Velocity",
        version = "0.1.0-SNAPSHOT",
        url = "https://moonsworth.com",
        description = "Implementation of Apollo for Velocity",
        authors = { "Moonsworth" }
)
public final class ApolloVelocityPlatform implements ApolloPlatform {

    public static MinecraftChannelIdentifier PLUGIN_CHANNEL = MinecraftChannelIdentifier.from(ApolloManager.PLUGIN_MESSAGE_CHANNEL);

    @Getter private static ApolloVelocityPlatform instance;

    private final ProxyServer server;
    private final Logger logger;
    private final Path dataDirectory;


    private HoconConfigurationLoader configurationLoader;

    @Inject
    public ApolloVelocityPlatform(ProxyServer server,
                                  Logger logger,
                                  @DataDirectory Path dataDirectory) {
        this.server = server;
        this.logger = logger;
        this.dataDirectory = dataDirectory;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        ApolloVelocityPlatform.instance = this;

        ApolloManager.bootstrap(this);

        ApolloManager.loadConfiguration(this.dataDirectory);

        ((ApolloModuleManagerImpl) Apollo.getModuleManager()).enableModules();

        server.getChannelRegistrar().register(ApolloVelocityPlatform.PLUGIN_CHANNEL);

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
        if(event.getSource() instanceof Player) {
            Player player = (Player) event.getSource();
            if(event.getIdentifier().getId().equals("REGISTER")) {
                String channels = new String(event.getData(), Charsets.UTF_8);
                if(!channels.contains(ApolloManager.PLUGIN_MESSAGE_CHANNEL)) return;

                ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).addPlayer(new VelocityApolloPlayer(player));
            }
        }
    }

    @Subscribe
    public void onDisconnect(DisconnectEvent event) {
        Player player = event.getPlayer();
        ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).removePlayer(player.getUniqueId());
    }

}

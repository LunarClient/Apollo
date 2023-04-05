package com.moonsworth.apollo.impl.velocity;

import com.google.common.base.Charsets;
import com.google.inject.Inject;
import com.moonsworth.apollo.Apollo;
import com.moonsworth.apollo.ApolloManager;
import com.moonsworth.apollo.ApolloPlatform;
import com.moonsworth.apollo.impl.velocity.wrapper.VelocityApolloPlayer;
import com.moonsworth.apollo.module.ApolloModuleManagerImpl;
import com.moonsworth.apollo.player.ApolloPlayerManagerImpl;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import lombok.Getter;
import org.slf4j.Logger;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;

import java.nio.file.Path;

@Plugin(
        id = "apollo",
        name = "Apollo-Velocity",
        version = "1.0.0-SNAPSHOT",
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

        this.loadConfiguration();

        server.getChannelRegistrar().register(ApolloVelocityPlatform.PLUGIN_CHANNEL);
    }

    @Override
    public Kind getKind() {
        return Kind.PROXY;
    }

    private void loadConfiguration() {
        try {
            if (this.configurationLoader == null) {
                this.configurationLoader = HoconConfigurationLoader.builder()
                        .path(this.dataDirectory.resolve("settings.conf"))
                        .build();
            }

            this.configurationLoader.load();

            CommentedConfigurationNode root = this.configurationLoader.load();
            CommentedConfigurationNode modules = root.node("modules");

            ((ApolloModuleManagerImpl) Apollo.getModuleManager()).loadConfiguration(modules);
            ((ApolloModuleManagerImpl) Apollo.getModuleManager()).saveConfiguration(modules);

            this.configurationLoader.save(root);
        } catch(Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Subscribe
    public void onPluginMessage(PluginMessageEvent event) {
        if(event.getSource() instanceof Player player) {
            if(event.getIdentifier().getId().equals("REGISTER")) {
                String channels = new String(event.getData(), Charsets.UTF_8);
                if(!channels.contains(ApolloManager.PLUGIN_MESSAGE_CHANNEL)) return;

                ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).addPlayer(new VelocityApolloPlayer(player));
            }
        }
    }

    @Subscribe
    public void onDisconnect(final DisconnectEvent event) {
        final Player player = event.getPlayer();
        ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).removePlayer(player.getUniqueId());
    }

}

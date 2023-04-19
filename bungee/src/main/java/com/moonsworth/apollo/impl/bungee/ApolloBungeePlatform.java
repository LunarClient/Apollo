package com.moonsworth.apollo.impl.bungee;

import com.google.common.base.Charsets;
import com.moonsworth.apollo.Apollo;
import com.moonsworth.apollo.ApolloManager;
import com.moonsworth.apollo.ApolloPlatform;
import com.moonsworth.apollo.impl.bungee.wrapper.BungeeApolloPlayer;
import com.moonsworth.apollo.module.ApolloModuleManagerImpl;
import com.moonsworth.apollo.player.ApolloPlayerManagerImpl;
import lombok.Getter;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;

public final class ApolloBungeePlatform extends Plugin implements ApolloPlatform, Listener {

    @Getter private static ApolloBungeePlatform instance;

    private HoconConfigurationLoader configurationLoader;

    @Override
    public void onEnable() {
        ApolloBungeePlatform.instance = this;

        this.getProxy().getPluginManager().registerListener(this, this);

        ApolloManager.bootstrap(this);

        this.loadConfiguration();

        this.getProxy().registerChannel(ApolloManager.PLUGIN_MESSAGE_CHANNEL);
    }

    @Override
    public Kind getKind() {
        return Kind.PROXY;
    }

    private void loadConfiguration() {
        try {
            if (this.configurationLoader == null) {
                this.configurationLoader = HoconConfigurationLoader.builder()
                        .path(this.getDataFolder().toPath().resolve("settings.conf"))
                        .build();
            }

            this.configurationLoader.load();

            CommentedConfigurationNode root = this.configurationLoader.load();
            CommentedConfigurationNode modules = root.node("modules");

            ((ApolloModuleManagerImpl) Apollo.getModuleManager()).loadConfiguration(modules);
            ((ApolloModuleManagerImpl) Apollo.getModuleManager()).saveConfiguration(modules);

            this.configurationLoader.save(root);
        } catch(final Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @EventHandler
    public void onPluginMessage(final PluginMessageEvent event) {
        if(event.getReceiver() instanceof ProxyServer && event.getSender() instanceof ProxiedPlayer) {
            final ProxiedPlayer player = (ProxiedPlayer) event.getSender();
            if(event.getTag().equals("REGISTER")) {
                String channels = new String(event.getData(), Charsets.UTF_8);
                if(!channels.contains(ApolloManager.PLUGIN_MESSAGE_CHANNEL)) return;

                ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).addPlayer(new BungeeApolloPlayer(player));
            }
        }
    }

    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent event) {
        ProxiedPlayer player = event.getPlayer();
        ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).removePlayer(player.getUniqueId());
    }

}

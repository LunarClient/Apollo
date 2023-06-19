package com.lunarclient.apollo;

import com.google.common.base.Charsets;
import com.lunarclient.apollo.wrapper.BungeeApolloPlayer;
import com.lunarclient.apollo.module.ApolloModuleManagerImpl;
import com.lunarclient.apollo.player.ApolloPlayerManagerImpl;
import lombok.Getter;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public final class ApolloBungeePlatform extends Plugin implements ApolloPlatform, Listener {

    @Getter private static ApolloBungeePlatform instance;

    @Override
    public void onEnable() {
        ApolloBungeePlatform.instance = this;

        this.getProxy().getPluginManager().registerListener(this, this);

        ApolloManager.bootstrap(this);
        ApolloManager.loadConfiguration(this.getDataFolder().toPath());

        ((ApolloModuleManagerImpl) Apollo.getModuleManager()).enableModules();

        this.getProxy().registerChannel(ApolloManager.PLUGIN_MESSAGE_CHANNEL);

        ApolloManager.saveConfiguration();
    }

    @Override
    public void onDisable() {
        ((ApolloModuleManagerImpl) Apollo.getModuleManager()).disableModules();

        ApolloManager.saveConfiguration();
    }

    @Override
    public Kind getKind() {
        return Kind.PROXY;
    }

    @EventHandler
    public void onPluginMessage(PluginMessageEvent event) {
        if (!(event.getReceiver() instanceof ProxyServer)) return;
        if (!(event.getSender() instanceof ProxiedPlayer)) return;
        if (!event.getTag().equals("REGISTER")) return;

        String channels = new String(event.getData(), Charsets.UTF_8);
        if(!channels.contains(ApolloManager.PLUGIN_MESSAGE_CHANNEL)) return;

        ProxiedPlayer player = (ProxiedPlayer) event.getSender();
        ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).addPlayer(new BungeeApolloPlayer(player));
    }

    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent event) {
        ProxiedPlayer player = event.getPlayer();
        ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).removePlayer(player.getUniqueId());
    }

}

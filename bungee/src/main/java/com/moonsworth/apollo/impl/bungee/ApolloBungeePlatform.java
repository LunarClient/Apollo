package com.moonsworth.apollo.impl.bungee;

import com.google.common.base.Charsets;
import com.moonsworth.apollo.Apollo;
import com.moonsworth.apollo.ApolloPlatform;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.events.EventBus;
import com.moonsworth.apollo.impl.bungee.util.ConfigurationUtil;
import com.moonsworth.apollo.impl.bungee.wrapper.BungeePlayer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.event.EventHandler;

import java.util.HashMap;
import java.util.Map;

public class ApolloBungeePlatform extends Plugin implements ApolloPlatform, Listener {

    @Override
    public void onEnable() {
        Apollo.setPlatform(this);
        handleConfiguration();
        getProxy().getPluginManager().registerListener(this, this);

        getProxy().registerChannel(Apollo.PLUGIN_MESSAGE_CHANNEL);
    }

    private void handleConfiguration() {
        Configuration modules = ConfigurationProvider.getProvider(YamlConfiguration.class).load("config.yml").getSection("modules");
        if (modules == null) {
            return;
        }
        Map<String, Object> values = new HashMap<>();
        ConfigurationUtil.flattenConfigurationDeep(values, "", modules);
        Apollo.getApolloModuleManager().loadConfiguration(values);
    }


    @Override
    public Kind getKind() {
        return Kind.PROXY;
    }

    @Override
    public ApolloPlayer tryWrapPlayer(Object o) {
        if (o instanceof ProxiedPlayer player) {
            return Apollo.getApolloPlayerManager().getApolloPlayer(player.getUniqueId()).orElse(new BungeePlayer(player));
        }
        return null;
    }

    @EventHandler
    public void onRegister(PluginMessageEvent event) {
        if (event.getReceiver() instanceof ProxyServer && event.getSender() instanceof ProxiedPlayer player) {
            if (event.getTag().equals("REGISTER")) {
                String channels = new String(event.getData(), Charsets.UTF_8);
                if (channels.contains(Apollo.PLUGIN_MESSAGE_CHANNEL)) {
                    Apollo.getApolloPlayerManager().registerPlayer(player);
                }
            }
        }
    }

}

package com.moonsworth.apollo.impl.bungee;

import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.impl.bungee.util.ConfigurationUtil;
import com.moonsworth.apollo.impl.bungee.wrapper.BungeePlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.event.EventHandler;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    )


    @Override
    public Kind getKind() {
        return Kind.PROXY;
    }

    @Override
    public ApolloPlayer tryWrapPlayer(Object o) {
        if (o instanceof ProxiedPlayer player) {
            if (Apollo.getApolloPlayerManager().getApolloPlayer(player.getUniqueId()).isEmpty()) {
                return new BungeePlayer(player);
            }
        }
        return null;
    }
}

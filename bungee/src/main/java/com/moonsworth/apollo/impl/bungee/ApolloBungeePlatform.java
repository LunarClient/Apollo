package com.moonsworth.apollo.impl.bungee;

import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.impl.bungee.wrapper.BungeePlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ApolloBungeePlatform extends Plugin implements ApolloPlatform {

    @Override
    public void onEnable() {
        Apollo.setPlatform(this);
        handleConfiguration();
    }

    private void handleConfiguration() {
        Configuration modules = ConfigurationProvider.getProvider(YamlConfiguration.class).load("config.yml").getSection("modules");
        if (modules == null) {
            return;
        }
        Map<String, Object> values = new HashMap<>();
        handleModules(values, "", modules);
        Apollo.getApolloModuleManager().loadConfiguration(values);
    }

    private void handleModules(Map<String, Object> values, String path, Configuration config) {
        for (String key : config.getKeys()) {
            if (config.contains(key)) {
                var value = config.get(key);
                if (value instanceof Configuration) {
                    handleModules(values, path + "." + key, ((Configuration) value));
                } else {
                    values.put(path + "." + key, config.get(key));
                }
            }
        }
    }

    @Override
    public Kind getKind() {
        return Kind.PROXY;
    }

    @Override
    public ApolloPlayer tryWrapPlayer(Object o) {
        if (o instanceof ProxiedPlayer player) {
            return new BungeePlayer(player);
        }
        return null;
    }
}

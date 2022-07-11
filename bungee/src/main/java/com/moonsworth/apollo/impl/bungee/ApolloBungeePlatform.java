package com.moonsworth.apollo.impl.bungee;

import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.impl.bungee.wrapper.BungeePlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

public class ApolloBungeePlatform extends Plugin implements ApolloPlatform {

    @Override
    public void onEnable() {
        Apollo.setPlatform(this);
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

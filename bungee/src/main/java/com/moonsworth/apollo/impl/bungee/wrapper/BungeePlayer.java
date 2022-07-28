package com.moonsworth.apollo.impl.bungee.wrapper;

import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public record BungeePlayer(ProxiedPlayer player) implements ApolloPlayer {

    @Override
    public void sendPacket(byte[] bytes) {
        player.sendData(Apollo.PLUGIN_MESSAGE_CHANNEL, bytes);
    }
}

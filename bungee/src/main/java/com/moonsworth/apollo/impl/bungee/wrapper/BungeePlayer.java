package com.moonsworth.apollo.impl.bungee.wrapper;

import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.network.Packet;
import com.moonsworth.apollo.api.network.PacketRegistry;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public record BungeePlayer(ProxiedPlayer player) implements ApolloPlayer {

    @Override
    public void sendPacket(Packet packet) {
        var bytes = PacketRegistry.getPacketData(packet);
        player.sendData(Apollo.PLUGIN_MESSAGE_CHANNEL, bytes);
    }
}

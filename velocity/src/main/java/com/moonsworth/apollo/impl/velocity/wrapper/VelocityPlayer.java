package com.moonsworth.apollo.impl.velocity.wrapper;

import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.network.Packet;
import com.moonsworth.apollo.api.network.PacketRegistry;
import com.moonsworth.apollo.impl.velocity.ApolloVelocityPlatform;
import com.velocitypowered.api.proxy.Player;

public record VelocityPlayer(Player player) implements ApolloPlayer {
    @Override
    public void sendPacket(Packet packet) {
        var bytes = PacketRegistry.getPacketData(packet);
        player.sendPluginMessage(ApolloVelocityPlatform.PLUGIN_CHANNEL, bytes);
    }
}

package com.lunarclient.apollo.module.ping;

import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.event.ApolloReceivePacketEvent;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.ping.ApolloPlayerPingEvent;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.ping.v1.PlayerPingMessage;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;

import java.util.UUID;

public class PingModuleImpl extends PingModule {
    public PingModuleImpl() {
        this.handle(ApolloReceivePacketEvent.class, this::onReceivePacket);
    }

    @Override
    public void pingTeamMembers(Recipients recipients, UUID playerUuid, ApolloLocation location, boolean isDoublePing) {
        PlayerPingMessage message = PlayerPingMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(playerUuid))
            .setLocation(NetworkTypes.toProtobuf(location))
            .setIsDoublePing(isDoublePing)
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }


    private void onReceivePacket(ApolloReceivePacketEvent event) {
        event.unpack(PlayerPingMessage.class).ifPresent(packet -> {
	        ApolloPlayerPingEvent playerPingEvent = new ApolloPlayerPingEvent(
                event.getPlayer().getUniqueId(), // Ignore the player ID in the packet
	            NetworkTypes.fromProtobuf(packet.getLocation()),
		        packet.getIsDoublePing()
            );

            EventBus.EventResult<ApolloPlayerPingEvent> result = EventBus.getBus().post(playerPingEvent);

            for (Throwable throwable : result.getThrowing()) {
                throwable.printStackTrace();
            }
        });
    }
}

package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.event.network.ApolloReceivePacketEvent;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.network.ServerPing;
import com.lunarclient.apollo.player.ui.network.ServerTransfer;
import com.lunarclient.apollo.roundtrip.async.Future;
import com.lunarclient.apollo.roundtrip.async.Handler;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;

import java.util.List;
import java.util.stream.Collectors;
import lunarclient.apollo.common.OptionOperation;
import lunarclient.apollo.modules.PingRequest;
import lunarclient.apollo.modules.PingResponse;
import lunarclient.apollo.modules.TransferRequest;
import lunarclient.apollo.modules.TransferResponse;

import static java.util.Objects.requireNonNull;

/**
 * Provides the transfer module.
 *
 * @since 1.0.0
 */
public final class TransferImpl extends Transfer {

    public TransferImpl() {
        super();

        this.handle(ApolloReceivePacketEvent.class, this::onTransferResponse);
    }

    @Override
    public void ping(ApolloPlayer player, ServerPing.Request ping, Handler<ServerPing.Response> response) {
        requireNonNull(player, "player");
        requireNonNull(ping, "ping");

        PingRequest message = PingRequest.newBuilder()
            .setPacketId(NetworkTypes.toUuid(ping.getPacketId()))
            .addAllAddresses(ping.getAddresses())
            .build();

        Future<ServerPing.Response> pingResponse = ((AbstractApolloPlayer) player)
            .sendRoundTripPacket(ping, this, OptionOperation.ADD, message);

        pingResponse.onFailure(Throwable::printStackTrace);
        pingResponse.onSuccess(response);
    }

    @Override
    public void transfer(ApolloPlayer player, ServerTransfer.Request transfer, Handler<ServerTransfer.Response> response) {
        requireNonNull(player, "player");
        requireNonNull(transfer, "transfer");

        TransferRequest message = TransferRequest.newBuilder()
            .setPacketId(NetworkTypes.toUuid(transfer.getPacketId()))
            .setAddress(transfer.getAddress())
            .build();

        Future<ServerTransfer.Response> transferResponse = ((AbstractApolloPlayer) player)
            .sendRoundTripPacket(transfer, this, OptionOperation.ADD, message);

        transferResponse.onFailure(Throwable::printStackTrace);
        transferResponse.onSuccess(response);
    }

    public void onTransferResponse(ApolloReceivePacketEvent event) {
        event.unpackIf(TransferResponse.class).ifPresent(packet -> {
            ServerTransfer.Response transferResponse = ServerTransfer.Response.of(packet.getAccepted());
            Apollo.getRoundtripManager().handleResponse(transferResponse);
        });

        event.unpackIf(PingResponse.class).ifPresent(packet -> {
            List<ServerPing.Response.PingData> pingData = packet.getDataList().stream()
                .map(data -> ServerPing.Response.PingData.of(
                    data.getAddress(),
                    ServerPing.Response.PingData.State.valueOf(data.getState().name()),
                    data.getPing()
                )).collect(Collectors.toList());

            ServerPing.Response pingResponse = ServerPing.Response.of(pingData);
            Apollo.getRoundtripManager().handleResponse(pingResponse);
        });
    }
}

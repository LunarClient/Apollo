package com.lunarclient.apollo.module.type;

import com.google.protobuf.ByteString;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.event.network.ApolloReceivePacketEvent;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.transfer.ServerPing;
import com.lunarclient.apollo.player.ui.transfer.ServerTransfer;
import com.lunarclient.apollo.roundtrip.async.Future;
import com.lunarclient.apollo.roundtrip.async.Handler;
import com.lunarclient.apollo.transfer.v1.PingRequest;
import com.lunarclient.apollo.transfer.v1.PingResponse;
import com.lunarclient.apollo.transfer.v1.TransferRequest;
import com.lunarclient.apollo.transfer.v1.TransferResponse;
import java.util.List;
import java.util.stream.Collectors;

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
            .setRequestId(ByteString.copyFromUtf8(ping.getPacketId().toString()))
            .addAllServerIps(ping.getAddresses())
            .build();

        Future<ServerPing.Response> pingResponse = ((AbstractApolloPlayer) player)
            .sendRoundTripPacket(ping, message);

        pingResponse.onFailure(Throwable::printStackTrace);
        pingResponse.onSuccess(response);
    }

    @Override
    public void transfer(ApolloPlayer player, ServerTransfer.Request transfer, Handler<ServerTransfer.Response> response) {
        requireNonNull(player, "player");
        requireNonNull(transfer, "transfer");

        TransferRequest message = TransferRequest.newBuilder()
            .setRequestId(ByteString.copyFromUtf8(transfer.getPacketId().toString()))
            .setServerIp(transfer.getAddress())
            .build();

        Future<ServerTransfer.Response> transferResponse = ((AbstractApolloPlayer) player)
            .sendRoundTripPacket(transfer, message);

        transferResponse.onFailure(Throwable::printStackTrace);
        transferResponse.onSuccess(response);
    }

    public void onTransferResponse(ApolloReceivePacketEvent event) {
        event.unpack(TransferResponse.class).ifPresent(packet -> {
            ServerTransfer.Response transferResponse = ServerTransfer.Response.builder()
                .withStatus(ServerTransfer.Response.Status.values()[packet.getStatusValue() - 1])
                .build();

            Apollo.getRoundtripManager().handleResponse(transferResponse);
        });

        event.unpack(PingResponse.class).ifPresent(packet -> {
            List<ServerPing.Response.PingData> pingData = packet.getPingDataList().stream()
                .map(data -> ServerPing.Response.PingData.builder()
                        .withAddress(data.getServerIp())
                        .withStatus(ServerPing.Response.PingData.Status.values()[data.getStatusValue() - 1])
                        .withPing(data.getPing())
                        .build()
                ).collect(Collectors.toList());

            ServerPing.Response pingResponse = ServerPing.Response.builder()
                .withData(pingData)
                .build();

            Apollo.getRoundtripManager().handleResponse(pingResponse);
        });
    }

}

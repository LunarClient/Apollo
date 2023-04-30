package com.lunarclient.apollo.module.transfer;

import com.google.protobuf.ByteString;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.event.ApolloReceivePacketEvent;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.roundtrip.async.Future;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;

/**
 * Provides the transfer module.
 *
 * @since 1.0.0
 */
public final class TransferModuleImpl extends TransferModule {

    public TransferModuleImpl() {
        super();
        this.handle(ApolloReceivePacketEvent.class, this::onTransferResponse);
    }

    @Override
    public Future<PingResponse> ping(@NonNull ApolloPlayer player, @NonNull PingRequest request) {
        com.lunarclient.apollo.transfer.v1.PingRequest requestProto = com.lunarclient.apollo.transfer.v1.PingRequest.newBuilder()
            .setRequestId(ByteString.copyFromUtf8(request.getRequestId().toString()))
            .addAllServerIps(request.getServerIps())
            .build();

        return ((AbstractApolloPlayer) player).sendRoundTripPacket(request, requestProto);
    }

    @Override
    public Future<TransferResponse> transfer(@NonNull ApolloPlayer player, @NonNull TransferRequest request) {
        com.lunarclient.apollo.transfer.v1.TransferRequest requestProto = com.lunarclient.apollo.transfer.v1.TransferRequest.newBuilder()
            .setRequestId(ByteString.copyFromUtf8(request.getRequestId().toString()))
            .setServerIp(request.getServerIp())
            .build();

        return ((AbstractApolloPlayer) player).sendRoundTripPacket(request, requestProto);
    }

    public void onTransferResponse(ApolloReceivePacketEvent event) {
        event.unpack(com.lunarclient.apollo.transfer.v1.TransferResponse.class).ifPresent(packet -> {
            TransferResponse transferResponse = TransferResponse.builder()
                .status(TransferResponse.Status.values()[packet.getStatusValue() - 1])
                .build();

            Apollo.getRoundtripManager().handleResponse(transferResponse);
        });

        event.unpack(com.lunarclient.apollo.transfer.v1.PingResponse.class).ifPresent(packet -> {
            List<PingResponse.PingData> pingData = packet.getPingDataList().stream()
                .map(data -> PingResponse.PingData.builder()
                    .serverIp(data.getServerIp())
                    .status(PingResponse.PingData.Status.values()[data.getStatusValue() - 1])
                    .pingMillis(data.getPing())
                    .build()
                ).collect(Collectors.toList());

            PingResponse pingResponse = PingResponse.builder()
                .data(pingData)
                .build();

            Apollo.getRoundtripManager().handleResponse(pingResponse);
        });
    }

}

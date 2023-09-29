/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2023 Moonsworth
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.lunarclient.apollo.module.transfer;

import com.google.protobuf.ByteString;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.async.Future;
import com.lunarclient.apollo.event.ApolloReceivePacketEvent;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.NonNull;

/**
 * Provides the transfer module.
 *
 * @since 1.0.0
 */
public final class TransferModuleImpl extends TransferModule {

    /**
     * Creates a new instance of {@link TransferModuleImpl}.
     *
     * @since 1.0.0
     */
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

    private void onTransferResponse(ApolloReceivePacketEvent event) {
        event.unpack(com.lunarclient.apollo.transfer.v1.TransferResponse.class).ifPresent(packet -> {
            TransferResponse transferResponse = TransferResponse.builder()
                .packetId(UUID.fromString(packet.getRequestId().toStringUtf8()))
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
                .packetId(UUID.fromString(packet.getRequestId().toStringUtf8()))
                .data(pingData)
                .build();

            Apollo.getRoundtripManager().handleResponse(pingResponse);
        });
    }

}

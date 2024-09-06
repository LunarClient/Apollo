package com.lunarclient.apollo.example.modules.impl.proto;

import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;
import com.lunarclient.apollo.example.listeners.proto.ApolloRoundtripProtoListener;
import com.lunarclient.apollo.example.modules.impl.TransferExample;
import com.lunarclient.apollo.transfer.v1.PingData;
import com.lunarclient.apollo.transfer.v1.PingRequest;
import com.lunarclient.apollo.transfer.v1.PingResponse;
import com.lunarclient.apollo.transfer.v1.TransferRequest;
import com.lunarclient.apollo.transfer.v1.TransferResponse;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TransferProtoExample extends TransferExample {

    @Override
    public void transferExample(Player player) {
        UUID requestId = UUID.randomUUID();

        TransferRequest transferRequestMessage = TransferRequest.newBuilder()
            .setRequestId(ByteString.copyFromUtf8(requestId.toString()))
            .setServerIp("mc.hypixel.net")
            .build();

        ApolloRoundtripProtoListener.getInstance().sendRequest(player, requestId, transferRequestMessage, TransferResponse.class, response -> {
            String message = "";

            switch (response.getStatus()) {
                case STATUS_ACCEPTED: {
                    message = "Transfer accepted! Goodbye!";
                    break;
                }

                case STATUS_REJECTED: {
                    message = "Transfer rejected by client!";
                    break;
                }
            }

            player.sendMessage(message);
        });
    }

    @Override
    public void pingExample(Player player) {
        UUID requestId = UUID.randomUUID();

        PingRequest pingRequestMessage = PingRequest.newBuilder()
            .setRequestId(ByteString.copyFromUtf8(requestId.toString()))
            .addAllServerIps(Lists.newArrayList("mc.hypixel.net", "minehut.com"))
            .build();

        ApolloRoundtripProtoListener.getInstance().sendRequest(player, requestId, pingRequestMessage, PingResponse.class, response -> {
            for (PingData pingData : response.getPingDataList()) {
                String message = "";

                switch (pingData.getStatus()) {
                    // Displays successful ping request to display the server IP and the players ping to that server.
                    case STATUS_SUCCESS: {
                        message = String.format("Ping to %s is %d ms.", pingData.getServerIp(), pingData.getPing());
                        break;
                    }

                    // If the ping request times-out
                    case STATUS_TIMED_OUT: {
                        message = String.format("Failed to ping %s", pingData.getServerIp());
                        break;
                    }
                }

                player.sendMessage(message);
            }
        });
    }

}

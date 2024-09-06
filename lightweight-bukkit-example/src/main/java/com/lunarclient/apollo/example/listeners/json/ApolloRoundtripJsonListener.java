package com.lunarclient.apollo.example.listeners.json;

import com.google.protobuf.GeneratedMessageV3;
import com.lunarclient.apollo.example.utilities.ProtobufPacketUtil;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class ApolloRoundtripJsonListener implements PluginMessageListener {

    @Getter
    private static ApolloRoundtripJsonListener instance;

    // Player UUID -> Packet ID -> Response Packet
    // Consider having a timeout for packets that expect a Response
    private final Map<UUID, Map<UUID, Consumer<GeneratedMessageV3>>> roundTripPacketConsumers = new HashMap<>();

    public ApolloRoundtripJsonListener() {
        instance = this;
    }

    @Override
    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {
        String data = new String(bytes);
        System.out.println(data);

//        try {
//            Any any = Any.parseFrom(bytes);
//
//            if (any.is(PingResponse.class)) {
//                PingResponse message = any.unpack(PingResponse.class);
//                UUID requestId = UUID.fromString(message.getRequestId().toStringUtf8());
//                this.handleResponse(player, requestId, message);
//            } else if (any.is(TransferResponse.class)) {
//                TransferResponse message = any.unpack(TransferResponse.class);
//                UUID requestId = UUID.fromString(message.getRequestId().toStringUtf8());
//                this.handleResponse(player, requestId, message);
//            }
//
//        } catch (InvalidProtocolBufferException e) {
//            throw new RuntimeException(e);
//        }
    }

    public <T extends GeneratedMessageV3> void sendRequest(Player player, UUID requestId, GeneratedMessageV3 request,
                                                            Class<T> response, Consumer<T> action) {
        ProtobufPacketUtil.sendPacket(player, request);

        this.roundTripPacketConsumers.computeIfAbsent(player.getUniqueId(), k -> new HashMap<>())
            .put(requestId, (Consumer<GeneratedMessageV3>) action);
    }

    private <T extends GeneratedMessageV3> void handleResponse(Player player, UUID requestId, T message) {
        Map<UUID, Consumer<GeneratedMessageV3>> consumers = this.roundTripPacketConsumers.get(player.getUniqueId());
        if (consumers == null) {
            return;
        }

        Consumer<GeneratedMessageV3> action = consumers.remove(requestId);

        if (action != null) {
            action.accept(message);
        }
    }

}

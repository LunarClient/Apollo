package com.lunarclient.apollo.roundtrip;

import com.lunarclient.apollo.roundtrip.async.future.UncertainFuture;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ApolloRoundtripManager {

    private final Map<UUID, UncertainFuture<ApolloResponse>> listeners;
    private final ScheduledThreadPoolExecutor timeoutExecutor;

    public ApolloRoundtripManager() {
        this.listeners = new ConcurrentHashMap<>();
        this.timeoutExecutor = new ScheduledThreadPoolExecutor(1);
    }

    public void handleResponse(ApolloResponse response) {
        UncertainFuture<ApolloResponse> future = this.listeners.remove(response.getPacketId());

        if(future != null) {
            future.getSuccess().forEach(handler -> handler.handle(response));
        }
    }

    public <T extends ApolloResponse> void registerListener(ApolloRequest<T> request, UncertainFuture<T> future) {
        UUID packetId = request.getPacketId();

        this.timeoutExecutor.schedule(() -> {
            try {
                UncertainFuture<ApolloResponse> listener = this.listeners.remove(packetId);

                if(listener != null) {
                    future.getFail().forEach(handler -> handler.handle(new Throwable("Timeout exceeded!")));
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }, ApolloRequest.TIMEOUT, TimeUnit.MILLISECONDS);

        this.listeners.put(packetId, (UncertainFuture<ApolloResponse>) future);
    }
}

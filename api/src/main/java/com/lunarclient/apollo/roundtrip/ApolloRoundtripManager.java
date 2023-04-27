package com.lunarclient.apollo.roundtrip;

import com.lunarclient.apollo.roundtrip.async.future.UncertainFuture;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Manages Apollo round-trip messages.
 */
public class ApolloRoundtripManager {

    /**
     * Represents a {@link Map} of {@link UUID} packet id as a key
     * and {@link UncertainFuture} response as value.
     */
    private final Map<UUID, UncertainFuture<ApolloResponse>> listeners;

    /**
     * The executor for packet timeouts.
     */
    private final ScheduledThreadPoolExecutor timeoutExecutor;

    public ApolloRoundtripManager() {
        this.listeners = new ConcurrentHashMap<>();
        this.timeoutExecutor = new ScheduledThreadPoolExecutor(1);
    }

    /**
     * Handles the given {@link ApolloResponse}.
     *
     * @param response the response
     */
    public void handleResponse(ApolloResponse response) {
        UncertainFuture<ApolloResponse> future = this.listeners.remove(response.getPacketId());

        if(future != null) {
            future.getSuccess().forEach(handler -> handler.handle(response));
        }
    }

    /**
     * Registers an {@link UncertainFuture} for an {@link ApolloRequest}.
     *
     * @param request the request
     * @param future the future
     * @param <T> the response type
     */
    public <T extends ApolloResponse> void registerListener(ApolloRequest<T> request, UncertainFuture<T> future) {
        UUID packetId = request.getPacketId();

        this.timeoutExecutor.schedule(() -> {
            try {
                UncertainFuture<ApolloResponse> listener = this.listeners.remove(packetId);

                if(listener != null) {
                    future.getFailure().forEach(handler -> handler.handle(new Throwable("Timeout exceeded!")));
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }, ApolloRequest.TIMEOUT, TimeUnit.MILLISECONDS);

        this.listeners.put(packetId, (UncertainFuture<ApolloResponse>) future);
    }
}

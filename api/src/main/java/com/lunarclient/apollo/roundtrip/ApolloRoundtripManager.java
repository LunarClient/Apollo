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
package com.lunarclient.apollo.roundtrip;

import com.lunarclient.apollo.async.future.UncertainFuture;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Manages Apollo round-trip messages.
 *
 * @since 1.0.0
 */
public final class ApolloRoundtripManager {

    /**
     * Represents a {@link Map} of {@link UUID} packet id as a key
     * and {@link UncertainFuture} response as value.
     *
     * @since 1.0.0
     */
    private final Map<UUID, UncertainFuture<ApolloResponse>> listeners;

    /**
     * The executor for packet timeouts.
     *
     * @since 1.0.0
     */
    private final ScheduledThreadPoolExecutor timeoutExecutor;

    /**
     * Constructs the {@link ApolloRoundtripManager}.
     *
     * @since 1.0.0
     */
    public ApolloRoundtripManager() {
        this.listeners = new ConcurrentHashMap<>();
        this.timeoutExecutor = new ScheduledThreadPoolExecutor(1);
    }

    /**
     * Handles the given {@link ApolloResponse}.
     *
     * @param response the response
     * @since 1.0.0
     */
    public void handleResponse(ApolloResponse response) {
        UncertainFuture<ApolloResponse> future = this.listeners.remove(response.getPacketId());

        if (future != null) {
            future.getSuccess().forEach(handler -> handler.handle(response));
        }
    }

    /**
     * Registers an {@link UncertainFuture} for an {@link ApolloRequest}.
     *
     * @param request the request
     * @param future  the future
     * @param <T>     the response type
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    public <T extends ApolloResponse> void registerListener(ApolloRequest<T> request, UncertainFuture<T> future) {
        UUID packetId = request.getRequestId();

        this.timeoutExecutor.schedule(() -> {
            try {
                UncertainFuture<ApolloResponse> listener = this.listeners.remove(packetId);

                if (listener != null) {
                    Throwable error = new Throwable("Timeout exceeded!");
                    future.getFailure().forEach(handler -> handler.handle(error));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, ApolloRequest.TIMEOUT, TimeUnit.MILLISECONDS);

        this.listeners.put(packetId, (UncertainFuture<ApolloResponse>) future);
    }

}

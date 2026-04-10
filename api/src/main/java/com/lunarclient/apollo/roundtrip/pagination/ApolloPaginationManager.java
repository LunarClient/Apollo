/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2026 Moonsworth
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
package com.lunarclient.apollo.roundtrip.pagination;

import com.lunarclient.apollo.roundtrip.ApolloRoundtripManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Handles the accumulation of paginated Apollo responses.
 *
 * @since 1.2.5
 */
public final class ApolloPaginationManager {

    private final Map<UUID, List<Object>> pagination;
    private final ApolloRoundtripManager roundtripManager;

    /**
     * Constructs the {@link ApolloPaginationManager}.
     *
     * @param roundtripManager the round trip manager
     * @since 1.2.5
     */
    public ApolloPaginationManager(ApolloRoundtripManager roundtripManager) {
        this.pagination = new ConcurrentHashMap<>();
        this.roundtripManager = roundtripManager;
    }

    /**
     * Processes a single page. If it's the last page, it combines the data
     * and passes it back to the main round-trip manager to complete the future.
     *
     * @param response the paginated response
     * @param <T>      the paginated response type
     * @since 1.2.5
     */
    @SuppressWarnings("unchecked")
    public <T> void handlePage(ApolloPaginatedResponse<T> response) {
        UUID packetId = response.getPacketId();

        List<T> elements = (List<T>) this.pagination.computeIfAbsent(packetId, k -> new ArrayList<>());
        if (response.getElements() != null) {
            elements.addAll(response.getElements());
        }

        if (!response.isLastPage()) {
            return;
        }

        this.pagination.remove(packetId);

        ApolloPaginatedResponse<?> combinedResponse = response.combine(packetId, elements);
        this.roundtripManager.completeFuture(combinedResponse);
    }

    /**
     * Clears pending data for a request that timed out.
     *
     * @param packetId the request packet id
     * @since 1.2.5
     */
    public void handleTimeout(UUID packetId) {
        this.pagination.remove(packetId);
    }

}

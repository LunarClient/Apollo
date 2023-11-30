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
package com.lunarclient.apollo.api.request;

import com.lunarclient.apollo.api.ApiRequest;
import com.lunarclient.apollo.api.ApiRequestType;
import com.lunarclient.apollo.api.ApiServiceType;
import com.lunarclient.apollo.api.response.ServerHeartbeatResponse;
import lombok.Builder;
import lombok.ToString;

/**
 * Represents the apollo version request.
 *
 * @since 1.0.0
 */
@ToString
@Builder(toBuilder = true)
public final class ServerHeartbeatRequest implements ApiRequest<ServerHeartbeatResponse> {

    /**
     * Randomly generated ID that gets saved to the apollo config file.
     *
     * @since 1.0.0
     */
    private final String serverInstallationId;

    /**
     * Randomly generated ID every single time Apollo starts.
     *
     * @since 1.0.0
     */
    private final String serverSessionId;

    /**
     * The system load average for the last minute.
     *
     * @since 1.0.0
     */
    private final double cpuUsage;

    /**
     * The servers maximum allocated ram in megabytes.
     *
     * @since 1.0.0
     */
    private final int ramMax;

    /**
     * The servers used ram in megabytes.
     *
     * @since 1.0.0
     */
    private final int ramUsed;

    /**
     * The servers total online players.
     *
     * @since 1.0.0
     */
    private final int totalPlayers;

    @Override
    public ApiServiceType getService() {
        return ApiServiceType.ANALYTICS;
    }

    @Override
    public ApiRequestType getType() {
        return ApiRequestType.POST;
    }

    @Override
    public String getRoute() {
        return "event/server.heartbeat";
    }

}

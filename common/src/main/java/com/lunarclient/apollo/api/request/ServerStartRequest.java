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
import com.lunarclient.apollo.api.response.VersionResponse;
import com.lunarclient.apollo.stats.ApolloPluginDescription;
import java.util.List;
import lombok.Builder;

/**
 * Represents the apollo version request.
 *
 * @since 1.0.0
 */
@Builder
public final class ServerStartRequest implements ApiRequest<VersionResponse> {

    // TODO: docs
    private final String serverSessionId;
    private final List<ApolloPluginDescription> plugins;
    private final boolean onlineMode;
    private final String platformType;
    private final String platformSubtype;
    private final String platformVersion;
    private final String javaVersion;
    private final String cpuArch;
    private final int cpuCoreCount;

    @Override
    public ApiRequestType getType() {
        return ApiRequestType.GET;
    }

    @Override
    public String getRoute() {
        return "/updates";
    }

}

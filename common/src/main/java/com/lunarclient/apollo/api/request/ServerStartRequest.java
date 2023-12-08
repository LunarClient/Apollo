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
import com.lunarclient.apollo.api.response.ServerStartResponse;
import com.lunarclient.apollo.stats.ApolloPluginDescription;
import java.util.List;
import lombok.Builder;
import lombok.ToString;

/**
 * Represents the apollo version request.
 *
 * @since 1.0.0
 */
@ToString
@Builder(toBuilder = true)
public final class ServerStartRequest implements ApiRequest<ServerStartResponse> {

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
     * A {@link List} of {@link ApolloPluginDescription} that contains
     * key plugin information such as the plugin name, author & version.
     *
     * @since 1.0.0
     */
    private final List<ApolloPluginDescription> plugins;

    /**
     * Whether the server is set to online mode.
     *
     * @since 1.0.0
     */
    private final boolean onlineMode;

    /**
     * The platform {@link com.lunarclient.apollo.ApolloPlatform.Kind} type.
     *
     * @since 1.0.0
     */
    private final String platformType;

    /**
     * The platform subtype (Bukkit, BungeeCord, Velocity...).
     *
     * @since 1.0.0
     */
    private final String platformSubtype;

    /**
     * The platform version.
     *
     * @since 1.0.0
     */
    private final String platformVersion;

    /**
     * The Java version used by the server.
     *
     * @since 1.0.0
     */
    private final String javaVersion;

    /**
     * The CPU architecture used by the server.
     *
     * @since 1.0.0
     */
    private final String cpuArch;

    /**
     * The number of processors available.
     *
     * @since 1.0.0
     */
    private final int cpuCoreCount;

    /**
     * The operating system name.
     *
     * @since 1.0.0
     */
    private final String operatingSystem;

    /**
     * The operating system release.
     *
     * @since 1.0.0
     */
    private final String operatingSystemRelease;

    /**
     * A {@link List} of {@link String} apollo modules that are currently enabled.
     *
     * @since 1.0.0
     */
    private final List<String> modules;

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
        return "event/server.start";
    }

}

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
package com.lunarclient.apollo.stats;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.ApolloPlatform;
import com.lunarclient.apollo.api.request.ServerStartRequest;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.Options;
import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Manages Apollo statistics.
 *
 * @since 1.0.0
 */
public final class ApolloStatsManager {

    public static final String SESSION_ID = UUID.randomUUID().toString();
    private static final String CONFIG_PREFIX = "mcstats";

    public static final SimpleOption<Boolean> SEND_STATS = Option.<Boolean>builder()
        .comment("Set to 'true' to send statistics to MCStats, otherwise 'false'.")
        .node(CONFIG_PREFIX, "send-stats").type(TypeToken.get(Boolean.class))
        .defaultValue(true).build();

    public static final SimpleOption<UUID> INSTALLATION_ID = Option.<UUID>builder()
        .comment("Your server's installation id used for stats.")
        .node(CONFIG_PREFIX, "installation-id").type(TypeToken.get(UUID.class))
        .defaultValue(UUID.randomUUID()).build();

    public static final SimpleOption<Boolean> SERVER_STATISTICS = Option.<Boolean>builder()
        .comment("Set to 'true' to send your minecraft server statistics to MCStats, otherwise 'false'.")
        .node(CONFIG_PREFIX, "server-statistics").type(TypeToken.get(Boolean.class))
        .defaultValue(true).build();

    public static final SimpleOption<Boolean> SOFTWARE_INFORMATION = Option.<Boolean>builder()
        .comment("Set to 'true' to send your server software information to MCStats, otherwise 'false'.")
        .node(CONFIG_PREFIX, "software-information").type(TypeToken.get(Boolean.class))
        .defaultValue(true).build();

    public static final SimpleOption<Boolean> HARDWARE_INFORMATION = Option.<Boolean>builder()
        .comment("Set to 'true' to send your server hardware information to MCStats, otherwise 'false'.")
        .node(CONFIG_PREFIX, "hardware-information").type(TypeToken.get(Boolean.class))
        .defaultValue(true).build();

    public static final SimpleOption<Boolean> HEARTBEAT_PERFORMANCE = Option.<Boolean>builder()
        .comment("Set to 'true' to send your server cpu and ram usage to MCStats, otherwise 'false'.")
        .node(CONFIG_PREFIX, "performance").type(TypeToken.get(Boolean.class))
        .defaultValue(true).build();

    public static final SimpleOption<Boolean> HEARTBEAT_COUNTS = Option.<Boolean>builder()
        .comment("Set to 'true' to send your server player count to MCStats, otherwise 'false'.")
        .node(CONFIG_PREFIX, "counts").type(TypeToken.get(Boolean.class))
        .defaultValue(true).build();

    /**
     * Constructs the {@link ApolloStatsManager}.
     *
     * @since 1.0.0
     */
    public ApolloStatsManager() {
        ApolloManager.registerOptions(
            ApolloStatsManager.SEND_STATS,
            ApolloStatsManager.INSTALLATION_ID,
            ApolloStatsManager.SERVER_STATISTICS,
            ApolloStatsManager.HARDWARE_INFORMATION,
            ApolloStatsManager.SOFTWARE_INFORMATION,
            ApolloStatsManager.HEARTBEAT_PERFORMANCE,
            ApolloStatsManager.HEARTBEAT_COUNTS
        );
    }

    /**
     * Enables the Apollo statistics.
     *
     * @since 1.0.0
     */
    public void enable() {
        this.handleServerStartStats();

        new ApolloStatsThread();
    }

    private void handleServerStartStats() {
        ApolloPlatform platform = Apollo.getPlatform();
        Options options = platform.getOptions();

        if (!options.get(ApolloStatsManager.SEND_STATS)) {
            return;
        }

        boolean serverStatistics = options.get(ApolloStatsManager.SERVER_STATISTICS);
        boolean softwareInformation = options.get(ApolloStatsManager.SOFTWARE_INFORMATION);
        boolean hardwareInformation = options.get(ApolloStatsManager.HARDWARE_INFORMATION);

        if (!serverStatistics && !softwareInformation && !hardwareInformation) {
            return;
        }

        ApolloStats stats = platform.getStats();
        Runtime runtime = Runtime.getRuntime();

        List<String> enabledModules = Apollo.getModuleManager().getModules().stream()
            .filter(ApolloModule::isEnabled)
            .map(ApolloModule::getId)
            .collect(Collectors.toList());

        ServerStartRequest.ServerStartRequestBuilder requestBuilder = ServerStartRequest.builder()
            .serverInstallationId(options.get(ApolloStatsManager.INSTALLATION_ID).toString())
            .serverSessionId(SESSION_ID);

        if (serverStatistics) {
            requestBuilder
                .plugins(stats.getPlugins())
                .onlineMode(stats.isOnlineMode())
                .platformType(platform.getKind().name())
                .platformSubtype(stats.getPlatformSubtype())
                .platformVersion(stats.getPlatformVersion())
                .modules(enabledModules);
        }

        if (softwareInformation) {
            requestBuilder
                .javaVersion(System.getProperty("java.version"))
                .operatingSystem(System.getProperty("os.name"))
                .operatingSystemRelease(System.getProperty("os.version"));
        }

        if (hardwareInformation) {
            requestBuilder
                .cpuArch(System.getProperty("os.arch"))
                .cpuCoreCount(runtime.availableProcessors());
        }

        ApolloManager.getHttpManager().request(requestBuilder.build())
            .onFailure(Throwable::printStackTrace);
    }

}

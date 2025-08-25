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
import com.lunarclient.apollo.api.ApolloHttpManager;
import com.lunarclient.apollo.api.request.heartbeat.ServerHeartbeatRequest;
import com.lunarclient.apollo.option.Options;
import com.lunarclient.apollo.stats.metadata.ApolloMetadataManager;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.concurrent.TimeUnit;

/**
 * Represents a background thread responsible for sending heartbeat data to MCStats.
 *
 * @since 1.0.0
 */
public final class ApolloStatsThread extends Thread {

    private static final long MB_BYTES = 1024 * 1024;
    private static final OperatingSystemMXBean MX_BEAN = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
    private static final long HEARTBEAT_INTERVAL = TimeUnit.MINUTES.toMillis(15);

    /**
     * Constructs the {@link ApolloStatsThread} thread.
     *
     * @since 1.0.0
     */
    public ApolloStatsThread() {
        this.setName("Apollo Stats Thread");
        this.setDaemon(true);
        this.start();
    }

    @Override
    public void run() {
        for(;;) {
            ServerHeartbeatRequest request = null;
            try {
                ApolloPlatform platform = Apollo.getPlatform();
                Options options = platform.getOptions();
                ApolloStats stats = platform.getStats();
                Runtime runtime = Runtime.getRuntime();

                if (!options.get(ApolloStatsManager.SEND_STATS)) {
                    break;
                }

                boolean performance = options.get(ApolloStatsManager.HEARTBEAT_PERFORMANCE);
                boolean counts = options.get(ApolloStatsManager.HEARTBEAT_COUNTS);
                boolean userMetadata = options.get(ApolloStatsManager.HEARTBEAT_USER_METADATA);

                if (!performance && !counts && !userMetadata) {
                    break;
                }

                ServerHeartbeatRequest.ServerHeartbeatRequestBuilder requestBuilder = ServerHeartbeatRequest.builder()
                    .serverInstallationId(options.get(ApolloStatsManager.INSTALLATION_ID).toString())
                    .serverSessionId(ApolloStatsManager.SESSION_ID);

                if (performance) {
                    requestBuilder
                        .cpuUsage(MX_BEAN.getSystemLoadAverage())
                        .ramMax((int) (runtime.maxMemory() / MB_BYTES))
                        .ramUsed((int) ((runtime.maxMemory() - runtime.freeMemory()) / MB_BYTES));
                }

                if (counts) {
                    requestBuilder
                        .totalPlayers(stats.getTotalPlayers());
                }

                if (userMetadata) {
                    ApolloMetadataManager metadataManager = ApolloManager.getMetadataManager();

                    requestBuilder
                        .metadata(metadataManager.extract());

                    metadataManager.clear();
                }

                final ServerHeartbeatRequest finalRequest = request = requestBuilder.build();

                ApolloManager.getHttpManager().request(request)
                    .onFailure(throwable -> ApolloHttpManager.handleError("Failed to send heartbeat!", throwable, finalRequest));
            } catch (Throwable e) {
                ApolloHttpManager.handleError("Failed to create heartbeat!", e, request);
            }

            try {
                Thread.sleep(HEARTBEAT_INTERVAL);
            } catch (InterruptedException e) {
                ApolloHttpManager.handleError("Failed to sleep stats thread!", e, null);
            }
        }
    }

}

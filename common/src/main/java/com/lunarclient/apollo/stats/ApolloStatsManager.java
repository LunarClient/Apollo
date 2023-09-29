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
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.Options;
import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Manages Apollo statistics.
 *
 * @since 1.0.0
 */
public final class ApolloStatsManager {

    private static final OperatingSystemMXBean MX_BEAN = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
    private static final String SESSION_ID = UUID.randomUUID().toString();
    private static final String CONFIG_PREFIX = "mcstats";

    public static final SimpleOption<Boolean> PLUGINS = Option.<Boolean>builder()
        .comment("Set to 'true' to send your server plugins to MCStats, otherwise 'false'.")
        .node(CONFIG_PREFIX, "plugins").type(TypeToken.get(Boolean.class))
        .defaultValue(true).build();

    public static final SimpleOption<Boolean> MOTD = Option.<Boolean>builder()
        .comment("Set to 'true' to send your server IP address to MCStats, otherwise 'false'.")
        .node(CONFIG_PREFIX, "address").type(TypeToken.get(Boolean.class))
        .defaultValue(true).build();

    public static final SimpleOption<Boolean> ICON = Option.<Boolean>builder()
        .comment("Set to 'true' to send your server icon to MCStats, otherwise 'false'.")
        .node(CONFIG_PREFIX, "icon").type(TypeToken.get(Boolean.class))
        .defaultValue(true).build();

    public static final SimpleOption<Boolean> VERSION = Option.<Boolean>builder()
        .comment("Set to 'true' to send your server version to MCStats, otherwise 'false'.")
        .node(CONFIG_PREFIX, "version").type(TypeToken.get(Boolean.class))
        .defaultValue(true).build();

    public static final SimpleOption<Boolean> PLATFORM_SUBTYPE = Option.<Boolean>builder()
        .comment("Set to 'true' to send your server platform subtype to MCStats, otherwise 'false'.")
        .node(CONFIG_PREFIX, "platform-subtype").type(TypeToken.get(Boolean.class))
        .defaultValue(true).build();

    public static final SimpleOption<Boolean> PLATFORM_VERSION = Option.<Boolean>builder()
        .comment("Set to 'true' to send your server platform version to MCStats, otherwise 'false'.")
        .node(CONFIG_PREFIX, "platform-version").type(TypeToken.get(Boolean.class))
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
        this.registerConfigOptions();
        this.handleServerStartStats();

        new ApolloStatsThread();
    }

    private void registerConfigOptions() {
        ApolloManager.registerOptions(
            ApolloStatsManager.MOTD,
            ApolloStatsManager.ICON,
            ApolloStatsManager.VERSION,
            ApolloStatsManager.PLUGINS,
            ApolloStatsManager.PLATFORM_SUBTYPE,
            ApolloStatsManager.PLATFORM_VERSION,
            ApolloStatsManager.HEARTBEAT_PERFORMANCE,
            ApolloStatsManager.HEARTBEAT_COUNTS
        );
    }

    private void handleServerStartStats() {
        ApolloPlatform platform = Apollo.getPlatform();
        Options options = platform.getOptions();
        ApolloStats stats = platform.getStats();
        Runtime runtime = Runtime.getRuntime();

        ServerStartRequest request = ServerStartRequest.builder()
            .serverSessionId(SESSION_ID)
            .plugins(options.get(ApolloStatsManager.PLUGINS) ? stats.getPlugins() : new ArrayList<>())
            .onlineMode(stats.isOnlineMode())
            .platformType(platform.getKind().name())
            .platformSubtype(options.get(ApolloStatsManager.PLATFORM_SUBTYPE) ? stats.getPlatformSubtype() : null)
            .platformVersion(options.get(ApolloStatsManager.PLATFORM_VERSION) ? stats.getPlatformVersion() : null)
            .javaVersion(System.getProperty("java.version"))
            .cpuArch(System.getProperty("os.arch"))
            .cpuCoreCount(runtime.availableProcessors())
            .build();

        System.out.println(ApolloManager.GSON.toJson(request));
    }

}

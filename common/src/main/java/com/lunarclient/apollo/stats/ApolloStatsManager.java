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
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * Manages Apollo statistics.
 *
 * @since 1.0.0
 */
public final class ApolloStatsManager {

    private static final String CONFIG_PREFIX = "mcstats";

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

    public static final SimpleOption<Boolean> PLUGINS = Option.<Boolean>builder()
        .comment("Set to 'true' to send your server plugins to MCStats, otherwise 'false'.")
        .node(CONFIG_PREFIX, "plugins").type(TypeToken.get(Boolean.class))
        .defaultValue(true).build();

    public static final SimpleOption<Boolean> PLATFORM_TYPE = Option.<Boolean>builder()
        .comment("Set to 'true' to send your server platform type to MCStats, otherwise 'false'.")
        .node(CONFIG_PREFIX, "platform-type").type(TypeToken.get(Boolean.class))
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
            ApolloStatsManager.PLATFORM_TYPE,
            ApolloStatsManager.PLATFORM_VERSION,
            ApolloStatsManager.HEARTBEAT_PERFORMANCE,
            ApolloStatsManager.HEARTBEAT_COUNTS
        );
    }

    private void handleServerStartStats() {
        ApolloStats stats = Apollo.getPlatform().getStats();

        // Request
        System.out.println("MOTD " + stats.getMotd());
        System.out.println("ICON " + stats.getIcon());
        System.out.println("VERSION " + stats.getVersion());
        System.out.println("PLUGINS " + stats.getPlugins());
        System.out.println("PLATFORM TYPE " + stats.getPlatformType());
        System.out.println("PLATFORM VERSION " + stats.getPlatformVersion());
    }

}

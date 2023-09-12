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

    public static final SimpleOption<Boolean> SERVER_IP = Option.<Boolean>builder()
        .comment("Set to 'true' to send your server IP address to MCStats, otherwise 'false'.")
        .node(CONFIG_PREFIX + "server-address").type(TypeToken.get(Boolean.class))
        .defaultValue(true).build();

    /**
     * Constructs the {@link ApolloStatsManager}.
     *
     * @since 1.0.0
     */
    public ApolloStatsManager() {
        this.handleServerStartStats();
    }

    private void handleServerStartStats() {
        ApolloStats stats = Apollo.getPlatform().getStats();

        // Request
        System.out.println(stats.getMotd());
        System.out.println(stats.getIcon());
        System.out.println(stats.getVersion());
        System.out.println(stats.getPlugins());
        System.out.println(stats.getPlatformType());
        System.out.println(stats.getPlatformVersion());
    }

}

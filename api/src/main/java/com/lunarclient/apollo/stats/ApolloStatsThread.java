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
import java.util.concurrent.TimeUnit;

/**
 * Represents a background thread responsible for sending heartbeat data to MCStats.
 *
 * @since 1.0.0
 */
public final class ApolloStatsThread extends Thread {

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
            try {
                ApolloStats stats = Apollo.getPlatform().getStats();

                System.out.println("CPU USAGE " + stats.getCpuUsage());
                System.out.println("MAX RAM " + stats.getMaximumAllocatedRam());
                System.out.println("USED RAM " + stats.getUsedRam());
                System.out.println("TOTAL PLAYERS " + stats.getTotalPlayers());
                System.out.println("MAX PLAYERS " + stats.getMaxPlayers());

                Thread.sleep(HEARTBEAT_INTERVAL);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
}

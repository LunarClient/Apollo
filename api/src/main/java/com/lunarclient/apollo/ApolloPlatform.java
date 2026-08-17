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
package com.lunarclient.apollo;

import com.lunarclient.apollo.option.Options;
import com.lunarclient.apollo.stats.ApolloStats;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents a platform that supports Apollo.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public interface ApolloPlatform {

    /**
     * Returns this kind of platform.
     *
     * @return this kind of platform
     * @since 1.0.0
     */
    Kind getKind();

    /**
     * Returns the platform type.
     *
     * @return the platform type
     * @since 1.2.0
     */
    Platform getPlatform();

    /**
     * Returns the platform options that don't belong to a specific module.
     *
     * @return the platform options
     * @since 1.0.0
     */
    Options getOptions();

    /**
     * Returns the current Apollo version.
     *
     * @return the current apollo version
     * @since 1.0.0
     */
    String getApolloVersion();

    /**
     * Returns the servers logger.
     *
     * @return the servers logger
     * @since 1.0.0
     */
    Logger getPlatformLogger();

    /**
     * Returns the platform stats.
     *
     * @return the platform stats
     * @since 1.0.0
     */
    ApolloStats getStats();

    /**
     * Returns the platform plugin class.
     *
     * @return the platform plugin class
     * @since 1.0.9
     */
    Object getPlugin();

    /**
     * Returns the platform {@link Scheduler}.
     *
     * @return the platform scheduler
     * @since 1.2.9
     */
    Scheduler getScheduler();

    /**
     * Represents the kind of server a platform is.
     *
     * @since 1.0.0
     */
    enum Kind {
        SERVER,
        PROXY
    }

    /**
     * Represents the platform type.
     *
     * @since 1.2.0
     */
    enum Platform {
        BUKKIT,
        FOLIA,
        MINESTOM,
        BUNGEE,
        VELOCITY
    }

    /**
     * Represents the platform scheduler, running tasks through the
     * platform plugin's own scheduler.
     *
     * @since 1.2.9
     */
    interface Scheduler {

        /**
         * Schedules a task to run asynchronously every period, first running
         * after the given delay.
         *
         * @param task   the task to run
         * @param delay  the delay before the first run
         * @param period the period between runs
         * @param unit   the unit of the delay and period
         * @since 1.2.9
         */
        void scheduleAsyncRepeating(Runnable task, long delay, long period, TimeUnit unit);

    }

}

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
package com.lunarclient.apollo.module.stopwatch;

import com.lunarclient.apollo.ApolloPlatform;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.recipients.Recipients;
import java.util.Arrays;
import java.util.Collection;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the stopwatch module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "stopwatch", name = "Stopwatch")
public abstract class StopwatchModule extends ApolloModule {

    @Override
    public Collection<ApolloPlatform.Kind> getSupportedPlatforms() {
        return Arrays.asList(ApolloPlatform.Kind.SERVER, ApolloPlatform.Kind.PROXY);
    }

    /**
     * Adds a {@link Stopwatch} to the HUD for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param stopwatch  the stopwatch to add
     * @since 1.2.6
     */
    public abstract void addStopwatch(Recipients recipients, Stopwatch stopwatch);

    /**
     * Removes the {@link Stopwatch} with the given id for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param id         the stopwatch id
     * @since 1.2.6
     */
    public abstract void removeStopwatch(Recipients recipients, String id);

    /**
     * Starts the {@link Stopwatch} with the given id for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param id         the stopwatch id
     * @since 1.2.6
     */
    public abstract void startStopwatch(Recipients recipients, String id);

    /**
     * Stops the {@link Stopwatch} with the given id for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param id         the stopwatch id
     * @since 1.2.6
     */
    public abstract void stopStopwatch(Recipients recipients, String id);

    /**
     * Resets the {@link Stopwatch} with the given id for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param id         the stopwatch id
     * @since 1.2.6
     */
    public abstract void resetStopwatch(Recipients recipients, String id);

    /**
     * Resets all {@link Stopwatch}es for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @since 1.2.6
     */
    public abstract void resetStopwatches(Recipients recipients);

    /**
     * Adds a {@link Timer} to the HUD for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param timer      the timer to add
     * @since 1.2.6
     */
    public abstract void addTimer(Recipients recipients, Timer timer);

    /**
     * Removes the {@link Timer} with the given id for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param id         the timer id
     * @since 1.2.6
     */
    public abstract void removeTimer(Recipients recipients, String id);

    /**
     * Starts the {@link Timer} with the given id for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param id         the timer id
     * @since 1.2.6
     */
    public abstract void startTimer(Recipients recipients, String id);

    /**
     * Stops the {@link Timer} with the given id for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param id         the timer id
     * @since 1.2.6
     */
    public abstract void stopTimer(Recipients recipients, String id);

    /**
     * Resets the {@link Timer} with the given id for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param id         the timer id
     * @since 1.2.6
     */
    public abstract void resetTimer(Recipients recipients, String id);

    /**
     * Resets all {@link Timer}s for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @since 1.2.6
     */
    public abstract void resetTimers(Recipients recipients);

    /**
     * Starts the stopwatch for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @deprecated for removal since 1.2.6, use {@link #addStopwatch(Recipients, Stopwatch)}
     *     and {@link #startStopwatch(Recipients, String)} instead.
     * @since 1.0.0
     */
    @Deprecated
    public abstract void startStopwatch(Recipients recipients);

    /**
     * Stops the stopwatch for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @deprecated for removal since 1.2.6, use {@link #stopStopwatch(Recipients, String)} instead.
     * @since 1.0.0
     */
    @Deprecated
    public abstract void stopStopwatch(Recipients recipients);

    /**
     * Resets the stopwatch for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @deprecated for removal since 1.2.6, use {@link #resetStopwatch(Recipients, String)} instead.
     * @since 1.0.0
     */
    @Deprecated
    public abstract void resetStopwatch(Recipients recipients);

}

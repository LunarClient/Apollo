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
package com.lunarclient.apollo.common.button;

import com.lunarclient.apollo.player.ApolloPlayer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import lombok.Getter;
import lombok.NonNull;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

/**
 * Represents the tooltip of an {@link ApolloButton}, either a static
 * list of lines or a live per-player resolver.
 *
 * @since 1.2.9
 */
@Getter
public final class ApolloButtonTooltip {

    /**
     * The maximum number of tooltip lines.
     *
     * @since 1.2.9
     */
    public static final int MAX_LINES = 100;

    /**
     * Creates a static tooltip from the given lines.
     *
     * @param lines the tooltip lines
     * @return the tooltip
     * @since 1.2.9
     */
    public static ApolloButtonTooltip of(@NonNull Component... lines) {
        return of(Arrays.asList(lines));
    }

    /**
     * Creates a static tooltip from the given lines.
     *
     * @param lines the tooltip lines
     * @return the tooltip
     * @since 1.2.9
     */
    public static ApolloButtonTooltip of(@NonNull List<Component> lines) {
        if (lines.size() > MAX_LINES) {
            throw new IllegalArgumentException("ApolloButtonTooltip supports at most " + MAX_LINES + " lines");
        }

        for (Component line : lines) {
            if (line == null) {
                throw new IllegalArgumentException("ApolloButtonTooltip lines must not contain null");
            }
        }

        return new ApolloButtonTooltip(lines, null, null);
    }

    /**
     * Creates a live tooltip, resolved into lines for each viewing
     * {@link ApolloPlayer} and refreshed at the given interval.
     *
     * <p>The interval must be positive and is quantized to server ticks
     * (50ms); anything below one tick refreshes every tick.</p>
     *
     * @param resolver       the per-player tooltip resolver
     * @param updateInterval the refresh interval
     * @return the tooltip
     * @since 1.2.9
     */
    public static ApolloButtonTooltip live(@NonNull Function<ApolloPlayer, List<Component>> resolver,
                                           @NonNull Duration updateInterval) {
        if (updateInterval.isNegative() || updateInterval.isZero()) {
            throw new IllegalArgumentException("ApolloButtonTooltip#updateInterval must be positive");
        }

        return new ApolloButtonTooltip(null, resolver, updateInterval);
    }

    /**
     * Returns the static tooltip lines, or {@code null} if this tooltip is live.
     *
     * @return the tooltip lines, or {@code null}
     * @since 1.2.9
     */
    private final @Nullable List<Component> lines;

    /**
     * Returns the per-player live tooltip resolver, or {@code null} if this
     * tooltip is static.
     *
     * @return the live tooltip resolver, or {@code null}
     * @since 1.2.9
     */
    private final @Nullable Function<ApolloPlayer, List<Component>> resolver;

    /**
     * Returns the refresh interval of a live tooltip, or {@code null} if
     * this tooltip is static.
     *
     * @return the update interval, or {@code null}
     * @since 1.2.9
     */
    private final @Nullable Duration updateInterval;

    private ApolloButtonTooltip(@Nullable List<Component> lines,
                                @Nullable Function<ApolloPlayer, List<Component>> resolver,
                                @Nullable Duration updateInterval) {
        this.lines = lines == null ? null : Collections.unmodifiableList(new ArrayList<>(lines));
        this.resolver = resolver;
        this.updateInterval = updateInterval;
    }

    /**
     * Returns whether this tooltip is live.
     *
     * @return whether this tooltip is live
     * @since 1.2.9
     */
    public boolean isLive() {
        return this.resolver != null;
    }

}

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
package com.lunarclient.apollo.common.button.content;

import com.lunarclient.apollo.common.icon.Icon;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.time.Duration;
import java.util.function.Function;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.ApiStatus;

/**
 * The abstract base class for a single {@link ApolloButtonContent} part.
 *
 * <p>Each part is one of {@link ComponentPart}, {@link IconPart} or
 * {@link LiveComponentPart}, created through the factory methods on this
 * class or the {@link ApolloButtonContent.Builder} append overloads.</p>
 *
 * @since 1.2.9
 */
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@ApiStatus.NonExtendable
public abstract class ApolloButtonContentPart {

    /**
     * Creates a static text part from the given adventure {@link Component}.
     *
     * @param component the component to render
     * @return the component part
     * @since 1.2.9
     */
    public static ComponentPart component(@NonNull Component component) {
        return new ComponentPart(component);
    }

    /**
     * Creates an icon part from the given {@link Icon}.
     *
     * @param icon the icon to render
     * @return the icon part
     * @since 1.2.9
     */
    public static IconPart icon(@NonNull Icon icon) {
        return new IconPart(icon);
    }

    /**
     * Creates a live part, resolved into an adventure {@link Component} for
     * each viewing {@link ApolloPlayer} and refreshed at the given interval.
     *
     * <p>The interval must be positive and is quantized to server ticks
     * (50ms); anything below one tick refreshes every tick.</p>
     *
     * @param resolver       the per-player component resolver
     * @param updateInterval the refresh interval
     * @return the live component part
     * @since 1.2.9
     */
    public static LiveComponentPart live(@NonNull Function<ApolloPlayer, Component> resolver,
                                         @NonNull Duration updateInterval) {
        if (updateInterval.isNegative() || updateInterval.isZero()) {
            throw new IllegalArgumentException("LiveComponentPart#updateInterval must be positive");
        }

        return new LiveComponentPart(resolver, updateInterval);
    }

    /**
     * Returns whether this part is live.
     *
     * @return whether this part is live
     * @since 1.2.9
     */
    public boolean isLive() {
        return false;
    }

}

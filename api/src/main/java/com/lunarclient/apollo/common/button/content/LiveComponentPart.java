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

import com.lunarclient.apollo.player.ApolloPlayer;
import java.time.Duration;
import java.util.function.Function;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;

/**
 * Represents a live content part: a per-player {@link Component} resolver
 * with an update interval controlling how often the module's live button
 * broadcast re-resolves and re-sends it.
 *
 * @since 1.2.9
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class LiveComponentPart extends ApolloButtonContentPart {

    /**
     * Returns the per-player live {@link Component} resolver.
     *
     * @return the component resolver
     * @since 1.2.9
     */
    private final Function<ApolloPlayer, Component> resolver;

    /**
     * Returns the refresh interval.
     *
     * @return the update interval
     * @since 1.2.9
     */
    private final Duration updateInterval;

    @Override
    public boolean isLive() {
        return true;
    }

}

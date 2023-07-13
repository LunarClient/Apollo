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
package com.lunarclient.apollo.audience;

import com.lunarclient.apollo.Apollo;
import java.util.function.Consumer;

/**
 * Represents a group of recipients.
 *
 * @since 1.0.0
 */
public interface Audience {

    /**
     * Creates a {@link ForwardingAudience} instance
     * from a collection of individual audience members.
     *
     * @param audiences the collection of audiences
     * @return a {@code ForwardingAudience} instance representing the given audiences
     * @since 1.0.0
     */
    static ForwardingAudience of(Iterable<? extends Audience> audiences) {
        return () -> audiences;
    }

    /**
     * Creates a {@link ForwardingAudience} instance
     * representing all available apollo players.
     *
     * @return a {@code ForwardingAudience} instance representing all apollo players
     * @since 1.0.0
     */
    static ForwardingAudience ofEveryone() {
        return () -> Apollo.getPlayerManager().getPlayers();
    }

    /**
     * Performs the given action on this audience.
     *
     * @param action the action
     * @since 1.0.0
     */
    default void forEach(Consumer<? super Audience> action) {
        action.accept(this);
    }

}

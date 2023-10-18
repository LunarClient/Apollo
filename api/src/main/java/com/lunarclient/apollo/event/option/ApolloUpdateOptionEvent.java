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
package com.lunarclient.apollo.event.option;

import com.lunarclient.apollo.event.EventCancellable;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.Options;
import com.lunarclient.apollo.player.ApolloPlayer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an event that is fired when an option is updated.
 *
 * @since 1.0.0
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class ApolloUpdateOptionEvent implements EventCancellable {

    /**
     * The {@link Options} container that the option is in.
     *
     * @return the options container
     * @since 1.0.0
     */
    Options container;

    /**
     * The {@link ApolloPlayer} that the option was updated for, or
     * {@code null} if it was a global option.
     *
     * @return the player
     * @since 1.0.0
     */
    @Nullable ApolloPlayer player;

    /**
     * The {@link Option} that was updated.
     *
     * @return the option
     * @since 1.0.0
     */
    Option<?, ?, ?> option;

    /**
     * The new value of the option.
     *
     * @return the new value
     * @since 1.0.0
     */
    @Nullable Object value;

    @NonFinal @Setter boolean cancelled;

}

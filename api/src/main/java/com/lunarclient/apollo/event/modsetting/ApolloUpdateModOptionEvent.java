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
package com.lunarclient.apollo.event.modsetting;

import com.lunarclient.apollo.event.Event;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.player.ApolloPlayer;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an event that is fired when an option is updated.
 *
 * @since 1.2.1
 */
@Value
public class ApolloUpdateModOptionEvent implements Event {

    /**
     * The {@link ApolloPlayer} that the option was updated for.
     *
     * @return the player
     * @since 1.2.1
     */
    ApolloPlayer player;

    /**
     * The {@link Option} that was updated.
     *
     * @return the option
     * @since 1.2.1
     */
    Option<?, ?, ?> option;

    /**
     * The new value of the option.
     *
     * @return the new value
     * @since 1.2.1
     */
    @Nullable Object value;

}

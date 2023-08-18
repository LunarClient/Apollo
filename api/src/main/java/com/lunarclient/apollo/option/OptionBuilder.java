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
package com.lunarclient.apollo.option;

import io.leangen.geantyref.TypeToken;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Represents an option builder.
 *
 * @param <V> the value type
 * @param <M> the option builder type
 * @param <I> the option type
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public abstract class OptionBuilder<V, M extends OptionBuilder<V, M, I>, I extends Option<V, M, I>> {

    String[] node;
    TypeToken<V> typeToken;
    String comment = null;
    V defaultValue = null;
    boolean notify;

    /**
     * Sets the option node to the provided {@link String} array and returns
     * this builder.
     *
     * @param key the node string array
     * @return this builder
     * @since 1.0.0
     */
    public M node(@NonNull String... key) {
        this.node = key;
        return (M) this;
    }

    /**
     * Sets the option type to the provided {@link TypeToken} and returns
     * this builder.
     *
     * @param typeToken the type token
     * @return this builder
     * @since 1.0.0
     */
    public M type(@NonNull TypeToken<V> typeToken) {
        this.typeToken = typeToken;
        return (M) this;
    }

    /**
     * Sets the option comment to the provided {@link String} and returns
     * this builder.
     *
     * @param comment the comment
     * @return this builder
     * @since 1.0.0
     */
    public M comment(String comment) {
        this.comment = comment;
        return (M) this;
    }

    /**
     * Sets the option default value to the provided {@code T} value and
     * returns this builder.
     *
     * @param value the default value
     * @return this builder
     * @since 1.0.0
     */
    public M defaultValue(V value) {
        this.defaultValue = value;
        return (M) this;
    }

    /**
     * Sets whether the option should be aware to the client to {@code true}
     * and returns this builder.
     *
     * @return this builder
     * @since 1.0.0
     */
    public M notifyClient() {
        this.notify = true;
        return (M) this;
    }

    /**
     * Returns a new {@code I} option from this builder.
     *
     * @return a new option
     * @since 1.0.0
     */
    public abstract I build();

}

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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.Nullable;

import static java.util.Objects.requireNonNull;

/**
 * Represents an option.
 *
 * @param <V> the value type
 * @param <M> the option builder type
 * @param <I> the option type
 * @since 1.0.0
 */
@Getter
@EqualsAndHashCode
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public abstract class Option<V, M extends OptionBuilder<V, M, I>, I extends Option<V, M, I>> {

    /**
     * Returns a new {@link SimpleOption.SimpleOptionBuilder}.
     *
     * @param <V> the value type
     * @return a new simple option builder
     * @since 1.0.0
     */
    public static <V> SimpleOption.SimpleOptionBuilder<V> builder() {
        return new SimpleOption.SimpleOptionBuilder<>();
    }

    /**
     * Returns a new {@link NumberOption.NumberOptionBuilder}.
     *
     * @param <V> the value type
     * @return a new number option builder
     * @since 1.0.0
     */
    public static <V extends Number & Comparable<V>> NumberOption.NumberOptionBuilder<V> number() {
        return new NumberOption.NumberOptionBuilder<>();
    }

    /**
     * Returns a new {@link ListOption.ListOptionBuilder}.
     *
     * @param <T> the value type
     * @return a new list option builder
     * @since 1.0.0
     */
    public static <T> ListOption.ListOptionBuilder<T> list() {
        return new ListOption.ListOptionBuilder<>();
    }

    /**
     * Returns a new {@link EnumOption.EnumOptionBuilder}.
     *
     * @param <T> the value type
     * @return a new enum option builder
     * @since 1.2.1
     */
    public static <T extends Enum<T>> EnumOption.EnumOptionBuilder<T> enumerator() {
        return new EnumOption.EnumOptionBuilder<>();
    }

    /**
     * Returns the option path.
     *
     * @return the path string array
     * @since 1.0.0
     */
    String[] path;

    /**
     * Returns the option {@link TypeToken} for {@code T}.
     *
     * @return the value type token
     * @since 1.0.0
     */
    TypeToken<V> typeToken;

    /**
     * Returns the option {@link String} comment.
     *
     * @return the option comment
     * @since 1.0.0
     */
    @Nullable String comment;

    /**
     * Returns the option {@code T} default value.
     *
     * @return the option default value
     * @since 1.0.0
     */
    @Nullable V defaultValue;

    /**
     * Returns {@code true} if this option should be aware to the client,
     * otherwise returns {@code false}.
     *
     * @return whether the option should be aware to the client
     * @since 1.0.0
     */
    boolean notify;

    Option(M builder) {
        this.path = requireNonNull(builder.node, "node");
        this.typeToken = requireNonNull(builder.typeToken, "typeToken");

        this.comment = builder.comment;
        this.defaultValue = builder.defaultValue;
        this.notify = builder.notify;
    }

    /**
     * Returns the key as a joined {@link String}.
     *
     * @return the key string
     * @since 1.0.0
     */
    public String getKey() {
        return String.join(".", this.getPath());
    }

}

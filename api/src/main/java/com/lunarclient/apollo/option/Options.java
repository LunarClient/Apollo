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

import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a container of options.
 *
 * @since 1.0.0
 */
public interface Options extends Iterable<Option<?, ?, ?>> {

    /**
     * Returns an empty {@link Options}.
     *
     * @return an empty options container
     * @since 1.0.0
     */
    static Options empty() {
        return EmptyOptions.EMPTY;
    }

    /**
     * Returns the {@code T} value stored for the provided {@link Option} if
     * it exists, or the default value.
     *
     * @param option the option
     * @param <T>    the value type
     * @param <C>    the option type
     * @return the value or default if present
     * @since 1.0.0
     */
    <T, C extends Option<T, ?, ?>> @Nullable T get(C option);

    /**
     * Returns the {@code T} value stored for the provided {@link ApolloPlayer}
     * and {@link Option} if it exists, or the default value if it exists.
     *
     * @param player the player
     * @param option the option
     * @param <T>    the value type
     * @param <C>    the option type
     * @return the value or default if present
     * @since 1.0.0
     */
    <T, C extends Option<T, ?, ?>> @Nullable T get(ApolloPlayer player, C option);

    /**
     * Returns an {@link Optional} of the {@code T} value stored for the
     * provided {@link Option} if it exists, otherwise returns
     * {@link Optional#empty()}.
     *
     * @param option the option
     * @param <T>    the value type
     * @param <C>    the option type
     * @return the optional stored value
     * @since 1.0.0
     */
    <T, C extends Option<T, ?, ?>> Optional<T> getDirect(C option);

    /**
     * Returns an {@link Optional} of the {@code T} value stored for the
     * provided {@link ApolloPlayer} and {@link Option} if it exists,
     * otherwise returns {@link Optional#empty()}.
     *
     * @param player the player
     * @param option the option
     * @param <T>    the value type
     * @param <C>    the option type
     * @return the optional stored value
     * @since 1.0.0
     */
    <T, C extends Option<T, ?, ?>> Optional<T> getDirect(ApolloPlayer player, C option);

    /**
     * Sets the provided {@code T} value for the provided {@link Option}.
     *
     * @param option the option
     * @param value  the value
     * @param <T>    the value type
     * @since 1.0.0
     */
    <T> void set(Option<?, ?, ?> option, @Nullable T value);

    /**
     * Sets the provided {@code T} value for the provided {@link ApolloPlayer}
     * and {@link Option}.
     *
     * @param player the player
     * @param option the option
     * @param value  the value
     * @param <T>    the value type
     * @since 1.0.0
     */
    <T> void set(ApolloPlayer player, Option<?, ?, ?> option, @Nullable T value);

    /**
     * Adds the provided {@code T} value for the provided {@link Option} if it
     * does not already exist.
     *
     * @param option the option
     * @param value  the value
     * @param <T>    the value type
     * @since 1.0.0
     */
    <T> void add(Option<?, ?, ?> option, T value);

    /**
     * Adds the provided {@code T} value for the provided {@link ApolloPlayer}
     * and {@link Option} if it does not already exist.
     *
     * @param player the player
     * @param option the option
     * @param value  the value
     * @param <T>    the value type
     * @since 1.0.0
     */
    <T> void add(ApolloPlayer player, Option<?, ?, ?> option, T value);

    /**
     * Removes the provided {@code T} value for the provided {@link Option} if
     * it equals the provided value.
     *
     * @param option  the option
     * @param compare the value to compare to
     * @param <T>     the value type
     * @since 1.0.0
     */
    <T> void remove(Option<?, ?, ?> option, @Nullable T compare);

    /**
     * Removes the provided {@code T} value for the provided {@link ApolloPlayer}
     * and {@link Option} if it equals the provided value.
     *
     * @param player  the player
     * @param option  the option
     * @param compare the value to compare to
     * @param <T>     the value type
     * @since 1.0.0
     */
    <T> void remove(ApolloPlayer player, Option<?, ?, ?> option, @Nullable T compare);

    /**
     * Replaces the {@code T} value for the provided {@link Option} with the
     * output of the provided function.
     *
     * @param option            the option
     * @param remappingFunction the function
     * @param <T>               the value type
     * @since 1.0.0
     */
    <T> void replace(Option<?, ?, ?> option, BiFunction<Option<?, ?, ?>, T, T> remappingFunction);

    /**
     * Replaces the {@code T} value for the provided {@link ApolloPlayer} and
     * {@link Option} with the output of the provided function.
     *
     * @param player            the player
     * @param option            the option
     * @param remappingFunction the function
     * @param <T>               the value type
     * @since 1.0.0
     */
    <T> void replace(ApolloPlayer player, Option<?, ?, ?> option, BiFunction<Option<?, ?, ?>, T, T> remappingFunction);

    /**
     * Registers the provided {@code C} option for the {@link Options} implementation.
     *
     * @param option the option
     * @param <T>    the value type
     * @param <C>    the option type
     * @since 1.2.1
     */
    <T, C extends Option<T, ?, ?>> void register(C option);

    /**
     * Returns the internal registry containing all registered {@link Option} instances, mapped by their keys.
     *
     * @return the map of option keys to their corresponding {@link Option} instances
     * @since 1.2.1
     */
    Map<String, Option<?, ?, ?>> getRegistry();

}

package com.lunarclient.apollo.option;

import com.lunarclient.apollo.player.ApolloPlayer;
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
     * @param <T> the value type
     * @param <C> the option type
     * @return the value or default if present
     * @since 1.0.0
     */
    <T, C extends Option<T, ?, ?>> T get(C option);

    /**
     * Returns the {@code T} value stored for the provided {@link ApolloPlayer}
     * and {@link Option} if it exists, or the default value if it exists.
     *
     * @param player the player
     * @param option the option
     * @param <T> the value type
     * @param <C> the option type
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
     * @param <T> the value type
     * @param <C> the option type
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
     * @param <T> the value type
     * @param <C> the option type
     * @return the optional stored value
     * @since 1.0.0
     */
    <T, C extends Option<T, ?, ?>> Optional<T> getDirect(ApolloPlayer player, C option);

    /**
     * Sets the provided {@code T} value for the provided {@link Option}.
     *
     * @param option the option
     * @param value the value
     * @param <T> the value type
     * @since 1.0.0
     */
    <T> void set(Option<?, ?, ?> option, @Nullable T value);

    /**
     * Sets the provided {@code T} value for the provided {@link ApolloPlayer}
     * and {@link Option}.
     *
     * @param player the player
     * @param option the option
     * @param value the value
     * @param <T> the value type
     * @since 1.0.0
     */
    <T> void set(ApolloPlayer player, Option<?, ?, ?> option, @Nullable T value);

    /**
     * Adds the provided {@code T} value for the provided {@link Option} if it
     * does not already exist.
     *
     * @param option the option
     * @param value the value
     * @param <T> the value type
     * @since 1.0.0
     */
    <T> void add(Option<?, ?, ?> option, T value);

    /**
     * Adds the provided {@code T} value for the provided {@link ApolloPlayer}
     * and {@link Option} if it does not already exist.
     *
     * @param player the player
     * @param option the option
     * @param value the value
     * @param <T> the value type
     * @since 1.0.0
     */
    <T> void add(ApolloPlayer player, Option<?, ?, ?> option, T value);

    /**
     * Removes the provided {@code T} value for the provided {@link Option} if
     * it equals the provided value.
     *
     * @param option the option
     * @param compare the value to compare to
     * @param <T> the value type
     * @since 1.0.0
     */
    <T> void remove(Option<?, ?, ?> option, @Nullable T compare);

    /**
     * Removes the provided {@code T} value for the provided {@link ApolloPlayer}
     * and {@link Option} if it equals the provided value.
     *
     * @param player the player
     * @param option the option
     * @param compare the value to compare to
     * @param <T> the value type
     * @since 1.0.0
     */
    <T> void remove(ApolloPlayer player, Option<?, ?, ?> option, @Nullable T compare);

    /**
     * Replaces the {@code T} value for the provided {@link Option} with the
     * output of the provided function.
     *
     * @param option the option
     * @param remappingFunction the function
     * @param <T> the value type
     * @since 1.0.0
     */
    <T> void replace(Option<?, ?, ?> option, BiFunction<Option<?, ?, ?>, T, T> remappingFunction);

    /**
     * Replaces the {@code T} value for the provided {@link ApolloPlayer} and
     * {@link Option} with the output of the provided function.
     *
     * @param player the player
     * @param option the option
     * @param remappingFunction the function
     * @param <T> the value type
     * @since 1.0.0
     */
    <T> void replace(ApolloPlayer player, Option<?, ?, ?> option, BiFunction<Option<?, ?, ?>, T, T> remappingFunction);

}

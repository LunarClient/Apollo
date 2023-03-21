package com.moonsworth.apollo.option;

import com.moonsworth.apollo.player.ApolloPlayer;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiFunction;

public interface Options extends Iterable<Option<?, ?, ?>> {

    /**
     * Returns an empty {@link Options.Container}.
     *
     * @return an empty options container
     * @since 1.0.0
     */
    static Options.Container empty() {
        return EmptyOptions.EMPTY;
    }

    /**
     * Returns the {@code T} value stored for the provided {@link Option} if
     * it exists, or the default value if it exists.
     *
     * @param option the option
     * @param <T> the value type
     * @return the value or default if present
     * @since 1.0.0
     */
    <T> @Nullable T get(final Option<?, ?, ?> option);

    /**
     * Returns an {@link Optional} of the {@code T} value stored for the
     * provided {@link Option} if it exists, otherwise returns
     * {@link Optional#empty()}.
     *
     * @param option the option
     * @param <T> the value type
     * @return the optional stored value
     * @since 1.0.0
     */
    <T> Optional<T> getDirect(final Option<?, ?, ?> option);

    /**
     * Sets the provided {@code T} value for the provided {@link Option}.
     *
     * @param option the option
     * @param value the value
     * @param <T> the value type
     * @since 1.0.0
     */
    <T> void set(final Option<?, ?, ?> option, final @Nullable T value);

    /**
     * Adds the provided {@code T} value for the provided {@link Option} if it
     * does not already exist.
     *
     * @param option the option
     * @param value the value
     * @param <T> the value type
     * @since 1.0.0
     */
    <T> void add(final Option<?, ?, ?> option, final @Nullable T value);

    /**
     * Removes the provided {@code T} value for the provided {@link Option} if
     * it equals the provided value.
     *
     * @param option the option
     * @param compare the value to compare to
     * @param <T> the value type
     * @since 1.0.0
     */
    <T> void remove(final Option<?, ?, ?> option, final @Nullable T compare);

    /**
     * Replaces the {@code T} value for the provided {@link Option} with the
     * output of the provided function.
     *
     * @param option the option
     * @param remappingFunction the function
     * @param <T> the value type
     * @since 1.0.0
     */
    <T> void replace(final Option<?, ?, ?> option, final BiFunction<Option<?, ?, ?>, T, T> remappingFunction);

    /**
     * Represents a base option container.
     *
     * @since 1.0.0
     */
    interface Container extends Options {

        Options.Single get(final ApolloPlayer player);

    }

    /**
     * Represents a single {@link ApolloPlayer}s view of the option container.
     *
     * @since 1.0.0
     */
    interface Single extends Options {

        ApolloPlayer getPlayer();

    }

}

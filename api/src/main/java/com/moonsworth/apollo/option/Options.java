package com.moonsworth.apollo.option;

import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiFunction;

public interface Options extends Iterable<Option<?, ?, ?>> {

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
     * Adds the provided {@code T} value for the provided {@link Option}
     * @param option
     * @param value
     * @param <T>
     */
    <T> void add(final Option<?, ?, ?> option, final @Nullable T value);

    <T> void replace(final Option<?, ?, ?> option, final BiFunction<Option<?, ?, ?>, T, T> remappingFunction);

}

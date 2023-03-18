package com.moonsworth.apollo.option;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

/**
 * Represents a container of {@link Option}s.
 *
 * @since 1.0.0
 */
public final class Options implements Iterable<Option<?, ?, ?>> {

    /**
     * Returns an empty {@link Option}s container.
     *
     * @return an empty container
     * @since 1.0.0
     */
    @Getter private static final Options empty = new Options(Collections.emptyList());

    /**
     * Returns a new {@link Option}s container with the provided options.
     *
     * @param options the default options
     * @return a new container
     * @since 1.0.0
     */
    public static Options of(final Option<?, ?, ?>... options) {
        return new Options(Arrays.asList(options));
    }

    private final Map<String, Option<?, ?, ?>> options = new HashMap<>();
    private final Map<String, Object> values = new HashMap<>();

    private Options(final Collection<Option<?, ?, ?>> options) {
        for(final Option<?, ?, ?> option : options) {
            this.options.put(option.getKey(), option);
        }
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
    @SuppressWarnings("unchecked")
    public <T> @Nullable T get(final Option<?, ?, ?> option) {
        requireNonNull(option, "option");
        final Object value;
        return (value = this.values.get(option.getKey())) == null
                ? (T) option.getDefaultValue()
                : (T) value;
    }

    /**
     * Returns an {@link Optional} of the {@code T} value stored for the
     * provided {@link Option} if it exists, otherwise returns
     * {@link Optional#empty()}.
     *
     * @param option the option
     * @param <T> the value type
     * @return the optional stored value
     */
    @SuppressWarnings("unchecked")
    public <T> Optional<T> getDirect(final Option<?, ?, ?> option) {
        return Optional.ofNullable((T) this.values.get(option.getKey()));
    }

    /**
     * Sets the provided {@code T} value for the provided {@link Option}.
     *
     * @param option the option
     * @param value the value
     * @param <T> the value type
     * @since 1.0.0
     */
    public <T> void set(final Option<?, ?, ?> option, final T value) {
        requireNonNull(option, "option");
        requireNonNull(value, "value");
        if(Objects.equals(value, option.getDefaultValue())) {
            this.values.remove(option.getKey());
        } else {
            this.values.put(option.getKey(), requireNonNull(value, "value"));
        }
    }

    /**
     * Removes the provided {@link Option}s stored value and returns it,
     * if it exists.
     *
     * @param option the option
     * @param <T> the value type
     * @return the value if present
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    public <T> @Nullable T remove(final Option<?, ?, ?> option) {
        requireNonNull(option, "option");
        return (T) this.values.remove(option.getKey());
    }

    @Override
    public Iterator<Option<?, ?, ?>> iterator() {
        return this.options.values().iterator();
    }
}

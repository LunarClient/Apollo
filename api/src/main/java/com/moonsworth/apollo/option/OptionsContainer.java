package com.moonsworth.apollo.option;

import com.moonsworth.apollo.player.ApolloPlayer;
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
import java.util.function.BiFunction;

import static java.util.Objects.requireNonNull;

/**
 * Represents a container of {@link Option}s.
 *
 * @since 1.0.0
 */
public final class OptionsContainer implements Options {

    /**
     * Returns an empty {@link Option}s container.
     *
     * @return an empty container
     * @since 1.0.0
     */
    @Getter private static final OptionsContainer empty = new OptionsContainer(Collections.emptyList());

    /**
     * Returns a new {@link Option}s container with the provided options.
     *
     * @param options the default options
     * @return a new container
     * @since 1.0.0
     */
    public static OptionsContainer of(final Option<?, ?, ?>... options) {
        return new OptionsContainer(Arrays.asList(options));
    }

    private final Map<ApolloPlayer, OptionsView> views = new HashMap<>();
    private final Map<String, Option<?, ?, ?>> options = new HashMap<>();
    private final Map<String, Object> values = new HashMap<>();

    private OptionsContainer(final Collection<Option<?, ?, ?>> options) {
        for(final Option<?, ?, ?> option : options) {
            this.options.put(option.getKey(), option);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> @Nullable T get(final Option<?, ?, ?> option) {
        requireNonNull(option, "option");
        final Object value;
        return (value = this.values.get(option.getKey())) == null
                ? (T) option.getDefaultValue()
                : (T) value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Optional<T> getDirect(final Option<?, ?, ?> option) {
        return Optional.ofNullable((T) this.values.get(option.getKey()));
    }

    @Override
    public <T> void set(final Option<?, ?, ?> option, final @Nullable T value) {
        requireNonNull(option, "option");
        if(value == null || Objects.equals(value, option.getDefaultValue())) {
            this.values.remove(option.getKey());
        } else {
            this.values.put(option.getKey(), requireNonNull(value, "value"));
        }
    }

    @Override
    public <T> void add(final Option<?, ?, ?> option, final @Nullable T value) {
        requireNonNull(option, "option");
        requireNonNull(value, "value");
        this.values.putIfAbsent(option.getKey(), value);
    }

    @Override
    public <T> void replace(final Option<?, ?, ?> option, final BiFunction<Option<?, ?, ?>, T, T> remappingFunction) {
        requireNonNull(option, "option");
        requireNonNull(remappingFunction, "remappingFunction");
        this.values.compute(option.getKey(), (key, current) -> remappingFunction.apply(option, (T) current));
    }

    public OptionsView view(final ApolloPlayer player) {
        requireNonNull(player, "player");
        return this.views.computeIfAbsent(player, key -> new OptionsView(this, key));
    }

    @Override
    public Iterator<Option<?, ?, ?>> iterator() {
        return this.options.values().iterator();
    }

}

package com.moonsworth.apollo.option;

import com.moonsworth.apollo.player.ApolloPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

import static java.util.Objects.requireNonNull;

public final class OptionsView implements Options.Single {

    private final OptionsContainer container;
    private final ApolloPlayer player;

    private final Map<String, Object> values = new HashMap<>();

    OptionsView(final OptionsContainer parent, final ApolloPlayer player) {
        this.container = parent;
        this.player = player;
    }

    @Override
    public ApolloPlayer getPlayer() {
        return this.player;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> @Nullable T get(final Option<?, ?, ?> option) {
        requireNonNull(option, "option");
        final Object value;
        return (value = this.values.get(option.getKey())) == null
                ? this.container.get(option)
                : (T) value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Optional<T> getDirect(final Option<?, ?, ?> option) {
        final Object value;
        return (value = this.values.get(option.getKey())) == null
                ? this.container.getDirect(option)
                : Optional.of((T) value);
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
    public <T> void remove(final Option<?, ?, ?> option, final @Nullable T compare) {
        requireNonNull(option, "option");
        requireNonNull(compare, "compare");
        this.values.remove(option.getKey(), compare);
    }

    @Override
    public <T> void replace(final Option<?, ?, ?> option, final BiFunction<Option<?, ?, ?>, T, T> remappingFunction) {
        requireNonNull(option, "option");
        requireNonNull(remappingFunction, "remappingFunction");
        this.values.compute(option.getKey(), (key, current) -> remappingFunction.apply(option, (T) current));
    }

    @NotNull
    @Override
    public Iterator<Option<?, ?, ?>> iterator() {
        // TODO: join the values here with the ones from the container for this iterator
        return this.container.iterator();
    }

}

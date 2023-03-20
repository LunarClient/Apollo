package com.moonsworth.apollo.option;

import com.moonsworth.apollo.player.ApolloPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

import static java.util.Objects.requireNonNull;

public final class OptionsView implements Options {

    private final OptionsContainer container;
    private final ApolloPlayer player;

    private final Map<String, Object> values = new HashMap<>();

    OptionsView(final OptionsContainer parent, final ApolloPlayer player) {
        this.container = parent;
        this.player = player;
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
    public <T> Optional<T> getDirect(final Option<?, ?, ?> option) {
        return Optional.empty();
    }

    @Override
    public <T> void set(Option<?, ?, ?> option, @Nullable T value) {

    }

    @Override
    public <T> void add(Option<?, ?, ?> option, @Nullable T value) {

    }

    @Override
    public <T> void replace(Option<?, ?, ?> option, BiFunction<Option<?, ?, ?>, T, T> remappingFunction) {

    }

    @NotNull
    @Override
    public Iterator<Option<?, ?, ?>> iterator() {
        return null;
    }
}

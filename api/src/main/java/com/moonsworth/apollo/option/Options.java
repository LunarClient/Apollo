package com.moonsworth.apollo.option;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public final class Options implements Iterable<Option<?, ?, ?>> {
    private static final Options EMPTY = new Options(Collections.emptyList());

    public static Options of(final Option<?, ?, ?>... options) {
        final List<Option<?, ?, ?>> optionList = new LinkedList<>();
        Collections.addAll(optionList, options);
        return new Options(Collections.unmodifiableList(optionList));
    }

    public static Options empty() {
        return Options.EMPTY;
    }

    private final Map<String, Option<?, ?, ?>> options = new HashMap<>();
    private final Map<String, Object> values = new HashMap<>();

    private Options(final Collection<Option<?, ?, ?>> options) {
        for(final Option<?, ?, ?> option : options) {
            this.options.put(option.getKey(), option);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> @Nullable T get(final Option<?, ?, ?> option) {
        requireNonNull(option, "option");
        final Object value;
        return (value = this.values.get(option.getKey())) == null
                ? (T) option.getDefaultValue()
                : (T) value;
    }

    public <T> void set(final Option<?, ?, ?> option, final T value) {
        requireNonNull(option, "option");
        requireNonNull(value, "value");
        if(Objects.equals(value, option.getDefaultValue())) {
            this.values.remove(option.getKey());
        } else {
            this.values.put(option.getKey(), requireNonNull(value, "value"));
        }
    }

    @SuppressWarnings("unchecked")
    public <T> @Nullable T remove(final Option<?, ?, ?> option) {
        requireNonNull(option, "option");
        return (T) this.values.remove(option.getKey());
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<T> getDirect(final Option<?, ?, ?> option) {
        return Optional.ofNullable((T) this.values.get(option.getKey()));
    }

    @NotNull
    @Override
    public Iterator<Option<?, ?, ?>> iterator() {
        return this.options.values().iterator();
    }
}

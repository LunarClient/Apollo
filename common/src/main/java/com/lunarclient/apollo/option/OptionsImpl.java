package com.lunarclient.apollo.option;

import com.google.protobuf.NullValue;
import com.google.protobuf.Value;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.option.ApolloUpdateOptionEvent;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.WeakHashMap;
import java.util.function.BiFunction;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a container of {@link Option}s.
 *
 * @since 1.0.0
 */
public final class OptionsImpl implements Options {

    private final Map<Option<?, ?, ?>, Object> options = Collections.synchronizedMap(new HashMap<>());
    private final Map<ApolloPlayer, Map<Option<?, ?, ?>, Object>> playerOptions = Collections.synchronizedMap(new WeakHashMap<>());

    /**
     * Constructs a new {@link OptionsImpl}.
     *
     * @since 1.0.0
     */
    public OptionsImpl() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, C extends Option<T, ?, ?>> T get(@NonNull C option) {
        final Object value = this.options.get(option);
        return value == null ? option.getDefaultValue() : (T) value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, C extends Option<T, ?, ?>> @Nullable T get(@NonNull ApolloPlayer player, @NonNull C option) {
        final Object value = this.playerOptions.getOrDefault(player, Collections.emptyMap()).get(option);
        return value == null ? this.get(option) : (T) value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, C extends Option<T, ?, ?>> Optional<T> getDirect(@NonNull C option) {
        final Object value = this.options.get(option);
        return value == null ? Optional.empty() : Optional.of((T) value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, C extends Option<T, ?, ?>> Optional<T> getDirect(@NonNull ApolloPlayer player, @NonNull C option) {
        final Object value = this.playerOptions.getOrDefault(player, Collections.emptyMap()).get(option);
        return value == null ? this.getDirect(option) : Optional.of((T) value);
    }

    @Override
    public <T> void set(@NonNull Option<?, ?, ?> option, @Nullable T value) {
        if(value == null || Objects.equals(value, option.getDefaultValue())) {
            if(this.postUpdate(option, null, option.getDefaultValue())) return;
            this.options.remove(option);
        } else {
            if(this.postUpdate(option, null, value)) return;
            this.options.put(option, value);
        }
    }

    @Override
    public <T> void set(@NonNull ApolloPlayer player, @NonNull Option<?, ?, ?> option, @Nullable T value) {
        final Object globalValue = this.get(option);
        if(value == null || Objects.equals(value, globalValue)) {
            if(this.postUpdate(option, player, globalValue)) return;
            this.playerOptions.computeIfAbsent(player, k -> Collections.synchronizedMap(new WeakHashMap<>())).remove(option);
        } else {
            if(this.postUpdate(option, player, value)) return;
            this.playerOptions.computeIfAbsent(player, k -> Collections.synchronizedMap(new WeakHashMap<>())).put(option, value);
        }
    }

    @Override
    public <T> void add(@NonNull Option<?, ?, ?> option, @NonNull T value) {
        if(this.postUpdate(option, null, value)) return;
        this.options.put(option, value);
    }

    @Override
    public <T> void add(@NonNull ApolloPlayer player, @NonNull Option<?, ?, ?> option, @NonNull T value) {
        if(this.postUpdate(option, player, value)) return;
        this.playerOptions.computeIfAbsent(player, k -> Collections.synchronizedMap(new WeakHashMap<>())).put(option, value);
    }

    @Override
    public <T> void remove(@NonNull Option<?, ?, ?> option, @Nullable T compare) {
        if(this.postUpdate(option, null, option.getDefaultValue())) return;
        this.options.remove(option, compare);
    }

    @Override
    public <T> void remove(@NonNull ApolloPlayer player, @NonNull Option<?, ?, ?> option, @Nullable T compare) {
        if(this.postUpdate(option, player, this.get(option))) return;
        this.playerOptions.computeIfAbsent(player, k -> Collections.synchronizedMap(new WeakHashMap<>())).remove(option, compare);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void replace(@NonNull Option<?, ?, ?> option, @NonNull BiFunction<Option<?, ?, ?>, T, T> remappingFunction) {
        this.options.replaceAll((k, v) -> {
            final T value = remappingFunction.apply(option, (T) v);
            if(this.postUpdate(option, null, value)) return null;
            return value;
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void replace(@NonNull ApolloPlayer player, @NonNull Option<?, ?, ?> option, @NonNull BiFunction<Option<?, ?, ?>, T, T> remappingFunction) {
        this.playerOptions.computeIfAbsent(player, k -> Collections.synchronizedMap(new WeakHashMap<>())).replaceAll((k, v) -> {
            final T value = remappingFunction.apply(option, (T) v);
            if(this.postUpdate(option, player, value)) return null;
            return value;
        });
    }

    @Override
    public @NonNull Iterator<Option<?, ?, ?>> iterator() {
        return this.options.keySet().iterator();
    }

    /**
     * Wraps the provided value into a protobuf {@link Value}.
     *
     * @param valueBuilder the value builder
     * @param current the current value
     * @return the wrapped value
     * @since 1.0.0
     */
    public Value wrapValue(Value.Builder valueBuilder, Object current) {
        if(current instanceof Number) {
            valueBuilder.setNumberValue(((Number) current).doubleValue());
        } else if(current instanceof String) {
            valueBuilder.setStringValue((String) current);
        } else if(current instanceof Boolean) {
            valueBuilder.setBoolValue((Boolean) current);
        } else {
            valueBuilder.setNullValue(NullValue.NULL_VALUE);
        }

        return valueBuilder.build();
    }

    /**
     * Unwraps the provided protobuf {@link Value} into the appropriate object.
     *
     * @param wrapper the wrapped value
     * @return the unwrapped value
     * @since 1.0.0
     */
    public @Nullable Object unwrapValue(Value wrapper) {
        if(wrapper.hasNumberValue()) {
            return wrapper.getNumberValue();
        } else if(wrapper.hasStringValue()) {
            return wrapper.getStringValue();
        } else if(wrapper.hasBoolValue()) {
            return wrapper.getBoolValue();
        }

        return null;
    }

    private boolean postUpdate(Option<?, ?, ?> option, @Nullable ApolloPlayer player, @Nullable Object value) {
        final EventBus.EventResult<ApolloUpdateOptionEvent> eventResult = EventBus.getBus().post(new ApolloUpdateOptionEvent(this, player, option, value));
        boolean cancelled = eventResult.getEvent().isCancelled();
        for(Throwable throwable : eventResult.getThrowing()) {
            throwable.printStackTrace();
        }
        return cancelled;
    }

}

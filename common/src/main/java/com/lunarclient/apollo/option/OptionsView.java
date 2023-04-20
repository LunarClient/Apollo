package com.lunarclient.apollo.option;

import com.google.protobuf.Value;
import com.lunarclient.apollo.network.NetworkOptions;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import org.jetbrains.annotations.Nullable;

import static java.util.Objects.requireNonNull;

/**
 * Represents a view of a {@link OptionsContainer} for a specific {@link ApolloPlayer}.
 *
 * @since 1.0.0
 */
public final class OptionsView extends AbstractOptions implements Options.Single {

    private final OptionsContainer container;
    private final ApolloPlayer player;

    private final Map<String, Object> values = new HashMap<>();

    OptionsView(OptionsContainer parent, ApolloPlayer player) {
        this.container = parent;
        this.player = player;
    }

    @Override
    public ApolloPlayer getPlayer() {
        return this.player;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, C extends Option<T, ?, ?>> @Nullable T get(C option) {
        requireNonNull(option, "option");
        Object value;
        return (value = this.values.get(option.getKey())) == null
                ? this.container.get(option)
                : (T) value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, C extends Option<T, ?, ?>> Optional<T> getDirect(C option) {
        Object value;
        return (value = this.values.get(option.getKey())) == null
                ? this.container.getDirect(option)
                : Optional.of((T) value);
    }

    @Override
    public <T> void set(Option<?, ?, ?> option, @Nullable T value) {
        requireNonNull(option, "option");
        if(!this.postUpdate(option, value)) return;
        boolean send = false;
        if(value == null || Objects.equals(value, option.getDefaultValue())) {
            send = this.values.remove(option.getKey()) != null;
        } else {
            this.values.put(option.getKey(), value);
            send = true;
        }

        if(send) {
            NetworkOptions.sendOption(
                    this.container.module,
                    option,
                    this.wrapValue(Value.newBuilder(), value),
                    Collections.singleton(this.player)
            );
        }
    }

    @Override
    public <T> void add(Option<?, ?, ?> option, T value) {
        requireNonNull(option, "option");
        requireNonNull(value, "value");
        if(!this.postUpdate(option, value)) return;
        if(this.values.putIfAbsent(option.getKey(), value) == null) {
            NetworkOptions.sendOption(
                    this.container.module,
                    option,
                    this.wrapValue(Value.newBuilder(), value),
                    Collections.singleton(this.player)
            );
        }
    }

    @Override
    public <T> void remove(Option<?, ?, ?> option, @Nullable T compare) {
        requireNonNull(option, "option");
        requireNonNull(compare, "compare");
        if(!this.postUpdate(option, null)) return;
        if(this.values.remove(option.getKey(), compare)) {
            NetworkOptions.sendOption(
                    this.container.module,
                    option,
                    this.wrapValue(Value.newBuilder(), null),
                    Collections.singleton(this.player)
            );
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void replace(Option<?, ?, ?> option, BiFunction<Option<?, ?, ?>, T, T> remappingFunction) {
        requireNonNull(option, "option");
        requireNonNull(remappingFunction, "remappingFunction");
        Object value = this.values.compute(option.getKey(), (key, current) -> remappingFunction.apply(option, (T) current));
        NetworkOptions.sendOption(
                this.container.module,
                option,
                this.wrapValue(Value.newBuilder(), value),
                Collections.singleton(this.player)
        );
    }

    @Override
    public Iterator<Option<?, ?, ?>> iterator() {
        // TODO: join the values here with the ones from the container for this iterator
        return this.container.iterator();
    }

}

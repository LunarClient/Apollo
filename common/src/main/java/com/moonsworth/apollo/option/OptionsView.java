package com.moonsworth.apollo.option;

import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.protocol.AddOption;
import com.moonsworth.apollo.protocol.ModuleOption;
import com.moonsworth.apollo.protocol.SetOption;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

import static java.util.Objects.requireNonNull;

public final class OptionsView extends AbstractOptions implements Options.Single {

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
    public <T, C extends Option<T, ?, ?>> @Nullable T get(final C option) {
        requireNonNull(option, "option");
        final Object value;
        return (value = this.values.get(option.getKey())) == null
                ? this.container.get(option)
                : (T) value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, C extends Option<T, ?, ?>> Optional<T> getDirect(final C option) {
        final Object value;
        return (value = this.values.get(option.getKey())) == null
                ? this.container.getDirect(option)
                : Optional.of((T) value);
    }

    @Override
    public <T> void set(final Option<?, ?, ?> option, final @Nullable T value) {
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
                    ModuleOption.newBuilder().setSet(SetOption.newBuilder().setValue(this.wrapElement(value)).build()).build(),
                    Collections.singleton(this.player)
            );
        }
    }

    @Override
    public <T> void add(final Option<?, ?, ?> option, final T value) {
        requireNonNull(option, "option");
        requireNonNull(value, "value");
        if(!this.postUpdate(option, value)) return;
        if(this.values.putIfAbsent(option.getKey(), value) == null) {
            NetworkOptions.sendOption(
                    this.container.module,
                    option,
                    ModuleOption.newBuilder().setAdd(AddOption.newBuilder().setValue(this.wrapElement(value)).build()).build(),
                    Collections.singleton(this.player)
            );
        }
    }

    @Override
    public <T> void remove(final Option<?, ?, ?> option, final @Nullable T compare) {
        requireNonNull(option, "option");
        requireNonNull(compare, "compare");
        if(!this.postUpdate(option, null)) return;
        if(this.values.remove(option.getKey(), compare)) {
            NetworkOptions.sendOption(
                    this.container.module,
                    option,
                    ModuleOption.newBuilder().setAdd(AddOption.newBuilder().setValue(this.wrapElement(null)).build()).build(),
                    Collections.singleton(this.player)
            );
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void replace(final Option<?, ?, ?> option, final BiFunction<Option<?, ?, ?>, T, T> remappingFunction) {
        requireNonNull(option, "option");
        requireNonNull(remappingFunction, "remappingFunction");
        final Object value = this.values.compute(option.getKey(), (key, current) -> remappingFunction.apply(option, (T) current));
        NetworkOptions.sendOption(
                this.container.module,
                option,
                ModuleOption.newBuilder().setAdd(AddOption.newBuilder().setValue(this.wrapElement(value)).build()).build(),
                Collections.singleton(this.player)
        );
    }

    @Override
    public Iterator<Option<?, ?, ?>> iterator() {
        // TODO: join the values here with the ones from the container for this iterator
        return this.container.iterator();
    }

}

package com.moonsworth.apollo.option;

import com.moonsworth.apollo.Apollo;
import com.moonsworth.apollo.module.ApolloModule;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.protocol.AddOption;
import com.moonsworth.apollo.protocol.ModuleOption;
import com.moonsworth.apollo.protocol.SetOption;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
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
public final class OptionsContainer extends AbstractOptions implements Options.Container {

    private final Map<ApolloPlayer, Single> views = new HashMap<>();
    private final Map<String, Option<?, ?, ?>> options = new HashMap<>();
    private final Map<String, Object> values = new HashMap<>();

    final ApolloModule module;

    public OptionsContainer(final ApolloModule module, final Collection<Option<?, ?, ?>> options) {
        this.module = module;

        for(final Option<?, ?, ?> option : options) {
            this.options.put(option.getKey(), option);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, C extends Option<T, ?, ?>> @Nullable T get(final C option) {
        requireNonNull(option, "option");
        final Object value;
        return (value = this.values.get(option.getKey())) == null
                ? option.getDefaultValue()
                : (T) value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, C extends Option<T, ?, ?>> Optional<T> getDirect(final C option) {
        return Optional.ofNullable((T) this.values.get(option.getKey()));
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
                    this.module,
                    option,
                    ModuleOption.newBuilder().setSet(SetOption.newBuilder().setValue(this.wrapElement(value)).build()).build(),
                    Apollo.getPlayerManager().getPlayers()
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
                    this.module,
                    option,
                    ModuleOption.newBuilder().setAdd(AddOption.newBuilder().setValue(this.wrapElement(value)).build()).build(),
                    Apollo.getPlayerManager().getPlayers()
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
                    this.module,
                    option,
                    ModuleOption.newBuilder().setAdd(AddOption.newBuilder().setValue(this.wrapElement(null)).build()).build(),
                    Apollo.getPlayerManager().getPlayers()
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
                this.module,
                option,
                ModuleOption.newBuilder().setAdd(AddOption.newBuilder().setValue(this.wrapElement(value)).build()).build(),
                Apollo.getPlayerManager().getPlayers()
        );
    }

    @Override
    public Single get(ApolloPlayer player) {
        requireNonNull(player, "player");
        return this.views.computeIfAbsent(player, key -> new OptionsView(this, key));
    }

    @Override
    public Iterator<Option<?, ?, ?>> iterator() {
        return this.options.values().iterator();
    }

}
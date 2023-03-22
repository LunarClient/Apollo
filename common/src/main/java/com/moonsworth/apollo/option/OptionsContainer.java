package com.moonsworth.apollo.option;

import com.google.protobuf.Any;
import com.google.protobuf.Message;
import com.google.protobuf.NullValue;
import com.moonsworth.apollo.Apollo;
import com.moonsworth.apollo.event.EventBus;
import com.moonsworth.apollo.event.option.ApolloUpdateOptionEvent;
import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.protocol.ListWrapper;
import com.moonsworth.apollo.protocol.ModuleOption;
import com.moonsworth.apollo.protocol.ModuleOptions;
import com.moonsworth.apollo.protocol.Modules;
import com.moonsworth.apollo.protocol.SetOption;
import com.moonsworth.apollo.protocol.StructWrapper;
import com.moonsworth.apollo.protocol.ValueWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
public final class OptionsContainer implements Options.Container {

    private final Map<ApolloPlayer, Single> views = new HashMap<>();
    private final Map<String, Option<?, ?, ?>> options = new HashMap<>();
    private final Map<String, Object> values = new HashMap<>();

    private final String name;

    public OptionsContainer(final String name, final Collection<Option<?, ?, ?>> options) {
        this.name = name;

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
        if(!this.postUpdate(option, value)) return;
        if(value == null) {
            this.values.remove(option.getKey());
        } else if(Objects.equals(value, option.getDefaultValue())) {
            this.values.remove(option.getKey());
        } else {
            this.values.put(option.getKey(), requireNonNull(value, "value"));
        }

        if(option.isNotify()) {
            this.sendOption(
                    option.getKey(),
                    ModuleOption.newBuilder()
                            .setSet(SetOption.newBuilder().setValue(wrapElement(value)).build())
                            .build()
            );
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
    @SuppressWarnings("unchecked")
    public <T> void replace(final Option<?, ?, ?> option, final BiFunction<Option<?, ?, ?>, T, T> remappingFunction) {
        requireNonNull(option, "option");
        requireNonNull(remappingFunction, "remappingFunction");
        this.values.compute(option.getKey(), (key, current) -> remappingFunction.apply(option, (T) current));
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

    private boolean postUpdate(final Option<?, ?, ?> option, final @Nullable Object value) {
        final EventBus.EventResult<ApolloUpdateOptionEvent> eventResult = EventBus.getBus().post(new ApolloUpdateOptionEvent(this, option, value));
        boolean cancelled = eventResult.getEvent().isCancelled();
        for(final Throwable throwable : eventResult.getThrowing()) {
            throwable.printStackTrace();
        }
        return cancelled;
    }

    private void sendOption(final String optionKey, final ModuleOption option) {
        final ModuleOptions moduleOptions = ModuleOptions.newBuilder()
                .setEnabled(true)
                .putOptions(optionKey, option)
                .build();

        final Modules modules = Modules.newBuilder()
                .putModules(this.name, moduleOptions)
                .build();

        for(final ApolloPlayer player : Apollo.getPlayerManager().getPlayers()) {
            ((AbstractApolloPlayer) player).sendPacket(modules);
        }
    }

    @SuppressWarnings("unchecked")
    ValueWrapper wrapElement(final @Nullable Object current) {
        final ValueWrapper.Builder wrapBuilder = ValueWrapper.newBuilder();
        if(current instanceof Collection) {
            final ListWrapper.Builder wrapper = ListWrapper.newBuilder();
            for(final Object element : (Collection<Object>) current) {
                wrapper.addValues(wrapElement(element));
            }
            return wrapBuilder.setListValue(wrapper.build()).build();
        } else if(current instanceof Map) {
            final StructWrapper.Builder wrapper = StructWrapper.newBuilder();
            for(final Map.Entry<Object, Object> entry : ((Map<Object, Object>) current).entrySet()) {
                wrapper.putFields(entry.getKey().toString(), wrapElement(entry.getValue()));
            }
            return wrapBuilder.setStructValue(wrapper.build()).build();
        } else if(current != null) {
            final OptionConverter<Object, Message> converter = OptionConverters.get(current.getClass());
            if(converter != null) {
                final Message message = converter.to(current);
                return wrapBuilder.setAnyValue(Any.pack(message)).build();
            } else {
                return wrapValue(wrapBuilder, current);
            }
        }

        return wrapBuilder.setNullValue(NullValue.NULL_VALUE).build();
    }

    @Nullable Object unwrapElement(final @Nullable ValueWrapper valueWrapper) {
        if(valueWrapper != null) {
            if(valueWrapper.hasListValue()) {
                final List<Object> collection = new ArrayList<>();
                for(final ValueWrapper element : valueWrapper.getListValue().getValuesList()) {
                    collection.add(unwrapElement(element));
                }
                return collection;
            } else if(valueWrapper.hasStructValue()) {
                final Map<String, Object> map = new HashMap<>();
                for(final Map.Entry<String, ValueWrapper> entry : valueWrapper.getStructValue().getFieldsMap().entrySet()) {
                    map.put(entry.getKey(), unwrapElement(entry.getValue()));
                }
                return map;
            } else if(valueWrapper.hasAnyValue()) {
                final Any any = valueWrapper.getAnyValue();
                final OptionConverter<Object, Message> converter = OptionConverters.get(any.getTypeUrl());
                if(converter != null) {
                    return converter.from(any);
                }
            } else {
                return unwrapValue(valueWrapper);
            }
        }

        return null;
    }

    ValueWrapper wrapValue(final ValueWrapper.Builder valueBuilder, final Object current) {
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

    @Nullable Object unwrapValue(final ValueWrapper wrapper) {
        if(wrapper.hasNumberValue()) {
            return wrapper.getNumberValue();
        } else if(wrapper.hasStringValue()) {
            return wrapper.getStringValue();
        } else if(wrapper.hasBoolValue()) {
            return wrapper.getBoolValue();
        }

        return null;
    }

}

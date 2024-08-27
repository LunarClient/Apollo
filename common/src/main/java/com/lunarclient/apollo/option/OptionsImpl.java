/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2023 Moonsworth
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.lunarclient.apollo.option;

import com.google.protobuf.ListValue;
import com.google.protobuf.NullValue;
import com.google.protobuf.Value;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.option.ApolloUpdateOptionEvent;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.network.NetworkOptions;
import com.lunarclient.apollo.player.ApolloPlayer;
import io.leangen.geantyref.GenericTypeReflector;
import java.awt.Color;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
public class OptionsImpl implements Options {

    private final Map<Option<?, ?, ?>, Object> options = Collections.synchronizedMap(new HashMap<>());
    private final Map<ApolloPlayer, Map<Option<?, ?, ?>, Object>> playerOptions = Collections.synchronizedMap(new WeakHashMap<>());

    private final ApolloModule module;

    /**
     * Constructs a new {@link OptionsImpl}.
     *
     * @param module the apollo module
     * @since 1.0.0
     */
    public OptionsImpl(@Nullable ApolloModule module) {
        this.module = module;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, C extends Option<T, ?, ?>> T get(@NonNull C option) {
        Object value = this.options.get(option);
        return value == null ? option.getDefaultValue() : (T) value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, C extends Option<T, ?, ?>> @Nullable T get(@NonNull ApolloPlayer player, @NonNull C option) {
        Object value = this.playerOptions.getOrDefault(player, Collections.emptyMap()).get(option);
        return value == null ? this.get(option) : (T) value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, C extends Option<T, ?, ?>> Optional<T> getDirect(@NonNull C option) {
        Object value = this.options.get(option);
        return value == null ? Optional.empty() : Optional.of((T) value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, C extends Option<T, ?, ?>> Optional<T> getDirect(@NonNull ApolloPlayer player, @NonNull C option) {
        Object value = this.playerOptions.getOrDefault(player, Collections.emptyMap()).get(option);
        return value == null ? this.getDirect(option) : Optional.of((T) value);
    }

    @Override
    public <T> void set(@NonNull Option<?, ?, ?> option, @Nullable T value) {
        Object nextValue = value == null ? option.getDefaultValue() : value;
        if (this.postEvent(option, null, nextValue)) {
            return;
        }

        Object currentValue;
        if (Objects.equals(nextValue, option.getDefaultValue())) {
            currentValue = this.options.remove(option);
        } else {
            currentValue = this.options.put(option, value);
        }

        if (!Objects.equals(currentValue, value)) {
            this.postPacket(option, null, nextValue);
        }
    }

    @Override
    public <T> void set(@NonNull ApolloPlayer player, @NonNull Option<?, ?, ?> option, @Nullable T value) {
        Object globalValue = this.get(option);
        Object nextValue = value == null ? globalValue : value;
        if (this.postEvent(option, player, nextValue)) {
            return;
        }

        Object currentValue;
        if (Objects.equals(value, globalValue)) {
            currentValue = this.playerOptions.computeIfAbsent(player, k -> Collections.synchronizedMap(new WeakHashMap<>()))
                .remove(option);
        } else {
            currentValue = this.playerOptions.computeIfAbsent(player, k -> Collections.synchronizedMap(new WeakHashMap<>()))
                .put(option, value);
        }

        if (!Objects.equals(currentValue, value)) {
            this.postPacket(option, player, nextValue);
        }
    }

    @Override
    public <T> void add(@NonNull Option<?, ?, ?> option, @NonNull T value) {
        if (this.postEvent(option, null, value)) {
            return;
        }

        Object currentValue = this.options.put(option, value);

        if (!Objects.equals(currentValue, value)) {
            this.postPacket(option, null, value);
        }
    }

    @Override
    public <T> void add(@NonNull ApolloPlayer player, @NonNull Option<?, ?, ?> option, @NonNull T value) {
        if (this.postEvent(option, player, value)) {
            return;
        }

        Object currentValue = this.playerOptions.computeIfAbsent(player, k -> Collections.synchronizedMap(new WeakHashMap<>()))
            .put(option, value);

        if (!Objects.equals(currentValue, value)) {
            this.postPacket(option, player, value);
        }
    }

    @Override
    public <T> void remove(@NonNull Option<?, ?, ?> option, @Nullable T compare) {
        if (this.postEvent(option, null, option.getDefaultValue())) {
            return;
        }

        if (this.options.remove(option, compare)) {
            this.postPacket(option, null, option.getDefaultValue());
        }
    }

    @Override
    public <T> void remove(@NonNull ApolloPlayer player, @NonNull Option<?, ?, ?> option, @Nullable T compare) {
        if (this.postEvent(option, player, this.get(option))) {
            return;
        }

        if (this.playerOptions.computeIfAbsent(player, k -> Collections.synchronizedMap(new WeakHashMap<>())).remove(option, compare)) {
            this.postPacket(option, player, option.getDefaultValue());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void replace(@NonNull Option<?, ?, ?> option, @NonNull BiFunction<Option<?, ?, ?>, T, T> remappingFunction) {
        this.options.replaceAll((k, v) -> {
            T value = remappingFunction.apply(option, (T) v);
            if (value == null) {
                value = (T) option.getDefaultValue();
            }

            if (this.postEvent(option, null, value)) {
                return null;
            }

            if (!Objects.equals(v, value)) {
                this.postPacket(option, null, value);
            }

            return value;
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void replace(@NonNull ApolloPlayer player, @NonNull Option<?, ?, ?> option, @NonNull BiFunction<Option<?, ?, ?>, T, T> remappingFunction) {
        this.playerOptions.computeIfAbsent(player, k -> Collections.synchronizedMap(new WeakHashMap<>()))
            .replaceAll((k, v) -> {
                T value = remappingFunction.apply(option, (T) v);
                if (value == null) {
                    value = (T) option.getDefaultValue();
                }

                if (this.postEvent(option, player, value)) {
                    return null;
                }

                if (!Objects.equals(v, value)) {
                    this.postPacket(option, player, value);
                }

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
     * @param type         the value type
     * @param current      the current value
     * @return the wrapped value
     * @since 1.0.0
     */
    public Value wrapValue(Value.Builder valueBuilder, Type type, @Nullable Object current) {
        if (current == null) {
            return valueBuilder.setNullValue(NullValue.NULL_VALUE).build();
        }

        Type boxed = GenericTypeReflector.box(type);
        Class<?> clazz = GenericTypeReflector.erase(boxed);

        if (clazz.isEnum()) {
            return valueBuilder.setStringValue(((Enum<?>) current).name()).build();
        } else if (Number.class.isAssignableFrom(clazz)) {
            return valueBuilder.setNumberValue(((Number) current).doubleValue()).build();
        } else if (String.class.isAssignableFrom(clazz)) {
            return valueBuilder.setStringValue((String) current).build();
        } else if (Boolean.class.isAssignableFrom(clazz)) {
            return valueBuilder.setBoolValue((Boolean) current).build();
        } else if (List.class.isAssignableFrom(clazz)) {
            AnnotatedType elementType = this.elementType(boxed);
            ListValue.Builder listBuilder = ListValue.newBuilder();
            for (Object object : (List<?>) current) {
                listBuilder.addValues(this.wrapValue(Value.newBuilder(), elementType.getType(), object));
            }

            return valueBuilder.setListValue(listBuilder.build()).build();
        } else if (Color.class.isAssignableFrom(clazz)) {
            if (current instanceof String) {
                String string = (String) current;
                return valueBuilder.setStringValue(string).build();
            } else if (current instanceof Color) {
                Color currentColor = (Color) current;
                return valueBuilder.setStringValue(Integer.toHexString(currentColor.getRGB())).build();
            } else {
                throw new RuntimeException("Unable to wrap Color value of type '" + clazz.getSimpleName() + "'!");

            }
        }

        throw new RuntimeException("Unable to wrap value of type '" + clazz.getSimpleName() + "'!");
    }

    /**
     * Unwraps the provided protobuf {@link Value} into the appropriate object.
     *
     * @param wrapper the wrapped value
     * @param type    the wrapped type
     * @return the unwrapped value
     * @since 1.0.0
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public @Nullable Object unwrapValue(Value wrapper, Type type) {
        if (wrapper.hasNullValue()) {
            return null;
        }

        Type boxed = GenericTypeReflector.box(type);
        Class<?> clazz = GenericTypeReflector.erase(boxed);

        if (clazz.isEnum() && wrapper.hasStringValue()) {
            return Enum.valueOf((Class<? extends Enum>) clazz, wrapper.getStringValue());
        } else if (Number.class.isAssignableFrom(clazz) && wrapper.hasNumberValue()) {
            return wrapper.getNumberValue();
        } else if (String.class.isAssignableFrom(clazz) && wrapper.hasStringValue()) {
            return wrapper.getStringValue();
        } else if (Boolean.class.isAssignableFrom(clazz) && wrapper.hasBoolValue()) {
            return wrapper.getBoolValue();
        } else if (List.class.isAssignableFrom(clazz) && wrapper.hasListValue()) {
            AnnotatedType elementType = this.elementType(boxed);
            ListValue listValue = wrapper.getListValue();
            List<Object> list = new ArrayList<>(listValue.getValuesCount());
            for (int i = 0; i < listValue.getValuesCount(); i++) {
                list.add(this.unwrapValue(listValue.getValues(i), elementType.getType()));
            }

            return Collections.unmodifiableList(list);
        } else if (Color.class.isAssignableFrom(clazz) && wrapper.hasStringValue()) {
            return wrapper.getStringValue();
        }

        throw new RuntimeException("Unable to unwrap value of type '" + clazz.getSimpleName() + "'!");
    }

    protected boolean postEvent(Option<?, ?, ?> option, @Nullable ApolloPlayer player, @Nullable Object value) {
        EventBus.EventResult<ApolloUpdateOptionEvent> eventResult = EventBus.getBus()
            .post(new ApolloUpdateOptionEvent(this, player, option, value));

        for (Throwable throwable : eventResult.getThrowing()) {
            throwable.printStackTrace();
        }

        return eventResult.getEvent().isCancelled();
    }

    protected void postPacket(Option<?, ?, ?> option, @Nullable ApolloPlayer player, @Nullable Object value) {
        if (!option.isNotify()) {
            return;
        }

        Collection<ApolloPlayer> players = player == null ? Apollo.getPlayerManager()
            .getPlayers() : Collections.singleton(player);

        Value valueWrapper = this.wrapValue(Value.newBuilder(), option.getTypeToken().getType(), value);
        NetworkOptions.sendOption(this.module, option, valueWrapper, players);
    }

    private AnnotatedType elementType(Type type) {
        AnnotatedType elementType = GenericTypeReflector.annotate(type);
        if(!(elementType instanceof AnnotatedParameterizedType)) {
            throw new RuntimeException("Raw types for lists are not supported!");
        }

        return ((AnnotatedParameterizedType) elementType).getAnnotatedActualTypeArguments()[0];
    }

}

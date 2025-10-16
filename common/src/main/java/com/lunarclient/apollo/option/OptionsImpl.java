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

import com.google.protobuf.Value;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.option.ApolloUpdateOptionEvent;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.network.NetworkOptions;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.function.BiFunction;
import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a container of {@link Option}s.
 *
 * @since 1.0.0
 */
public class OptionsImpl implements Options {

    @Getter
    private final Map<Option<?, ?, ?>, Object> options = Collections.synchronizedMap(new HashMap<>());

    @Getter
    protected final Map<UUID, Map<Option<?, ?, ?>, Object>> playerOptions = Collections.synchronizedMap(new WeakHashMap<>());

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
        return this.get(player.getUniqueId(), option);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, C extends Option<T, ?, ?>> @Nullable T get(@NonNull UUID playerUuid, @NonNull C option) {
        Object value = this.playerOptions.getOrDefault(playerUuid, Collections.emptyMap()).get(option);
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
        return this.getDirect(player.getUniqueId(), option);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, C extends Option<T, ?, ?>> Optional<T> getDirect(@NonNull UUID playerUuid, @NonNull C option) {
        Object value = this.playerOptions.getOrDefault(playerUuid, Collections.emptyMap()).get(option);
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
            currentValue = this.playerOptions.computeIfAbsent(player.getUniqueId(), k -> Collections.synchronizedMap(new WeakHashMap<>()))
                .remove(option);
        } else {
            currentValue = this.playerOptions.computeIfAbsent(player.getUniqueId(), k -> Collections.synchronizedMap(new WeakHashMap<>()))
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

        Object currentValue = this.playerOptions.computeIfAbsent(player.getUniqueId(), k -> Collections.synchronizedMap(new WeakHashMap<>()))
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

        if (this.playerOptions.computeIfAbsent(player.getUniqueId(), k -> Collections.synchronizedMap(new WeakHashMap<>())).remove(option, compare)) {
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
        this.playerOptions.computeIfAbsent(player.getUniqueId(), k -> Collections.synchronizedMap(new WeakHashMap<>()))
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

        Value valueWrapper = NetworkOptions.wrapValue(Value.newBuilder(), option.getTypeToken().getType(), value);
        NetworkOptions.sendOption(this.module, option, valueWrapper, players);
    }

}

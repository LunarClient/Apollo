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

import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents the {@link Options} implementation for the mod status API.
 *
 * @since 1.2.1
 */
public class StatusOptionsImpl extends OptionsImpl {

    /**
     * Constructs a new {@link StatusOptionsImpl}.
     *
     * @param options the mod options with default values
     * @since 1.2.1
     */
    public StatusOptionsImpl(Collection<Option<?, ?, ?>> options) {
        super(null, options);
    }

    @Override
    public <T> void set(@NonNull ApolloPlayer player, @NonNull Option<?, ?, ?> option, @Nullable T value) {
        option = this.registry.get(option.getKey());
        Object globalValue = this.get(option);
        Object nextValue = value == null ? globalValue : value;

        Object currentValue;
        if (Objects.equals(value, globalValue)) {
            currentValue = this.playerOptions.computeIfAbsent(player.getUniqueId(), k -> Collections.synchronizedMap(new HashMap<>()))
                .remove(option.getKey());
        } else {
            currentValue = this.playerOptions.computeIfAbsent(player.getUniqueId(), k -> Collections.synchronizedMap(new HashMap<>()))
                .put(option.getKey(), value);
        }

        if (!Objects.equals(currentValue, value)) {
            this.postEvent(option, player, nextValue);
        }
    }

    @Override
    protected boolean postEvent(Option<?, ?, ?> option, @Nullable ApolloPlayer player, @Nullable Object value) {
        return false;
    }

    @Override
    protected void postPacket(Option<?, ?, ?> option, @Nullable ApolloPlayer player, @Nullable Object value) {

    }

}

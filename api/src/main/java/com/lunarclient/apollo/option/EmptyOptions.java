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
import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiFunction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
final class EmptyOptions implements Options {
    static final EmptyOptions EMPTY = new EmptyOptions();

    @Override
    public <T, C extends Option<T, ?, ?>> @Nullable T get(C option) {
        return option.getDefaultValue();
    }

    @Override
    public <T, C extends Option<T, ?, ?>> @Nullable T get(ApolloPlayer player, C option) {
        return option.getDefaultValue();
    }

    @Override
    public <T, C extends Option<T, ?, ?>> @Nullable T get(UUID playerUuid, C option) {
        return option.getDefaultValue();
    }

    @Override
    public <T, C extends Option<T, ?, ?>> Optional<T> getDirect(C option) {
        return Optional.empty();
    }

    @Override
    public <T, C extends Option<T, ?, ?>> Optional<T> getDirect(ApolloPlayer player, C option) {
        return Optional.empty();
    }

    @Override
    public <T, C extends Option<T, ?, ?>> Optional<T> getDirect(UUID playerUuid, C option) {
        return Optional.empty();
    }

    @Override
    public <T> void set(Option<?, ?, ?> option, @Nullable T value) {

    }

    @Override
    public <T> void set(ApolloPlayer player, Option<?, ?, ?> option, @Nullable T value) {

    }

    @Override
    public <T> void add(Option<?, ?, ?> option, @Nullable T value) {

    }

    @Override
    public <T> void add(ApolloPlayer player, Option<?, ?, ?> option, T value) {

    }

    @Override
    public <T> void remove(Option<?, ?, ?> option, @Nullable T compare) {

    }

    @Override
    public <T> void remove(ApolloPlayer player, Option<?, ?, ?> option, @Nullable T compare) {

    }

    @Override
    public <T> void replace(Option<?, ?, ?> option, BiFunction<Option<?, ?, ?>, T, T> remappingFunction) {

    }

    @Override
    public <T> void replace(ApolloPlayer player, Option<?, ?, ?> option, BiFunction<Option<?, ?, ?>, T, T> remappingFunction) {

    }

    @Override
    public @NonNull Iterator<Option<?, ?, ?>> iterator() {
        return Collections.emptyIterator();
    }

}

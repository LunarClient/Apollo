package com.lunarclient.apollo.option;

import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;
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
        return null;
    }

    @Override
    public <T, C extends Option<T, ?, ?>> @Nullable T get(ApolloPlayer player, C option) {
        return null;
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

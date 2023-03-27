package com.moonsworth.apollo.option;

import com.moonsworth.apollo.player.ApolloPlayer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.BiFunction;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
final class EmptyOptions implements Options.Container {
    static final EmptyOptions EMPTY = new EmptyOptions();

    @Override
    public <T, C extends Option<T, ?, ?>> @Nullable T get(final C option) {
        return null;
    }

    @Override
    public <T, C extends Option<T, ?, ?>> Optional<T> getDirect(final C option) {
        return Optional.empty();
    }

    @Override
    public <T> void set(final Option<?, ?, ?> option, final @Nullable T value) {

    }

    @Override
    public <T> void add(final Option<?, ?, ?> option, final @Nullable T value) {

    }

    @Override
    public <T> void remove(final Option<?, ?, ?> option, final @Nullable T compare) {

    }

    @Override
    public <T> void replace(final Option<?, ?, ?> option, final BiFunction<Option<?, ?, ?>, T, T> remappingFunction) {

    }

    @Override
    public Single get(final ApolloPlayer player) {
        return new EmptySingle(player);
    }

    @Override
    public Iterator<Option<?, ?, ?>> iterator() {
        return Collections.emptyIterator();
    }

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    static final class EmptySingle implements Options.Single {
        private final ApolloPlayer player;

        @Override
        public <T, C extends Option<T, ?, ?>> @Nullable T get(final C option) {
            return null;
        }

        @Override
        public <T, C extends Option<T, ?, ?>> Optional<T> getDirect(final C option) {
            return Optional.empty();
        }

        @Override
        public <T> void set(final Option<?, ?, ?> option, final @Nullable T value) {

        }

        @Override
        public <T> void add(final Option<?, ?, ?> option, final @Nullable T value) {

        }

        @Override
        public <T> void remove(final Option<?, ?, ?> option, final @Nullable T compare) {

        }

        @Override
        public <T> void replace(final Option<?, ?, ?> option, final BiFunction<Option<?, ?, ?>, T, T> remappingFunction) {

        }

        @Override
        public Iterator<Option<?, ?, ?>> iterator() {
            return Collections.emptyIterator();
        }

    }

}

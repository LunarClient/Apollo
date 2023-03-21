package com.moonsworth.apollo.option;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

public final class ListOption<T> extends Option<List<T>, ListOption.ListOptionBuilder<T>, ListOption<T>> {

    private ListOption(final ListOptionBuilder<T> builder) {
        super(builder);
    }

    @NoArgsConstructor(access = AccessLevel.PACKAGE)
    public static class ListOptionBuilder<T> extends OptionBuilder<List<T>, ListOptionBuilder<T>, ListOption<T>> {

        @Override
        public ListOption<T> build() {
            return new ListOption<>(this);
        }
    }

}

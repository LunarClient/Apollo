package com.lunarclient.apollo.option;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Represents a list {@link Option}.
 *
 * @param <T> the value type
 * @since 1.0.0
 */
public final class ListOption<T> extends Option<List<T>, ListOption.ListOptionBuilder<T>, ListOption<T>> {

    private ListOption(ListOptionBuilder<T> builder) {
        super(builder);
    }

    /**
     * Represents a list {@link OptionBuilder}.
     *
     * @param <T> the value type
     * @since 1.0.0
     */
    @NoArgsConstructor(access = AccessLevel.PACKAGE)
    public static class ListOptionBuilder<T> extends OptionBuilder<List<T>, ListOptionBuilder<T>, ListOption<T>> {

        @Override
        public ListOption<T> build() {
            return new ListOption<>(this);
        }

    }

}

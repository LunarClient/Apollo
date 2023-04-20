package com.lunarclient.apollo.option;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Represents a simple {@link Option}.
 *
 * @param <T> the value type
 * @since 1.0.0
 */
public final class SimpleOption<T> extends Option<T, SimpleOption.SimpleOptionBuilder<T>, SimpleOption<T>> {

    private SimpleOption(SimpleOptionBuilder<T> builder) {
        super(builder);
    }

    /**
     * Represents a simple {@link OptionBuilder}.
     *
     * @param <T> the value type
     * @since 1.0.0
     */
    @NoArgsConstructor(access = AccessLevel.PACKAGE)
    public static class SimpleOptionBuilder<T> extends OptionBuilder<T, SimpleOptionBuilder<T>, SimpleOption<T>> {

        @Override
        public SimpleOption<T> build() {
            return new SimpleOption<>(this);
        }

    }

}

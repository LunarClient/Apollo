package com.moonsworth.apollo.option;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents a number {@link Option}.
 *
 * @param <T> the number type
 * @since 1.0.0
 */
@Getter
public final class NumberOption<T extends Number & Comparable<T>> extends Option<T, NumberOption.NumberOptionBuilder<T>, NumberOption<T>> {

    private final T min, max;

    private NumberOption(NumberOptionBuilder<T> builder) {
        super(builder);

        this.min = builder.min;
        this.max = builder.max;
    }

    /**
     * Represents a number {@link OptionBuilder}.
     *
     * @param <T> the number type
     * @since 1.0.0
     */
    @NoArgsConstructor(access = AccessLevel.PACKAGE)
    public static class NumberOptionBuilder<T extends Number & Comparable<T>> extends OptionBuilder<T, NumberOptionBuilder<T>, NumberOption<T>> {

        private T min, max;

        public NumberOptionBuilder<T> min(T value) {
            this.min = value;
            return this;
        }

        public NumberOptionBuilder<T> max(T value) {
            this.max = value;
            return this;
        }

        @Override
        public NumberOption<T> build() {
            return new NumberOption<>(this);
        }

    }

}

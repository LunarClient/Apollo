package com.moonsworth.apollo.option;

import lombok.Getter;

@Getter
public final class NumberOption<T extends Number & Comparable<T>> extends Option<T, NumberOption.NumberOptionBuilder<T>, NumberOption<T>> {

    private final T min, max;

    private NumberOption(final NumberOptionBuilder<T> builder) {
        super(builder);

        this.min = builder.min;
        this.max = builder.max;
    }

    public static class NumberOptionBuilder<T extends Number & Comparable<T>> extends OptionBuilder<T, NumberOptionBuilder<T>, NumberOption<T>> {

        private T min, max;

        NumberOptionBuilder() {}

        public NumberOptionBuilder<T> min(final T value) {
            this.min = value;
            return this;
        }

        public NumberOptionBuilder<T> max(final T value) {
            this.max = value;
            return this;
        }

        public NumberOption<T> build() {
            return new NumberOption<>(this);
        }

    }

}

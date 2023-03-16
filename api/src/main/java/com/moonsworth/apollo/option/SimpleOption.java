package com.moonsworth.apollo.option;

public final class SimpleOption<T> extends Option<T, SimpleOption.SimpleOptionBuilder<T>, SimpleOption<T>> {

    private SimpleOption(final SimpleOptionBuilder<T> builder) {
        super(builder);
    }

    public static class SimpleOptionBuilder<T> extends OptionBuilder<T, SimpleOptionBuilder<T>, SimpleOption<T>> {

        @Override
        public SimpleOption<T> build() {
            return new SimpleOption<>(this);
        }

    }

}

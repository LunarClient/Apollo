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

    /**
     * Returns the minimum value.
     *
     * @return the minimum value
     * @since 1.0.0
     */
    private final T min;

    /**
     * Returns the maximum value.
     *
     * @return the maximum value
     * @since 1.0.0
     */
    private final T max;

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

        private T min;
        private T max;

        /**
         * Sets the minimum value and returns this builder.
         *
         * @param value the minimum value
         * @return this builder
         * @since 1.0.0
         */
        public NumberOptionBuilder<T> min(T value) {
            this.min = value;
            return this;
        }

        /**
         * Sets the maximum value and returns this builder.
         *
         * @param value the maximum value
         * @return this builder
         * @since 1.0.0
         */
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

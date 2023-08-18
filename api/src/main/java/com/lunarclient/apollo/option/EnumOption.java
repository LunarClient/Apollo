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
import lombok.NoArgsConstructor;

/**
 * Represents an enum {@link Option}.
 *
 * @param <T> the enum type
 * @since 1.0.0
 */
public final class EnumOption<T extends Enum<T>> extends Option<T, EnumOption.EnumOptionBuilder<T>, EnumOption<T>> {

    private EnumOption(EnumOptionBuilder<T> builder) {
        super(builder);
    }

    /**
     * Represents an enum {@link OptionBuilder}.
     *
     * @param <T> the enum type
     * @since 1.0.0
     */
    @NoArgsConstructor(access = AccessLevel.PACKAGE)
    public static class EnumOptionBuilder<T extends Enum<T>> extends OptionBuilder<T, EnumOptionBuilder<T>, EnumOption<T>> {

        @Override
        public EnumOption<T> build() {
            return new EnumOption<>(this);
        }

    }

}

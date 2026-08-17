/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2026 Moonsworth
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
package com.lunarclient.apollo.common.button;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents the size of an {@link ApolloButton}, in GUI-scaled pixels.
 *
 * <p>Surface types ship suggested sizes tuned to their container
 * dimensions (e.g. {@code InventoryButton.SIZE_MEDIUM}); use
 * {@link #of(float, float)} for a fully custom size.</p>
 *
 * @since 1.2.9
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApolloButtonSize {

    /**
     * Creates a new {@link ApolloButtonSize} with the given dimensions.
     *
     * @param width  the button width, must be finite and greater than 0
     * @param height the button height, must be finite and greater than 0
     * @return the button size
     * @since 1.2.9
     */
    public static ApolloButtonSize of(float width, float height) {
        if (!(width > 0.0F) || !(height > 0.0F) || !Float.isFinite(width) || !Float.isFinite(height)) {
            throw new IllegalArgumentException("ApolloButtonSize dimensions must be finite and greater than 0");
        }

        return new ApolloButtonSize(width, height);
    }

    /**
     * Creates a new square {@link ApolloButtonSize}.
     *
     * @param size the button width and height, must be finite and greater than 0
     * @return the button size
     * @since 1.2.9
     */
    public static ApolloButtonSize of(float size) {
        return of(size, size);
    }

    /**
     * Returns the {@code float} button width.
     *
     * @return the button width
     * @since 1.2.9
     */
    private final float width;

    /**
     * Returns the {@code float} button height.
     *
     * @return the button height
     * @since 1.2.9
     */
    private final float height;

}

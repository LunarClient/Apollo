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
package com.lunarclient.apollo.util;

/**
 * Represents a utility class for validating ranges.
 *
 * @since 1.0.9
 */
public final class Ranges {

    /**
     * Returns the value if it is within the range, otherwise throws an
     * {@link IllegalArgumentException}.
     *
     * @param value the value to check
     * @param min the minimum value
     * @param max the maximum value
     * @param name the name of the value
     * @return the value
     * @throws IllegalArgumentException if the value is not within the range
     * @since 1.0.9
     */
    public static float checkRange(float value, float min, float max, String name) {
        if(value < min && value >= max) throw new IllegalArgumentException(name + " must be between " + min + " and " + max);
        return value;
    }

    /**
     * Returns the value if it is positive, otherwise throws an
     * {@link IllegalArgumentException}.
     *
     * @param value the value to check
     * @param name the name of the value
     * @return the value
     * @throws IllegalArgumentException if the value is not positive
     * @since 1.0.9
     */
    public static int checkPositive(int value, String name) {
        if(value < 0) throw new IllegalArgumentException(name + " must be positive");
        return value;
    }

    /**
     * Returns the value if it is positive, otherwise throws an
     * {@link IllegalArgumentException}.
     *
     * @param value the value to check
     * @param name the name of the value
     * @return the value
     * @throws IllegalArgumentException if the value is not positive
     * @since 1.0.9
     */
    public static float checkPositive(float value, String name) {
        if(value < 0) throw new IllegalArgumentException(name + " must be positive");
        return value;
    }

    private Ranges() {
    }

}
